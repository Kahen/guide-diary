
package me.zhengjie.modules.security.rest;

import cn.hutool.captcha.generator.RandomGenerator;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.wf.captcha.base.Captcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhengjie.annotation.rest.AnonymousDeleteMapping;
import me.zhengjie.annotation.rest.AnonymousGetMapping;
import me.zhengjie.annotation.rest.AnonymousPostMapping;
import me.zhengjie.config.RsaProperties;
import me.zhengjie.exception.BadRequestException;
import me.zhengjie.modules.blog.domain.ActiveCode;
import me.zhengjie.modules.blog.domain.DiaryUser;
import me.zhengjie.modules.blog.domain.HttpResult;
import me.zhengjie.modules.blog.service.ActiveCodeService;
import me.zhengjie.modules.blog.service.DiaryUserService;
import me.zhengjie.modules.blog.service.dto.DiaryUserDto;
import me.zhengjie.modules.blog.service.mapstruct.DiaryUserMapper;
import me.zhengjie.modules.security.config.bean.LoginCodeEnum;
import me.zhengjie.modules.security.config.bean.LoginProperties;
import me.zhengjie.modules.security.config.bean.SecurityProperties;
import me.zhengjie.modules.security.security.TokenProvider;
import me.zhengjie.modules.security.service.OnlineUserService;
import me.zhengjie.modules.security.service.dto.AuthUserDto;
import me.zhengjie.modules.security.service.dto.JwtUserDto;
import me.zhengjie.utils.RedisUtils;
import me.zhengjie.utils.RsaUtils;
import me.zhengjie.utils.SecurityUtils;
import me.zhengjie.utils.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Kahen
 * @date 2018-11-23
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Api(tags = "系统：系统授权接口")
public class AuthorizationController {
    private final SecurityProperties properties;
    private final RedisUtils redisUtils;
    private final OnlineUserService onlineUserService;
    private final TokenProvider tokenProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ActiveCodeService activeCodeService;
    private final DiaryUserService diaryUserService;
    private final DiaryUserMapper diaryUserMapper;

    @Resource
    private LoginProperties loginProperties;

    @ApiOperation("登录授权")
    @AnonymousPostMapping(value = "/login")
    public ResponseEntity<Object> login(@Validated @RequestBody AuthUserDto authUser, HttpServletRequest request) throws Exception {
        // 密码解密
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, authUser.getPassword());
        // 查询验证码
        String code = (String) redisUtils.get(authUser.getUuid());
        // 清除验证码
        redisUtils.del(authUser.getUuid());
        if (StringUtils.isBlank(code)) {
            throw new BadRequestException("验证码不存在或已过期");
        }
        if (StringUtils.isBlank(authUser.getCode()) || !authUser.getCode().equalsIgnoreCase(code)) {
            throw new BadRequestException("验证码错误");
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(authUser.getUsername(), password);
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // 生成令牌
        String token = tokenProvider.createToken(authentication);
        final JwtUserDto jwtUserDto = (JwtUserDto) authentication.getPrincipal();
        // 保存在线信息
        onlineUserService.save(jwtUserDto, token, request);
        // 返回 token 与 用户信息
        Map<String, Object> authInfo = new HashMap<String, Object>(2) {{
            put("token", properties.getTokenStartWith() + token);
            put("user", jwtUserDto);
        }};
        if (loginProperties.isSingleLogin()) {
            //踢掉之前已经登录的token
            onlineUserService.checkLoginOnUser(authUser.getUsername(), token);
        }
        return ResponseEntity.ok(authInfo);
    }

    @ApiOperation("获取用户信息")
    @GetMapping(value = "/info")
    public ResponseEntity<Object> getUserInfo() {
        return ResponseEntity.ok(SecurityUtils.getCurrentUser());
    }

    @ApiOperation("获取验证码")
    @AnonymousGetMapping(value = "/code")
    public ResponseEntity<Object> getCode() {
        // 获取运算的结果
        Captcha captcha = loginProperties.getCaptcha();
        String uuid = properties.getCodeKey() + IdUtil.simpleUUID();
        //当验证码类型为 arithmetic时且长度 >= 2 时，captcha.text()的结果有几率为浮点型
        String captchaValue = captcha.text();
        if (captcha.getCharType() - 1 == LoginCodeEnum.arithmetic.ordinal() && captchaValue.contains(".")) {
            captchaValue = captchaValue.split("\\.")[0];
        }
        // 保存
        redisUtils.set(uuid, captchaValue, loginProperties.getLoginCode().getExpiration(), TimeUnit.MINUTES);
        // 验证码信息
        Map<String, Object> imgResult = new HashMap<String, Object>(2) {{
            put("img", captcha.toBase64());
            put("uuid", uuid);
        }};
        return ResponseEntity.ok(imgResult);
    }

