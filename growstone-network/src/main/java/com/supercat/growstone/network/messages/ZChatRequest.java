// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZChatRequest}
 */
public final class ZChatRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZChatRequest)
    ZChatRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZChatRequest.newBuilder() to construct.
  private ZChatRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZChatRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZChatRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZChatRequest(
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
            com.supercat.growstone.network.messages.TChat.Builder subBuilder = null;
            if (chat_ != null) {
              subBuilder = chat_.toBuilder();
            }
            chat_ = input.readMessage(com.supercat.growstone.network.messages.TChat.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(chat_);
              chat_ = subBuilder.buildPartial();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZChatRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZChatRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZChatRequest.class, com.supercat.growstone.network.messages.ZChatRequest.Builder.class);
  }

  public static final int CHAT_FIELD_NUMBER = 1;
  private com.supercat.growstone.network.messages.TChat chat_;
  /**
   * <code>.TChat chat = 1;</code>
   * @return Whether the chat field is set.
   */
  @java.lang.Override
  public boolean hasChat() {
    return chat_ != null;
  }
  /**
   * <code>.TChat chat = 1;</code>
   * @return The chat.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TChat getChat() {
    return chat_ == null ? com.supercat.growstone.network.messages.TChat.getDefaultInstance() : chat_;
  }
  /**
   * <code>.TChat chat = 1;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TChatOrBuilder getChatOrBuilder() {
    return getChat();
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
    if (chat_ != null) {
      output.writeMessage(1, getChat());
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (chat_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(1, getChat());
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZChatRequest)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZChatRequest other = (com.supercat.growstone.network.messages.ZChatRequest) obj;

    if (hasChat() != other.hasChat()) return false;
    if (hasChat()) {
      if (!getChat()
          .equals(other.getChat())) return false;
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
    if (hasChat()) {
      hash = (37 * hash) + CHAT_FIELD_NUMBER;
      hash = (53 * hash) + getChat().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZChatRequest parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZChatRequest prototype) {
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
   * Protobuf type {@code ZChatRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZChatRequest)
      com.supercat.growstone.network.messages.ZChatRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZChatRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZChatRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZChatRequest.class, com.supercat.growstone.network.messages.ZChatRequest.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZChatRequest.newBuilder()
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
      if (chatBuilder_ == null) {
        chat_ = null;
      } else {
        chat_ = null;
        chatBuilder_ = null;
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZChatRequest_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZChatRequest getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZChatRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZChatRequest build() {
      com.supercat.growstone.network.messages.ZChatRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZChatRequest buildPartial() {
      com.supercat.growstone.network.messages.ZChatRequest result = new com.supercat.growstone.network.messages.ZChatRequest(this);
      if (chatBuilder_ == null) {
        result.chat_ = chat_;
      } else {
        result.chat_ = chatBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZChatRequest) {
        return mergeFrom((com.supercat.growstone.network.messages.ZChatRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZChatRequest other) {
      if (other == com.supercat.growstone.network.messages.ZChatRequest.getDefaultInstance()) return this;
      if (other.hasChat()) {
        mergeChat(other.getChat());
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
      com.supercat.growstone.network.messages.ZChatRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZChatRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private com.supercat.growstone.network.messages.TChat chat_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TChat, com.supercat.growstone.network.messages.TChat.Builder, com.supercat.growstone.network.messages.TChatOrBuilder> chatBuilder_;
    /**
     * <code>.TChat chat = 1;</code>
     * @return Whether the chat field is set.
     */
    public boolean hasChat() {
      return chatBuilder_ != null || chat_ != null;
    }
    /**
     * <code>.TChat chat = 1;</code>
     * @return The chat.
     */
    public com.supercat.growstone.network.messages.TChat getChat() {
      if (chatBuilder_ == null) {
        return chat_ == null ? com.supercat.growstone.network.messages.TChat.getDefaultInstance() : chat_;
      } else {
        return chatBuilder_.getMessage();
      }
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    public Builder setChat(com.supercat.growstone.network.messages.TChat value) {
      if (chatBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        chat_ = value;
        onChanged();
      } else {
        chatBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    public Builder setChat(
        com.supercat.growstone.network.messages.TChat.Builder builderForValue) {
      if (chatBuilder_ == null) {
        chat_ = builderForValue.build();
        onChanged();
      } else {
        chatBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    public Builder mergeChat(com.supercat.growstone.network.messages.TChat value) {
      if (chatBuilder_ == null) {
        if (chat_ != null) {
          chat_ =
            com.supercat.growstone.network.messages.TChat.newBuilder(chat_).mergeFrom(value).buildPartial();
        } else {
          chat_ = value;
        }
        onChanged();
      } else {
        chatBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    public Builder clearChat() {
      if (chatBuilder_ == null) {
        chat_ = null;
        onChanged();
      } else {
        chat_ = null;
        chatBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    public com.supercat.growstone.network.messages.TChat.Builder getChatBuilder() {
      
      onChanged();
      return getChatFieldBuilder().getBuilder();
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    public com.supercat.growstone.network.messages.TChatOrBuilder getChatOrBuilder() {
      if (chatBuilder_ != null) {
        return chatBuilder_.getMessageOrBuilder();
      } else {
        return chat_ == null ?
            com.supercat.growstone.network.messages.TChat.getDefaultInstance() : chat_;
      }
    }
    /**
     * <code>.TChat chat = 1;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TChat, com.supercat.growstone.network.messages.TChat.Builder, com.supercat.growstone.network.messages.TChatOrBuilder> 
        getChatFieldBuilder() {
      if (chatBuilder_ == null) {
        chatBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TChat, com.supercat.growstone.network.messages.TChat.Builder, com.supercat.growstone.network.messages.TChatOrBuilder>(
                getChat(),
                getParentForChildren(),
                isClean());
        chat_ = null;
      }
      return chatBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZChatRequest)
  }

  // @@protoc_insertion_point(class_scope:ZChatRequest)
  private static final com.supercat.growstone.network.messages.ZChatRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZChatRequest();
  }

  public static com.supercat.growstone.network.messages.ZChatRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZChatRequest>
      PARSER = new com.google.protobuf.AbstractParser<ZChatRequest>() {
    @java.lang.Override
    public ZChatRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZChatRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZChatRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZChatRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZChatRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

