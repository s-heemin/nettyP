// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TAchievementOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TAchievement)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>int64 progress = 2;</code>
   * @return The progress.
   */
  long getProgress();

  /**
   * <code>.ZClear.State state = 3;</code>
   * @return The enum numeric value on the wire for state.
   */
  int getStateValue();
  /**
   * <code>.ZClear.State state = 3;</code>
   * @return The state.
   */
  com.supercat.growstone.network.messages.ZClear.State getState();
}
