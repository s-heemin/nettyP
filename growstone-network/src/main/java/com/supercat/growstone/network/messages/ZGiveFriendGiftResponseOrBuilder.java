// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZGiveFriendGiftResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZGiveFriendGiftResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TFriendInfo friends = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TFriendInfo> 
      getFriendsList();
  /**
   * <code>repeated .TFriendInfo friends = 2;</code>
   */
  com.supercat.growstone.network.messages.TFriendInfo getFriends(int index);
  /**
   * <code>repeated .TFriendInfo friends = 2;</code>
   */
  int getFriendsCount();
  /**
   * <code>repeated .TFriendInfo friends = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TFriendInfoOrBuilder> 
      getFriendsOrBuilderList();
  /**
   * <code>repeated .TFriendInfo friends = 2;</code>
   */
  com.supercat.growstone.network.messages.TFriendInfoOrBuilder getFriendsOrBuilder(
      int index);
}
