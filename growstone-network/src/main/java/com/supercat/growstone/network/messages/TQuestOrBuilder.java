// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface TQuestOrBuilder extends
    // @@protoc_insertion_point(interface_extends:TQuest)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.ZClear.State state = 1;</code>
   * @return The enum numeric value on the wire for state.
   */
  int getStateValue();
  /**
   * <code>.ZClear.State state = 1;</code>
   * @return The state.
   */
  com.supercat.growstone.network.messages.ZClear.State getState();

  /**
   * <code>int32 step = 2;</code>
   * @return The step.
   */
  int getStep();

  /**
   * <code>int64 progress = 3;</code>
   * @return The progress.
   */
  long getProgress();

  /**
   * <code>int64 next_stage_group_id_condition = 4;</code>
   * @return The nextStageGroupIdCondition.
   */
  long getNextStageGroupIdCondition();

  /**
   * <code>int32 next_stage_id_condition = 5;</code>
   * @return The nextStageIdCondition.
   */
  int getNextStageIdCondition();
}
