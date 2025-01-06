// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TStoneStatueEnchantSlotOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TStoneStatueEnchantSlot)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 slot_id = 1;</code>
   * @return The slotId.
   */
  int getSlotId();

  /**
   * <code>bool is_locked = 2;</code>
   * @return The isLocked.
   */
  boolean getIsLocked();

  /**
   * <code>.ZTier.Type tier = 3;</code>
   * @return The enum numeric value on the wire for tier.
   */
  int getTierValue();
  /**
   * <code>.ZTier.Type tier = 3;</code>
   * @return The tier.
   */
  com.supercat.growstone.network.messages.ZTier.Type getTier();

  /**
   * <code>.TStat stat = 4;</code>
   * @return Whether the stat field is set.
   */
  boolean hasStat();
  /**
   * <code>.TStat stat = 4;</code>
   * @return The stat.
   */
  com.supercat.growstone.network.messages.TStat getStat();
  /**
   * <code>.TStat stat = 4;</code>
   */
  com.supercat.growstone.network.messages.TStatOrBuilder getStatOrBuilder();
}
