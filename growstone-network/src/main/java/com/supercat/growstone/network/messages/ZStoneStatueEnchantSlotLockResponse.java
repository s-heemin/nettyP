// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZStoneStatueEnchantSlotLockResponse}
 */
public final class ZStoneStatueEnchantSlotLockResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZStoneStatueEnchantSlotLockResponse)
    ZStoneStatueEnchantSlotLockResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZStoneStatueEnchantSlotLockResponse.newBuilder() to construct.
  private ZStoneStatueEnchantSlotLockResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZStoneStatueEnchantSlotLockResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZStoneStatueEnchantSlotLockResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZStoneStatueEnchantSlotLockResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
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
            com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder subBuilder = null;
            if (stoneStatueEnchant_ != null) {
              subBuilder = stoneStatueEnchant_.toBuilder();
            }
            stoneStatueEnchant_ = input.readMessage(com.supercat.growstone.network.messages.TStoneStatueEnchant.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(stoneStatueEnchant_);
              stoneStatueEnchant_ = subBuilder.buildPartial();
            }

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
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZStoneStatueEnchantSlotLockResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZStoneStatueEnchantSlotLockResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.class, com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.Builder.class);
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

  public static final int STONE_STATUE_ENCHANT_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TStoneStatueEnchant stoneStatueEnchant_;
  /**
   * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
   * @return Whether the stoneStatueEnchant field is set.
   */
  @java.lang.Override
  public boolean hasStoneStatueEnchant() {
    return stoneStatueEnchant_ != null;
  }
  /**
   * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
   * @return The stoneStatueEnchant.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStoneStatueEnchant getStoneStatueEnchant() {
    return stoneStatueEnchant_ == null ? com.supercat.growstone.network.messages.TStoneStatueEnchant.getDefaultInstance() : stoneStatueEnchant_;
  }
  /**
   * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStoneStatueEnchantOrBuilder getStoneStatueEnchantOrBuilder() {
    return getStoneStatueEnchant();
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
    if (stoneStatueEnchant_ != null) {
      output.writeMessage(2, getStoneStatueEnchant());
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
    if (stoneStatueEnchant_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getStoneStatueEnchant());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse other = (com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasStoneStatueEnchant() != other.hasStoneStatueEnchant()) return false;
    if (hasStoneStatueEnchant()) {
      if (!getStoneStatueEnchant()
          .equals(other.getStoneStatueEnchant())) return false;
    }
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
    if (hasStoneStatueEnchant()) {
      hash = (37 * hash) + STONE_STATUE_ENCHANT_FIELD_NUMBER;
      hash = (53 * hash) + getStoneStatueEnchant().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse prototype) {
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
   * Protobuf type {@code ZStoneStatueEnchantSlotLockResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZStoneStatueEnchantSlotLockResponse)
      com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZStoneStatueEnchantSlotLockResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZStoneStatueEnchantSlotLockResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.class, com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.newBuilder()
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
      status_ = 0;

      if (stoneStatueEnchantBuilder_ == null) {
        stoneStatueEnchant_ = null;
      } else {
        stoneStatueEnchant_ = null;
        stoneStatueEnchantBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZStoneStatueEnchantSlotLockResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse build() {
      com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse buildPartial() {
      com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse result = new com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse(this);
      result.status_ = status_;
      if (stoneStatueEnchantBuilder_ == null) {
        result.stoneStatueEnchant_ = stoneStatueEnchant_;
      } else {
        result.stoneStatueEnchant_ = stoneStatueEnchantBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse other) {
      if (other == com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasStoneStatueEnchant()) {
        mergeStoneStatueEnchant(other.getStoneStatueEnchant());
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
      com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

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

    private com.supercat.growstone.network.messages.TStoneStatueEnchant stoneStatueEnchant_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TStoneStatueEnchant, com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder, com.supercat.growstone.network.messages.TStoneStatueEnchantOrBuilder> stoneStatueEnchantBuilder_;
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     * @return Whether the stoneStatueEnchant field is set.
     */
    public boolean hasStoneStatueEnchant() {
      return stoneStatueEnchantBuilder_ != null || stoneStatueEnchant_ != null;
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     * @return The stoneStatueEnchant.
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchant getStoneStatueEnchant() {
      if (stoneStatueEnchantBuilder_ == null) {
        return stoneStatueEnchant_ == null ? com.supercat.growstone.network.messages.TStoneStatueEnchant.getDefaultInstance() : stoneStatueEnchant_;
      } else {
        return stoneStatueEnchantBuilder_.getMessage();
      }
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    public Builder setStoneStatueEnchant(com.supercat.growstone.network.messages.TStoneStatueEnchant value) {
      if (stoneStatueEnchantBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        stoneStatueEnchant_ = value;
        onChanged();
      } else {
        stoneStatueEnchantBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    public Builder setStoneStatueEnchant(
        com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder builderForValue) {
      if (stoneStatueEnchantBuilder_ == null) {
        stoneStatueEnchant_ = builderForValue.build();
        onChanged();
      } else {
        stoneStatueEnchantBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    public Builder mergeStoneStatueEnchant(com.supercat.growstone.network.messages.TStoneStatueEnchant value) {
      if (stoneStatueEnchantBuilder_ == null) {
        if (stoneStatueEnchant_ != null) {
          stoneStatueEnchant_ =
            com.supercat.growstone.network.messages.TStoneStatueEnchant.newBuilder(stoneStatueEnchant_).mergeFrom(value).buildPartial();
        } else {
          stoneStatueEnchant_ = value;
        }
        onChanged();
      } else {
        stoneStatueEnchantBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    public Builder clearStoneStatueEnchant() {
      if (stoneStatueEnchantBuilder_ == null) {
        stoneStatueEnchant_ = null;
        onChanged();
      } else {
        stoneStatueEnchant_ = null;
        stoneStatueEnchantBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder getStoneStatueEnchantBuilder() {
      
      onChanged();
      return getStoneStatueEnchantFieldBuilder().getBuilder();
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStoneStatueEnchantOrBuilder getStoneStatueEnchantOrBuilder() {
      if (stoneStatueEnchantBuilder_ != null) {
        return stoneStatueEnchantBuilder_.getMessageOrBuilder();
      } else {
        return stoneStatueEnchant_ == null ?
            com.supercat.growstone.network.messages.TStoneStatueEnchant.getDefaultInstance() : stoneStatueEnchant_;
      }
    }
    /**
     * <code>.TStoneStatueEnchant stone_statue_enchant = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TStoneStatueEnchant, com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder, com.supercat.growstone.network.messages.TStoneStatueEnchantOrBuilder> 
        getStoneStatueEnchantFieldBuilder() {
      if (stoneStatueEnchantBuilder_ == null) {
        stoneStatueEnchantBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TStoneStatueEnchant, com.supercat.growstone.network.messages.TStoneStatueEnchant.Builder, com.supercat.growstone.network.messages.TStoneStatueEnchantOrBuilder>(
                getStoneStatueEnchant(),
                getParentForChildren(),
                isClean());
        stoneStatueEnchant_ = null;
      }
      return stoneStatueEnchantBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZStoneStatueEnchantSlotLockResponse)
  }

  // @@protoc_insertion_point(class_scope:ZStoneStatueEnchantSlotLockResponse)
  private static final com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse();
  }

  public static com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZStoneStatueEnchantSlotLockResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZStoneStatueEnchantSlotLockResponse>() {
    @java.lang.Override
    public ZStoneStatueEnchantSlotLockResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZStoneStatueEnchantSlotLockResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZStoneStatueEnchantSlotLockResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZStoneStatueEnchantSlotLockResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZStoneStatueEnchantSlotLockResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

