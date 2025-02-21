// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZPlayerDailyContentResponse}
 */
public final class ZPlayerDailyContentResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZPlayerDailyContentResponse)
    ZPlayerDailyContentResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZPlayerDailyContentResponse.newBuilder() to construct.
  private ZPlayerDailyContentResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZPlayerDailyContentResponse() {
    dailyContents_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZPlayerDailyContentResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZPlayerDailyContentResponse(
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
              dailyContents_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TPlayerDailyContent>();
              mutable_bitField0_ |= 0x00000001;
            }
            dailyContents_.add(
                input.readMessage(com.supercat.growstone.network.messages.TPlayerDailyContent.parser(), extensionRegistry));
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
        dailyContents_ = java.util.Collections.unmodifiableList(dailyContents_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerDailyContentResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerDailyContentResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.class, com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.Builder.class);
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

  public static final int DAILY_CONTENTS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TPlayerDailyContent> dailyContents_;
  /**
   * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TPlayerDailyContent> getDailyContentsList() {
    return dailyContents_;
  }
  /**
   * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder> 
      getDailyContentsOrBuilderList() {
    return dailyContents_;
  }
  /**
   * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
   */
  @java.lang.Override
  public int getDailyContentsCount() {
    return dailyContents_.size();
  }
  /**
   * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPlayerDailyContent getDailyContents(int index) {
    return dailyContents_.get(index);
  }
  /**
   * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder getDailyContentsOrBuilder(
      int index) {
    return dailyContents_.get(index);
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
    for (int i = 0; i < dailyContents_.size(); i++) {
      output.writeMessage(2, dailyContents_.get(i));
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
    for (int i = 0; i < dailyContents_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, dailyContents_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZPlayerDailyContentResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZPlayerDailyContentResponse other = (com.supercat.growstone.network.messages.ZPlayerDailyContentResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getDailyContentsList()
        .equals(other.getDailyContentsList())) return false;
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
    if (getDailyContentsCount() > 0) {
      hash = (37 * hash) + DAILY_CONTENTS_FIELD_NUMBER;
      hash = (53 * hash) + getDailyContentsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZPlayerDailyContentResponse prototype) {
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
   * Protobuf type {@code ZPlayerDailyContentResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZPlayerDailyContentResponse)
      com.supercat.growstone.network.messages.ZPlayerDailyContentResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerDailyContentResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerDailyContentResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.class, com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.newBuilder()
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
        getDailyContentsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (dailyContentsBuilder_ == null) {
        dailyContents_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        dailyContentsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerDailyContentResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerDailyContentResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerDailyContentResponse build() {
      com.supercat.growstone.network.messages.ZPlayerDailyContentResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerDailyContentResponse buildPartial() {
      com.supercat.growstone.network.messages.ZPlayerDailyContentResponse result = new com.supercat.growstone.network.messages.ZPlayerDailyContentResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (dailyContentsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          dailyContents_ = java.util.Collections.unmodifiableList(dailyContents_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.dailyContents_ = dailyContents_;
      } else {
        result.dailyContents_ = dailyContentsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZPlayerDailyContentResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZPlayerDailyContentResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZPlayerDailyContentResponse other) {
      if (other == com.supercat.growstone.network.messages.ZPlayerDailyContentResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (dailyContentsBuilder_ == null) {
        if (!other.dailyContents_.isEmpty()) {
          if (dailyContents_.isEmpty()) {
            dailyContents_ = other.dailyContents_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureDailyContentsIsMutable();
            dailyContents_.addAll(other.dailyContents_);
          }
          onChanged();
        }
      } else {
        if (!other.dailyContents_.isEmpty()) {
          if (dailyContentsBuilder_.isEmpty()) {
            dailyContentsBuilder_.dispose();
            dailyContentsBuilder_ = null;
            dailyContents_ = other.dailyContents_;
            bitField0_ = (bitField0_ & ~0x00000001);
            dailyContentsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getDailyContentsFieldBuilder() : null;
          } else {
            dailyContentsBuilder_.addAllMessages(other.dailyContents_);
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
      com.supercat.growstone.network.messages.ZPlayerDailyContentResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZPlayerDailyContentResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TPlayerDailyContent> dailyContents_ =
      java.util.Collections.emptyList();
    private void ensureDailyContentsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        dailyContents_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TPlayerDailyContent>(dailyContents_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TPlayerDailyContent, com.supercat.growstone.network.messages.TPlayerDailyContent.Builder, com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder> dailyContentsBuilder_;

    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TPlayerDailyContent> getDailyContentsList() {
      if (dailyContentsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(dailyContents_);
      } else {
        return dailyContentsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public int getDailyContentsCount() {
      if (dailyContentsBuilder_ == null) {
        return dailyContents_.size();
      } else {
        return dailyContentsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerDailyContent getDailyContents(int index) {
      if (dailyContentsBuilder_ == null) {
        return dailyContents_.get(index);
      } else {
        return dailyContentsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder setDailyContents(
        int index, com.supercat.growstone.network.messages.TPlayerDailyContent value) {
      if (dailyContentsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDailyContentsIsMutable();
        dailyContents_.set(index, value);
        onChanged();
      } else {
        dailyContentsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder setDailyContents(
        int index, com.supercat.growstone.network.messages.TPlayerDailyContent.Builder builderForValue) {
      if (dailyContentsBuilder_ == null) {
        ensureDailyContentsIsMutable();
        dailyContents_.set(index, builderForValue.build());
        onChanged();
      } else {
        dailyContentsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder addDailyContents(com.supercat.growstone.network.messages.TPlayerDailyContent value) {
      if (dailyContentsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDailyContentsIsMutable();
        dailyContents_.add(value);
        onChanged();
      } else {
        dailyContentsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder addDailyContents(
        int index, com.supercat.growstone.network.messages.TPlayerDailyContent value) {
      if (dailyContentsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureDailyContentsIsMutable();
        dailyContents_.add(index, value);
        onChanged();
      } else {
        dailyContentsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder addDailyContents(
        com.supercat.growstone.network.messages.TPlayerDailyContent.Builder builderForValue) {
      if (dailyContentsBuilder_ == null) {
        ensureDailyContentsIsMutable();
        dailyContents_.add(builderForValue.build());
        onChanged();
      } else {
        dailyContentsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder addDailyContents(
        int index, com.supercat.growstone.network.messages.TPlayerDailyContent.Builder builderForValue) {
      if (dailyContentsBuilder_ == null) {
        ensureDailyContentsIsMutable();
        dailyContents_.add(index, builderForValue.build());
        onChanged();
      } else {
        dailyContentsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder addAllDailyContents(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TPlayerDailyContent> values) {
      if (dailyContentsBuilder_ == null) {
        ensureDailyContentsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, dailyContents_);
        onChanged();
      } else {
        dailyContentsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder clearDailyContents() {
      if (dailyContentsBuilder_ == null) {
        dailyContents_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        dailyContentsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public Builder removeDailyContents(int index) {
      if (dailyContentsBuilder_ == null) {
        ensureDailyContentsIsMutable();
        dailyContents_.remove(index);
        onChanged();
      } else {
        dailyContentsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerDailyContent.Builder getDailyContentsBuilder(
        int index) {
      return getDailyContentsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder getDailyContentsOrBuilder(
        int index) {
      if (dailyContentsBuilder_ == null) {
        return dailyContents_.get(index);  } else {
        return dailyContentsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder> 
         getDailyContentsOrBuilderList() {
      if (dailyContentsBuilder_ != null) {
        return dailyContentsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(dailyContents_);
      }
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerDailyContent.Builder addDailyContentsBuilder() {
      return getDailyContentsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TPlayerDailyContent.getDefaultInstance());
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerDailyContent.Builder addDailyContentsBuilder(
        int index) {
      return getDailyContentsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TPlayerDailyContent.getDefaultInstance());
    }
    /**
     * <code>repeated .TPlayerDailyContent daily_contents = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TPlayerDailyContent.Builder> 
         getDailyContentsBuilderList() {
      return getDailyContentsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TPlayerDailyContent, com.supercat.growstone.network.messages.TPlayerDailyContent.Builder, com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder> 
        getDailyContentsFieldBuilder() {
      if (dailyContentsBuilder_ == null) {
        dailyContentsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TPlayerDailyContent, com.supercat.growstone.network.messages.TPlayerDailyContent.Builder, com.supercat.growstone.network.messages.TPlayerDailyContentOrBuilder>(
                dailyContents_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        dailyContents_ = null;
      }
      return dailyContentsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZPlayerDailyContentResponse)
  }

  // @@protoc_insertion_point(class_scope:ZPlayerDailyContentResponse)
  private static final com.supercat.growstone.network.messages.ZPlayerDailyContentResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZPlayerDailyContentResponse();
  }

  public static com.supercat.growstone.network.messages.ZPlayerDailyContentResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZPlayerDailyContentResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZPlayerDailyContentResponse>() {
    @java.lang.Override
    public ZPlayerDailyContentResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZPlayerDailyContentResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZPlayerDailyContentResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZPlayerDailyContentResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZPlayerDailyContentResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

