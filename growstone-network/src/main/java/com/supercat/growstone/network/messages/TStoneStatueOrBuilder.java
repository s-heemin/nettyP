// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TStoneStatueOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TStoneStatue)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 enchant_level = 1;</code>
   * @return The enchantLevel.
   */
  int getEnchantLevel();

  /**
   * <code>int32 enchant_exp = 2;</code>
   * @return The enchantExp.
   */
  int getEnchantExp();

  /**
   * <code>.ZTier.Type enchant_safe_grade = 3;</code>
   * @return The enum numeric value on the wire for enchantSafeGrade.
   */
  int getEnchantSafeGradeValue();
  /**
   * <code>.ZTier.Type enchant_safe_grade = 3;</code>
   * @return The enchantSafeGrade.
   */
  com.supercat.growstone.network.messages.ZTier.Type getEnchantSafeGrade();

  /**
   * <code>int64 avatar_id = 4;</code>
   * @return The avatarId.
   */
  long getAvatarId();
}
