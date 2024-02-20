package com.fish.net.tcp.base;

import io.netty.channel.Channel;

/**
 * @author: cc
 * @date: 2024/1/11
 */
public interface _INetListener {
    /**
     * 接受到来自网络层的一个连接
     */
    void onAccept(Channel channel, _IProtocolHandlerMgr protocolHandlerMgr);

    /**
     * channel失效
     */
    void onInactive(Channel channel);

    /**
     * 有消息响应
     */
    void onRead(Channel channel, MessageProtocol message);
}
