// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZGrowthLimitBreakResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZGrowthLimitBreakResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TGrowth growths = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TGrowth> 
      getGrowthsList();
  /**
   * <code>repeated .TGrowth growths = 2;</code>
   */
  com.supercat.growstone.network.messages.TGrowth getGrowths(int index);
  /**
   * <code>repeated .TGrowth growths = 2;</code>
   */
  int getGrowthsCount();
  /**
   * <code>repeated .TGrowth growths = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TGrowthOrBuilder> 
      getGrowthsOrBuilderList();
  /**
   * <code>repeated .TGrowth growths = 2;</code>
   */
  com.supercat.growstone.network.messages.TGrowthOrBuilder getGrowthsOrBuilder(
      int index);
}
