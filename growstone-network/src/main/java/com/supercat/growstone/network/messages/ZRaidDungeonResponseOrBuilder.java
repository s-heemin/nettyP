// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZRaidDungeonResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZRaidDungeonResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TRaidDungeon raid_dungeon = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TRaidDungeon> 
      getRaidDungeonList();
  /**
   * <code>repeated .TRaidDungeon raid_dungeon = 2;</code>
   */
  com.supercat.growstone.network.messages.TRaidDungeon getRaidDungeon(int index);
  /**
   * <code>repeated .TRaidDungeon raid_dungeon = 2;</code>
   */
  int getRaidDungeonCount();
  /**
   * <code>repeated .TRaidDungeon raid_dungeon = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TRaidDungeonOrBuilder> 
      getRaidDungeonOrBuilderList();
  /**
   * <code>repeated .TRaidDungeon raid_dungeon = 2;</code>
   */
  com.supercat.growstone.network.messages.TRaidDungeonOrBuilder getRaidDungeonOrBuilder(
      int index);
}
