// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZStatsResponse}
 */
public final class ZStatsResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZStatsResponse)
    ZStatsResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZStatsResponse.newBuilder() to construct.
  private ZStatsResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZStatsResponse() {
    stats_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZStatsResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZStatsResponse(
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

            status_ = input.readInt32();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              stats_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TStat>();
              mutable_bitField0_ |= 0x00000001;
            }
            stats_.add(
                input.readMessage(com.supercat.growstone.network.messages.TStat.parser(), extensionRegistry));
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
        stats_ = java.util.Collections.unmodifiableList(stats_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZStatsResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZStatsResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZStatsResponse.class, com.supercat.growstone.network.messages.ZStatsResponse.Builder.class);
  }

  public static final int STATUS_FIELD_NUMBER = 1;
  private int status_;
  /**
   * <code>int32 status = 1;</code>
   * @return The status.
   */
  @java.lang.Override
  public int getStatus() {
    return status_;
  }

  public static final int STATS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TStat> stats_;
  /**
   * <code>repeated .TStat stats = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TStat> getStatsList() {
    return stats_;
  }
  /**
   * <code>repeated .TStat stats = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TStatOrBuilder> 
      getStatsOrBuilderList() {
    return stats_;
  }
  /**
   * <code>repeated .TStat stats = 2;</code>
   */
  @java.lang.Override
  public int getStatsCount() {
    return stats_.size();
  }
  /**
   * <code>repeated .TStat stats = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStat getStats(int index) {
    return stats_.get(index);
  }
  /**
   * <code>repeated .TStat stats = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStatOrBuilder getStatsOrBuilder(
      int index) {
    return stats_.get(index);
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
    if (status_ != 0) {
      output.writeInt32(1, status_);
    }
    for (int i = 0; i < stats_.size(); i++) {
      output.writeMessage(2, stats_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (status_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, status_);
    }
    for (int i = 0; i < stats_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, stats_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZStatsResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZStatsResponse other = (com.supercat.growstone.network.messages.ZStatsResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getStatsList()
        .equals(other.getStatsList())) return false;
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
    hash = (37 * hash) + STATUS_FIELD_NUMBER;
    hash = (53 * hash) + getStatus();
    if (getStatsCount() > 0) {
      hash = (37 * hash) + STATS_FIELD_NUMBER;
      hash = (53 * hash) + getStatsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZStatsResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZStatsResponse prototype) {
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
   * Protobuf type {@code ZStatsResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZStatsResponse)
      com.supercat.growstone.network.messages.ZStatsResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZStatsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZStatsResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZStatsResponse.class, com.supercat.growstone.network.messages.ZStatsResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZStatsResponse.newBuilder()
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
        getStatsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (statsBuilder_ == null) {
        stats_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        statsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZStatsResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZStatsResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZStatsResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZStatsResponse build() {
      com.supercat.growstone.network.messages.ZStatsResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZStatsResponse buildPartial() {
      com.supercat.growstone.network.messages.ZStatsResponse result = new com.supercat.growstone.network.messages.ZStatsResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (statsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          stats_ = java.util.Collections.unmodifiableList(stats_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.stats_ = stats_;
      } else {
        result.stats_ = statsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZStatsResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZStatsResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZStatsResponse other) {
      if (other == com.supercat.growstone.network.messages.ZStatsResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (statsBuilder_ == null) {
        if (!other.stats_.isEmpty()) {
          if (stats_.isEmpty()) {
            stats_ = other.stats_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureStatsIsMutable();
            stats_.addAll(other.stats_);
          }
          onChanged();
        }
      } else {
        if (!other.stats_.isEmpty()) {
          if (statsBuilder_.isEmpty()) {
            statsBuilder_.dispose();
            statsBuilder_ = null;
            stats_ = other.stats_;
            bitField0_ = (bitField0_ & ~0x00000001);
            statsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getStatsFieldBuilder() : null;
          } else {
            statsBuilder_.addAllMessages(other.stats_);
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
      com.supercat.growstone.network.messages.ZStatsResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZStatsResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int status_ ;
    /**
     * <code>int32 status = 1;</code>
     * @return The status.
     */
    @java.lang.Override
    public int getStatus() {
      return status_;
    }
    /**
     * <code>int32 status = 1;</code>
     * @param value The status to set.
     * @return This builder for chaining.
     */
    public Builder setStatus(int value) {
      
      status_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 status = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearStatus() {
      
      status_ = 0;
      onChanged();
      return this;
    }

    private java.util.List<com.supercat.growstone.network.messages.TStat> stats_ =
      java.util.Collections.emptyList();
    private void ensureStatsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        stats_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TStat>(stats_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TStat, com.supercat.growstone.network.messages.TStat.Builder, com.supercat.growstone.network.messages.TStatOrBuilder> statsBuilder_;

    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TStat> getStatsList() {
      if (statsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(stats_);
      } else {
        return statsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public int getStatsCount() {
      if (statsBuilder_ == null) {
        return stats_.size();
      } else {
        return statsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStat getStats(int index) {
      if (statsBuilder_ == null) {
        return stats_.get(index);
      } else {
        return statsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder setStats(
        int index, com.supercat.growstone.network.messages.TStat value) {
      if (statsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStatsIsMutable();
        stats_.set(index, value);
        onChanged();
      } else {
        statsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder setStats(
        int index, com.supercat.growstone.network.messages.TStat.Builder builderForValue) {
      if (statsBuilder_ == null) {
        ensureStatsIsMutable();
        stats_.set(index, builderForValue.build());
        onChanged();
      } else {
        statsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder addStats(com.supercat.growstone.network.messages.TStat value) {
      if (statsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStatsIsMutable();
        stats_.add(value);
        onChanged();
      } else {
        statsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder addStats(
        int index, com.supercat.growstone.network.messages.TStat value) {
      if (statsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStatsIsMutable();
        stats_.add(index, value);
        onChanged();
      } else {
        statsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder addStats(
        com.supercat.growstone.network.messages.TStat.Builder builderForValue) {
      if (statsBuilder_ == null) {
        ensureStatsIsMutable();
        stats_.add(builderForValue.build());
        onChanged();
      } else {
        statsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder addStats(
        int index, com.supercat.growstone.network.messages.TStat.Builder builderForValue) {
      if (statsBuilder_ == null) {
        ensureStatsIsMutable();
        stats_.add(index, builderForValue.build());
        onChanged();
      } else {
        statsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder addAllStats(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TStat> values) {
      if (statsBuilder_ == null) {
        ensureStatsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, stats_);
        onChanged();
      } else {
        statsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder clearStats() {
      if (statsBuilder_ == null) {
        stats_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        statsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public Builder removeStats(int index) {
      if (statsBuilder_ == null) {
        ensureStatsIsMutable();
        stats_.remove(index);
        onChanged();
      } else {
        statsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStat.Builder getStatsBuilder(
        int index) {
      return getStatsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatOrBuilder getStatsOrBuilder(
        int index) {
      if (statsBuilder_ == null) {
        return stats_.get(index);  } else {
        return statsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TStatOrBuilder> 
         getStatsOrBuilderList() {
      if (statsBuilder_ != null) {
        return statsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(stats_);
      }
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStat.Builder addStatsBuilder() {
      return getStatsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TStat.getDefaultInstance());
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStat.Builder addStatsBuilder(
        int index) {
      return getStatsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TStat.getDefaultInstance());
    }
    /**
     * <code>repeated .TStat stats = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TStat.Builder> 
         getStatsBuilderList() {
      return getStatsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TStat, com.supercat.growstone.network.messages.TStat.Builder, com.supercat.growstone.network.messages.TStatOrBuilder> 
        getStatsFieldBuilder() {
      if (statsBuilder_ == null) {
        statsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TStat, com.supercat.growstone.network.messages.TStat.Builder, com.supercat.growstone.network.messages.TStatOrBuilder>(
                stats_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        stats_ = null;
      }
      return statsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZStatsResponse)
  }

  // @@protoc_insertion_point(class_scope:ZStatsResponse)
  private static final com.supercat.growstone.network.messages.ZStatsResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZStatsResponse();
  }

  public static com.supercat.growstone.network.messages.ZStatsResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZStatsResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZStatsResponse>() {
    @java.lang.Override
    public ZStatsResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZStatsResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZStatsResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZStatsResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZStatsResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

