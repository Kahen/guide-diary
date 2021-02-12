package me.zhengjie.modules.blog.service.impl;

import cn.hutool.extra.mail.MailUtil;
import lombok.RequiredArgsConstructor;
import me.zhengjie.modules.blog.domain.ActiveCode;
import me.zhengjie.modules.blog.repository.ActiveCodeRepository;
import me.zhengjie.modules.blog.service.ActiveCodeService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * @author kahen
 * @date 2021/2/11
 */
@Service
@RequiredArgsConstructor
@EnableAsync
public class ActiveCodeServiceImpl implements ActiveCodeService {

    private final ActiveCodeRepository activeCodeRepository;

    @Override
    public ActiveCode findActiveCodeByEmail(String email) {
        return activeCodeRepository.findById(email).orElse(null);
    }

    @Override
    public ActiveCode create(ActiveCode activeCode) {
        return activeCodeRepository.save(activeCode);
    }

    @Override
    public void update(ActiveCode activeCode) {
        activeCodeRepository.save(activeCode);
    }

    @Async
    @Override
    public void sendActiveCode(String email, String activeCode) {
        MailUtil.send(email, "格子日志", "【格子日志】验证码：" + activeCode + "，用于格子日志账号注册与密码重置验证。泄露有风险，请勿转发。", true);
    }


}
