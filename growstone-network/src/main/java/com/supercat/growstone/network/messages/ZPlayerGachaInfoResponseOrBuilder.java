// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerGachaInfoResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerGachaInfoResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>.TGacha gacha = 2;</code>
   * @return Whether the gacha field is set.
   */
  boolean hasGacha();
  /**
   * <code>.TGacha gacha = 2;</code>
   * @return The gacha.
   */
  com.supercat.growstone.network.messages.TGacha getGacha();
  /**
   * <code>.TGacha gacha = 2;</code>
   */
  com.supercat.growstone.network.messages.TGachaOrBuilder getGachaOrBuilder();

  /**
   * <code>.TPickUpGacha pick_up_gacha = 3;</code>
   * @return Whether the pickUpGacha field is set.
   */
  boolean hasPickUpGacha();
  /**
   * <code>.TPickUpGacha pick_up_gacha = 3;</code>
   * @return The pickUpGacha.
   */
  com.supercat.growstone.network.messages.TPickUpGacha getPickUpGacha();
  /**
   * <code>.TPickUpGacha pick_up_gacha = 3;</code>
   */
  com.supercat.growstone.network.messages.TPickUpGachaOrBuilder getPickUpGachaOrBuilder();
}
