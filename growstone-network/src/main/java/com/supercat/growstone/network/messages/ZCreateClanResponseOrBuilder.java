// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZCreateClanResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZCreateClanResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TClan clan = 2;</code>
   * @return Whether the clan field is set.
   */
  boolean hasClan();
  /**
   * <code>.TClan clan = 2;</code>
   * @return The clan.
   */
  com.supercat.growstone.network.messages.TClan getClan();
  /**
   * <code>.TClan clan = 2;</code>
   */
  com.supercat.growstone.network.messages.TClanOrBuilder getClanOrBuilder();

  /**
   * <code>.TClanMember member = 3;</code>
   * @return Whether the member field is set.
   */
  boolean hasMember();
  /**
   * <code>.TClanMember member = 3;</code>
   * @return The member.
   */
  com.supercat.growstone.network.messages.TClanMember getMember();
  /**
   * <code>.TClanMember member = 3;</code>
   */
  com.supercat.growstone.network.messages.TClanMemberOrBuilder getMemberOrBuilder();
}
