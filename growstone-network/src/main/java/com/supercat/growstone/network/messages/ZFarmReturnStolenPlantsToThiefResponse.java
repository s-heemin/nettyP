// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZFarmReturnStolenPlantsToThiefResponse}
 */
public final class ZFarmReturnStolenPlantsToThiefResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZFarmReturnStolenPlantsToThiefResponse)
    ZFarmReturnStolenPlantsToThiefResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZFarmReturnStolenPlantsToThiefResponse.newBuilder() to construct.
  private ZFarmReturnStolenPlantsToThiefResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZFarmReturnStolenPlantsToThiefResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZFarmReturnStolenPlantsToThiefResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZFarmReturnStolenPlantsToThiefResponse(
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
            com.supercat.growstone.network.messages.TFarm.Builder subBuilder = null;
            if (farm_ != null) {
              subBuilder = farm_.toBuilder();
            }
            farm_ = input.readMessage(com.supercat.growstone.network.messages.TFarm.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(farm_);
              farm_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZFarmReturnStolenPlantsToThiefResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZFarmReturnStolenPlantsToThiefResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.class, com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.Builder.class);
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

  public static final int FARM_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TFarm farm_;
  /**
   * <code>.TFarm farm = 2;</code>
   * @return Whether the farm field is set.
   */
  @java.lang.Override
  public boolean hasFarm() {
    return farm_ != null;
  }
  /**
   * <code>.TFarm farm = 2;</code>
   * @return The farm.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TFarm getFarm() {
    return farm_ == null ? com.supercat.growstone.network.messages.TFarm.getDefaultInstance() : farm_;
  }
  /**
   * <code>.TFarm farm = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TFarmOrBuilder getFarmOrBuilder() {
    return getFarm();
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
    if (farm_ != null) {
      output.writeMessage(2, getFarm());
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
    if (farm_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getFarm());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse other = (com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasFarm() != other.hasFarm()) return false;
    if (hasFarm()) {
      if (!getFarm()
          .equals(other.getFarm())) return false;
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
    if (hasFarm()) {
      hash = (37 * hash) + FARM_FIELD_NUMBER;
      hash = (53 * hash) + getFarm().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse prototype) {
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
   * Protobuf type {@code ZFarmReturnStolenPlantsToThiefResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZFarmReturnStolenPlantsToThiefResponse)
      com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZFarmReturnStolenPlantsToThiefResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZFarmReturnStolenPlantsToThiefResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.class, com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.newBuilder()
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

      if (farmBuilder_ == null) {
        farm_ = null;
      } else {
        farm_ = null;
        farmBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZFarmReturnStolenPlantsToThiefResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse build() {
      com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse buildPartial() {
      com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse result = new com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse(this);
      result.status_ = status_;
      if (farmBuilder_ == null) {
        result.farm_ = farm_;
      } else {
        result.farm_ = farmBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse other) {
      if (other == com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasFarm()) {
        mergeFarm(other.getFarm());
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
      com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse) e.getUnfinishedMessage();
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

    private com.supercat.growstone.network.messages.TFarm farm_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TFarm, com.supercat.growstone.network.messages.TFarm.Builder, com.supercat.growstone.network.messages.TFarmOrBuilder> farmBuilder_;
    /**
     * <code>.TFarm farm = 2;</code>
     * @return Whether the farm field is set.
     */
    public boolean hasFarm() {
      return farmBuilder_ != null || farm_ != null;
    }
    /**
     * <code>.TFarm farm = 2;</code>
     * @return The farm.
     */
    public com.supercat.growstone.network.messages.TFarm getFarm() {
      if (farmBuilder_ == null) {
        return farm_ == null ? com.supercat.growstone.network.messages.TFarm.getDefaultInstance() : farm_;
      } else {
        return farmBuilder_.getMessage();
      }
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    public Builder setFarm(com.supercat.growstone.network.messages.TFarm value) {
      if (farmBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        farm_ = value;
        onChanged();
      } else {
        farmBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    public Builder setFarm(
        com.supercat.growstone.network.messages.TFarm.Builder builderForValue) {
      if (farmBuilder_ == null) {
        farm_ = builderForValue.build();
        onChanged();
      } else {
        farmBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    public Builder mergeFarm(com.supercat.growstone.network.messages.TFarm value) {
      if (farmBuilder_ == null) {
        if (farm_ != null) {
          farm_ =
            com.supercat.growstone.network.messages.TFarm.newBuilder(farm_).mergeFrom(value).buildPartial();
        } else {
          farm_ = value;
        }
        onChanged();
      } else {
        farmBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    public Builder clearFarm() {
      if (farmBuilder_ == null) {
        farm_ = null;
        onChanged();
      } else {
        farm_ = null;
        farmBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    public com.supercat.growstone.network.messages.TFarm.Builder getFarmBuilder() {
      
      onChanged();
      return getFarmFieldBuilder().getBuilder();
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    public com.supercat.growstone.network.messages.TFarmOrBuilder getFarmOrBuilder() {
      if (farmBuilder_ != null) {
        return farmBuilder_.getMessageOrBuilder();
      } else {
        return farm_ == null ?
            com.supercat.growstone.network.messages.TFarm.getDefaultInstance() : farm_;
      }
    }
    /**
     * <code>.TFarm farm = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TFarm, com.supercat.growstone.network.messages.TFarm.Builder, com.supercat.growstone.network.messages.TFarmOrBuilder> 
        getFarmFieldBuilder() {
      if (farmBuilder_ == null) {
        farmBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TFarm, com.supercat.growstone.network.messages.TFarm.Builder, com.supercat.growstone.network.messages.TFarmOrBuilder>(
                getFarm(),
                getParentForChildren(),
                isClean());
        farm_ = null;
      }
      return farmBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZFarmReturnStolenPlantsToThiefResponse)
  }

  // @@protoc_insertion_point(class_scope:ZFarmReturnStolenPlantsToThiefResponse)
  private static final com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse();
  }

  public static com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZFarmReturnStolenPlantsToThiefResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZFarmReturnStolenPlantsToThiefResponse>() {
    @java.lang.Override
    public ZFarmReturnStolenPlantsToThiefResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZFarmReturnStolenPlantsToThiefResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZFarmReturnStolenPlantsToThiefResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZFarmReturnStolenPlantsToThiefResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZFarmReturnStolenPlantsToThiefResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