    @ApiOperation("退出登录")
    @AnonymousDeleteMapping(value = "/logout")
    public ResponseEntity<Object> logout(HttpServletRequest request) {
        onlineUserService.logout(tokenProvider.getToken(request));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("重新发送验证码")
    @AnonymousGetMapping("/reSendActiveCode")
    public ResponseEntity<Object> reSendActiveCode(@RequestParam(value = "email") String email) {
        DiaryUser diaryUser = diaryUserService.findByEmail(email);
        if (diaryUser.getEnable() == 1) {
            return new ResponseEntity<>(new HttpResult(HttpResult.Type.WARN, "该账户已激活"), HttpStatus.OK);
        }
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String activeCode = randomGenerator.generate();
        ActiveCode activeCodeByEmail = activeCodeService.create(
                new ActiveCode()
                        .setEmail(email)
                        .setActiveCode(activeCode));
        activeCodeService.sendActiveCode(activeCodeByEmail.getEmail(), activeCodeByEmail.getActiveCode());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @ApiOperation("获取邮箱验证码")
    @AnonymousPostMapping(value = "/activeCode")
    public ResponseEntity<Object> getActiveCode(@RequestBody String body) throws Exception {

        JSONObject jsonObject = JSONObject.parseObject(body);
        String email = jsonObject.getString("email");
        DiaryUser byEmail = diaryUserService.findByEmail(email);
        if (byEmail != null) {
            if (byEmail.getEnable() != 0) {
                return ResponseEntity.ok(new HttpResult(HttpResult.Type.WARN, "该帐号已存在"));
            }
            RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
            String activeCode = randomGenerator.generate();
            ActiveCode activeCodeByEmail = activeCodeService.create(
                    new ActiveCode()
                            .setEmail(email)
                            .setActiveCode(activeCode));
            activeCodeService.sendActiveCode(email, activeCodeByEmail.getActiveCode());
            return ResponseEntity.ok().body(new HttpResult(HttpResult.Type.SUCCESS, "该帐号已存在但未激活，已重新发送激活邮箱，请注意查收"));
        }
        RandomGenerator randomGenerator = new RandomGenerator("0123456789", 6);
        String activeCode = randomGenerator.generate();
        String password = RsaUtils.decryptByPrivateKey(RsaProperties.privateKey, jsonObject.getString("password"));
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashPassword = passwordEncoder.encode(password);

        ActiveCode activeCodeByEmail = activeCodeService.create(
                new ActiveCode()
                        .setEmail(email)
                        .setActiveCode(activeCode));
        activeCodeService.sendActiveCode(activeCodeByEmail.getEmail(), activeCodeByEmail.getActiveCode());
        DiaryUserDto diaryUserDto = diaryUserService.create(
                new DiaryUser().setUid(IdUtil.simpleUUID())
                        .setAvatarUrl("https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg")
                        .setDescription("")
                        .setCreateTime(new Timestamp(System.currentTimeMillis()))
                        .setName(email)
                        .setNickname(jsonObject.getString("nickname"))
                        .setFollowersCount(0L)
                        .setFriendsCount(0L)
                        .setStatusesCount(0L)
                        .setPassword(hashPassword)
                        .setEnable(0)
        );
        return new ResponseEntity<>(new HttpResult(HttpResult.Type.SUCCESS, diaryUserDto.getName(), diaryUserDto), HttpStatus.OK);
    }

    @ApiOperation("激活用户")
    @AnonymousGetMapping(value = "enable")
    public ResponseEntity<Object> enable(@RequestParam(value = "email") String email
            , @RequestParam(value = "activeCode") String activeCode) {
        ActiveCode activeCodeByEmail = activeCodeService.findActiveCodeByEmail(email);
        if (activeCodeByEmail == null) {
            return new ResponseEntity<>(new HttpResult(HttpResult.Type.WARN, "未请求发送激活码"), HttpStatus.OK);
        }
        if (activeCodeByEmail.getActiveCode().equalsIgnoreCase(activeCode)) {
            DiaryUser diaryUser = diaryUserService.findByEmail(email);
            if (diaryUser.getEnable() == 1) {
                return new ResponseEntity<>(new HttpResult(HttpResult.Type.WARN, "该账户已激活"), HttpStatus.OK);
            }
            diaryUser.setEnable(1);
            diaryUserService.update(diaryUser);
            return new ResponseEntity<>(new HttpResult(HttpResult.Type.SUCCESS, "注册成功", diaryUserMapper.toDto(diaryUser)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new HttpResult(HttpResult.Type.ERROR, "验证码错误"), HttpStatus.OK);
        }
    }
}
