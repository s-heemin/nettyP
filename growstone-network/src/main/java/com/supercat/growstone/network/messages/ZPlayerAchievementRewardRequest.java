// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZPlayerAchievementRewardRequest}
 */
public final class ZPlayerAchievementRewardRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZPlayerAchievementRewardRequest)
    ZPlayerAchievementRewardRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZPlayerAchievementRewardRequest.newBuilder() to construct.
  private ZPlayerAchievementRewardRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZPlayerAchievementRewardRequest() {
    achievementId_ = emptyLongList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZPlayerAchievementRewardRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZPlayerAchievementRewardRequest(
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
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              achievementId_ = newLongList();
              mutable_bitField0_ |= 0x00000001;
            }
            achievementId_.addLong(input.readInt64());
            break;
          }
          case 10: {
            int length = input.readRawVarint32();
            int limit = input.pushLimit(length);
            if (!((mutable_bitField0_ & 0x00000001) != 0) && input.getBytesUntilLimit() > 0) {
              achievementId_ = newLongList();
              mutable_bitField0_ |= 0x00000001;
            }
            while (input.getBytesUntilLimit() > 0) {
              achievementId_.addLong(input.readInt64());
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
        achievementId_.makeImmutable(); // C
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerAchievementRewardRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerAchievementRewardRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.class, com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.Builder.class);
  }

  public static final int ACHIEVEMENT_ID_FIELD_NUMBER = 1;
  private com.google.protobuf.Internal.LongList achievementId_;
  /**
   * <code>repeated int64 achievement_id = 1;</code>
   * @return A list containing the achievementId.
   */
  @java.lang.Override
  public java.util.List<java.lang.Long>
      getAchievementIdList() {
    return achievementId_;
  }
  /**
   * <code>repeated int64 achievement_id = 1;</code>
   * @return The count of achievementId.
   */
  public int getAchievementIdCount() {
    return achievementId_.size();
  }
  /**
   * <code>repeated int64 achievement_id = 1;</code>
   * @param index The index of the element to return.
   * @return The achievementId at the given index.
   */
  public long getAchievementId(int index) {
    return achievementId_.getLong(index);
  }
  private int achievementIdMemoizedSerializedSize = -1;

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
    if (getAchievementIdList().size() > 0) {
      output.writeUInt32NoTag(10);
      output.writeUInt32NoTag(achievementIdMemoizedSerializedSize);
    }
    for (int i = 0; i < achievementId_.size(); i++) {
      output.writeInt64NoTag(achievementId_.getLong(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < achievementId_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream
          .computeInt64SizeNoTag(achievementId_.getLong(i));
      }
      size += dataSize;
      if (!getAchievementIdList().isEmpty()) {
        size += 1;
        size += com.google.protobuf.CodedOutputStream
            .computeInt32SizeNoTag(dataSize);
      }
      achievementIdMemoizedSerializedSize = dataSize;
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest other = (com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest) obj;

    if (!getAchievementIdList()
        .equals(other.getAchievementIdList())) return false;
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
    if (getAchievementIdCount() > 0) {
      hash = (37 * hash) + ACHIEVEMENT_ID_FIELD_NUMBER;
      hash = (53 * hash) + getAchievementIdList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest prototype) {
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
   * Protobuf type {@code ZPlayerAchievementRewardRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZPlayerAchievementRewardRequest)
      com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerAchievementRewardRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerAchievementRewardRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.class, com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.newBuilder()
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
      achievementId_ = emptyLongList();
      bitField0_ = (bitField0_ & ~0x00000001);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerAchievementRewardRequest_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest build() {
      com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest buildPartial() {
      com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest result = new com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest(this);
      int from_bitField0_ = bitField0_;
      if (((bitField0_ & 0x00000001) != 0)) {
        achievementId_.makeImmutable();
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.achievementId_ = achievementId_;
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
      if (other instanceof com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest) {
        return mergeFrom((com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest other) {
      if (other == com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest.getDefaultInstance()) return this;
      if (!other.achievementId_.isEmpty()) {
        if (achievementId_.isEmpty()) {
          achievementId_ = other.achievementId_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureAchievementIdIsMutable();
          achievementId_.addAll(other.achievementId_);
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
      com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private com.google.protobuf.Internal.LongList achievementId_ = emptyLongList();
    private void ensureAchievementIdIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        achievementId_ = mutableCopy(achievementId_);
        bitField0_ |= 0x00000001;
       }
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @return A list containing the achievementId.
     */
    public java.util.List<java.lang.Long>
        getAchievementIdList() {
      return ((bitField0_ & 0x00000001) != 0) ?
               java.util.Collections.unmodifiableList(achievementId_) : achievementId_;
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @return The count of achievementId.
     */
    public int getAchievementIdCount() {
      return achievementId_.size();
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @param index The index of the element to return.
     * @return The achievementId at the given index.
     */
    public long getAchievementId(int index) {
      return achievementId_.getLong(index);
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @param index The index to set the value at.
     * @param value The achievementId to set.
     * @return This builder for chaining.
     */
    public Builder setAchievementId(
        int index, long value) {
      ensureAchievementIdIsMutable();
      achievementId_.setLong(index, value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @param value The achievementId to add.
     * @return This builder for chaining.
     */
    public Builder addAchievementId(long value) {
      ensureAchievementIdIsMutable();
      achievementId_.addLong(value);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @param values The achievementId to add.
     * @return This builder for chaining.
     */
    public Builder addAllAchievementId(
        java.lang.Iterable<? extends java.lang.Long> values) {
      ensureAchievementIdIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(
          values, achievementId_);
      onChanged();
      return this;
    }
    /**
     * <code>repeated int64 achievement_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearAchievementId() {
      achievementId_ = emptyLongList();
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


    // @@protoc_insertion_point(builder_scope:ZPlayerAchievementRewardRequest)
  }

  // @@protoc_insertion_point(class_scope:ZPlayerAchievementRewardRequest)
  private static final com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest();
  }

  public static com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZPlayerAchievementRewardRequest>
      PARSER = new com.google.protobuf.AbstractParser<ZPlayerAchievementRewardRequest>() {
    @java.lang.Override
    public ZPlayerAchievementRewardRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZPlayerAchievementRewardRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZPlayerAchievementRewardRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZPlayerAchievementRewardRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZPlayerAchievementRewardRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

