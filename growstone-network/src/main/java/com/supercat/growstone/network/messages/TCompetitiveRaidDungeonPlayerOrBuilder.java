// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TCompetitiveRaidDungeonPlayerOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TCompetitiveRaidDungeonPlayer)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 player_score = 1;</code>
   * @return The playerScore.
   */
  long getPlayerScore();

  /**
   * <code>int32 player_rank = 2;</code>
   * @return The playerRank.
   */
  int getPlayerRank();

  /**
   * <code>string player_name = 3;</code>
   * @return The playerName.
   */
  java.lang.String getPlayerName();
  /**
   * <code>string player_name = 3;</code>
   * @return The bytes for playerName.
   */
  com.google.protobuf.ByteString
      getPlayerNameBytes();

  /**
   * <code>int64 avatar_id = 4;</code>
   * @return The avatarId.
   */
  long getAvatarId();

  /**
   * <code>int32 remain_ticket_count = 5;</code>
   * @return The remainTicketCount.
   */
  int getRemainTicketCount();

  /**
   * <code>int32 remain_ad_view_count = 6;</code>
   * @return The remainAdViewCount.
   */
  int getRemainAdViewCount();
}
