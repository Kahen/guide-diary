
package me.zhengjie.modules.mnt.websocket;

/**
 * @author Kahen
 * @date 2019-08-10 9:56
 */
public enum MsgType {
    /**
     * 连接
     */
    CONNECT,
    /**
     * 关闭
     */
    CLOSE,
    /**
     * 信息
     */
    INFO,
    /**
     * 错误
     */
    ERROR
}
