// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerEventResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerEventResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TPlayerEvent player_events = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TPlayerEvent> 
      getPlayerEventsList();
  /**
   * <code>repeated .TPlayerEvent player_events = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerEvent getPlayerEvents(int index);
  /**
   * <code>repeated .TPlayerEvent player_events = 2;</code>
   */
  int getPlayerEventsCount();
  /**
   * <code>repeated .TPlayerEvent player_events = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TPlayerEventOrBuilder> 
      getPlayerEventsOrBuilderList();
  /**
   * <code>repeated .TPlayerEvent player_events = 2;</code>
   */
  com.supercat.growstone.network.messages.TPlayerEventOrBuilder getPlayerEventsOrBuilder(
      int index);
}
