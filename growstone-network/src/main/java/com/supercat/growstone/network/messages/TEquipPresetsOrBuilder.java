// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TEquipPresetsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TEquipPresets)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 preset_index = 1;</code>
   * @return The presetIndex.
   */
  int getPresetIndex();

  /**
   * <code>string preset_name = 2;</code>
   * @return The presetName.
   */
  java.lang.String getPresetName();
  /**
   * <code>string preset_name = 2;</code>
   * @return The bytes for presetName.
   */
  com.google.protobuf.ByteString
      getPresetNameBytes();

  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TEquipPresetsByType> 
      getEquipsList();
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  com.supercat.growstone.network.messages.TEquipPresetsByType getEquips(int index);
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  int getEquipsCount();
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder> 
      getEquipsOrBuilderList();
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder getEquipsOrBuilder(
      int index);
}
