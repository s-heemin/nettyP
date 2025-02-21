// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZPlayerConditionPackageInfoResponse}
 */
public final class ZPlayerConditionPackageInfoResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZPlayerConditionPackageInfoResponse)
    ZPlayerConditionPackageInfoResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZPlayerConditionPackageInfoResponse.newBuilder() to construct.
  private ZPlayerConditionPackageInfoResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZPlayerConditionPackageInfoResponse() {
    conditionPackages_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZPlayerConditionPackageInfoResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZPlayerConditionPackageInfoResponse(
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
              conditionPackages_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TConditionPackage>();
              mutable_bitField0_ |= 0x00000001;
            }
            conditionPackages_.add(
                input.readMessage(com.supercat.growstone.network.messages.TConditionPackage.parser(), extensionRegistry));
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
        conditionPackages_ = java.util.Collections.unmodifiableList(conditionPackages_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerConditionPackageInfoResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerConditionPackageInfoResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.class, com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.Builder.class);
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

  public static final int CONDITION_PACKAGES_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TConditionPackage> conditionPackages_;
  /**
   * <code>repeated .TConditionPackage condition_packages = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TConditionPackage> getConditionPackagesList() {
    return conditionPackages_;
  }
  /**
   * <code>repeated .TConditionPackage condition_packages = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TConditionPackageOrBuilder> 
      getConditionPackagesOrBuilderList() {
    return conditionPackages_;
  }
  /**
   * <code>repeated .TConditionPackage condition_packages = 2;</code>
   */
  @java.lang.Override
  public int getConditionPackagesCount() {
    return conditionPackages_.size();
  }
  /**
   * <code>repeated .TConditionPackage condition_packages = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TConditionPackage getConditionPackages(int index) {
    return conditionPackages_.get(index);
  }
  /**
   * <code>repeated .TConditionPackage condition_packages = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TConditionPackageOrBuilder getConditionPackagesOrBuilder(
      int index) {
    return conditionPackages_.get(index);
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
    for (int i = 0; i < conditionPackages_.size(); i++) {
      output.writeMessage(2, conditionPackages_.get(i));
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
    for (int i = 0; i < conditionPackages_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, conditionPackages_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse other = (com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getConditionPackagesList()
        .equals(other.getConditionPackagesList())) return false;
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
    if (getConditionPackagesCount() > 0) {
      hash = (37 * hash) + CONDITION_PACKAGES_FIELD_NUMBER;
      hash = (53 * hash) + getConditionPackagesList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse prototype) {
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
   * Protobuf type {@code ZPlayerConditionPackageInfoResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZPlayerConditionPackageInfoResponse)
      com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerConditionPackageInfoResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerConditionPackageInfoResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.class, com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.newBuilder()
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
        getConditionPackagesFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (conditionPackagesBuilder_ == null) {
        conditionPackages_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        conditionPackagesBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZPlayerConditionPackageInfoResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse build() {
      com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse buildPartial() {
      com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse result = new com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (conditionPackagesBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          conditionPackages_ = java.util.Collections.unmodifiableList(conditionPackages_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.conditionPackages_ = conditionPackages_;
      } else {
        result.conditionPackages_ = conditionPackagesBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse other) {
      if (other == com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (conditionPackagesBuilder_ == null) {
        if (!other.conditionPackages_.isEmpty()) {
          if (conditionPackages_.isEmpty()) {
            conditionPackages_ = other.conditionPackages_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureConditionPackagesIsMutable();
            conditionPackages_.addAll(other.conditionPackages_);
          }
          onChanged();
        }
      } else {
        if (!other.conditionPackages_.isEmpty()) {
          if (conditionPackagesBuilder_.isEmpty()) {
            conditionPackagesBuilder_.dispose();
            conditionPackagesBuilder_ = null;
            conditionPackages_ = other.conditionPackages_;
            bitField0_ = (bitField0_ & ~0x00000001);
            conditionPackagesBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getConditionPackagesFieldBuilder() : null;
          } else {
            conditionPackagesBuilder_.addAllMessages(other.conditionPackages_);
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
      com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TConditionPackage> conditionPackages_ =
      java.util.Collections.emptyList();
    private void ensureConditionPackagesIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        conditionPackages_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TConditionPackage>(conditionPackages_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TConditionPackage, com.supercat.growstone.network.messages.TConditionPackage.Builder, com.supercat.growstone.network.messages.TConditionPackageOrBuilder> conditionPackagesBuilder_;

    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TConditionPackage> getConditionPackagesList() {
      if (conditionPackagesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(conditionPackages_);
      } else {
        return conditionPackagesBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public int getConditionPackagesCount() {
      if (conditionPackagesBuilder_ == null) {
        return conditionPackages_.size();
      } else {
        return conditionPackagesBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public com.supercat.growstone.network.messages.TConditionPackage getConditionPackages(int index) {
      if (conditionPackagesBuilder_ == null) {
        return conditionPackages_.get(index);
      } else {
        return conditionPackagesBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder setConditionPackages(
        int index, com.supercat.growstone.network.messages.TConditionPackage value) {
      if (conditionPackagesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConditionPackagesIsMutable();
        conditionPackages_.set(index, value);
        onChanged();
      } else {
        conditionPackagesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder setConditionPackages(
        int index, com.supercat.growstone.network.messages.TConditionPackage.Builder builderForValue) {
      if (conditionPackagesBuilder_ == null) {
        ensureConditionPackagesIsMutable();
        conditionPackages_.set(index, builderForValue.build());
        onChanged();
      } else {
        conditionPackagesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder addConditionPackages(com.supercat.growstone.network.messages.TConditionPackage value) {
      if (conditionPackagesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConditionPackagesIsMutable();
        conditionPackages_.add(value);
        onChanged();
      } else {
        conditionPackagesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder addConditionPackages(
        int index, com.supercat.growstone.network.messages.TConditionPackage value) {
      if (conditionPackagesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureConditionPackagesIsMutable();
        conditionPackages_.add(index, value);
        onChanged();
      } else {
        conditionPackagesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder addConditionPackages(
        com.supercat.growstone.network.messages.TConditionPackage.Builder builderForValue) {
      if (conditionPackagesBuilder_ == null) {
        ensureConditionPackagesIsMutable();
        conditionPackages_.add(builderForValue.build());
        onChanged();
      } else {
        conditionPackagesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder addConditionPackages(
        int index, com.supercat.growstone.network.messages.TConditionPackage.Builder builderForValue) {
      if (conditionPackagesBuilder_ == null) {
        ensureConditionPackagesIsMutable();
        conditionPackages_.add(index, builderForValue.build());
        onChanged();
      } else {
        conditionPackagesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder addAllConditionPackages(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TConditionPackage> values) {
      if (conditionPackagesBuilder_ == null) {
        ensureConditionPackagesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, conditionPackages_);
        onChanged();
      } else {
        conditionPackagesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder clearConditionPackages() {
      if (conditionPackagesBuilder_ == null) {
        conditionPackages_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        conditionPackagesBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public Builder removeConditionPackages(int index) {
      if (conditionPackagesBuilder_ == null) {
        ensureConditionPackagesIsMutable();
        conditionPackages_.remove(index);
        onChanged();
      } else {
        conditionPackagesBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public com.supercat.growstone.network.messages.TConditionPackage.Builder getConditionPackagesBuilder(
        int index) {
      return getConditionPackagesFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public com.supercat.growstone.network.messages.TConditionPackageOrBuilder getConditionPackagesOrBuilder(
        int index) {
      if (conditionPackagesBuilder_ == null) {
        return conditionPackages_.get(index);  } else {
        return conditionPackagesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TConditionPackageOrBuilder> 
         getConditionPackagesOrBuilderList() {
      if (conditionPackagesBuilder_ != null) {
        return conditionPackagesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(conditionPackages_);
      }
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public com.supercat.growstone.network.messages.TConditionPackage.Builder addConditionPackagesBuilder() {
      return getConditionPackagesFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TConditionPackage.getDefaultInstance());
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public com.supercat.growstone.network.messages.TConditionPackage.Builder addConditionPackagesBuilder(
        int index) {
      return getConditionPackagesFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TConditionPackage.getDefaultInstance());
    }
    /**
     * <code>repeated .TConditionPackage condition_packages = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TConditionPackage.Builder> 
         getConditionPackagesBuilderList() {
      return getConditionPackagesFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TConditionPackage, com.supercat.growstone.network.messages.TConditionPackage.Builder, com.supercat.growstone.network.messages.TConditionPackageOrBuilder> 
        getConditionPackagesFieldBuilder() {
      if (conditionPackagesBuilder_ == null) {
        conditionPackagesBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TConditionPackage, com.supercat.growstone.network.messages.TConditionPackage.Builder, com.supercat.growstone.network.messages.TConditionPackageOrBuilder>(
                conditionPackages_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        conditionPackages_ = null;
      }
      return conditionPackagesBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZPlayerConditionPackageInfoResponse)
  }

  // @@protoc_insertion_point(class_scope:ZPlayerConditionPackageInfoResponse)
  private static final com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse();
  }

  public static com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZPlayerConditionPackageInfoResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZPlayerConditionPackageInfoResponse>() {
    @java.lang.Override
    public ZPlayerConditionPackageInfoResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZPlayerConditionPackageInfoResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZPlayerConditionPackageInfoResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZPlayerConditionPackageInfoResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZPlayerConditionPackageInfoResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

