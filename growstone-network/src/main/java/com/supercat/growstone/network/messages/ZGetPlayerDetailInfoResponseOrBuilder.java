// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZGetPlayerDetailInfoResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZGetPlayerDetailInfoResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TPlayerDetailInfo player_detail_info = 2;</code>
   * @return Whether the playerDetailInfo field is set.
   */
  boolean hasPlayerDetailInfo();
  /**
   * <code>.TPlayerDetailInfo player_detail_info = 2;</code>
   * @return The playerDetailInfo.
   */
  com.supercat.growstone.network.messages.TPlayerDetailInfo getPlayerDetailInfo();
  /**
   * <code>.TPlayerDetailInfo player_detail_info = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerDetailInfoOrBuilder getPlayerDetailInfoOrBuilder();
}
