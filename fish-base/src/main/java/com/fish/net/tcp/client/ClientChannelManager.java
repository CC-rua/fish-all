package com.fish.net.tcp.client;

import com.fish.net.tcp.base.ChannelManager;
import com.fish.net.tcp.base._IProtocolHandler;
import io.netty.channel.ChannelId;

/**
 * @author: cc
 * @date: 2024/1/16
 */
public class ClientChannelManager extends ChannelManager {
    public static final ClientChannelManager INSTANCE = new ClientChannelManager();
}
