package com.fish.net.tcp.client;

import com.fish.net.tcp.base.MessageDecoder;
import com.fish.net.tcp.base.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketClientCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

/**
 * 初始化 netty 启动时管道相关配置
 *
 * @author: cc
 * @date: 2024/1/6
 */
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new IdleStateHandler(0L, 0L, 3600L, TimeUnit.SECONDS))
                .addLast(new HttpClientCodec())
                .addLast(WebSocketClientCompressionHandler.INSTANCE)
                .addLast(new ChunkedWriteHandler())
                .addLast(new HttpObjectAggregator(65536))
                .addLast(new ClientOutHandler())
                .addLast(new MessageDecoder())
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new MessageEncoder())
                .addLast(new ClientHandler());
    }
}
