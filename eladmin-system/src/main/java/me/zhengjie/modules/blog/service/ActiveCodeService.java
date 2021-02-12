package me.zhengjie.modules.blog.service;

import me.zhengjie.modules.blog.domain.ActiveCode;

/**
 * @author kahen
 * @date 2021/2/11
 */

public interface ActiveCodeService {
    /**
     * 根据邮箱查询验证码
     *
     * @param email 邮箱
     * @return 验证码
     */
    ActiveCode findActiveCodeByEmail(String email);

    /**
     * 创建验证码
     *
     * @param activeCode /
     * @return /
     */
    ActiveCode create(ActiveCode activeCode);

    /**
     * 更新验证码
     *
     * @param activeCode /
     */
    void update(ActiveCode activeCode);

    void sendActiveCode(String email, String activeCode);
}
