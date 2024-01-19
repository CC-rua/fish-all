package com.fish.net.tcp.base;

import io.netty.channel.Channel;
import io.netty.channel.ChannelId;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 单例的,保存 channel 对象的管理器,同时要对网络事件做出响应
 *
 * @author: cc
 * @date: 2024/1/11
 */
public class ChannelManager implements _INetListener {
    private ConcurrentHashMap<ChannelId, ChannelObj> channelMap;

    private MessageBus messageBus;

    public ChannelManager() {
        channelMap = new ConcurrentHashMap<>();
    }

    @Override
    public void onAccept(Channel channel) {
        ChannelObj channelObj = new ChannelObj(channel);
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
        channelObj.onRead(message);
    }
}
