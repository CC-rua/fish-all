package com.fish.net.tcp.base;

import com.fish.protocol.TestProtocol;
import io.netty.channel.Channel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * 保存信道的对象
 * 处理协议的对象
 *
 * @author: cc
 * @date: 2024/1/11
 */
@Slf4j
public class ChannelObj {
    @Getter
    private final Channel channel;
    @Getter
    private final MessageBus inboundMessageBus;
    @Getter
    private final _IProtocolHandler protocolHandler;

    public ChannelObj(Channel channel, _IProtocolHandler protocolHandler) {
        this.channel = channel;
        this.protocolHandler = protocolHandler;
        this.inboundMessageBus = new MessageBus();
        sayHello();
    }


    /**
     * 本对象失效的处理
     */
    public void inactive() {

    }

    /**
     * 添加一个待处理消息到队列中
     *
     * @param message
     */
    public void putInBoundMessage(MessageProtocol message) {
        inboundMessageBus.putMessage(message, protocolHandler);
    }

    public void tick() {
        try {
            MessageBusObj msg = inboundMessageBus.receiveProtocol();
            if (msg == null) {
                return;
            }
            _IProtocolHandler handler = msg.getHandler();
            if (handler == null) {
                return;
            }
            MessageProtocol protocol = msg.getProtocol();
            if (protocol == null) {
                return;
            }
            handler.dealProtocol(protocol);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void send(MessageProtocol msg) {
        channel.writeAndFlush(msg);
    }


    private void sayHello() {

        TestProtocol.TestReq.Builder builder = TestProtocol.TestReq.newBuilder();
        builder.setMessage("hello world");
        TestProtocol.TestReq proto = builder.build();

        MessageProtocol protocol = new MessageProtocol();
        protocol.setMessageId(1);
        protocol.setSeqNum(1);
        protocol.setResponseCode(200);
        protocol.setData(proto.toByteArray());
        send(protocol);
        log.info("channel say hello");
    }
}
