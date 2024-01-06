package com.fish.net.tcp.base;

import com.google.protobuf.MessageLite;

public interface ProtocolHandler {
    MessageLite.Builder toMessage(MessageProtocol protocol);

    MessageProtocol toProtocol(MessageLite.Builder message, int seqNum, int responseCode);

    MessageProtocol toProtocol(int messageId, int seqNum, int responseCode);

    int getMessageId(MessageLite.Builder msg);
}
