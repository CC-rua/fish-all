package com.fish.net.tcp.user;

import com.fish.net.tcp.base.MessageProtocol;
import com.fish.net.tcp.base._IProtocolHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author: cc
 * @date: 2024/2/19
 */
@Slf4j
public class UserObj implements _IProtocolHandler {
    public UserObj() {
        log.info("userObj on created");
    }

    @Override
    public void dealProtocol(MessageProtocol protocol) {
        log.info("userObj deal protocol");
    }
}
