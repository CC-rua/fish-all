package com.fish.net.tcp.base;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * 将协议和处理对象绑定在一起的一个数据结构
 *
 * @author: cc
 * @date: 2024/1/17
 */
@Getter
@Setter
@Builder
public class MessageBusObj {
    private MessageProtocol protocol;
    private _IProtocolHandler handler;
}
