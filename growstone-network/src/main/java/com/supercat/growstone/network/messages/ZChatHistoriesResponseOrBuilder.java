// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZChatHistoriesResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZChatHistoriesResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TChatHistory chats = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TChatHistory> 
      getChatsList();
  /**
   * <code>repeated .TChatHistory chats = 2;</code>
   */
  com.supercat.growstone.network.messages.TChatHistory getChats(int index);
  /**
   * <code>repeated .TChatHistory chats = 2;</code>
   */
  int getChatsCount();
  /**
   * <code>repeated .TChatHistory chats = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TChatHistoryOrBuilder> 
      getChatsOrBuilderList();
  /**
   * <code>repeated .TChatHistory chats = 2;</code>
   */
  com.supercat.growstone.network.messages.TChatHistoryOrBuilder getChatsOrBuilder(
      int index);
}
