package com.fish.net.tcp.base;

/**
 * 单例的,保存 channel 对象的管理器,同时要对网络事件做出响应
 *
 * @author: cc
 * @date: 2024/1/11
 */
public class ChannelManager implements _INetListener {
    public static ChannelManager INSTANCE = new ChannelManager();

    @Override
    public void onAccept() {

    }

    @Override
    public void onInactive() {

    }
}
