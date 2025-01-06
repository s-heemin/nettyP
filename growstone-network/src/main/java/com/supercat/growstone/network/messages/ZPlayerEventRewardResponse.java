// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZPlayerEventRewardResponse}
 */
public final class ZPlayerEventRewardResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZPlayerEventRewardResponse)
    ZPlayerEventRewardResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZPlayerEventRewardResponse.newBuilder() to construct.
  private ZPlayerEventRewardResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZPlayerEventRewardResponse() {
    rewards_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZPlayerEventRewardResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZPlayerEventRewardResponse(
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
            com.supercat.growstone.network.messages.TPlayerEvent.Builder subBuilder = null;
            if (event_ != null) {
              subBuilder = event_.toBuilder();
            }
            event_ = input.readMessage(com.supercat.growstone.network.messages.TPlayerEvent.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(event_);
              event_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              rewards_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TContentReward>();
              mutable_bitField0_ |= 0x00000001;
            }
            rewards_.add(
                input.readMessage(com.supercat.growstone.network.messages.TContentReward.parser(), extensionRegistry));
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
        rewards_ = java.util.Collections.unmodifiableList(rewards_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerEventRewardResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerEventRewardResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.class, com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.Builder.class);
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

  public static final int EVENT_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TPlayerEvent event_;
  /**
   * <code>.TPlayerEvent event = 2;</code>
   * @return Whether the event field is set.
   */
  @java.lang.Override
  public boolean hasEvent() {
    return event_ != null;
  }
  /**
   * <code>.TPlayerEvent event = 2;</code>
   * @return The event.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPlayerEvent getEvent() {
    return event_ == null ? com.supercat.growstone.network.messages.TPlayerEvent.getDefaultInstance() : event_;
  }
  /**
   * <code>.TPlayerEvent event = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPlayerEventOrBuilder getEventOrBuilder() {
    return getEvent();
  }

  public static final int REWARDS_FIELD_NUMBER = 3;
  private java.util.List<com.supercat.growstone.network.messages.TContentReward> rewards_;
  /**
   * <code>repeated .TContentReward rewards = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TContentReward> getRewardsList() {
    return rewards_;
  }
  /**
   * <code>repeated .TContentReward rewards = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TContentRewardOrBuilder> 
      getRewardsOrBuilderList() {
    return rewards_;
  }
  /**
   * <code>repeated .TContentReward rewards = 3;</code>
   */
  @java.lang.Override
  public int getRewardsCount() {
    return rewards_.size();
  }
  /**
   * <code>repeated .TContentReward rewards = 3;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TContentReward getRewards(int index) {
    return rewards_.get(index);
  }
  /**
   * <code>repeated .TContentReward rewards = 3;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TContentRewardOrBuilder getRewardsOrBuilder(
      int index) {
    return rewards_.get(index);
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
    if (event_ != null) {
      output.writeMessage(2, getEvent());
    }
    for (int i = 0; i < rewards_.size(); i++) {
      output.writeMessage(3, rewards_.get(i));
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
    if (event_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getEvent());
    }
    for (int i = 0; i < rewards_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, rewards_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZPlayerEventRewardResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZPlayerEventRewardResponse other = (com.supercat.growstone.network.messages.ZPlayerEventRewardResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasEvent() != other.hasEvent()) return false;
    if (hasEvent()) {
      if (!getEvent()
          .equals(other.getEvent())) return false;
    }
    if (!getRewardsList()
        .equals(other.getRewardsList())) return false;
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
    if (hasEvent()) {
      hash = (37 * hash) + EVENT_FIELD_NUMBER;
      hash = (53 * hash) + getEvent().hashCode();
    }
    if (getRewardsCount() > 0) {
      hash = (37 * hash) + REWARDS_FIELD_NUMBER;
      hash = (53 * hash) + getRewardsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZPlayerEventRewardResponse prototype) {
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
   * Protobuf type {@code ZPlayerEventRewardResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZPlayerEventRewardResponse)
      com.supercat.growstone.network.messages.ZPlayerEventRewardResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerEventRewardResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerEventRewardResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.class, com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.newBuilder()
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
        getRewardsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (eventBuilder_ == null) {
        event_ = null;
      } else {
        event_ = null;
        eventBuilder_ = null;
      }
      if (rewardsBuilder_ == null) {
        rewards_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        rewardsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerEventRewardResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerEventRewardResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerEventRewardResponse build() {
      com.supercat.growstone.network.messages.ZPlayerEventRewardResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerEventRewardResponse buildPartial() {
      com.supercat.growstone.network.messages.ZPlayerEventRewardResponse result = new com.supercat.growstone.network.messages.ZPlayerEventRewardResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (eventBuilder_ == null) {
        result.event_ = event_;
      } else {
        result.event_ = eventBuilder_.build();
      }
      if (rewardsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          rewards_ = java.util.Collections.unmodifiableList(rewards_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.rewards_ = rewards_;
      } else {
        result.rewards_ = rewardsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZPlayerEventRewardResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZPlayerEventRewardResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZPlayerEventRewardResponse other) {
      if (other == com.supercat.growstone.network.messages.ZPlayerEventRewardResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasEvent()) {
        mergeEvent(other.getEvent());
      }
      if (rewardsBuilder_ == null) {
        if (!other.rewards_.isEmpty()) {
          if (rewards_.isEmpty()) {
            rewards_ = other.rewards_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureRewardsIsMutable();
            rewards_.addAll(other.rewards_);
          }
          onChanged();
        }
      } else {
        if (!other.rewards_.isEmpty()) {
          if (rewardsBuilder_.isEmpty()) {
            rewardsBuilder_.dispose();
            rewardsBuilder_ = null;
            rewards_ = other.rewards_;
            bitField0_ = (bitField0_ & ~0x00000001);
            rewardsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getRewardsFieldBuilder() : null;
          } else {
            rewardsBuilder_.addAllMessages(other.rewards_);
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
      com.supercat.growstone.network.messages.ZPlayerEventRewardResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZPlayerEventRewardResponse) e.getUnfinishedMessage();
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

    private com.supercat.growstone.network.messages.TPlayerEvent event_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TPlayerEvent, com.supercat.growstone.network.messages.TPlayerEvent.Builder, com.supercat.growstone.network.messages.TPlayerEventOrBuilder> eventBuilder_;
    /**
     * <code>.TPlayerEvent event = 2;</code>
     * @return Whether the event field is set.
     */
    public boolean hasEvent() {
      return eventBuilder_ != null || event_ != null;
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     * @return The event.
     */
    public com.supercat.growstone.network.messages.TPlayerEvent getEvent() {
      if (eventBuilder_ == null) {
        return event_ == null ? com.supercat.growstone.network.messages.TPlayerEvent.getDefaultInstance() : event_;
      } else {
        return eventBuilder_.getMessage();
      }
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    public Builder setEvent(com.supercat.growstone.network.messages.TPlayerEvent value) {
      if (eventBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        event_ = value;
        onChanged();
      } else {
        eventBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    public Builder setEvent(
        com.supercat.growstone.network.messages.TPlayerEvent.Builder builderForValue) {
      if (eventBuilder_ == null) {
        event_ = builderForValue.build();
        onChanged();
      } else {
        eventBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    public Builder mergeEvent(com.supercat.growstone.network.messages.TPlayerEvent value) {
      if (eventBuilder_ == null) {
        if (event_ != null) {
          event_ =
            com.supercat.growstone.network.messages.TPlayerEvent.newBuilder(event_).mergeFrom(value).buildPartial();
        } else {
          event_ = value;
        }
        onChanged();
      } else {
        eventBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    public Builder clearEvent() {
      if (eventBuilder_ == null) {
        event_ = null;
        onChanged();
      } else {
        event_ = null;
        eventBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerEvent.Builder getEventBuilder() {
      
      onChanged();
      return getEventFieldBuilder().getBuilder();
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerEventOrBuilder getEventOrBuilder() {
      if (eventBuilder_ != null) {
        return eventBuilder_.getMessageOrBuilder();
      } else {
        return event_ == null ?
            com.supercat.growstone.network.messages.TPlayerEvent.getDefaultInstance() : event_;
      }
    }
    /**
     * <code>.TPlayerEvent event = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TPlayerEvent, com.supercat.growstone.network.messages.TPlayerEvent.Builder, com.supercat.growstone.network.messages.TPlayerEventOrBuilder> 
        getEventFieldBuilder() {
      if (eventBuilder_ == null) {
        eventBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TPlayerEvent, com.supercat.growstone.network.messages.TPlayerEvent.Builder, com.supercat.growstone.network.messages.TPlayerEventOrBuilder>(
                getEvent(),
                getParentForChildren(),
                isClean());
        event_ = null;
      }
      return eventBuilder_;
    }

    private java.util.List<com.supercat.growstone.network.messages.TContentReward> rewards_ =
      java.util.Collections.emptyList();
    private void ensureRewardsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        rewards_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TContentReward>(rewards_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TContentReward, com.supercat.growstone.network.messages.TContentReward.Builder, com.supercat.growstone.network.messages.TContentRewardOrBuilder> rewardsBuilder_;

    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TContentReward> getRewardsList() {
      if (rewardsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(rewards_);
      } else {
        return rewardsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public int getRewardsCount() {
      if (rewardsBuilder_ == null) {
        return rewards_.size();
      } else {
        return rewardsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public com.supercat.growstone.network.messages.TContentReward getRewards(int index) {
      if (rewardsBuilder_ == null) {
        return rewards_.get(index);
      } else {
        return rewardsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder setRewards(
        int index, com.supercat.growstone.network.messages.TContentReward value) {
      if (rewardsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRewardsIsMutable();
        rewards_.set(index, value);
        onChanged();
      } else {
        rewardsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder setRewards(
        int index, com.supercat.growstone.network.messages.TContentReward.Builder builderForValue) {
      if (rewardsBuilder_ == null) {
        ensureRewardsIsMutable();
        rewards_.set(index, builderForValue.build());
        onChanged();
      } else {
        rewardsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder addRewards(com.supercat.growstone.network.messages.TContentReward value) {
      if (rewardsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRewardsIsMutable();
        rewards_.add(value);
        onChanged();
      } else {
        rewardsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder addRewards(
        int index, com.supercat.growstone.network.messages.TContentReward value) {
      if (rewardsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRewardsIsMutable();
        rewards_.add(index, value);
        onChanged();
      } else {
        rewardsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder addRewards(
        com.supercat.growstone.network.messages.TContentReward.Builder builderForValue) {
      if (rewardsBuilder_ == null) {
        ensureRewardsIsMutable();
        rewards_.add(builderForValue.build());
        onChanged();
      } else {
        rewardsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder addRewards(
        int index, com.supercat.growstone.network.messages.TContentReward.Builder builderForValue) {
      if (rewardsBuilder_ == null) {
        ensureRewardsIsMutable();
        rewards_.add(index, builderForValue.build());
        onChanged();
      } else {
        rewardsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder addAllRewards(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TContentReward> values) {
      if (rewardsBuilder_ == null) {
        ensureRewardsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, rewards_);
        onChanged();
      } else {
        rewardsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder clearRewards() {
      if (rewardsBuilder_ == null) {
        rewards_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        rewardsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public Builder removeRewards(int index) {
      if (rewardsBuilder_ == null) {
        ensureRewardsIsMutable();
        rewards_.remove(index);
        onChanged();
      } else {
        rewardsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public com.supercat.growstone.network.messages.TContentReward.Builder getRewardsBuilder(
        int index) {
      return getRewardsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public com.supercat.growstone.network.messages.TContentRewardOrBuilder getRewardsOrBuilder(
        int index) {
      if (rewardsBuilder_ == null) {
        return rewards_.get(index);  } else {
        return rewardsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TContentRewardOrBuilder> 
         getRewardsOrBuilderList() {
      if (rewardsBuilder_ != null) {
        return rewardsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(rewards_);
      }
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public com.supercat.growstone.network.messages.TContentReward.Builder addRewardsBuilder() {
      return getRewardsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TContentReward.getDefaultInstance());
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public com.supercat.growstone.network.messages.TContentReward.Builder addRewardsBuilder(
        int index) {
      return getRewardsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TContentReward.getDefaultInstance());
    }
    /**
     * <code>repeated .TContentReward rewards = 3;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TContentReward.Builder> 
         getRewardsBuilderList() {
      return getRewardsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TContentReward, com.supercat.growstone.network.messages.TContentReward.Builder, com.supercat.growstone.network.messages.TContentRewardOrBuilder> 
        getRewardsFieldBuilder() {
      if (rewardsBuilder_ == null) {
        rewardsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TContentReward, com.supercat.growstone.network.messages.TContentReward.Builder, com.supercat.growstone.network.messages.TContentRewardOrBuilder>(
                rewards_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        rewards_ = null;
      }
      return rewardsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZPlayerEventRewardResponse)
  }

  // @@protoc_insertion_point(class_scope:ZPlayerEventRewardResponse)
  private static final com.supercat.growstone.network.messages.ZPlayerEventRewardResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZPlayerEventRewardResponse();
  }

  public static com.supercat.growstone.network.messages.ZPlayerEventRewardResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZPlayerEventRewardResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZPlayerEventRewardResponse>() {
    @java.lang.Override
    public ZPlayerEventRewardResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZPlayerEventRewardResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZPlayerEventRewardResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZPlayerEventRewardResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZPlayerEventRewardResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

