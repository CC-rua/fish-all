package com.fish.net.tcp.client;

import com.fish.config.MyServerConfig;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author: cc
 * @date: 2024/1/6
 */
public class TcpClient {

    public Bootstrap clientBootstrap() {
        Bootstrap bs = new Bootstrap();
        //BossGroup 处理线程池 关注注册事件的线程
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ClientChannelInitializer initializer = new ClientChannelInitializer();

            MyServerConfig config = MyServerConfig.INSTANCE;
            bs.group(group)
                    //说明客户端通道的实现类
                    .channel(config.isUseEpoll() ? EpollSocketChannel.class : NioSocketChannel.class)
                    //通道初始化对象
                    .handler(initializer);
            System.out.println("client is ready...");
            //启动客户端去连接服务端
            ChannelFuture future = bs.connect(config.getClientConnectIp(), config.getClientConnectPort()).sync();

            //对信道关闭做监听
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
        return bs;
    }
}
