// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZCharacterStatGrowthResponse}
 */
public final class ZCharacterStatGrowthResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZCharacterStatGrowthResponse)
    ZCharacterStatGrowthResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZCharacterStatGrowthResponse.newBuilder() to construct.
  private ZCharacterStatGrowthResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZCharacterStatGrowthResponse() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZCharacterStatGrowthResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZCharacterStatGrowthResponse(
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
            com.supercat.growstone.network.messages.TStatGrowthPage.Builder subBuilder = null;
            if (statGrowthPage_ != null) {
              subBuilder = statGrowthPage_.toBuilder();
            }
            statGrowthPage_ = input.readMessage(com.supercat.growstone.network.messages.TStatGrowthPage.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(statGrowthPage_);
              statGrowthPage_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZCharacterStatGrowthResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZCharacterStatGrowthResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.class, com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.Builder.class);
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

  public static final int STAT_GROWTH_PAGE_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TStatGrowthPage statGrowthPage_;
  /**
   * <code>.TStatGrowthPage stat_growth_page = 2;</code>
   * @return Whether the statGrowthPage field is set.
   */
  @java.lang.Override
  public boolean hasStatGrowthPage() {
    return statGrowthPage_ != null;
  }
  /**
   * <code>.TStatGrowthPage stat_growth_page = 2;</code>
   * @return The statGrowthPage.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStatGrowthPage getStatGrowthPage() {
    return statGrowthPage_ == null ? com.supercat.growstone.network.messages.TStatGrowthPage.getDefaultInstance() : statGrowthPage_;
  }
  /**
   * <code>.TStatGrowthPage stat_growth_page = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TStatGrowthPageOrBuilder getStatGrowthPageOrBuilder() {
    return getStatGrowthPage();
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
    if (statGrowthPage_ != null) {
      output.writeMessage(2, getStatGrowthPage());
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
    if (statGrowthPage_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getStatGrowthPage());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse other = (com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasStatGrowthPage() != other.hasStatGrowthPage()) return false;
    if (hasStatGrowthPage()) {
      if (!getStatGrowthPage()
          .equals(other.getStatGrowthPage())) return false;
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
    if (hasStatGrowthPage()) {
      hash = (37 * hash) + STAT_GROWTH_PAGE_FIELD_NUMBER;
      hash = (53 * hash) + getStatGrowthPage().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse prototype) {
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
   * Protobuf type {@code ZCharacterStatGrowthResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZCharacterStatGrowthResponse)
      com.supercat.growstone.network.messages.ZCharacterStatGrowthResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCharacterStatGrowthResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCharacterStatGrowthResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.class, com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.newBuilder()
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

      if (statGrowthPageBuilder_ == null) {
        statGrowthPage_ = null;
      } else {
        statGrowthPage_ = null;
        statGrowthPageBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCharacterStatGrowthResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse build() {
      com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse buildPartial() {
      com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse result = new com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse(this);
      result.status_ = status_;
      if (statGrowthPageBuilder_ == null) {
        result.statGrowthPage_ = statGrowthPage_;
      } else {
        result.statGrowthPage_ = statGrowthPageBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse other) {
      if (other == com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasStatGrowthPage()) {
        mergeStatGrowthPage(other.getStatGrowthPage());
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
      com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse) e.getUnfinishedMessage();
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

    private com.supercat.growstone.network.messages.TStatGrowthPage statGrowthPage_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TStatGrowthPage, com.supercat.growstone.network.messages.TStatGrowthPage.Builder, com.supercat.growstone.network.messages.TStatGrowthPageOrBuilder> statGrowthPageBuilder_;
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     * @return Whether the statGrowthPage field is set.
     */
    public boolean hasStatGrowthPage() {
      return statGrowthPageBuilder_ != null || statGrowthPage_ != null;
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     * @return The statGrowthPage.
     */
    public com.supercat.growstone.network.messages.TStatGrowthPage getStatGrowthPage() {
      if (statGrowthPageBuilder_ == null) {
        return statGrowthPage_ == null ? com.supercat.growstone.network.messages.TStatGrowthPage.getDefaultInstance() : statGrowthPage_;
      } else {
        return statGrowthPageBuilder_.getMessage();
      }
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    public Builder setStatGrowthPage(com.supercat.growstone.network.messages.TStatGrowthPage value) {
      if (statGrowthPageBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        statGrowthPage_ = value;
        onChanged();
      } else {
        statGrowthPageBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    public Builder setStatGrowthPage(
        com.supercat.growstone.network.messages.TStatGrowthPage.Builder builderForValue) {
      if (statGrowthPageBuilder_ == null) {
        statGrowthPage_ = builderForValue.build();
        onChanged();
      } else {
        statGrowthPageBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    public Builder mergeStatGrowthPage(com.supercat.growstone.network.messages.TStatGrowthPage value) {
      if (statGrowthPageBuilder_ == null) {
        if (statGrowthPage_ != null) {
          statGrowthPage_ =
            com.supercat.growstone.network.messages.TStatGrowthPage.newBuilder(statGrowthPage_).mergeFrom(value).buildPartial();
        } else {
          statGrowthPage_ = value;
        }
        onChanged();
      } else {
        statGrowthPageBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    public Builder clearStatGrowthPage() {
      if (statGrowthPageBuilder_ == null) {
        statGrowthPage_ = null;
        onChanged();
      } else {
        statGrowthPage_ = null;
        statGrowthPageBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowthPage.Builder getStatGrowthPageBuilder() {
      
      onChanged();
      return getStatGrowthPageFieldBuilder().getBuilder();
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    public com.supercat.growstone.network.messages.TStatGrowthPageOrBuilder getStatGrowthPageOrBuilder() {
      if (statGrowthPageBuilder_ != null) {
        return statGrowthPageBuilder_.getMessageOrBuilder();
      } else {
        return statGrowthPage_ == null ?
            com.supercat.growstone.network.messages.TStatGrowthPage.getDefaultInstance() : statGrowthPage_;
      }
    }
    /**
     * <code>.TStatGrowthPage stat_growth_page = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TStatGrowthPage, com.supercat.growstone.network.messages.TStatGrowthPage.Builder, com.supercat.growstone.network.messages.TStatGrowthPageOrBuilder> 
        getStatGrowthPageFieldBuilder() {
      if (statGrowthPageBuilder_ == null) {
        statGrowthPageBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TStatGrowthPage, com.supercat.growstone.network.messages.TStatGrowthPage.Builder, com.supercat.growstone.network.messages.TStatGrowthPageOrBuilder>(
                getStatGrowthPage(),
                getParentForChildren(),
                isClean());
        statGrowthPage_ = null;
      }
      return statGrowthPageBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZCharacterStatGrowthResponse)
  }

  // @@protoc_insertion_point(class_scope:ZCharacterStatGrowthResponse)
  private static final com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse();
  }

  public static com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZCharacterStatGrowthResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZCharacterStatGrowthResponse>() {
    @java.lang.Override
    public ZCharacterStatGrowthResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZCharacterStatGrowthResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZCharacterStatGrowthResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZCharacterStatGrowthResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZCharacterStatGrowthResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

