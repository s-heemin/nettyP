// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZRaidDungeonClearRequest}
 */
public final class ZRaidDungeonClearRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZRaidDungeonClearRequest)
    ZRaidDungeonClearRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZRaidDungeonClearRequest.newBuilder() to construct.
  private ZRaidDungeonClearRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZRaidDungeonClearRequest() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZRaidDungeonClearRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZRaidDungeonClearRequest(
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

            id_ = input.readInt64();
            break;
          }
          case 16: {

            stageId_ = input.readInt32();
            break;
          }
          case 24: {

            score_ = input.readInt64();
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZRaidDungeonClearRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZRaidDungeonClearRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.class, com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.Builder.class);
  }

  public static final int ID_FIELD_NUMBER = 1;
  private long id_;
  /**
   * <code>int64 id = 1;</code>
   * @return The id.
   */
  @java.lang.Override
  public long getId() {
    return id_;
  }

  public static final int STAGE_ID_FIELD_NUMBER = 2;
  private int stageId_;
  /**
   * <code>int32 stage_id = 2;</code>
   * @return The stageId.
   */
  @java.lang.Override
  public int getStageId() {
    return stageId_;
  }

  public static final int SCORE_FIELD_NUMBER = 3;
  private long score_;
  /**
   * <code>int64 score = 3;</code>
   * @return The score.
   */
  @java.lang.Override
  public long getScore() {
    return score_;
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
    if (id_ != 0L) {
      output.writeInt64(1, id_);
    }
    if (stageId_ != 0) {
      output.writeInt32(2, stageId_);
    }
    if (score_ != 0L) {
      output.writeInt64(3, score_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (id_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, id_);
    }
    if (stageId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, stageId_);
    }
    if (score_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(3, score_);
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZRaidDungeonClearRequest)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZRaidDungeonClearRequest other = (com.supercat.growstone.network.messages.ZRaidDungeonClearRequest) obj;

    if (getId()
        != other.getId()) return false;
    if (getStageId()
        != other.getStageId()) return false;
    if (getScore()
        != other.getScore()) return false;
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
    hash = (37 * hash) + ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getId());
    hash = (37 * hash) + STAGE_ID_FIELD_NUMBER;
    hash = (53 * hash) + getStageId();
    hash = (37 * hash) + SCORE_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getScore());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZRaidDungeonClearRequest prototype) {
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
   * Protobuf type {@code ZRaidDungeonClearRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZRaidDungeonClearRequest)
      com.supercat.growstone.network.messages.ZRaidDungeonClearRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZRaidDungeonClearRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZRaidDungeonClearRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.class, com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.newBuilder()
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
      id_ = 0L;

      stageId_ = 0;

      score_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZRaidDungeonClearRequest_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZRaidDungeonClearRequest getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZRaidDungeonClearRequest build() {
      com.supercat.growstone.network.messages.ZRaidDungeonClearRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZRaidDungeonClearRequest buildPartial() {
      com.supercat.growstone.network.messages.ZRaidDungeonClearRequest result = new com.supercat.growstone.network.messages.ZRaidDungeonClearRequest(this);
      result.id_ = id_;
      result.stageId_ = stageId_;
      result.score_ = score_;
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
      if (other instanceof com.supercat.growstone.network.messages.ZRaidDungeonClearRequest) {
        return mergeFrom((com.supercat.growstone.network.messages.ZRaidDungeonClearRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZRaidDungeonClearRequest other) {
      if (other == com.supercat.growstone.network.messages.ZRaidDungeonClearRequest.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.getStageId() != 0) {
        setStageId(other.getStageId());
      }
      if (other.getScore() != 0L) {
        setScore(other.getScore());
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
      com.supercat.growstone.network.messages.ZRaidDungeonClearRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZRaidDungeonClearRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long id_ ;
    /**
     * <code>int64 id = 1;</code>
     * @return The id.
     */
    @java.lang.Override
    public long getId() {
      return id_;
    }
    /**
     * <code>int64 id = 1;</code>
     * @param value The id to set.
     * @return This builder for chaining.
     */
    public Builder setId(long value) {
      
      id_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearId() {
      
      id_ = 0L;
      onChanged();
      return this;
    }

    private int stageId_ ;
    /**
     * <code>int32 stage_id = 2;</code>
     * @return The stageId.
     */
    @java.lang.Override
    public int getStageId() {
      return stageId_;
    }
    /**
     * <code>int32 stage_id = 2;</code>
     * @param value The stageId to set.
     * @return This builder for chaining.
     */
    public Builder setStageId(int value) {
      
      stageId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 stage_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearStageId() {
      
      stageId_ = 0;
      onChanged();
      return this;
    }

    private long score_ ;
    /**
     * <code>int64 score = 3;</code>
     * @return The score.
     */
    @java.lang.Override
    public long getScore() {
      return score_;
    }
    /**
     * <code>int64 score = 3;</code>
     * @param value The score to set.
     * @return This builder for chaining.
     */
    public Builder setScore(long value) {
      
      score_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 score = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearScore() {
      
      score_ = 0L;
      onChanged();
      return this;
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


    // @@protoc_insertion_point(builder_scope:ZRaidDungeonClearRequest)
  }

  // @@protoc_insertion_point(class_scope:ZRaidDungeonClearRequest)
  private static final com.supercat.growstone.network.messages.ZRaidDungeonClearRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZRaidDungeonClearRequest();
  }

  public static com.supercat.growstone.network.messages.ZRaidDungeonClearRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZRaidDungeonClearRequest>
      PARSER = new com.google.protobuf.AbstractParser<ZRaidDungeonClearRequest>() {
    @java.lang.Override
    public ZRaidDungeonClearRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZRaidDungeonClearRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZRaidDungeonClearRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZRaidDungeonClearRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZRaidDungeonClearRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

