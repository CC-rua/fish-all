package com.fish.net.tcp.base;

import io.netty.channel.Channel;

/**
 * 保存信道的对象
 *
 * @author: cc
 * @date: 2024/1/11
 */
public class ChannelObj {
    private final Channel channel;

    public ChannelObj(Channel channel) {
        this.channel = channel;
    }

    public void inactive() {

    }

    public void onRead(MessageProtocol message) {

    }
}
