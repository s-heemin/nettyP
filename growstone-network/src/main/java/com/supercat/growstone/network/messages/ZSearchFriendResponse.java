// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZSearchFriendResponse}
 */
public final class ZSearchFriendResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZSearchFriendResponse)
    ZSearchFriendResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZSearchFriendResponse.newBuilder() to construct.
  private ZSearchFriendResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZSearchFriendResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZSearchFriendResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZSearchFriendResponse(
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
            com.supercat.growstone.network.messages.TPlayerSimpleInfo.Builder subBuilder = null;
            if (friend_ != null) {
              subBuilder = friend_.toBuilder();
            }
            friend_ = input.readMessage(com.supercat.growstone.network.messages.TPlayerSimpleInfo.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(friend_);
              friend_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZSearchFriendResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZSearchFriendResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZSearchFriendResponse.class, com.supercat.growstone.network.messages.ZSearchFriendResponse.Builder.class);
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

  public static final int FRIEND_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TPlayerSimpleInfo friend_;
  /**
   * <code>.TPlayerSimpleInfo friend = 2;</code>
   * @return Whether the friend field is set.
   */
  @java.lang.Override
  public boolean hasFriend() {
    return friend_ != null;
  }
  /**
   * <code>.TPlayerSimpleInfo friend = 2;</code>
   * @return The friend.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPlayerSimpleInfo getFriend() {
    return friend_ == null ? com.supercat.growstone.network.messages.TPlayerSimpleInfo.getDefaultInstance() : friend_;
  }
  /**
   * <code>.TPlayerSimpleInfo friend = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder getFriendOrBuilder() {
    return getFriend();
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
    if (friend_ != null) {
      output.writeMessage(2, getFriend());
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
    if (friend_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getFriend());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZSearchFriendResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZSearchFriendResponse other = (com.supercat.growstone.network.messages.ZSearchFriendResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasFriend() != other.hasFriend()) return false;
    if (hasFriend()) {
      if (!getFriend()
          .equals(other.getFriend())) return false;
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
    if (hasFriend()) {
      hash = (37 * hash) + FRIEND_FIELD_NUMBER;
      hash = (53 * hash) + getFriend().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZSearchFriendResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZSearchFriendResponse prototype) {
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
   * Protobuf type {@code ZSearchFriendResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZSearchFriendResponse)
      com.supercat.growstone.network.messages.ZSearchFriendResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZSearchFriendResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZSearchFriendResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZSearchFriendResponse.class, com.supercat.growstone.network.messages.ZSearchFriendResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZSearchFriendResponse.newBuilder()
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

      if (friendBuilder_ == null) {
        friend_ = null;
      } else {
        friend_ = null;
        friendBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZSearchFriendResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZSearchFriendResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZSearchFriendResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZSearchFriendResponse build() {
      com.supercat.growstone.network.messages.ZSearchFriendResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZSearchFriendResponse buildPartial() {
      com.supercat.growstone.network.messages.ZSearchFriendResponse result = new com.supercat.growstone.network.messages.ZSearchFriendResponse(this);
      result.status_ = status_;
      if (friendBuilder_ == null) {
        result.friend_ = friend_;
      } else {
        result.friend_ = friendBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZSearchFriendResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZSearchFriendResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZSearchFriendResponse other) {
      if (other == com.supercat.growstone.network.messages.ZSearchFriendResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasFriend()) {
        mergeFriend(other.getFriend());
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
      com.supercat.growstone.network.messages.ZSearchFriendResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZSearchFriendResponse) e.getUnfinishedMessage();
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

    private com.supercat.growstone.network.messages.TPlayerSimpleInfo friend_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TPlayerSimpleInfo, com.supercat.growstone.network.messages.TPlayerSimpleInfo.Builder, com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder> friendBuilder_;
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     * @return Whether the friend field is set.
     */
    public boolean hasFriend() {
      return friendBuilder_ != null || friend_ != null;
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     * @return The friend.
     */
    public com.supercat.growstone.network.messages.TPlayerSimpleInfo getFriend() {
      if (friendBuilder_ == null) {
        return friend_ == null ? com.supercat.growstone.network.messages.TPlayerSimpleInfo.getDefaultInstance() : friend_;
      } else {
        return friendBuilder_.getMessage();
      }
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    public Builder setFriend(com.supercat.growstone.network.messages.TPlayerSimpleInfo value) {
      if (friendBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        friend_ = value;
        onChanged();
      } else {
        friendBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    public Builder setFriend(
        com.supercat.growstone.network.messages.TPlayerSimpleInfo.Builder builderForValue) {
      if (friendBuilder_ == null) {
        friend_ = builderForValue.build();
        onChanged();
      } else {
        friendBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    public Builder mergeFriend(com.supercat.growstone.network.messages.TPlayerSimpleInfo value) {
      if (friendBuilder_ == null) {
        if (friend_ != null) {
          friend_ =
            com.supercat.growstone.network.messages.TPlayerSimpleInfo.newBuilder(friend_).mergeFrom(value).buildPartial();
        } else {
          friend_ = value;
        }
        onChanged();
      } else {
        friendBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    public Builder clearFriend() {
      if (friendBuilder_ == null) {
        friend_ = null;
        onChanged();
      } else {
        friend_ = null;
        friendBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerSimpleInfo.Builder getFriendBuilder() {
      
      onChanged();
      return getFriendFieldBuilder().getBuilder();
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder getFriendOrBuilder() {
      if (friendBuilder_ != null) {
        return friendBuilder_.getMessageOrBuilder();
      } else {
        return friend_ == null ?
            com.supercat.growstone.network.messages.TPlayerSimpleInfo.getDefaultInstance() : friend_;
      }
    }
    /**
     * <code>.TPlayerSimpleInfo friend = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TPlayerSimpleInfo, com.supercat.growstone.network.messages.TPlayerSimpleInfo.Builder, com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder> 
        getFriendFieldBuilder() {
      if (friendBuilder_ == null) {
        friendBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TPlayerSimpleInfo, com.supercat.growstone.network.messages.TPlayerSimpleInfo.Builder, com.supercat.growstone.network.messages.TPlayerSimpleInfoOrBuilder>(
                getFriend(),
                getParentForChildren(),
                isClean());
        friend_ = null;
      }
      return friendBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZSearchFriendResponse)
  }

  // @@protoc_insertion_point(class_scope:ZSearchFriendResponse)
  private static final com.supercat.growstone.network.messages.ZSearchFriendResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZSearchFriendResponse();
  }

  public static com.supercat.growstone.network.messages.ZSearchFriendResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZSearchFriendResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZSearchFriendResponse>() {
    @java.lang.Override
    public ZSearchFriendResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZSearchFriendResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZSearchFriendResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZSearchFriendResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZSearchFriendResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

