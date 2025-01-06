package org.supercat.growstone.network;

import com.google.protobuf.InvalidProtocolBufferException;
import com.supercat.growstone.network.messages.Packet;
import io.netty.buffer.ByteBuf;

public class PacketUtils {
    public static Packet decode(ByteBuf content) throws InvalidProtocolBufferException {
        byte[] decodedBytes = new byte[content.readableBytes()];
        content.readBytes(decodedBytes);
        byte[] decodedData = XorUtils.xorDecode(decodedBytes);

        return Packet.parseFrom(decodedData);
    }

    public static byte[] encode(Packet packet) {
        return XorUtils.xorEncode(packet.toByteArray());
    }
}
