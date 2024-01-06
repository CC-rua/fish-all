package com.fish.net.tcp.server;


import com.fish.net.tcp.base.MessageDecoder;
import com.fish.net.tcp.base.MessageEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
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
                // 添加编码、解码器
                .addLast(new HttpServerCodec())
                // 支持参数对象解析， 比如POST参数， 设置聚合内容的最大长度
                .addLast(new HttpObjectAggregator(65536))
                // 支持大数据流写入
                .addLast(new ChunkedWriteHandler())
                // 支持WebSocket数据压缩
                .addLast(new WebSocketServerCompressionHandler())
                .addLast(new MessageDecoder())
                .addLast(new ProtobufVarint32LengthFieldPrepender())
                .addLast(new MessageEncoder())
                // 添加消息处理器
                .addLast(new ServerHandler());
    }
}
