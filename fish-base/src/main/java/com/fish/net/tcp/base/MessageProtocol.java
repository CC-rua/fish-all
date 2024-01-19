package com.fish.net.tcp.base;


import com.google.protobuf.MessageLite;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class MessageProtocol implements Serializable {
    private Integer messageId;
    private Integer seqNum;

    private Integer responseCode;
    private byte[] data;

    public int getDataLength() {
        if (this.data == null) {
            return 0;
        }
        return this.data.length;
    }
}
