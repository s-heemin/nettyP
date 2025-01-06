// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZTowerResponse}
 */
public final class ZTowerResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZTowerResponse)
    ZTowerResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZTowerResponse.newBuilder() to construct.
  private ZTowerResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZTowerResponse() {
    tower_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZTowerResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZTowerResponse(
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
              tower_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TTower>();
              mutable_bitField0_ |= 0x00000001;
            }
            tower_.add(
                input.readMessage(com.supercat.growstone.network.messages.TTower.parser(), extensionRegistry));
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
        tower_ = java.util.Collections.unmodifiableList(tower_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZTowerResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZTowerResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZTowerResponse.class, com.supercat.growstone.network.messages.ZTowerResponse.Builder.class);
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

  public static final int TOWER_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TTower> tower_;
  /**
   * <code>repeated .TTower tower = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TTower> getTowerList() {
    return tower_;
  }
  /**
   * <code>repeated .TTower tower = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TTowerOrBuilder> 
      getTowerOrBuilderList() {
    return tower_;
  }
  /**
   * <code>repeated .TTower tower = 2;</code>
   */
  @java.lang.Override
  public int getTowerCount() {
    return tower_.size();
  }
  /**
   * <code>repeated .TTower tower = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TTower getTower(int index) {
    return tower_.get(index);
  }
  /**
   * <code>repeated .TTower tower = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TTowerOrBuilder getTowerOrBuilder(
      int index) {
    return tower_.get(index);
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
    for (int i = 0; i < tower_.size(); i++) {
      output.writeMessage(2, tower_.get(i));
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
    for (int i = 0; i < tower_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, tower_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZTowerResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZTowerResponse other = (com.supercat.growstone.network.messages.ZTowerResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getTowerList()
        .equals(other.getTowerList())) return false;
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
    if (getTowerCount() > 0) {
      hash = (37 * hash) + TOWER_FIELD_NUMBER;
      hash = (53 * hash) + getTowerList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZTowerResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZTowerResponse prototype) {
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
   * Protobuf type {@code ZTowerResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZTowerResponse)
      com.supercat.growstone.network.messages.ZTowerResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZTowerResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZTowerResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZTowerResponse.class, com.supercat.growstone.network.messages.ZTowerResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZTowerResponse.newBuilder()
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
        getTowerFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (towerBuilder_ == null) {
        tower_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        towerBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZTowerResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZTowerResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZTowerResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZTowerResponse build() {
      com.supercat.growstone.network.messages.ZTowerResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZTowerResponse buildPartial() {
      com.supercat.growstone.network.messages.ZTowerResponse result = new com.supercat.growstone.network.messages.ZTowerResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (towerBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          tower_ = java.util.Collections.unmodifiableList(tower_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.tower_ = tower_;
      } else {
        result.tower_ = towerBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZTowerResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZTowerResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZTowerResponse other) {
      if (other == com.supercat.growstone.network.messages.ZTowerResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (towerBuilder_ == null) {
        if (!other.tower_.isEmpty()) {
          if (tower_.isEmpty()) {
            tower_ = other.tower_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureTowerIsMutable();
            tower_.addAll(other.tower_);
          }
          onChanged();
        }
      } else {
        if (!other.tower_.isEmpty()) {
          if (towerBuilder_.isEmpty()) {
            towerBuilder_.dispose();
            towerBuilder_ = null;
            tower_ = other.tower_;
            bitField0_ = (bitField0_ & ~0x00000001);
            towerBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getTowerFieldBuilder() : null;
          } else {
            towerBuilder_.addAllMessages(other.tower_);
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
      com.supercat.growstone.network.messages.ZTowerResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZTowerResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TTower> tower_ =
      java.util.Collections.emptyList();
    private void ensureTowerIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        tower_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TTower>(tower_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TTower, com.supercat.growstone.network.messages.TTower.Builder, com.supercat.growstone.network.messages.TTowerOrBuilder> towerBuilder_;

    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TTower> getTowerList() {
      if (towerBuilder_ == null) {
        return java.util.Collections.unmodifiableList(tower_);
      } else {
        return towerBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public int getTowerCount() {
      if (towerBuilder_ == null) {
        return tower_.size();
      } else {
        return towerBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public com.supercat.growstone.network.messages.TTower getTower(int index) {
      if (towerBuilder_ == null) {
        return tower_.get(index);
      } else {
        return towerBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder setTower(
        int index, com.supercat.growstone.network.messages.TTower value) {
      if (towerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTowerIsMutable();
        tower_.set(index, value);
        onChanged();
      } else {
        towerBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder setTower(
        int index, com.supercat.growstone.network.messages.TTower.Builder builderForValue) {
      if (towerBuilder_ == null) {
        ensureTowerIsMutable();
        tower_.set(index, builderForValue.build());
        onChanged();
      } else {
        towerBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder addTower(com.supercat.growstone.network.messages.TTower value) {
      if (towerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTowerIsMutable();
        tower_.add(value);
        onChanged();
      } else {
        towerBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder addTower(
        int index, com.supercat.growstone.network.messages.TTower value) {
      if (towerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureTowerIsMutable();
        tower_.add(index, value);
        onChanged();
      } else {
        towerBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder addTower(
        com.supercat.growstone.network.messages.TTower.Builder builderForValue) {
      if (towerBuilder_ == null) {
        ensureTowerIsMutable();
        tower_.add(builderForValue.build());
        onChanged();
      } else {
        towerBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder addTower(
        int index, com.supercat.growstone.network.messages.TTower.Builder builderForValue) {
      if (towerBuilder_ == null) {
        ensureTowerIsMutable();
        tower_.add(index, builderForValue.build());
        onChanged();
      } else {
        towerBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder addAllTower(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TTower> values) {
      if (towerBuilder_ == null) {
        ensureTowerIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, tower_);
        onChanged();
      } else {
        towerBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder clearTower() {
      if (towerBuilder_ == null) {
        tower_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        towerBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public Builder removeTower(int index) {
      if (towerBuilder_ == null) {
        ensureTowerIsMutable();
        tower_.remove(index);
        onChanged();
      } else {
        towerBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public com.supercat.growstone.network.messages.TTower.Builder getTowerBuilder(
        int index) {
      return getTowerFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public com.supercat.growstone.network.messages.TTowerOrBuilder getTowerOrBuilder(
        int index) {
      if (towerBuilder_ == null) {
        return tower_.get(index);  } else {
        return towerBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TTowerOrBuilder> 
         getTowerOrBuilderList() {
      if (towerBuilder_ != null) {
        return towerBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(tower_);
      }
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public com.supercat.growstone.network.messages.TTower.Builder addTowerBuilder() {
      return getTowerFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TTower.getDefaultInstance());
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public com.supercat.growstone.network.messages.TTower.Builder addTowerBuilder(
        int index) {
      return getTowerFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TTower.getDefaultInstance());
    }
    /**
     * <code>repeated .TTower tower = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TTower.Builder> 
         getTowerBuilderList() {
      return getTowerFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TTower, com.supercat.growstone.network.messages.TTower.Builder, com.supercat.growstone.network.messages.TTowerOrBuilder> 
        getTowerFieldBuilder() {
      if (towerBuilder_ == null) {
        towerBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TTower, com.supercat.growstone.network.messages.TTower.Builder, com.supercat.growstone.network.messages.TTowerOrBuilder>(
                tower_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        tower_ = null;
      }
      return towerBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZTowerResponse)
  }

  // @@protoc_insertion_point(class_scope:ZTowerResponse)
  private static final com.supercat.growstone.network.messages.ZTowerResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZTowerResponse();
  }

  public static com.supercat.growstone.network.messages.ZTowerResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZTowerResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZTowerResponse>() {
    @java.lang.Override
    public ZTowerResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZTowerResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZTowerResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZTowerResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZTowerResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

