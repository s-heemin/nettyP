// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZCompetitiveRaidDungeonRankResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZCompetitiveRaidDungeonRankResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo> 
      getPlayersList();
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo getPlayers(int index);
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  int getPlayersCount();
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder> 
      getPlayersOrBuilderList();
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder getPlayersOrBuilder(
      int index);

  /**
   * <code>int32 next_rank = 3;</code>
   * @return The nextRank.
   */
  int getNextRank();
}
