// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TMailRewardOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TMailReward)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ZCategory.Type category = 1;</code>
   * @return The enum numeric value on the wire for category.
   */
  int getCategoryValue();
  /**
   * <code>.ZCategory.Type category = 1;</code>
   * @return The category.
   */
  com.supercat.growstone.network.messages.ZCategory.Type getCategory();

  /**
   * <code>int64 data_id = 2;</code>
   * @return The dataId.
   */
  long getDataId();

  /**
   * <code>int64 count = 3;</code>
   * @return The count.
   */
  long getCount();
}
