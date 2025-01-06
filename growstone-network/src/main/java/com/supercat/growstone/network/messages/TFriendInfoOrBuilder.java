// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TFriendInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TFriendInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int64 player_id = 1;</code>
   * @return The playerId.
   */
  long getPlayerId();

  /**
   * <code>int64 friend_id = 2;</code>
   * @return The friendId.
   */
  long getFriendId();

  /**
   * <code>string name = 3;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <code>string name = 3;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <code>int64 portrait_id = 4;</code>
   * @return The portraitId.
   */
  long getPortraitId();

  /**
   * <code>int32 level = 5;</code>
   * @return The level.
   */
  int getLevel();

  /**
   * <code>int64 attack_power = 6;</code>
   * @return The attackPower.
   */
  long getAttackPower();

  /**
   * <code>.ZFriend.State state = 7;</code>
   * @return The enum numeric value on the wire for state.
   */
  int getStateValue();
  /**
   * <code>.ZFriend.State state = 7;</code>
   * @return The state.
   */
  com.supercat.growstone.network.messages.ZFriend.State getState();

  /**
   * <code>int64 gift_expire_time = 8;</code>
   * @return The giftExpireTime.
   */
  long getGiftExpireTime();

  /**
   * <code>int64 time_since_last_logout_time = 9;</code>
   * @return The timeSinceLastLogoutTime.
   */
  long getTimeSinceLastLogoutTime();

  /**
   * <code>string friend_code = 10;</code>
   * @return The friendCode.
   */
  java.lang.String getFriendCode();
  /**
   * <code>string friend_code = 10;</code>
   * @return The bytes for friendCode.
   */
  com.google.protobuf.ByteString
      getFriendCodeBytes();
}
