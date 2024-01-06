package com.fish;

import com.fish.config.load.yaml.YamlConfigAutoReader;
import com.fish.net.tcp.TcpBootStrap;

/**
 * @author: cc
 * @date: 2024/1/6
 */
public class ClientMain {
    public static void main(String[] args) {
        YamlConfigAutoReader.INSTANCE.autoReadYml();
        TcpBootStrap.clientStart();
    }
}
