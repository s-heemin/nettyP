package com.supercat.growstone;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.hash.Hashing;
import com.google.common.io.BaseEncoding;
import com.google.protobuf.*;
import com.google.protobuf.util.JsonFormat;

import com.supercat.growstone.network.messages.Packet;
import com.supercat.growstone.network.messages.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketUtils {
    private static final Logger logger = LoggerFactory.getLogger(PacketUtils.class);
    private static final Map<String, MethodHandle> methodCache = new ConcurrentHashMap<>();

    private static MethodHandle getMethodHandle(String methodName, Class<?> parameterType) {
        try {
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodType methodType = MethodType.methodType(Packet.Builder.class, parameterType);
            return lookup.findVirtual(Packet.Builder.class, methodName, methodType);
        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException("Failed to find method handle", e);
        }
    }

    private static void setDynamicField(Packet.Builder packetBuilder, String fieldName, Message payload) {
        String methodName = "set" + capitalize(fieldName);

        try {
            MethodHandle methodHandle = methodCache.computeIfAbsent(methodName, name -> getMethodHandle(name, payload.getClass()));
            methodHandle.invoke(packetBuilder, payload);
        } catch (Throwable e) {
            logger.error("Failed to set dynamic field", e);
        }
    }

    private static String capitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    public static <Type extends com.google.protobuf.GeneratedMessageV3>
    Packet packet(long id, Type.Builder contents) {
        var name = contents.getDescriptorForType().getName().substring(1);
        var packetBuilder = Packet.newBuilder()
                .setId(id)
                .setType(PacketType.valueOf(name));

        setDynamicField(packetBuilder, name, contents.build());

        //
        return packetBuilder.build();
    }

}
