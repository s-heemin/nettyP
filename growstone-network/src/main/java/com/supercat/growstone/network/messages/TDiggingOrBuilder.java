// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TDiggingOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TDigging)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 index = 1;</code>
   * @return The index.
   */
  int getIndex();

  /**
   * <code>.ZTier.Type type = 2;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.ZTier.Type type = 2;</code>
   * @return The type.
   */
  com.supercat.growstone.network.messages.ZTier.Type getType();

  /**
   * <code>int64 remain_time = 3;</code>
   * @return The remainTime.
   */
  long getRemainTime();
}
