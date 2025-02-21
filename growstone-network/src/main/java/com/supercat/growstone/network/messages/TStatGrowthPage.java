// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code TStatGrowthPage}
 */
public final class TStatGrowthPage extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TStatGrowthPage)
    TStatGrowthPageOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TStatGrowthPage.newBuilder() to construct.
  private TStatGrowthPage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TStatGrowthPage() {
    statGrowths_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TStatGrowthPage();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TStatGrowthPage(
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

            page_ = input.readInt64();
            break;
          }
          case 18: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              statGrowths_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TStatGrowth>();
              mutable_bitField0_ |= 0x00000001;
            }
            statGrowths_.add(
                input.readMessage(com.supercat.growstone.network.messages.TStatGrowth.parser(), extensionRegistry));
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
        statGrowths_ = java.util.Collections.unmodifiableList(statGrowths_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_TStatGrowthPage_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_TStatGrowthPage_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.TStatGrowthPage.class, com.supercat.growstone.network.messages.TStatGrowthPage.Builder.class);
  }

  public static final int PAGE_FIELD_NUMBER = 1;
  private long page_;
  /**
   * <code>int64 page = 1;</code>
   * @return The page.
   */
  @java.lang.Override
  public long getPage() {
    return page_;
  }

  public static final int STAT_GROWTHS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TStatGrowth> statGrowths_;
  /**
   * <code>repeated .TStatGrowth stat_growths = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TStatGrowth> getStatGrowthsList() {
    return statGrowths_;
  }
  /**
   * <code>repeated .TStatGrowth stat_growths = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TStatGrowthOrBuilder> 
      getStatGrowthsOrBuilderList() {
    return statGrowths_;
  }
  /**
   * <code>repeated .TStatGrowth stat_growths = 2;</code>
   */
  @java.lang.Override
  public int getStatGrowthsCount() {
    return statGrowths_.size();
  }
  /**
   * <code>repeated .TStatGrowth stat_growths = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStatGrowth getStatGrowths(int index) {
    return statGrowths_.get(index);
  }
  /**
   * <code>repeated .TStatGrowth stat_growths = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStatGrowthOrBuilder getStatGrowthsOrBuilder(
      int index) {
    return statGrowths_.get(index);
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
    if (page_ != 0L) {
      output.writeInt64(1, page_);
    }
    for (int i = 0; i < statGrowths_.size(); i++) {
      output.writeMessage(2, statGrowths_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (page_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, page_);
    }
    for (int i = 0; i < statGrowths_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, statGrowths_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.TStatGrowthPage)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.TStatGrowthPage other = (com.supercat.growstone.network.messages.TStatGrowthPage) obj;

    if (getPage()
        != other.getPage()) return false;
    if (!getStatGrowthsList()
        .equals(other.getStatGrowthsList())) return false;
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
    hash = (37 * hash) + PAGE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPage());
    if (getStatGrowthsCount() > 0) {
      hash = (37 * hash) + STAT_GROWTHS_FIELD_NUMBER;
      hash = (53 * hash) + getStatGrowthsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TStatGrowthPage parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.TStatGrowthPage prototype) {
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
   * Protobuf type {@code TStatGrowthPage}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TStatGrowthPage)
      com.supercat.growstone.network.messages.TStatGrowthPageOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_TStatGrowthPage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_TStatGrowthPage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.TStatGrowthPage.class, com.supercat.growstone.network.messages.TStatGrowthPage.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.TStatGrowthPage.newBuilder()
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
        getStatGrowthsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      page_ = 0L;

      if (statGrowthsBuilder_ == null) {
        statGrowths_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        statGrowthsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_TStatGrowthPage_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TStatGrowthPage getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.TStatGrowthPage.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TStatGrowthPage build() {
      com.supercat.growstone.network.messages.TStatGrowthPage result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TStatGrowthPage buildPartial() {
      com.supercat.growstone.network.messages.TStatGrowthPage result = new com.supercat.growstone.network.messages.TStatGrowthPage(this);
      int from_bitField0_ = bitField0_;
      result.page_ = page_;
      if (statGrowthsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          statGrowths_ = java.util.Collections.unmodifiableList(statGrowths_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.statGrowths_ = statGrowths_;
      } else {
        result.statGrowths_ = statGrowthsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.TStatGrowthPage) {
        return mergeFrom((com.supercat.growstone.network.messages.TStatGrowthPage)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.TStatGrowthPage other) {
      if (other == com.supercat.growstone.network.messages.TStatGrowthPage.getDefaultInstance()) return this;
      if (other.getPage() != 0L) {
        setPage(other.getPage());
      }
      if (statGrowthsBuilder_ == null) {
        if (!other.statGrowths_.isEmpty()) {
          if (statGrowths_.isEmpty()) {
            statGrowths_ = other.statGrowths_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureStatGrowthsIsMutable();
            statGrowths_.addAll(other.statGrowths_);
          }
          onChanged();
        }
      } else {
        if (!other.statGrowths_.isEmpty()) {
          if (statGrowthsBuilder_.isEmpty()) {
            statGrowthsBuilder_.dispose();
            statGrowthsBuilder_ = null;
            statGrowths_ = other.statGrowths_;
            bitField0_ = (bitField0_ & ~0x00000001);
            statGrowthsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getStatGrowthsFieldBuilder() : null;
          } else {
            statGrowthsBuilder_.addAllMessages(other.statGrowths_);
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
      com.supercat.growstone.network.messages.TStatGrowthPage parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.TStatGrowthPage) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private long page_ ;
    /**
     * <code>int64 page = 1;</code>
     * @return The page.
     */
    @java.lang.Override
    public long getPage() {
      return page_;
    }
    /**
     * <code>int64 page = 1;</code>
     * @param value The page to set.
     * @return This builder for chaining.
     */
    public Builder setPage(long value) {
      
      page_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 page = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPage() {
      
      page_ = 0L;
      onChanged();
      return this;
    }

    private java.util.List<com.supercat.growstone.network.messages.TStatGrowth> statGrowths_ =
      java.util.Collections.emptyList();
    private void ensureStatGrowthsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        statGrowths_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TStatGrowth>(statGrowths_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TStatGrowth, com.supercat.growstone.network.messages.TStatGrowth.Builder, com.supercat.growstone.network.messages.TStatGrowthOrBuilder> statGrowthsBuilder_;

    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TStatGrowth> getStatGrowthsList() {
      if (statGrowthsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(statGrowths_);
      } else {
        return statGrowthsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public int getStatGrowthsCount() {
      if (statGrowthsBuilder_ == null) {
        return statGrowths_.size();
      } else {
        return statGrowthsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowth getStatGrowths(int index) {
      if (statGrowthsBuilder_ == null) {
        return statGrowths_.get(index);
      } else {
        return statGrowthsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder setStatGrowths(
        int index, com.supercat.growstone.network.messages.TStatGrowth value) {
      if (statGrowthsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStatGrowthsIsMutable();
        statGrowths_.set(index, value);
        onChanged();
      } else {
        statGrowthsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder setStatGrowths(
        int index, com.supercat.growstone.network.messages.TStatGrowth.Builder builderForValue) {
      if (statGrowthsBuilder_ == null) {
        ensureStatGrowthsIsMutable();
        statGrowths_.set(index, builderForValue.build());
        onChanged();
      } else {
        statGrowthsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder addStatGrowths(com.supercat.growstone.network.messages.TStatGrowth value) {
      if (statGrowthsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStatGrowthsIsMutable();
        statGrowths_.add(value);
        onChanged();
      } else {
        statGrowthsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder addStatGrowths(
        int index, com.supercat.growstone.network.messages.TStatGrowth value) {
      if (statGrowthsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureStatGrowthsIsMutable();
        statGrowths_.add(index, value);
        onChanged();
      } else {
        statGrowthsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder addStatGrowths(
        com.supercat.growstone.network.messages.TStatGrowth.Builder builderForValue) {
      if (statGrowthsBuilder_ == null) {
        ensureStatGrowthsIsMutable();
        statGrowths_.add(builderForValue.build());
        onChanged();
      } else {
        statGrowthsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder addStatGrowths(
        int index, com.supercat.growstone.network.messages.TStatGrowth.Builder builderForValue) {
      if (statGrowthsBuilder_ == null) {
        ensureStatGrowthsIsMutable();
        statGrowths_.add(index, builderForValue.build());
        onChanged();
      } else {
        statGrowthsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder addAllStatGrowths(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TStatGrowth> values) {
      if (statGrowthsBuilder_ == null) {
        ensureStatGrowthsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, statGrowths_);
        onChanged();
      } else {
        statGrowthsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder clearStatGrowths() {
      if (statGrowthsBuilder_ == null) {
        statGrowths_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        statGrowthsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public Builder removeStatGrowths(int index) {
      if (statGrowthsBuilder_ == null) {
        ensureStatGrowthsIsMutable();
        statGrowths_.remove(index);
        onChanged();
      } else {
        statGrowthsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowth.Builder getStatGrowthsBuilder(
        int index) {
      return getStatGrowthsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowthOrBuilder getStatGrowthsOrBuilder(
        int index) {
      if (statGrowthsBuilder_ == null) {
        return statGrowths_.get(index);  } else {
        return statGrowthsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TStatGrowthOrBuilder> 
         getStatGrowthsOrBuilderList() {
      if (statGrowthsBuilder_ != null) {
        return statGrowthsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(statGrowths_);
      }
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowth.Builder addStatGrowthsBuilder() {
      return getStatGrowthsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TStatGrowth.getDefaultInstance());
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowth.Builder addStatGrowthsBuilder(
        int index) {
      return getStatGrowthsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TStatGrowth.getDefaultInstance());
    }
    /**
     * <code>repeated .TStatGrowth stat_growths = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TStatGrowth.Builder> 
         getStatGrowthsBuilderList() {
      return getStatGrowthsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TStatGrowth, com.supercat.growstone.network.messages.TStatGrowth.Builder, com.supercat.growstone.network.messages.TStatGrowthOrBuilder> 
        getStatGrowthsFieldBuilder() {
      if (statGrowthsBuilder_ == null) {
        statGrowthsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TStatGrowth, com.supercat.growstone.network.messages.TStatGrowth.Builder, com.supercat.growstone.network.messages.TStatGrowthOrBuilder>(
                statGrowths_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        statGrowths_ = null;
      }
      return statGrowthsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:TStatGrowthPage)
  }

  // @@protoc_insertion_point(class_scope:TStatGrowthPage)
  private static final com.supercat.growstone.network.messages.TStatGrowthPage DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.TStatGrowthPage();
  }

  public static com.supercat.growstone.network.messages.TStatGrowthPage getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TStatGrowthPage>
      PARSER = new com.google.protobuf.AbstractParser<TStatGrowthPage>() {
    @java.lang.Override
    public TStatGrowthPage parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TStatGrowthPage(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TStatGrowthPage> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TStatGrowthPage> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.TStatGrowthPage getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

