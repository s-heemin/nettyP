// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

public interface ZPlayerShopItemInfoResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ZPlayerShopItemInfoResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  int getStatus();

  /**
   * <code>repeated .TBuyShopItem shop_items = 2;</code>
   */
  java.util.List<com.supercat.growstone.network.messages.TBuyShopItem> 
      getShopItemsList();
  /**
   * <code>repeated .TBuyShopItem shop_items = 2;</code>
   */
  com.supercat.growstone.network.messages.TBuyShopItem getShopItems(int index);
  /**
   * <code>repeated .TBuyShopItem shop_items = 2;</code>
   */
  int getShopItemsCount();
  /**
   * <code>repeated .TBuyShopItem shop_items = 2;</code>
   */
  java.util.List<? extends com.supercat.growstone.network.messages.TBuyShopItemOrBuilder> 
      getShopItemsOrBuilderList();
  /**
   * <code>repeated .TBuyShopItem shop_items = 2;</code>
   */
  com.supercat.growstone.network.messages.TBuyShopItemOrBuilder getShopItemsOrBuilder(
      int index);
}
