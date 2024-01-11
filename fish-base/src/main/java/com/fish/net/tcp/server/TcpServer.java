package com.fish.net.tcp.server;

import com.fish.config.MyServerConfig;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

/**
 * @author: cc
 * @date: 2024/1/6
 */
public class TcpServer {

    public ServerBootstrap serverBootstrap() {
        ServerBootstrap bs = new ServerBootstrap();
        boolean useEpoll = MyServerConfig.INSTANCE.isUseEpoll();
        int serverPort = MyServerConfig.INSTANCE.getServerPort();

        EventLoopGroup bossGroup = useEpoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        EventLoopGroup workerGroup = useEpoll ? new EpollEventLoopGroup() : new NioEventLoopGroup();
        try {
            ServerChannelInitializer initializer = new ServerChannelInitializer();

            bs.group(bossGroup, workerGroup)
                    .channel(useEpoll ? EpollServerSocketChannel.class : NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(serverPort))
                    // 配置 编码器、解码器、业务处理
                    .childHandler(initializer)
                    // tcp缓冲区
                    .option(ChannelOption.SO_BACKLOG, 128)
                    // 将网络数据积累到一定的数量后,服务器端才发送出去,会造成一定的延迟。希望服务是低延迟的,建议将TCP_NODELAY设置为true
                    .childOption(ChannelOption.TCP_NODELAY, false)
                    // 保持长连接
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // 绑定端口，开始接收进来的连接
                    .bind().sync();
        } catch (Exception e) {
            e.printStackTrace();
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
        return bs;
    }
}
