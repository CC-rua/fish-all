package com.fish.net.tcp.base;

import io.netty.channel.ChannelId;

/**
 * @author: cc
 * @date: 2024/2/19
 */
public interface _IProtocolHandlerMgr {
    _IProtocolHandler ensureProtocolHandler(ChannelId id);
}
