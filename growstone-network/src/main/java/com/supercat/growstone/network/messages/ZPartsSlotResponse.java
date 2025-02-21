// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZPartsSlotResponse}
 */
public final class ZPartsSlotResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZPartsSlotResponse)
    ZPartsSlotResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZPartsSlotResponse.newBuilder() to construct.
  private ZPartsSlotResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZPartsSlotResponse() {
    partsSlots_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZPartsSlotResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZPartsSlotResponse(
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
              partsSlots_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TPartsSlot>();
              mutable_bitField0_ |= 0x00000001;
            }
            partsSlots_.add(
                input.readMessage(com.supercat.growstone.network.messages.TPartsSlot.parser(), extensionRegistry));
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
        partsSlots_ = java.util.Collections.unmodifiableList(partsSlots_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPartsSlotResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPartsSlotResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZPartsSlotResponse.class, com.supercat.growstone.network.messages.ZPartsSlotResponse.Builder.class);
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

  public static final int PARTSSLOTS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TPartsSlot> partsSlots_;
  /**
   * <code>repeated .TPartsSlot partsSlots = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TPartsSlot> getPartsSlotsList() {
    return partsSlots_;
  }
  /**
   * <code>repeated .TPartsSlot partsSlots = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TPartsSlotOrBuilder> 
      getPartsSlotsOrBuilderList() {
    return partsSlots_;
  }
  /**
   * <code>repeated .TPartsSlot partsSlots = 2;</code>
   */
  @java.lang.Override
  public int getPartsSlotsCount() {
    return partsSlots_.size();
  }
  /**
   * <code>repeated .TPartsSlot partsSlots = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPartsSlot getPartsSlots(int index) {
    return partsSlots_.get(index);
  }
  /**
   * <code>repeated .TPartsSlot partsSlots = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TPartsSlotOrBuilder getPartsSlotsOrBuilder(
      int index) {
    return partsSlots_.get(index);
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
    for (int i = 0; i < partsSlots_.size(); i++) {
      output.writeMessage(2, partsSlots_.get(i));
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
    for (int i = 0; i < partsSlots_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, partsSlots_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZPartsSlotResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZPartsSlotResponse other = (com.supercat.growstone.network.messages.ZPartsSlotResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getPartsSlotsList()
        .equals(other.getPartsSlotsList())) return false;
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
    if (getPartsSlotsCount() > 0) {
      hash = (37 * hash) + PARTSSLOTS_FIELD_NUMBER;
      hash = (53 * hash) + getPartsSlotsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPartsSlotResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZPartsSlotResponse prototype) {
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
   * Protobuf type {@code ZPartsSlotResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZPartsSlotResponse)
      com.supercat.growstone.network.messages.ZPartsSlotResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPartsSlotResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPartsSlotResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZPartsSlotResponse.class, com.supercat.growstone.network.messages.ZPartsSlotResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZPartsSlotResponse.newBuilder()
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
        getPartsSlotsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (partsSlotsBuilder_ == null) {
        partsSlots_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        partsSlotsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPartsSlotResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPartsSlotResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZPartsSlotResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPartsSlotResponse build() {
      com.supercat.growstone.network.messages.ZPartsSlotResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPartsSlotResponse buildPartial() {
      com.supercat.growstone.network.messages.ZPartsSlotResponse result = new com.supercat.growstone.network.messages.ZPartsSlotResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (partsSlotsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          partsSlots_ = java.util.Collections.unmodifiableList(partsSlots_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.partsSlots_ = partsSlots_;
      } else {
        result.partsSlots_ = partsSlotsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZPartsSlotResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZPartsSlotResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZPartsSlotResponse other) {
      if (other == com.supercat.growstone.network.messages.ZPartsSlotResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (partsSlotsBuilder_ == null) {
        if (!other.partsSlots_.isEmpty()) {
          if (partsSlots_.isEmpty()) {
            partsSlots_ = other.partsSlots_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensurePartsSlotsIsMutable();
            partsSlots_.addAll(other.partsSlots_);
          }
          onChanged();
        }
      } else {
        if (!other.partsSlots_.isEmpty()) {
          if (partsSlotsBuilder_.isEmpty()) {
            partsSlotsBuilder_.dispose();
            partsSlotsBuilder_ = null;
            partsSlots_ = other.partsSlots_;
            bitField0_ = (bitField0_ & ~0x00000001);
            partsSlotsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPartsSlotsFieldBuilder() : null;
          } else {
            partsSlotsBuilder_.addAllMessages(other.partsSlots_);
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
      com.supercat.growstone.network.messages.ZPartsSlotResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZPartsSlotResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TPartsSlot> partsSlots_ =
      java.util.Collections.emptyList();
    private void ensurePartsSlotsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        partsSlots_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TPartsSlot>(partsSlots_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TPartsSlot, com.supercat.growstone.network.messages.TPartsSlot.Builder, com.supercat.growstone.network.messages.TPartsSlotOrBuilder> partsSlotsBuilder_;

    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TPartsSlot> getPartsSlotsList() {
      if (partsSlotsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(partsSlots_);
      } else {
        return partsSlotsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public int getPartsSlotsCount() {
      if (partsSlotsBuilder_ == null) {
        return partsSlots_.size();
      } else {
        return partsSlotsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPartsSlot getPartsSlots(int index) {
      if (partsSlotsBuilder_ == null) {
        return partsSlots_.get(index);
      } else {
        return partsSlotsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder setPartsSlots(
        int index, com.supercat.growstone.network.messages.TPartsSlot value) {
      if (partsSlotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePartsSlotsIsMutable();
        partsSlots_.set(index, value);
        onChanged();
      } else {
        partsSlotsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder setPartsSlots(
        int index, com.supercat.growstone.network.messages.TPartsSlot.Builder builderForValue) {
      if (partsSlotsBuilder_ == null) {
        ensurePartsSlotsIsMutable();
        partsSlots_.set(index, builderForValue.build());
        onChanged();
      } else {
        partsSlotsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder addPartsSlots(com.supercat.growstone.network.messages.TPartsSlot value) {
      if (partsSlotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePartsSlotsIsMutable();
        partsSlots_.add(value);
        onChanged();
      } else {
        partsSlotsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder addPartsSlots(
        int index, com.supercat.growstone.network.messages.TPartsSlot value) {
      if (partsSlotsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePartsSlotsIsMutable();
        partsSlots_.add(index, value);
        onChanged();
      } else {
        partsSlotsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder addPartsSlots(
        com.supercat.growstone.network.messages.TPartsSlot.Builder builderForValue) {
      if (partsSlotsBuilder_ == null) {
        ensurePartsSlotsIsMutable();
        partsSlots_.add(builderForValue.build());
        onChanged();
      } else {
        partsSlotsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder addPartsSlots(
        int index, com.supercat.growstone.network.messages.TPartsSlot.Builder builderForValue) {
      if (partsSlotsBuilder_ == null) {
        ensurePartsSlotsIsMutable();
        partsSlots_.add(index, builderForValue.build());
        onChanged();
      } else {
        partsSlotsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder addAllPartsSlots(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TPartsSlot> values) {
      if (partsSlotsBuilder_ == null) {
        ensurePartsSlotsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, partsSlots_);
        onChanged();
      } else {
        partsSlotsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder clearPartsSlots() {
      if (partsSlotsBuilder_ == null) {
        partsSlots_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        partsSlotsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public Builder removePartsSlots(int index) {
      if (partsSlotsBuilder_ == null) {
        ensurePartsSlotsIsMutable();
        partsSlots_.remove(index);
        onChanged();
      } else {
        partsSlotsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPartsSlot.Builder getPartsSlotsBuilder(
        int index) {
      return getPartsSlotsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPartsSlotOrBuilder getPartsSlotsOrBuilder(
        int index) {
      if (partsSlotsBuilder_ == null) {
        return partsSlots_.get(index);  } else {
        return partsSlotsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TPartsSlotOrBuilder> 
         getPartsSlotsOrBuilderList() {
      if (partsSlotsBuilder_ != null) {
        return partsSlotsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(partsSlots_);
      }
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPartsSlot.Builder addPartsSlotsBuilder() {
      return getPartsSlotsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TPartsSlot.getDefaultInstance());
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public com.supercat.growstone.network.messages.TPartsSlot.Builder addPartsSlotsBuilder(
        int index) {
      return getPartsSlotsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TPartsSlot.getDefaultInstance());
    }
    /**
     * <code>repeated .TPartsSlot partsSlots = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TPartsSlot.Builder> 
         getPartsSlotsBuilderList() {
      return getPartsSlotsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TPartsSlot, com.supercat.growstone.network.messages.TPartsSlot.Builder, com.supercat.growstone.network.messages.TPartsSlotOrBuilder> 
        getPartsSlotsFieldBuilder() {
      if (partsSlotsBuilder_ == null) {
        partsSlotsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TPartsSlot, com.supercat.growstone.network.messages.TPartsSlot.Builder, com.supercat.growstone.network.messages.TPartsSlotOrBuilder>(
                partsSlots_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        partsSlots_ = null;
      }
      return partsSlotsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZPartsSlotResponse)
  }

  // @@protoc_insertion_point(class_scope:ZPartsSlotResponse)
  private static final com.supercat.growstone.network.messages.ZPartsSlotResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZPartsSlotResponse();
  }

  public static com.supercat.growstone.network.messages.ZPartsSlotResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZPartsSlotResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZPartsSlotResponse>() {
    @java.lang.Override
    public ZPartsSlotResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZPartsSlotResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZPartsSlotResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZPartsSlotResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZPartsSlotResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

