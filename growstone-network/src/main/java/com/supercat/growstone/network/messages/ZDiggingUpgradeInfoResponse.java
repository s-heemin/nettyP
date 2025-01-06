// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZDiggingUpgradeInfoResponse}
 */
public final class ZDiggingUpgradeInfoResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZDiggingUpgradeInfoResponse)
    ZDiggingUpgradeInfoResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZDiggingUpgradeInfoResponse.newBuilder() to construct.
  private ZDiggingUpgradeInfoResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZDiggingUpgradeInfoResponse() {
    upgrades_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZDiggingUpgradeInfoResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZDiggingUpgradeInfoResponse(
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
              upgrades_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TDiggingUpgrade>();
              mutable_bitField0_ |= 0x00000001;
            }
            upgrades_.add(
                input.readMessage(com.supercat.growstone.network.messages.TDiggingUpgrade.parser(), extensionRegistry));
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
        upgrades_ = java.util.Collections.unmodifiableList(upgrades_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZDiggingUpgradeInfoResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZDiggingUpgradeInfoResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.class, com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.Builder.class);
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

  public static final int UPGRADES_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TDiggingUpgrade> upgrades_;
  /**
   * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TDiggingUpgrade> getUpgradesList() {
    return upgrades_;
  }
  /**
   * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder> 
      getUpgradesOrBuilderList() {
    return upgrades_;
  }
  /**
   * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
   */
  @java.lang.Override
  public int getUpgradesCount() {
    return upgrades_.size();
  }
  /**
   * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TDiggingUpgrade getUpgrades(int index) {
    return upgrades_.get(index);
  }
  /**
   * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder getUpgradesOrBuilder(
      int index) {
    return upgrades_.get(index);
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
    for (int i = 0; i < upgrades_.size(); i++) {
      output.writeMessage(2, upgrades_.get(i));
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
    for (int i = 0; i < upgrades_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, upgrades_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse other = (com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getUpgradesList()
        .equals(other.getUpgradesList())) return false;
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
    if (getUpgradesCount() > 0) {
      hash = (37 * hash) + UPGRADES_FIELD_NUMBER;
      hash = (53 * hash) + getUpgradesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse prototype) {
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
   * Protobuf type {@code ZDiggingUpgradeInfoResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZDiggingUpgradeInfoResponse)
      com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZDiggingUpgradeInfoResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZDiggingUpgradeInfoResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.class, com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.newBuilder()
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
        getUpgradesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (upgradesBuilder_ == null) {
        upgrades_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        upgradesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZDiggingUpgradeInfoResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse build() {
      com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse buildPartial() {
      com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse result = new com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (upgradesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          upgrades_ = java.util.Collections.unmodifiableList(upgrades_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.upgrades_ = upgrades_;
      } else {
        result.upgrades_ = upgradesBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse other) {
      if (other == com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (upgradesBuilder_ == null) {
        if (!other.upgrades_.isEmpty()) {
          if (upgrades_.isEmpty()) {
            upgrades_ = other.upgrades_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureUpgradesIsMutable();
            upgrades_.addAll(other.upgrades_);
          }
          onChanged();
        }
      } else {
        if (!other.upgrades_.isEmpty()) {
          if (upgradesBuilder_.isEmpty()) {
            upgradesBuilder_.dispose();
            upgradesBuilder_ = null;
            upgrades_ = other.upgrades_;
            bitField0_ = (bitField0_ & ~0x00000001);
            upgradesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getUpgradesFieldBuilder() : null;
          } else {
            upgradesBuilder_.addAllMessages(other.upgrades_);
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
      com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TDiggingUpgrade> upgrades_ =
      java.util.Collections.emptyList();
    private void ensureUpgradesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        upgrades_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TDiggingUpgrade>(upgrades_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TDiggingUpgrade, com.supercat.growstone.network.messages.TDiggingUpgrade.Builder, com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder> upgradesBuilder_;

    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TDiggingUpgrade> getUpgradesList() {
      if (upgradesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(upgrades_);
      } else {
        return upgradesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public int getUpgradesCount() {
      if (upgradesBuilder_ == null) {
        return upgrades_.size();
      } else {
        return upgradesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDiggingUpgrade getUpgrades(int index) {
      if (upgradesBuilder_ == null) {
        return upgrades_.get(index);
      } else {
        return upgradesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder setUpgrades(
        int index, com.supercat.growstone.network.messages.TDiggingUpgrade value) {
      if (upgradesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUpgradesIsMutable();
        upgrades_.set(index, value);
        onChanged();
      } else {
        upgradesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder setUpgrades(
        int index, com.supercat.growstone.network.messages.TDiggingUpgrade.Builder builderForValue) {
      if (upgradesBuilder_ == null) {
        ensureUpgradesIsMutable();
        upgrades_.set(index, builderForValue.build());
        onChanged();
      } else {
        upgradesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder addUpgrades(com.supercat.growstone.network.messages.TDiggingUpgrade value) {
      if (upgradesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUpgradesIsMutable();
        upgrades_.add(value);
        onChanged();
      } else {
        upgradesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder addUpgrades(
        int index, com.supercat.growstone.network.messages.TDiggingUpgrade value) {
      if (upgradesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureUpgradesIsMutable();
        upgrades_.add(index, value);
        onChanged();
      } else {
        upgradesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder addUpgrades(
        com.supercat.growstone.network.messages.TDiggingUpgrade.Builder builderForValue) {
      if (upgradesBuilder_ == null) {
        ensureUpgradesIsMutable();
        upgrades_.add(builderForValue.build());
        onChanged();
      } else {
        upgradesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder addUpgrades(
        int index, com.supercat.growstone.network.messages.TDiggingUpgrade.Builder builderForValue) {
      if (upgradesBuilder_ == null) {
        ensureUpgradesIsMutable();
        upgrades_.add(index, builderForValue.build());
        onChanged();
      } else {
        upgradesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder addAllUpgrades(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TDiggingUpgrade> values) {
      if (upgradesBuilder_ == null) {
        ensureUpgradesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, upgrades_);
        onChanged();
      } else {
        upgradesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder clearUpgrades() {
      if (upgradesBuilder_ == null) {
        upgrades_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        upgradesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public Builder removeUpgrades(int index) {
      if (upgradesBuilder_ == null) {
        ensureUpgradesIsMutable();
        upgrades_.remove(index);
        onChanged();
      } else {
        upgradesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDiggingUpgrade.Builder getUpgradesBuilder(
        int index) {
      return getUpgradesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder getUpgradesOrBuilder(
        int index) {
      if (upgradesBuilder_ == null) {
        return upgrades_.get(index);  } else {
        return upgradesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder> 
         getUpgradesOrBuilderList() {
      if (upgradesBuilder_ != null) {
        return upgradesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(upgrades_);
      }
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDiggingUpgrade.Builder addUpgradesBuilder() {
      return getUpgradesFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TDiggingUpgrade.getDefaultInstance());
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDiggingUpgrade.Builder addUpgradesBuilder(
        int index) {
      return getUpgradesFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TDiggingUpgrade.getDefaultInstance());
    }
    /**
     * <code>repeated .TDiggingUpgrade upgrades = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TDiggingUpgrade.Builder> 
         getUpgradesBuilderList() {
      return getUpgradesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TDiggingUpgrade, com.supercat.growstone.network.messages.TDiggingUpgrade.Builder, com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder> 
        getUpgradesFieldBuilder() {
      if (upgradesBuilder_ == null) {
        upgradesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TDiggingUpgrade, com.supercat.growstone.network.messages.TDiggingUpgrade.Builder, com.supercat.growstone.network.messages.TDiggingUpgradeOrBuilder>(
                upgrades_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        upgrades_ = null;
      }
      return upgradesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZDiggingUpgradeInfoResponse)
  }

  // @@protoc_insertion_point(class_scope:ZDiggingUpgradeInfoResponse)
  private static final com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse();
  }

  public static com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZDiggingUpgradeInfoResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZDiggingUpgradeInfoResponse>() {
    @java.lang.Override
    public ZDiggingUpgradeInfoResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZDiggingUpgradeInfoResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZDiggingUpgradeInfoResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZDiggingUpgradeInfoResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZDiggingUpgradeInfoResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

