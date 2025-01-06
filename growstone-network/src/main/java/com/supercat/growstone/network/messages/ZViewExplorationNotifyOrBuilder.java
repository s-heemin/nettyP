// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZViewExplorationNotifyOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZViewExplorationNotify)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 level = 1;</code>
   * @return The level.
   */
  int getLevel();

  /**
   * <code>int32 exp = 2;</code>
   * @return The exp.
   */
  int getExp();

  /**
   * <code>bool auto = 3;</code>
   * @return The auto.
   */
  boolean getAuto();

  /**
   * <code>repeated .TExplorationQuest quest = 4;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TExplorationQuest> 
      getQuestList();
  /**
   * <code>repeated .TExplorationQuest quest = 4;</code>
   */
  com.supercat.growstone.network.messages.TExplorationQuest getQuest(int index);
  /**
   * <code>repeated .TExplorationQuest quest = 4;</code>
   */
  int getQuestCount();
  /**
   * <code>repeated .TExplorationQuest quest = 4;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TExplorationQuestOrBuilder> 
      getQuestOrBuilderList();
  /**
   * <code>repeated .TExplorationQuest quest = 4;</code>
   */
  com.supercat.growstone.network.messages.TExplorationQuestOrBuilder getQuestOrBuilder(
      int index);
}
