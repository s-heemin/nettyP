// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZRaidDungeonViewAdvertiseResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZRaidDungeonViewAdvertiseResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TRaidDungeon raid_dungeon = 2;</code>
   * @return Whether the raidDungeon field is set.
   */
  boolean hasRaidDungeon();
  /**
   * <code>.TRaidDungeon raid_dungeon = 2;</code>
   * @return The raidDungeon.
   */
  com.supercat.growstone.network.messages.TRaidDungeon getRaidDungeon();
  /**
   * <code>.TRaidDungeon raid_dungeon = 2;</code>
   */
  com.supercat.growstone.network.messages.TRaidDungeonOrBuilder getRaidDungeonOrBuilder();
}
