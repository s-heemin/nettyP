// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZAddFriendNotify}
 */
public final class ZAddFriendNotify extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZAddFriendNotify)
    ZAddFriendNotifyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZAddFriendNotify.newBuilder() to construct.
  private ZAddFriendNotify(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZAddFriendNotify() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZAddFriendNotify();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZAddFriendNotify(
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
          case 10: {
            com.supercat.growstone.network.messages.TFriendInfo.Builder subBuilder = null;
            if (requestPlayer_ != null) {
              subBuilder = requestPlayer_.toBuilder();
            }
            requestPlayer_ = input.readMessage(com.supercat.growstone.network.messages.TFriendInfo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(requestPlayer_);
              requestPlayer_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZAddFriendNotify_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZAddFriendNotify_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZAddFriendNotify.class, com.supercat.growstone.network.messages.ZAddFriendNotify.Builder.class);
  }

  public static final int REQUEST_PLAYER_FIELD_NUMBER = 1;
  private com.supercat.growstone.network.messages.TFriendInfo requestPlayer_;
  /**
   * <code>.TFriendInfo request_player = 1;</code>
   * @return Whether the requestPlayer field is set.
   */
  @java.lang.Override
  public boolean hasRequestPlayer() {
    return requestPlayer_ != null;
  }
  /**
   * <code>.TFriendInfo request_player = 1;</code>
   * @return The requestPlayer.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TFriendInfo getRequestPlayer() {
    return requestPlayer_ == null ? com.supercat.growstone.network.messages.TFriendInfo.getDefaultInstance() : requestPlayer_;
  }
  /**
   * <code>.TFriendInfo request_player = 1;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TFriendInfoOrBuilder getRequestPlayerOrBuilder() {
    return getRequestPlayer();
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
    if (requestPlayer_ != null) {
      output.writeMessage(1, getRequestPlayer());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (requestPlayer_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getRequestPlayer());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZAddFriendNotify)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZAddFriendNotify other = (com.supercat.growstone.network.messages.ZAddFriendNotify) obj;

    if (hasRequestPlayer() != other.hasRequestPlayer()) return false;
    if (hasRequestPlayer()) {
      if (!getRequestPlayer()
          .equals(other.getRequestPlayer())) return false;
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
    if (hasRequestPlayer()) {
      hash = (37 * hash) + REQUEST_PLAYER_FIELD_NUMBER;
      hash = (53 * hash) + getRequestPlayer().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZAddFriendNotify parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZAddFriendNotify prototype) {
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
   * Protobuf type {@code ZAddFriendNotify}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZAddFriendNotify)
      com.supercat.growstone.network.messages.ZAddFriendNotifyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZAddFriendNotify_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZAddFriendNotify_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZAddFriendNotify.class, com.supercat.growstone.network.messages.ZAddFriendNotify.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZAddFriendNotify.newBuilder()
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
      if (requestPlayerBuilder_ == null) {
        requestPlayer_ = null;
      } else {
        requestPlayer_ = null;
        requestPlayerBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZAddFriendNotify_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZAddFriendNotify getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZAddFriendNotify.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZAddFriendNotify build() {
      com.supercat.growstone.network.messages.ZAddFriendNotify result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZAddFriendNotify buildPartial() {
      com.supercat.growstone.network.messages.ZAddFriendNotify result = new com.supercat.growstone.network.messages.ZAddFriendNotify(this);
      if (requestPlayerBuilder_ == null) {
        result.requestPlayer_ = requestPlayer_;
      } else {
        result.requestPlayer_ = requestPlayerBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZAddFriendNotify) {
        return mergeFrom((com.supercat.growstone.network.messages.ZAddFriendNotify)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZAddFriendNotify other) {
      if (other == com.supercat.growstone.network.messages.ZAddFriendNotify.getDefaultInstance()) return this;
      if (other.hasRequestPlayer()) {
        mergeRequestPlayer(other.getRequestPlayer());
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
      com.supercat.growstone.network.messages.ZAddFriendNotify parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZAddFriendNotify) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.supercat.growstone.network.messages.TFriendInfo requestPlayer_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TFriendInfo, com.supercat.growstone.network.messages.TFriendInfo.Builder, com.supercat.growstone.network.messages.TFriendInfoOrBuilder> requestPlayerBuilder_;
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     * @return Whether the requestPlayer field is set.
     */
    public boolean hasRequestPlayer() {
      return requestPlayerBuilder_ != null || requestPlayer_ != null;
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     * @return The requestPlayer.
     */
    public com.supercat.growstone.network.messages.TFriendInfo getRequestPlayer() {
      if (requestPlayerBuilder_ == null) {
        return requestPlayer_ == null ? com.supercat.growstone.network.messages.TFriendInfo.getDefaultInstance() : requestPlayer_;
      } else {
        return requestPlayerBuilder_.getMessage();
      }
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    public Builder setRequestPlayer(com.supercat.growstone.network.messages.TFriendInfo value) {
      if (requestPlayerBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        requestPlayer_ = value;
        onChanged();
      } else {
        requestPlayerBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    public Builder setRequestPlayer(
        com.supercat.growstone.network.messages.TFriendInfo.Builder builderForValue) {
      if (requestPlayerBuilder_ == null) {
        requestPlayer_ = builderForValue.build();
        onChanged();
      } else {
        requestPlayerBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    public Builder mergeRequestPlayer(com.supercat.growstone.network.messages.TFriendInfo value) {
      if (requestPlayerBuilder_ == null) {
        if (requestPlayer_ != null) {
          requestPlayer_ =
            com.supercat.growstone.network.messages.TFriendInfo.newBuilder(requestPlayer_).mergeFrom(value).buildPartial();
        } else {
          requestPlayer_ = value;
        }
        onChanged();
      } else {
        requestPlayerBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    public Builder clearRequestPlayer() {
      if (requestPlayerBuilder_ == null) {
        requestPlayer_ = null;
        onChanged();
      } else {
        requestPlayer_ = null;
        requestPlayerBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    public com.supercat.growstone.network.messages.TFriendInfo.Builder getRequestPlayerBuilder() {
      
      onChanged();
      return getRequestPlayerFieldBuilder().getBuilder();
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    public com.supercat.growstone.network.messages.TFriendInfoOrBuilder getRequestPlayerOrBuilder() {
      if (requestPlayerBuilder_ != null) {
        return requestPlayerBuilder_.getMessageOrBuilder();
      } else {
        return requestPlayer_ == null ?
            com.supercat.growstone.network.messages.TFriendInfo.getDefaultInstance() : requestPlayer_;
      }
    }
    /**
     * <code>.TFriendInfo request_player = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TFriendInfo, com.supercat.growstone.network.messages.TFriendInfo.Builder, com.supercat.growstone.network.messages.TFriendInfoOrBuilder> 
        getRequestPlayerFieldBuilder() {
      if (requestPlayerBuilder_ == null) {
        requestPlayerBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TFriendInfo, com.supercat.growstone.network.messages.TFriendInfo.Builder, com.supercat.growstone.network.messages.TFriendInfoOrBuilder>(
                getRequestPlayer(),
                getParentForChildren(),
                isClean());
        requestPlayer_ = null;
      }
      return requestPlayerBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZAddFriendNotify)
  }

  // @@protoc_insertion_point(class_scope:ZAddFriendNotify)
  private static final com.supercat.growstone.network.messages.ZAddFriendNotify DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZAddFriendNotify();
  }

  public static com.supercat.growstone.network.messages.ZAddFriendNotify getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZAddFriendNotify>
      PARSER = new com.google.protobuf.AbstractParser<ZAddFriendNotify>() {
    @java.lang.Override
    public ZAddFriendNotify parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZAddFriendNotify(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZAddFriendNotify> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZAddFriendNotify> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZAddFriendNotify getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

