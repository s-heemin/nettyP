// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZFarmPlantBoostRequestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZFarmPlantBoostRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .TFarmBoost boosts = 1;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TFarmBoost> 
      getBoostsList();
  /**
   * <code>repeated .TFarmBoost boosts = 1;</code>
   */
  com.supercat.growstone.network.messages.TFarmBoost getBoosts(int index);
  /**
   * <code>repeated .TFarmBoost boosts = 1;</code>
   */
  int getBoostsCount();
  /**
   * <code>repeated .TFarmBoost boosts = 1;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TFarmBoostOrBuilder> 
      getBoostsOrBuilderList();
  /**
   * <code>repeated .TFarmBoost boosts = 1;</code>
   */
  com.supercat.growstone.network.messages.TFarmBoostOrBuilder getBoostsOrBuilder(
      int index);
}
