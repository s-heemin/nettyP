// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerShopPassInfoResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerShopPassInfoResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TShopPass shop_pass = 2;</code>
   * @return Whether the shopPass field is set.
   */
  boolean hasShopPass();
  /**
   * <code>.TShopPass shop_pass = 2;</code>
   * @return The shopPass.
   */
  com.supercat.growstone.network.messages.TShopPass getShopPass();
  /**
   * <code>.TShopPass shop_pass = 2;</code>
   */
  com.supercat.growstone.network.messages.TShopPassOrBuilder getShopPassOrBuilder();
}
