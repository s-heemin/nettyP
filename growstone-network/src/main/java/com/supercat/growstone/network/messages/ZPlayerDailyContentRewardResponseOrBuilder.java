// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerDailyContentRewardResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerDailyContentRewardResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TContentReward rewards = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TContentReward> 
      getRewardsList();
  /**
   * <code>repeated .TContentReward rewards = 2;</code>
   */
  com.supercat.growstone.network.messages.TContentReward getRewards(int index);
  /**
   * <code>repeated .TContentReward rewards = 2;</code>
   */
  int getRewardsCount();
  /**
   * <code>repeated .TContentReward rewards = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TContentRewardOrBuilder> 
      getRewardsOrBuilderList();
  /**
   * <code>repeated .TContentReward rewards = 2;</code>
   */
  com.supercat.growstone.network.messages.TContentRewardOrBuilder getRewardsOrBuilder(
      int index);
}
