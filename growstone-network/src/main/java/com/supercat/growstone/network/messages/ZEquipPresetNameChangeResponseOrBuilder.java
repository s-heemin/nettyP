// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZEquipPresetNameChangeResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZEquipPresetNameChangeResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TEquipPresetName name = 2;</code>
   * @return Whether the name field is set.
   */
  boolean hasName();
  /**
   * <code>.TEquipPresetName name = 2;</code>
   * @return The name.
   */
  com.supercat.growstone.network.messages.TEquipPresetName getName();
  /**
   * <code>.TEquipPresetName name = 2;</code>
   */
  com.supercat.growstone.network.messages.TEquipPresetNameOrBuilder getNameOrBuilder();
}
