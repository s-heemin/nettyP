// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerBlockListResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerBlockListResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TPlayerSimpleInfo block_players = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TPlayerSimpleInfo> 
      getBlockPlayersList();
  /**
   * <code>repeated .TPlayerSimpleInfo block_players = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerSimpleInfo getBlockPlayers(int index);
  /**
   * <code>repeated .TPlayerSimpleInfo block_players = 2;</code>
   */
  int getBlockPlayersCount();
  /**
   * <code>repeated .TPlayerSimpleInfo block_players = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder> 
      getBlockPlayersOrBuilderList();
  /**
   * <code>repeated .TPlayerSimpleInfo block_players = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder getBlockPlayersOrBuilder(
      int index);
}
