// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZCollectionResponse}
 */
public final class ZCollectionResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZCollectionResponse)
    ZCollectionResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZCollectionResponse.newBuilder() to construct.
  private ZCollectionResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZCollectionResponse() {
    collections_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZCollectionResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZCollectionResponse(
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
              collections_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TCollection>();
              mutable_bitField0_ |= 0x00000001;
            }
            collections_.add(
                input.readMessage(com.supercat.growstone.network.messages.TCollection.parser(), extensionRegistry));
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
        collections_ = java.util.Collections.unmodifiableList(collections_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZCollectionResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZCollectionResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZCollectionResponse.class, com.supercat.growstone.network.messages.ZCollectionResponse.Builder.class);
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

  public static final int COLLECTIONS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TCollection> collections_;
  /**
   * <code>repeated .TCollection collections = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TCollection> getCollectionsList() {
    return collections_;
  }
  /**
   * <code>repeated .TCollection collections = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TCollectionOrBuilder> 
      getCollectionsOrBuilderList() {
    return collections_;
  }
  /**
   * <code>repeated .TCollection collections = 2;</code>
   */
  @java.lang.Override
  public int getCollectionsCount() {
    return collections_.size();
  }
  /**
   * <code>repeated .TCollection collections = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TCollection getCollections(int index) {
    return collections_.get(index);
  }
  /**
   * <code>repeated .TCollection collections = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TCollectionOrBuilder getCollectionsOrBuilder(
      int index) {
    return collections_.get(index);
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
    for (int i = 0; i < collections_.size(); i++) {
      output.writeMessage(2, collections_.get(i));
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
    for (int i = 0; i < collections_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, collections_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZCollectionResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZCollectionResponse other = (com.supercat.growstone.network.messages.ZCollectionResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getCollectionsList()
        .equals(other.getCollectionsList())) return false;
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
    if (getCollectionsCount() > 0) {
      hash = (37 * hash) + COLLECTIONS_FIELD_NUMBER;
      hash = (53 * hash) + getCollectionsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCollectionResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZCollectionResponse prototype) {
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
   * Protobuf type {@code ZCollectionResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZCollectionResponse)
      com.supercat.growstone.network.messages.ZCollectionResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCollectionResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCollectionResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZCollectionResponse.class, com.supercat.growstone.network.messages.ZCollectionResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZCollectionResponse.newBuilder()
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
        getCollectionsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (collectionsBuilder_ == null) {
        collections_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        collectionsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCollectionResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCollectionResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZCollectionResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCollectionResponse build() {
      com.supercat.growstone.network.messages.ZCollectionResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCollectionResponse buildPartial() {
      com.supercat.growstone.network.messages.ZCollectionResponse result = new com.supercat.growstone.network.messages.ZCollectionResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (collectionsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          collections_ = java.util.Collections.unmodifiableList(collections_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.collections_ = collections_;
      } else {
        result.collections_ = collectionsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZCollectionResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZCollectionResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZCollectionResponse other) {
      if (other == com.supercat.growstone.network.messages.ZCollectionResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (collectionsBuilder_ == null) {
        if (!other.collections_.isEmpty()) {
          if (collections_.isEmpty()) {
            collections_ = other.collections_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureCollectionsIsMutable();
            collections_.addAll(other.collections_);
          }
          onChanged();
        }
      } else {
        if (!other.collections_.isEmpty()) {
          if (collectionsBuilder_.isEmpty()) {
            collectionsBuilder_.dispose();
            collectionsBuilder_ = null;
            collections_ = other.collections_;
            bitField0_ = (bitField0_ & ~0x00000001);
            collectionsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getCollectionsFieldBuilder() : null;
          } else {
            collectionsBuilder_.addAllMessages(other.collections_);
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
      com.supercat.growstone.network.messages.ZCollectionResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZCollectionResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TCollection> collections_ =
      java.util.Collections.emptyList();
    private void ensureCollectionsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        collections_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TCollection>(collections_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TCollection, com.supercat.growstone.network.messages.TCollection.Builder, com.supercat.growstone.network.messages.TCollectionOrBuilder> collectionsBuilder_;

    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TCollection> getCollectionsList() {
      if (collectionsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(collections_);
      } else {
        return collectionsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public int getCollectionsCount() {
      if (collectionsBuilder_ == null) {
        return collections_.size();
      } else {
        return collectionsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCollection getCollections(int index) {
      if (collectionsBuilder_ == null) {
        return collections_.get(index);
      } else {
        return collectionsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder setCollections(
        int index, com.supercat.growstone.network.messages.TCollection value) {
      if (collectionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCollectionsIsMutable();
        collections_.set(index, value);
        onChanged();
      } else {
        collectionsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder setCollections(
        int index, com.supercat.growstone.network.messages.TCollection.Builder builderForValue) {
      if (collectionsBuilder_ == null) {
        ensureCollectionsIsMutable();
        collections_.set(index, builderForValue.build());
        onChanged();
      } else {
        collectionsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder addCollections(com.supercat.growstone.network.messages.TCollection value) {
      if (collectionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCollectionsIsMutable();
        collections_.add(value);
        onChanged();
      } else {
        collectionsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder addCollections(
        int index, com.supercat.growstone.network.messages.TCollection value) {
      if (collectionsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureCollectionsIsMutable();
        collections_.add(index, value);
        onChanged();
      } else {
        collectionsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder addCollections(
        com.supercat.growstone.network.messages.TCollection.Builder builderForValue) {
      if (collectionsBuilder_ == null) {
        ensureCollectionsIsMutable();
        collections_.add(builderForValue.build());
        onChanged();
      } else {
        collectionsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder addCollections(
        int index, com.supercat.growstone.network.messages.TCollection.Builder builderForValue) {
      if (collectionsBuilder_ == null) {
        ensureCollectionsIsMutable();
        collections_.add(index, builderForValue.build());
        onChanged();
      } else {
        collectionsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder addAllCollections(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TCollection> values) {
      if (collectionsBuilder_ == null) {
        ensureCollectionsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, collections_);
        onChanged();
      } else {
        collectionsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder clearCollections() {
      if (collectionsBuilder_ == null) {
        collections_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        collectionsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public Builder removeCollections(int index) {
      if (collectionsBuilder_ == null) {
        ensureCollectionsIsMutable();
        collections_.remove(index);
        onChanged();
      } else {
        collectionsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCollection.Builder getCollectionsBuilder(
        int index) {
      return getCollectionsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCollectionOrBuilder getCollectionsOrBuilder(
        int index) {
      if (collectionsBuilder_ == null) {
        return collections_.get(index);  } else {
        return collectionsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TCollectionOrBuilder> 
         getCollectionsOrBuilderList() {
      if (collectionsBuilder_ != null) {
        return collectionsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(collections_);
      }
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCollection.Builder addCollectionsBuilder() {
      return getCollectionsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TCollection.getDefaultInstance());
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCollection.Builder addCollectionsBuilder(
        int index) {
      return getCollectionsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TCollection.getDefaultInstance());
    }
    /**
     * <code>repeated .TCollection collections = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TCollection.Builder> 
         getCollectionsBuilderList() {
      return getCollectionsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TCollection, com.supercat.growstone.network.messages.TCollection.Builder, com.supercat.growstone.network.messages.TCollectionOrBuilder> 
        getCollectionsFieldBuilder() {
      if (collectionsBuilder_ == null) {
        collectionsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TCollection, com.supercat.growstone.network.messages.TCollection.Builder, com.supercat.growstone.network.messages.TCollectionOrBuilder>(
                collections_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        collections_ = null;
      }
      return collectionsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZCollectionResponse)
  }

  // @@protoc_insertion_point(class_scope:ZCollectionResponse)
  private static final com.supercat.growstone.network.messages.ZCollectionResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZCollectionResponse();
  }

  public static com.supercat.growstone.network.messages.ZCollectionResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZCollectionResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZCollectionResponse>() {
    @java.lang.Override
    public ZCollectionResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZCollectionResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZCollectionResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZCollectionResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZCollectionResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

