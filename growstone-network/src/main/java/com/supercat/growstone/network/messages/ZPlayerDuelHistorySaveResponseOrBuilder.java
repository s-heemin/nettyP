// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerDuelHistorySaveResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerDuelHistorySaveResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TPlayerDuelHistory result = 2;</code>
   * @return Whether the result field is set.
   */
  boolean hasResult();
  /**
   * <code>.TPlayerDuelHistory result = 2;</code>
   * @return The result.
   */
  com.supercat.growstone.network.messages.TPlayerDuelHistory getResult();
  /**
   * <code>.TPlayerDuelHistory result = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerDuelHistoryOrBuilder getResultOrBuilder();
}
