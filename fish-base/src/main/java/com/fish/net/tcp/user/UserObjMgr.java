package com.fish.net.tcp.user;

import com.fish.net.tcp.base._IProtocolHandler;
import com.fish.net.tcp.base._IProtocolHandlerMgr;
import io.netty.channel.ChannelId;

/**
 * @author: cc
 * @date: 2024/2/19
 */
public class UserObjMgr implements _IProtocolHandlerMgr {
    @Override
    public _IProtocolHandler ensureProtocolHandler(ChannelId id) {
        return new UserObj();
    }
}
