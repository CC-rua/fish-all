package com.fish.net.tcp.base;

import com.fish.task.TickTask;
import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例的,保存 channel 对象的管理器,同时要对网络事件做出响应
 *
 * @author: cc
 * @date: 2024/1/11
 */
public abstract class ChannelManager implements _INetListener, TickTask {
    protected ConcurrentHashMap<ChannelId, ChannelObj> channelMap;

    public ChannelManager() {
        channelMap = new ConcurrentHashMap<>();
    }

    @Override
    public void onAccept(Channel channel, _IProtocolHandlerMgr protocolHandlerMgr) {
        if (channelMap.containsKey(channel.id())) {
            //已经存在这个channel
            return;
        }
        _IProtocolHandler handler = protocolHandlerMgr.ensureProtocolHandler(channel.id());
        ChannelObj channelObj = new ChannelObj(channel, handler);
        channelMap.put(channel.id(), channelObj);
    }

    @Override
    public void onInactive(Channel channel) {
        ChannelObj channelObj = channelMap.get(channel.id());
        if (channelObj == null) {
            return;
        }
        channelObj.inactive();
        channelMap.remove(channel.id());
    }

    @Override
    public void onRead(Channel channel, MessageProtocol message) {
        ChannelObj channelObj = channelMap.get(channel.id());
        if (channelObj == null) {
            return;
        }
        channelObj.putInBoundMessage(message);
    }

    @Override
    public void tick() {
        for (ChannelObj ob : channelMap.values()) {
            if (ob == null) {
                continue;
            }
            ob.tick();
        }
    }
}
