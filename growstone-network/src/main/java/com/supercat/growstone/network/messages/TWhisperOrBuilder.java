// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TWhisperOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TWhisper)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 target_player_id = 1;</code>
   * @return The targetPlayerId.
   */
  long getTargetPlayerId();

  /**
   * <code>string text = 2;</code>
   * @return The text.
   */
  java.lang.String getText();
  /**
   * <code>string text = 2;</code>
   * @return The bytes for text.
   */
  com.google.protobuf.ByteString
      getTextBytes();
}
