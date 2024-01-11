package com.fish.net.tcp.base;

/**
 * @author: cc
 * @date: 2024/1/11
 */
public interface  _INetListener {
    /**
     * 接受到来自网络层的一个连接
     */
    void onAccept();
    /**
     *
     */
    void onInactive();
}
