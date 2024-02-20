package com.fish.net.tcp.server;


import com.fish.net.tcp.base.MessageDecoder;
import com.fish.net.tcp.base.MessageEncoder;
import com.fish.net.tcp.user.UserObjMgr;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class ServerChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) {
        channel.pipeline()
                // 心跳时间
                .addLast(new IdleStateHandler(0, 0, 20, TimeUnit.SECONDS))
                .addLast(new ServerOutHandler())
                .addLast(new MessageDecoder())
                .addLast(new MessageEncoder())
                // 添加消息处理器
                .addLast(makeServerHandler());
    }

    private ServerHandler makeServerHandler() {
        ServerHandler serverHandler = new ServerHandler();
        serverHandler.register(ServerChannelManager.INSTANCE);
        serverHandler.setProtocolHandlerMgr(new UserObjMgr());
        return serverHandler;
    }
}
