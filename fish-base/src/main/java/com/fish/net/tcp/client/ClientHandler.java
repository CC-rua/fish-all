package com.fish.net.tcp.client;

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
    private List<_INetListener> listenerList;

    public ClientHandler() {
        listenerList = new ArrayList<>();
    }

    public void register(_INetListener listener) {
        listenerList.add(listener);
    }

    public void unregister(_INetListener listener) {
        listenerList.remove(listener);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        super.channelRead(ctx, msg);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol message) throws Exception {
        log.info("信道读取 - channel id:{} message:{}", ctx.channel().id(), message);
        listenerList.forEach(l -> l.onRead(ctx.channel(), message));
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) {
        log.info("信道失效 - channel id {}", ctx.channel().id());
        listenerList.forEach(l -> l.onInactive(ctx.channel()));
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("信道活跃 - channel id {}", ctx.channel().id());
        super.channelActive(ctx);
        listenerList.forEach(l -> l.onAccept(ctx.channel()));
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
