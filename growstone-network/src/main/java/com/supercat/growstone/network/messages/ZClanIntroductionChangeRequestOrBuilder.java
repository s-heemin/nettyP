// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZClanIntroductionChangeRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZClanIntroductionChangeRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>string introduction = 1;</code>
   * @return The introduction.
   */
  java.lang.String getIntroduction();
  /**
   * <code>string introduction = 1;</code>
   * @return The bytes for introduction.
   */
  com.google.protobuf.ByteString
      getIntroductionBytes();

  /**
   * <code>.ZClanJoin.Type type = 2;</code>
   * @return The enum numeric value on the wire for type.
   */
  int getTypeValue();
  /**
   * <code>.ZClanJoin.Type type = 2;</code>
   * @return The type.
   */
  com.supercat.growstone.network.messages.ZClanJoin.Type getType();
}
