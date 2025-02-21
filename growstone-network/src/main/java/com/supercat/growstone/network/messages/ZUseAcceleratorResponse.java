// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZUseAcceleratorResponse}
 */
public final class ZUseAcceleratorResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZUseAcceleratorResponse)
    ZUseAcceleratorResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZUseAcceleratorResponse.newBuilder() to construct.
  private ZUseAcceleratorResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZUseAcceleratorResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZUseAcceleratorResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZUseAcceleratorResponse(
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
            com.supercat.growstone.network.messages.TDigging.Builder subBuilder = null;
            if (digging_ != null) {
              subBuilder = digging_.toBuilder();
            }
            digging_ = input.readMessage(com.supercat.growstone.network.messages.TDigging.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(digging_);
              digging_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZUseAcceleratorResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZUseAcceleratorResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZUseAcceleratorResponse.class, com.supercat.growstone.network.messages.ZUseAcceleratorResponse.Builder.class);
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

  public static final int DIGGING_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TDigging digging_;
  /**
   * <code>.TDigging digging = 2;</code>
   * @return Whether the digging field is set.
   */
  @java.lang.Override
  public boolean hasDigging() {
    return digging_ != null;
  }
  /**
   * <code>.TDigging digging = 2;</code>
   * @return The digging.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TDigging getDigging() {
    return digging_ == null ? com.supercat.growstone.network.messages.TDigging.getDefaultInstance() : digging_;
  }
  /**
   * <code>.TDigging digging = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TDiggingOrBuilder getDiggingOrBuilder() {
    return getDigging();
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
    if (digging_ != null) {
      output.writeMessage(2, getDigging());
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
    if (digging_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getDigging());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZUseAcceleratorResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZUseAcceleratorResponse other = (com.supercat.growstone.network.messages.ZUseAcceleratorResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasDigging() != other.hasDigging()) return false;
    if (hasDigging()) {
      if (!getDigging()
          .equals(other.getDigging())) return false;
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
    if (hasDigging()) {
      hash = (37 * hash) + DIGGING_FIELD_NUMBER;
      hash = (53 * hash) + getDigging().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZUseAcceleratorResponse prototype) {
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
   * Protobuf type {@code ZUseAcceleratorResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZUseAcceleratorResponse)
      com.supercat.growstone.network.messages.ZUseAcceleratorResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZUseAcceleratorResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZUseAcceleratorResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZUseAcceleratorResponse.class, com.supercat.growstone.network.messages.ZUseAcceleratorResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZUseAcceleratorResponse.newBuilder()
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

      if (diggingBuilder_ == null) {
        digging_ = null;
      } else {
        digging_ = null;
        diggingBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZUseAcceleratorResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZUseAcceleratorResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZUseAcceleratorResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZUseAcceleratorResponse build() {
      com.supercat.growstone.network.messages.ZUseAcceleratorResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZUseAcceleratorResponse buildPartial() {
      com.supercat.growstone.network.messages.ZUseAcceleratorResponse result = new com.supercat.growstone.network.messages.ZUseAcceleratorResponse(this);
      result.status_ = status_;
      if (diggingBuilder_ == null) {
        result.digging_ = digging_;
      } else {
        result.digging_ = diggingBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZUseAcceleratorResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZUseAcceleratorResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZUseAcceleratorResponse other) {
      if (other == com.supercat.growstone.network.messages.ZUseAcceleratorResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasDigging()) {
        mergeDigging(other.getDigging());
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
      com.supercat.growstone.network.messages.ZUseAcceleratorResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZUseAcceleratorResponse) e.getUnfinishedMessage();
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

    private com.supercat.growstone.network.messages.TDigging digging_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TDigging, com.supercat.growstone.network.messages.TDigging.Builder, com.supercat.growstone.network.messages.TDiggingOrBuilder> diggingBuilder_;
    /**
     * <code>.TDigging digging = 2;</code>
     * @return Whether the digging field is set.
     */
    public boolean hasDigging() {
      return diggingBuilder_ != null || digging_ != null;
    }
    /**
     * <code>.TDigging digging = 2;</code>
     * @return The digging.
     */
    public com.supercat.growstone.network.messages.TDigging getDigging() {
      if (diggingBuilder_ == null) {
        return digging_ == null ? com.supercat.growstone.network.messages.TDigging.getDefaultInstance() : digging_;
      } else {
        return diggingBuilder_.getMessage();
      }
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    public Builder setDigging(com.supercat.growstone.network.messages.TDigging value) {
      if (diggingBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        digging_ = value;
        onChanged();
      } else {
        diggingBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    public Builder setDigging(
        com.supercat.growstone.network.messages.TDigging.Builder builderForValue) {
      if (diggingBuilder_ == null) {
        digging_ = builderForValue.build();
        onChanged();
      } else {
        diggingBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    public Builder mergeDigging(com.supercat.growstone.network.messages.TDigging value) {
      if (diggingBuilder_ == null) {
        if (digging_ != null) {
          digging_ =
            com.supercat.growstone.network.messages.TDigging.newBuilder(digging_).mergeFrom(value).buildPartial();
        } else {
          digging_ = value;
        }
        onChanged();
      } else {
        diggingBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    public Builder clearDigging() {
      if (diggingBuilder_ == null) {
        digging_ = null;
        onChanged();
      } else {
        digging_ = null;
        diggingBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDigging.Builder getDiggingBuilder() {
      
      onChanged();
      return getDiggingFieldBuilder().getBuilder();
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    public com.supercat.growstone.network.messages.TDiggingOrBuilder getDiggingOrBuilder() {
      if (diggingBuilder_ != null) {
        return diggingBuilder_.getMessageOrBuilder();
      } else {
        return digging_ == null ?
            com.supercat.growstone.network.messages.TDigging.getDefaultInstance() : digging_;
      }
    }
    /**
     * <code>.TDigging digging = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TDigging, com.supercat.growstone.network.messages.TDigging.Builder, com.supercat.growstone.network.messages.TDiggingOrBuilder> 
        getDiggingFieldBuilder() {
      if (diggingBuilder_ == null) {
        diggingBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TDigging, com.supercat.growstone.network.messages.TDigging.Builder, com.supercat.growstone.network.messages.TDiggingOrBuilder>(
                getDigging(),
                getParentForChildren(),
                isClean());
        digging_ = null;
      }
      return diggingBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZUseAcceleratorResponse)
  }

  // @@protoc_insertion_point(class_scope:ZUseAcceleratorResponse)
  private static final com.supercat.growstone.network.messages.ZUseAcceleratorResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZUseAcceleratorResponse();
  }

  public static com.supercat.growstone.network.messages.ZUseAcceleratorResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZUseAcceleratorResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZUseAcceleratorResponse>() {
    @java.lang.Override
    public ZUseAcceleratorResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZUseAcceleratorResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZUseAcceleratorResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZUseAcceleratorResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZUseAcceleratorResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

