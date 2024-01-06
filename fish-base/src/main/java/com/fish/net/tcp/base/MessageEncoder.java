package com.fish.net.tcp.base;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

public class MessageEncoder extends MessageToMessageEncoder<MessageProtocol> {

    static final int msgHeaderLen = 16;

    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, List<Object> out) throws Exception {
        ByteBuf byteBuf = Unpooled.buffer();
        byteBuf.writeInt(msgHeaderLen + msg.getDataLength());
        byteBuf.writeInt(msg.getMessageId());
        byteBuf.writeInt(msg.getSeqNum());
        byteBuf.writeInt(msg.getResponseCode());
        if (msg.getDataLength() > 0) byteBuf.writeBytes(msg.getData());

        out.add(byteBuf);
    }
}
