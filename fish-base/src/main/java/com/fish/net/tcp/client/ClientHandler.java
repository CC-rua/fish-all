package com.fish.net.tcp.client;

import com.fish.net.tcp.base.ChannelObj;
import com.fish.net.tcp.base.MessageProtocol;
import com.fish.net.tcp.base._INetListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;


@Slf4j
public class ClientHandler extends SimpleChannelInboundHandler<MessageProtocol> {

    /**
     * 信道事件的监听者
     */
    //TODO 对监听列表的各种事件在合适的地方触发
    private List<_INetListener> listenerList = new ArrayList<>();

    public ClientHandler() {
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol message) throws Exception {
        log.info("信道读取 - channel id:{} message:{}", ctx.channel().id(), message);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("信道失效 - channel id {}", ctx.channel().id());
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("信道活跃 - channel id {}", ctx.channel().id());
        super.channelActive(ctx);
        //TODO 创建客户端信道对象,保存在manager中
        ChannelObj channelObj = new ChannelObj(ctx.channel());
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        log.info("事件触发- channel id {} evt:{}", ctx.channel().id(), evt);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
    }
}
