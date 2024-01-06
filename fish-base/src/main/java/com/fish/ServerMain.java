package com.fish;

import com.fish.config.load.yaml.YamlConfigAutoReader;
import com.fish.net.tcp.TcpBootStrap;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: cc
 * @date: 2024/1/6
 */
@Slf4j
public class ServerMain {
    public static void main(String[] args) {
        YamlConfigAutoReader.INSTANCE.autoReadYml();
        log.info("config load down");
        TcpBootStrap.serverStart();
        log.info("server start");
    }
}
