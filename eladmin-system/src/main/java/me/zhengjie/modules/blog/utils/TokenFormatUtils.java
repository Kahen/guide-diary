package me.zhengjie.modules.blog.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author lijiaxing
 * @date 2021/1/12
 */
@Slf4j
public class TokenFormatUtils {
    private static final String ONLINE_KEY = "online-token-";
    private static final String BEARER = "Bearer ";


    public static String bearerTokenToOnlineToken(String auth) {
        return ONLINE_KEY + resolveToken(auth);
    }

    /**
     * 去掉令牌前缀
     *
     * @param bearerToken /
     * @return /
     */
    private static String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER)) {
            // 去掉令牌前缀
            return bearerToken.replace(BEARER, "");
        } else {
            log.debug("非法Token：{}", bearerToken);
        }
        return null;
    }
}
