// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TCollectionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TCollection)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ZResource.Type type = 1;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.ZResource.Type type = 1;</code>
   * @return The type.
   */
  com.supercat.growstone.network.messages.ZResource.Type getType();

  /**
   * <code>int64 data_id = 2;</code>
   * @return The dataId.
   */
  long getDataId();

  /**
   * <code>int32 level = 3;</code>
   * @return The level.
   */
  int getLevel();
}
