// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code TPickUpGacha}
 */
public final class TPickUpGacha extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TPickUpGacha)
    TPickUpGachaOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TPickUpGacha.newBuilder() to construct.
  private TPickUpGacha(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TPickUpGacha() {
    getRewards_ = emptyIntList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TPickUpGacha();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TPickUpGacha(
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

            point_ = input.readInt32();
            break;
          }
          case 16: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              getRewards_ = newIntList();
              mutable_bitField0_ |= 0x00000001;
            }
            getRewards_.addInt(input.readInt32());
            break;
          }
          case 18: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              getRewards_ = newIntList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              getRewards_.addInt(input.readInt32());
            }
            input.popLimit(limit);
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
        getRewards_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_TPickUpGacha_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_TPickUpGacha_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.TPickUpGacha.class, com.supercat.growstone.network.messages.TPickUpGacha.Builder.class);
  }

  public static final int POINT_FIELD_NUMBER = 1;
  private int point_;
  /**
   * <code>int32 point = 1;</code>
   * @return The point.
   */
  @java.lang.Override
  public int getPoint() {
    return point_;
  }

  public static final int GET_REWARDS_FIELD_NUMBER = 2;
  private com.google.protobuf.Internal.IntList getRewards_;
  /**
   * <code>repeated int32 get_rewards = 2;</code>
   * @return A list containing the getRewards.
   */
  @java.lang.Override
  public java.util.List<java.lang.Integer>
      getGetRewardsList() {
    return getRewards_;
  }
  /**
   * <code>repeated int32 get_rewards = 2;</code>
   * @return The count of getRewards.
   */
  public int getGetRewardsCount() {
    return getRewards_.size();
  }
  /**
   * <code>repeated int32 get_rewards = 2;</code>
   * @param index The index of the element to return.
   * @return The getRewards at the given index.
   */
  public int getGetRewards(int index) {
    return getRewards_.getInt(index);
  }
  private int getRewardsMemoizedSerializedSize = -1;

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
    getSerializedSize();
    if (point_ != 0) {
      output.writeInt32(1, point_);
    }
    if (getGetRewardsList().size() > 0) {
      output.writeUInt32NoTag(18);
      output.writeUInt32NoTag(getRewardsMemoizedSerializedSize);
    }
    for (int i = 0; i < getRewards_.size(); i++) {
      output.writeInt32NoTag(getRewards_.getInt(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (point_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, point_);
    }
    {
      int dataSize = 0;
      for (int i = 0; i < getRewards_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt32SizeNoTag(getRewards_.getInt(i));
      }
      size += dataSize;
      if (!getGetRewardsList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      getRewardsMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof com.supercat.growstone.network.messages.TPickUpGacha)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.TPickUpGacha other = (com.supercat.growstone.network.messages.TPickUpGacha) obj;

    if (getPoint()
        != other.getPoint()) return false;
    if (!getGetRewardsList()
        .equals(other.getGetRewardsList())) return false;
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
    hash = (37 * hash) + POINT_FIELD_NUMBER;
    hash = (53 * hash) + getPoint();
    if (getGetRewardsCount() > 0) {
      hash = (37 * hash) + GET_REWARDS_FIELD_NUMBER;
      hash = (53 * hash) + getGetRewardsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TPickUpGacha parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.TPickUpGacha prototype) {
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
   * Protobuf type {@code TPickUpGacha}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TPickUpGacha)
      com.supercat.growstone.network.messages.TPickUpGachaOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_TPickUpGacha_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_TPickUpGacha_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.TPickUpGacha.class, com.supercat.growstone.network.messages.TPickUpGacha.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.TPickUpGacha.newBuilder()
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
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      point_ = 0;

      getRewards_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_TPickUpGacha_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TPickUpGacha getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.TPickUpGacha.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TPickUpGacha build() {
      com.supercat.growstone.network.messages.TPickUpGacha result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TPickUpGacha buildPartial() {
      com.supercat.growstone.network.messages.TPickUpGacha result = new com.supercat.growstone.network.messages.TPickUpGacha(this);
      int from_bitField0_ = bitField0_;
      result.point_ = point_;
      if (((bitField0_ & 0x00000001) != 0)) {
        getRewards_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.getRewards_ = getRewards_;
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
      if (other instanceof com.supercat.growstone.network.messages.TPickUpGacha) {
        return mergeFrom((com.supercat.growstone.network.messages.TPickUpGacha)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.TPickUpGacha other) {
      if (other == com.supercat.growstone.network.messages.TPickUpGacha.getDefaultInstance()) return this;
      if (other.getPoint() != 0) {
        setPoint(other.getPoint());
      }
      if (!other.getRewards_.isEmpty()) {
        if (getRewards_.isEmpty()) {
          getRewards_ = other.getRewards_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureGetRewardsIsMutable();
          getRewards_.addAll(other.getRewards_);
        }
        onChanged();
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
      com.supercat.growstone.network.messages.TPickUpGacha parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.TPickUpGacha) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int point_ ;
    /**
     * <code>int32 point = 1;</code>
     * @return The point.
     */
    @java.lang.Override
    public int getPoint() {
      return point_;
    }
    /**
     * <code>int32 point = 1;</code>
     * @param value The point to set.
     * @return This builder for chaining.
     */
    public Builder setPoint(int value) {
      
      point_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 point = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPoint() {
      
      point_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Internal.IntList getRewards_ = emptyIntList();
    private void ensureGetRewardsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        getRewards_ = mutableCopy(getRewards_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @return A list containing the getRewards.
     */
    public java.util.List<java.lang.Integer>
        getGetRewardsList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(getRewards_) : getRewards_;
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @return The count of getRewards.
     */
    public int getGetRewardsCount() {
      return getRewards_.size();
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @param index The index of the element to return.
     * @return The getRewards at the given index.
     */
    public int getGetRewards(int index) {
      return getRewards_.getInt(index);
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @param index The index to set the value at.
     * @param value The getRewards to set.
     * @return This builder for chaining.
     */
    public Builder setGetRewards(
        int index, int value) {
      ensureGetRewardsIsMutable();
      getRewards_.setInt(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @param value The getRewards to add.
     * @return This builder for chaining.
     */
    public Builder addGetRewards(int value) {
      ensureGetRewardsIsMutable();
      getRewards_.addInt(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @param values The getRewards to add.
     * @return This builder for chaining.
     */
    public Builder addAllGetRewards(
        java.lang.Iterable<? extends java.lang.Integer> values) {
      ensureGetRewardsIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, getRewards_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int32 get_rewards = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearGetRewards() {
      getRewards_ = emptyIntList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:TPickUpGacha)
  }

  // @@protoc_insertion_point(class_scope:TPickUpGacha)
  private static final com.supercat.growstone.network.messages.TPickUpGacha DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.TPickUpGacha();
  }

  public static com.supercat.growstone.network.messages.TPickUpGacha getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TPickUpGacha>
      PARSER = new com.google.protobuf.AbstractParser<TPickUpGacha>() {
    @java.lang.Override
    public TPickUpGacha parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TPickUpGacha(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TPickUpGacha> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TPickUpGacha> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.TPickUpGacha getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

