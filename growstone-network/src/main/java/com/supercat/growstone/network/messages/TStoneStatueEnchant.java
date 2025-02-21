// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code TStoneStatueEnchant}
 */
public final class TStoneStatueEnchant extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TStoneStatueEnchant)
    TStoneStatueEnchantOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TStoneStatueEnchant.newBuilder() to construct.
  private TStoneStatueEnchant(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TStoneStatueEnchant() {
    stoneStatueEnchants_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TStoneStatueEnchant();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TStoneStatueEnchant(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 8: {

            orderId_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              stoneStatueEnchants_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot>();
              mutable_bitField0_ |= 0x00000001;
            }
            stoneStatueEnchants_.add(
                input.readMessage(com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.parser(), extensionRegistry));
            break;
          }
          default: {
            if (!parseUnknownField(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (com.google.protobuf.UninitializedMessageException e) {
      throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      if (((mutable_bitField0_ & 0x00000001) != 0)) {
        stoneStatueEnchants_ = java.util.Collections.unmodifiableList(stoneStatueEnchants_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_TStoneStatueEnchant_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_TStoneStatueEnchant_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.TStoneStatueEnchant.class, com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder.class);
  }

  public static final int ORDER_ID_FIELD_NUMBER = 1;
  private int orderId_;
  /**
   * <code>int32 order_id = 1;</code>
   * @return The orderId.
   */
  @java.lang.Override
  public int getOrderId() {
    return orderId_;
  }

  public static final int STONE_STATUE_ENCHANTS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot> stoneStatueEnchants_;
  /**
   * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot> getStoneStatueEnchantsList() {
    return stoneStatueEnchants_;
  }
  /**
   * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder> 
      getStoneStatueEnchantsOrBuilderList() {
    return stoneStatueEnchants_;
  }
  /**
   * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
   */
  @java.lang.Override
  public int getStoneStatueEnchantsCount() {
    return stoneStatueEnchants_.size();
  }
  /**
   * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStoneStatueEnchantSlot getStoneStatueEnchants(int index) {
    return stoneStatueEnchants_.get(index);
  }
  /**
   * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder getStoneStatueEnchantsOrBuilder(
      int index) {
    return stoneStatueEnchants_.get(index);
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (orderId_ != 0) {
      output.writeInt32(1, orderId_);
    }
    for (int i = 0; i < stoneStatueEnchants_.size(); i++) {
      output.writeMessage(2, stoneStatueEnchants_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (orderId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, orderId_);
    }
    for (int i = 0; i < stoneStatueEnchants_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, stoneStatueEnchants_.get(i));
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.supercat.growstone.network.messages.TStoneStatueEnchant)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.TStoneStatueEnchant other = (com.supercat.growstone.network.messages.TStoneStatueEnchant) obj;

    if (getOrderId()
        != other.getOrderId()) return false;
    if (!getStoneStatueEnchantsList()
        .equals(other.getStoneStatueEnchantsList())) return false;
    if (!unknownFields.equals(other.unknownFields)) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + ORDER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getOrderId();
    if (getStoneStatueEnchantsCount() > 0) {
      hash = (37 * hash) + STONE_STATUE_ENCHANTS_FIELD_NUMBER;
      hash = (53 * hash) + getStoneStatueEnchantsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TStoneStatueEnchant parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.supercat.growstone.network.messages.TStoneStatueEnchant prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code TStoneStatueEnchant}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TStoneStatueEnchant)
      com.supercat.growstone.network.messages.TStoneStatueEnchantOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_TStoneStatueEnchant_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_TStoneStatueEnchant_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.TStoneStatueEnchant.class, com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.TStoneStatueEnchant.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
        getStoneStatueEnchantsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      orderId_ = 0;

      if (stoneStatueEnchantsBuilder_ == null) {
        stoneStatueEnchants_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        stoneStatueEnchantsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_TStoneStatueEnchant_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TStoneStatueEnchant getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.TStoneStatueEnchant.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TStoneStatueEnchant build() {
      com.supercat.growstone.network.messages.TStoneStatueEnchant result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TStoneStatueEnchant buildPartial() {
      com.supercat.growstone.network.messages.TStoneStatueEnchant result = new com.supercat.growstone.network.messages.TStoneStatueEnchant(this);
      int from_bitField0_ = bitField0_;
      result.orderId_ = orderId_;
      if (stoneStatueEnchantsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          stoneStatueEnchants_ = java.util.Collections.unmodifiableList(stoneStatueEnchants_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.stoneStatueEnchants_ = stoneStatueEnchants_;
      } else {
        result.stoneStatueEnchants_ = stoneStatueEnchantsBuilder_.build();
      }
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.supercat.growstone.network.messages.TStoneStatueEnchant) {
        return mergeFrom((com.supercat.growstone.network.messages.TStoneStatueEnchant)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.TStoneStatueEnchant other) {
      if (other == com.supercat.growstone.network.messages.TStoneStatueEnchant.getDefaultInstance()) return this;
      if (other.getOrderId() != 0) {
        setOrderId(other.getOrderId());
      }
      if (stoneStatueEnchantsBuilder_ == null) {
        if (!other.stoneStatueEnchants_.isEmpty()) {
          if (stoneStatueEnchants_.isEmpty()) {
            stoneStatueEnchants_ = other.stoneStatueEnchants_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureStoneStatueEnchantsIsMutable();
            stoneStatueEnchants_.addAll(other.stoneStatueEnchants_);
          }
          onChanged();
        }
      } else {
        if (!other.stoneStatueEnchants_.isEmpty()) {
          if (stoneStatueEnchantsBuilder_.isEmpty()) {
            stoneStatueEnchantsBuilder_.dispose();
            stoneStatueEnchantsBuilder_ = null;
            stoneStatueEnchants_ = other.stoneStatueEnchants_;
            bitField0_ = (bitField0_ & ~0x00000001);
            stoneStatueEnchantsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getStoneStatueEnchantsFieldBuilder() : null;
          } else {
            stoneStatueEnchantsBuilder_.addAllMessages(other.stoneStatueEnchants_);
          }
        }
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      com.supercat.growstone.network.messages.TStoneStatueEnchant parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.TStoneStatueEnchant) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int orderId_ ;
    /**
     * <code>int32 order_id = 1;</code>
     * @return The orderId.
     */
    @java.lang.Override
    public int getOrderId() {
      return orderId_;
    }
    /**
     * <code>int32 order_id = 1;</code>
     * @param value The orderId to set.
     * @return This builder for chaining.
     */
    public Builder setOrderId(int value) {
      
      orderId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 order_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearOrderId() {
      
      orderId_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot> stoneStatueEnchants_ =
      java.util.Collections.emptyList();
    private void ensureStoneStatueEnchantsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        stoneStatueEnchants_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot>(stoneStatueEnchants_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TStoneStatueEnchantSlot, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder, com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder> stoneStatueEnchantsBuilder_;

    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot> getStoneStatueEnchantsList() {
      if (stoneStatueEnchantsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(stoneStatueEnchants_);
      } else {
        return stoneStatueEnchantsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public int getStoneStatueEnchantsCount() {
      if (stoneStatueEnchantsBuilder_ == null) {
        return stoneStatueEnchants_.size();
      } else {
        return stoneStatueEnchantsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchantSlot getStoneStatueEnchants(int index) {
      if (stoneStatueEnchantsBuilder_ == null) {
        return stoneStatueEnchants_.get(index);
      } else {
        return stoneStatueEnchantsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder setStoneStatueEnchants(
        int index, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot value) {
      if (stoneStatueEnchantsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.set(index, value);
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder setStoneStatueEnchants(
        int index, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder builderForValue) {
      if (stoneStatueEnchantsBuilder_ == null) {
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.set(index, builderForValue.build());
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder addStoneStatueEnchants(com.supercat.growstone.network.messages.TStoneStatueEnchantSlot value) {
      if (stoneStatueEnchantsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.add(value);
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder addStoneStatueEnchants(
        int index, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot value) {
      if (stoneStatueEnchantsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.add(index, value);
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder addStoneStatueEnchants(
        com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder builderForValue) {
      if (stoneStatueEnchantsBuilder_ == null) {
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.add(builderForValue.build());
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder addStoneStatueEnchants(
        int index, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder builderForValue) {
      if (stoneStatueEnchantsBuilder_ == null) {
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.add(index, builderForValue.build());
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder addAllStoneStatueEnchants(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TStoneStatueEnchantSlot> values) {
      if (stoneStatueEnchantsBuilder_ == null) {
        ensureStoneStatueEnchantsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, stoneStatueEnchants_);
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder clearStoneStatueEnchants() {
      if (stoneStatueEnchantsBuilder_ == null) {
        stoneStatueEnchants_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public Builder removeStoneStatueEnchants(int index) {
      if (stoneStatueEnchantsBuilder_ == null) {
        ensureStoneStatueEnchantsIsMutable();
        stoneStatueEnchants_.remove(index);
        onChanged();
      } else {
        stoneStatueEnchantsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder getStoneStatueEnchantsBuilder(
        int index) {
      return getStoneStatueEnchantsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder getStoneStatueEnchantsOrBuilder(
        int index) {
      if (stoneStatueEnchantsBuilder_ == null) {
        return stoneStatueEnchants_.get(index);  } else {
        return stoneStatueEnchantsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder> 
         getStoneStatueEnchantsOrBuilderList() {
      if (stoneStatueEnchantsBuilder_ != null) {
        return stoneStatueEnchantsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(stoneStatueEnchants_);
      }
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder addStoneStatueEnchantsBuilder() {
      return getStoneStatueEnchantsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.getDefaultInstance());
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder addStoneStatueEnchantsBuilder(
        int index) {
      return getStoneStatueEnchantsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.getDefaultInstance());
    }
    /**
     * <code>repeated .TStoneStatueEnchantSlot stone_statue_enchants = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder> 
         getStoneStatueEnchantsBuilderList() {
      return getStoneStatueEnchantsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TStoneStatueEnchantSlot, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder, com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder> 
        getStoneStatueEnchantsFieldBuilder() {
      if (stoneStatueEnchantsBuilder_ == null) {
        stoneStatueEnchantsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TStoneStatueEnchantSlot, com.supercat.growstone.network.messages.TStoneStatueEnchantSlot.Builder, com.supercat.growstone.network.messages.TStoneStatueEnchantSlotOrBuilder>(
                stoneStatueEnchants_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        stoneStatueEnchants_ = null;
      }
      return stoneStatueEnchantsBuilder_;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:TStoneStatueEnchant)
  }

  // @@protoc_insertion_point(class_scope:TStoneStatueEnchant)
  private static final com.supercat.growstone.network.messages.TStoneStatueEnchant DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.TStoneStatueEnchant();
  }

  public static com.supercat.growstone.network.messages.TStoneStatueEnchant getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TStoneStatueEnchant>
      PARSER = new com.google.protobuf.AbstractParser<TStoneStatueEnchant>() {
    @java.lang.Override
    public TStoneStatueEnchant parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TStoneStatueEnchant(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TStoneStatueEnchant> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TStoneStatueEnchant> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.TStoneStatueEnchant getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

