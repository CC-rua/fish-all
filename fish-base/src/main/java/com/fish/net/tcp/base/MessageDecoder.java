package com.fish.net.tcp.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

import java.util.List;


@Sharable
public class MessageDecoder extends MessageToMessageDecoder<ByteBuf> {

    static final int msgHeaderLen = 16;
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (in.readableBytes() >= msgHeaderLen) {
            in.markReaderIndex();
            int msgLength = in.readInt();
            if (msgLength < 0) {
                ctx.close();
            }
            if (in.readableBytes() < msgLength - 4) {
                in.resetReaderIndex();
                return;
            }

            MessageProtocol myMessageProtocol = new MessageProtocol();
            myMessageProtocol.setMessageId(in.readInt());
            myMessageProtocol.setSeqNum(in.readInt());
            myMessageProtocol.setResponseCode(in.readInt());

            byte[] content = new byte[msgLength - msgHeaderLen];
            in.readBytes(content);
            myMessageProtocol.setData(content);
            out.add(myMessageProtocol);
        }
    }

}
