package com.fish.config;


import com.fish.config.load.yaml.AutoLoadYmlConfig;
import com.fish.config.load.yaml.YamlConfigReader;
import lombok.Getter;
import lombok.Setter;

@AutoLoadYmlConfig("/server_conf.yml")
@Getter
@Setter
public class MyServerConfig extends YamlConfigReader {
    public static MyServerConfig INSTANCE;

    private MyServerConfig() {
    }

    @AutoLoadYmlConfig("netty.port")
    private int clientConnectPort;

    @AutoLoadYmlConfig("netty.ip")
    private String clientConnectIp;

    @AutoLoadYmlConfig("netty.use_epoll")
    private boolean useEpoll;

    @AutoLoadYmlConfig("netty.wsUrl")
    private String wsUrlFormat;

    @AutoLoadYmlConfig("server.port")
    private int serverPort;


    @Override
    public YamlConfigReader createInstance() {
        if (INSTANCE == null) {
            synchronized (MyServerConfig.class) {
                if (INSTANCE == null) {
                    INSTANCE = new MyServerConfig();
                }
            }
        }
        return INSTANCE;
    }
}
