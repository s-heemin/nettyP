// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZClanJoinRequestReplyNotify}
 */
public final class ZClanJoinRequestReplyNotify extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZClanJoinRequestReplyNotify)
    ZClanJoinRequestReplyNotifyOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZClanJoinRequestReplyNotify.newBuilder() to construct.
  private ZClanJoinRequestReplyNotify(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZClanJoinRequestReplyNotify() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZClanJoinRequestReplyNotify();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZClanJoinRequestReplyNotify(
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
            com.supercat.growstone.network.messages.TClan.Builder subBuilder = null;
            if (clan_ != null) {
              subBuilder = clan_.toBuilder();
            }
            clan_ = input.readMessage(com.supercat.growstone.network.messages.TClan.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(clan_);
              clan_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZClanJoinRequestReplyNotify_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZClanJoinRequestReplyNotify_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.class, com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.Builder.class);
  }

  public static final int CLAN_FIELD_NUMBER = 1;
  private com.supercat.growstone.network.messages.TClan clan_;
  /**
   * <code>.TClan clan = 1;</code>
   * @return Whether the clan field is set.
   */
  @java.lang.Override
  public boolean hasClan() {
    return clan_ != null;
  }
  /**
   * <code>.TClan clan = 1;</code>
   * @return The clan.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TClan getClan() {
    return clan_ == null ? com.supercat.growstone.network.messages.TClan.getDefaultInstance() : clan_;
  }
  /**
   * <code>.TClan clan = 1;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TClanOrBuilder getClanOrBuilder() {
    return getClan();
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
    if (clan_ != null) {
      output.writeMessage(1, getClan());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (clan_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getClan());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify other = (com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify) obj;

    if (hasClan() != other.hasClan()) return false;
    if (hasClan()) {
      if (!getClan()
          .equals(other.getClan())) return false;
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
    if (hasClan()) {
      hash = (37 * hash) + CLAN_FIELD_NUMBER;
      hash = (53 * hash) + getClan().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify prototype) {
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
   * Protobuf type {@code ZClanJoinRequestReplyNotify}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZClanJoinRequestReplyNotify)
      com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotifyOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZClanJoinRequestReplyNotify_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZClanJoinRequestReplyNotify_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.class, com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.newBuilder()
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
      if (clanBuilder_ == null) {
        clan_ = null;
      } else {
        clan_ = null;
        clanBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZClanJoinRequestReplyNotify_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify build() {
      com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify buildPartial() {
      com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify result = new com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify(this);
      if (clanBuilder_ == null) {
        result.clan_ = clan_;
      } else {
        result.clan_ = clanBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify) {
        return mergeFrom((com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify other) {
      if (other == com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify.getDefaultInstance()) return this;
      if (other.hasClan()) {
        mergeClan(other.getClan());
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
      com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.supercat.growstone.network.messages.TClan clan_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TClan, com.supercat.growstone.network.messages.TClan.Builder, com.supercat.growstone.network.messages.TClanOrBuilder> clanBuilder_;
    /**
     * <code>.TClan clan = 1;</code>
     * @return Whether the clan field is set.
     */
    public boolean hasClan() {
      return clanBuilder_ != null || clan_ != null;
    }
    /**
     * <code>.TClan clan = 1;</code>
     * @return The clan.
     */
    public com.supercat.growstone.network.messages.TClan getClan() {
      if (clanBuilder_ == null) {
        return clan_ == null ? com.supercat.growstone.network.messages.TClan.getDefaultInstance() : clan_;
      } else {
        return clanBuilder_.getMessage();
      }
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    public Builder setClan(com.supercat.growstone.network.messages.TClan value) {
      if (clanBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        clan_ = value;
        onChanged();
      } else {
        clanBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    public Builder setClan(
        com.supercat.growstone.network.messages.TClan.Builder builderForValue) {
      if (clanBuilder_ == null) {
        clan_ = builderForValue.build();
        onChanged();
      } else {
        clanBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    public Builder mergeClan(com.supercat.growstone.network.messages.TClan value) {
      if (clanBuilder_ == null) {
        if (clan_ != null) {
          clan_ =
            com.supercat.growstone.network.messages.TClan.newBuilder(clan_).mergeFrom(value).buildPartial();
        } else {
          clan_ = value;
        }
        onChanged();
      } else {
        clanBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    public Builder clearClan() {
      if (clanBuilder_ == null) {
        clan_ = null;
        onChanged();
      } else {
        clan_ = null;
        clanBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    public com.supercat.growstone.network.messages.TClan.Builder getClanBuilder() {
      
      onChanged();
      return getClanFieldBuilder().getBuilder();
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    public com.supercat.growstone.network.messages.TClanOrBuilder getClanOrBuilder() {
      if (clanBuilder_ != null) {
        return clanBuilder_.getMessageOrBuilder();
      } else {
        return clan_ == null ?
            com.supercat.growstone.network.messages.TClan.getDefaultInstance() : clan_;
      }
    }
    /**
     * <code>.TClan clan = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TClan, com.supercat.growstone.network.messages.TClan.Builder, com.supercat.growstone.network.messages.TClanOrBuilder> 
        getClanFieldBuilder() {
      if (clanBuilder_ == null) {
        clanBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TClan, com.supercat.growstone.network.messages.TClan.Builder, com.supercat.growstone.network.messages.TClanOrBuilder>(
                getClan(),
                getParentForChildren(),
                isClean());
        clan_ = null;
      }
      return clanBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZClanJoinRequestReplyNotify)
  }

  // @@protoc_insertion_point(class_scope:ZClanJoinRequestReplyNotify)
  private static final com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify();
  }

  public static com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZClanJoinRequestReplyNotify>
      PARSER = new com.google.protobuf.AbstractParser<ZClanJoinRequestReplyNotify>() {
    @java.lang.Override
    public ZClanJoinRequestReplyNotify parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZClanJoinRequestReplyNotify(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZClanJoinRequestReplyNotify> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZClanJoinRequestReplyNotify> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZClanJoinRequestReplyNotify getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

