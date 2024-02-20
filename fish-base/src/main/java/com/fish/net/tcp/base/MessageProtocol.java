package com.fish.net.tcp.base;


import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class MessageProtocol implements Serializable {
    //协议号
    private Integer messageId;
    //序列号
    private Integer seqNum;
    //响应码
    private Integer responseCode;
    //正文
    private byte[] data;

    public int getDataLength() {
        if (this.data == null) {
            return 0;
        }
        return this.data.length;
    }
}
