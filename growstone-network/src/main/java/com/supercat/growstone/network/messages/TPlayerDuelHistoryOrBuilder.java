// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TPlayerDuelHistoryOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TPlayerDuelHistory)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  long getId();

  /**
   * <code>.TPlayerDuel win_player = 2;</code>
   * @return Whether the winPlayer field is set.
   */
  boolean hasWinPlayer();
  /**
   * <code>.TPlayerDuel win_player = 2;</code>
   * @return The winPlayer.
   */
  com.supercat.growstone.network.messages.TPlayerDuel getWinPlayer();
  /**
   * <code>.TPlayerDuel win_player = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerDuelOrBuilder getWinPlayerOrBuilder();

  /**
   * <code>.TPlayerDuel lose_player = 3;</code>
   * @return Whether the losePlayer field is set.
   */
  boolean hasLosePlayer();
  /**
   * <code>.TPlayerDuel lose_player = 3;</code>
   * @return The losePlayer.
   */
  com.supercat.growstone.network.messages.TPlayerDuel getLosePlayer();
  /**
   * <code>.TPlayerDuel lose_player = 3;</code>
   */
  com.supercat.growstone.network.messages.TPlayerDuelOrBuilder getLosePlayerOrBuilder();

  /**
   * <code>int32 critical_seed = 4;</code>
   * @return The criticalSeed.
   */
  int getCriticalSeed();
}
