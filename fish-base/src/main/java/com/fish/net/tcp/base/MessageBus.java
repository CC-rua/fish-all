package com.fish.net.tcp.base;

import io.netty.util.internal.shaded.org.jctools.queues.MpscUnboundedArrayQueue;

/**
 * 消息队列
 *
 * @author: cc
 * @date: 2024/1/17
 */
public class MessageBus {
    /**
     * 消息队列
     */
    private final MpscUnboundedArrayQueue<MessageBusObj> messageQueue;

    public MessageBus() {
        messageQueue = new MpscUnboundedArrayQueue<>(4096);
    }

    public void putMessage(MessageProtocol msg, _IProtocolHandler protocolHandler) {
        MessageBusObj busObj = MessageBusObj.builder().protocol(msg).handler(protocolHandler).build();
        messageQueue.offer(busObj);
    }

    public MessageBusObj receiveProtocol() {
        return messageQueue.relaxedPoll();
    }
}
