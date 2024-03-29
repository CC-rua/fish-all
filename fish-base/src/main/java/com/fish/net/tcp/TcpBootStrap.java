package com.fish.net.tcp;

import com.fish.ServerMain;
import com.fish.net.tcp.client.ClientChannelManager;
import com.fish.net.tcp.client.TcpClient;
import com.fish.net.tcp.server.ServerChannelManager;
import com.fish.net.tcp.server.TcpServer;

/**
 * @author: cc
 * @date: 2024/1/6
 */
public class TcpBootStrap {
    public static void serverStart() {
        TcpServer tcpServer = new TcpServer();
        tcpServer.serverBootstrap();
        ServerMain.INSTANCE.registerTickTask(ServerChannelManager.INSTANCE);
    }

    public static void clientStart() {
        TcpClient tcpClient = new TcpClient();
        tcpClient.clientBootstrap();
        ServerMain.INSTANCE.registerTickTask(ClientChannelManager.INSTANCE);

    }
}
