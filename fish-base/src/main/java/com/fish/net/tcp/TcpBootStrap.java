package com.fish.net.tcp;

import com.fish.net.tcp.client.TcpClient;
import com.fish.net.tcp.server.TcpServer;

/**
 * @author: cc
 * @date: 2024/1/6
 */
public class TcpBootStrap {
    public static void serverStart() {
        TcpServer tcpServer = new TcpServer();
        tcpServer.serverBootstrap();
    }

    public static void clientStart() {
        TcpClient tcpClient = new TcpClient();
        tcpClient.clientBootstrap();
    }
}
