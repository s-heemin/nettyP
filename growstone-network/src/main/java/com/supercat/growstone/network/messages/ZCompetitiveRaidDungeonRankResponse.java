// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZCompetitiveRaidDungeonRankResponse}
 */
public final class ZCompetitiveRaidDungeonRankResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZCompetitiveRaidDungeonRankResponse)
    ZCompetitiveRaidDungeonRankResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZCompetitiveRaidDungeonRankResponse.newBuilder() to construct.
  private ZCompetitiveRaidDungeonRankResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZCompetitiveRaidDungeonRankResponse() {
    players_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZCompetitiveRaidDungeonRankResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZCompetitiveRaidDungeonRankResponse(
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
              players_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo>();
              mutable_bitField0_ |= 0x00000001;
            }
            players_.add(
                input.readMessage(com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.parser(), extensionRegistry));
            break;
          }
          case 24: {

            nextRank_ = input.readInt32();
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
        players_ = java.util.Collections.unmodifiableList(players_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZCompetitiveRaidDungeonRankResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZCompetitiveRaidDungeonRankResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.class, com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.Builder.class);
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

  public static final int PLAYERS_FIELD_NUMBER = 2;
  private java.util.List<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo> players_;
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo> getPlayersList() {
    return players_;
  }
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder> 
      getPlayersOrBuilderList() {
    return players_;
  }
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  @java.lang.Override
  public int getPlayersCount() {
    return players_.size();
  }
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo getPlayers(int index) {
    return players_.get(index);
  }
  /**
   * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder getPlayersOrBuilder(
      int index) {
    return players_.get(index);
  }

  public static final int NEXT_RANK_FIELD_NUMBER = 3;
  private int nextRank_;
  /**
   * <code>int32 next_rank = 3;</code>
   * @return The nextRank.
   */
  @java.lang.Override
  public int getNextRank() {
    return nextRank_;
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
    for (int i = 0; i < players_.size(); i++) {
      output.writeMessage(2, players_.get(i));
    }
    if (nextRank_ != 0) {
      output.writeInt32(3, nextRank_);
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
    for (int i = 0; i < players_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, players_.get(i));
    }
    if (nextRank_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, nextRank_);
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse other = (com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (!getPlayersList()
        .equals(other.getPlayersList())) return false;
    if (getNextRank()
        != other.getNextRank()) return false;
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
    if (getPlayersCount() > 0) {
      hash = (37 * hash) + PLAYERS_FIELD_NUMBER;
      hash = (53 * hash) + getPlayersList().hashCode();
    }
    hash = (37 * hash) + NEXT_RANK_FIELD_NUMBER;
    hash = (53 * hash) + getNextRank();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse prototype) {
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
   * Protobuf type {@code ZCompetitiveRaidDungeonRankResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZCompetitiveRaidDungeonRankResponse)
      com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCompetitiveRaidDungeonRankResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCompetitiveRaidDungeonRankResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.class, com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.newBuilder()
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
        getPlayersFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      status_ = 0;

      if (playersBuilder_ == null) {
        players_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        playersBuilder_.clear();
      }
      nextRank_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZCompetitiveRaidDungeonRankResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse build() {
      com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse buildPartial() {
      com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse result = new com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (playersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          players_ = java.util.Collections.unmodifiableList(players_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.players_ = players_;
      } else {
        result.players_ = playersBuilder_.build();
      }
      result.nextRank_ = nextRank_;
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
      if (other instanceof com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse other) {
      if (other == com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (playersBuilder_ == null) {
        if (!other.players_.isEmpty()) {
          if (players_.isEmpty()) {
            players_ = other.players_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensurePlayersIsMutable();
            players_.addAll(other.players_);
          }
          onChanged();
        }
      } else {
        if (!other.players_.isEmpty()) {
          if (playersBuilder_.isEmpty()) {
            playersBuilder_.dispose();
            playersBuilder_ = null;
            players_ = other.players_;
            bitField0_ = (bitField0_ & ~0x00000001);
            playersBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getPlayersFieldBuilder() : null;
          } else {
            playersBuilder_.addAllMessages(other.players_);
          }
        }
      }
      if (other.getNextRank() != 0) {
        setNextRank(other.getNextRank());
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
      com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse) e.getUnfinishedMessage();
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

    private java.util.List<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo> players_ =
      java.util.Collections.emptyList();
    private void ensurePlayersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        players_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo>(players_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder> playersBuilder_;

    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo> getPlayersList() {
      if (playersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(players_);
      } else {
        return playersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public int getPlayersCount() {
      if (playersBuilder_ == null) {
        return players_.size();
      } else {
        return playersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo getPlayers(int index) {
      if (playersBuilder_ == null) {
        return players_.get(index);
      } else {
        return playersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder setPlayers(
        int index, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo value) {
      if (playersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlayersIsMutable();
        players_.set(index, value);
        onChanged();
      } else {
        playersBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder setPlayers(
        int index, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder builderForValue) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.set(index, builderForValue.build());
        onChanged();
      } else {
        playersBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder addPlayers(com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo value) {
      if (playersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlayersIsMutable();
        players_.add(value);
        onChanged();
      } else {
        playersBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder addPlayers(
        int index, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo value) {
      if (playersBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensurePlayersIsMutable();
        players_.add(index, value);
        onChanged();
      } else {
        playersBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder addPlayers(
        com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder builderForValue) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.add(builderForValue.build());
        onChanged();
      } else {
        playersBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder addPlayers(
        int index, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder builderForValue) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.add(index, builderForValue.build());
        onChanged();
      } else {
        playersBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder addAllPlayers(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo> values) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, players_);
        onChanged();
      } else {
        playersBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder clearPlayers() {
      if (playersBuilder_ == null) {
        players_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        playersBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public Builder removePlayers(int index) {
      if (playersBuilder_ == null) {
        ensurePlayersIsMutable();
        players_.remove(index);
        onChanged();
      } else {
        playersBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder getPlayersBuilder(
        int index) {
      return getPlayersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder getPlayersOrBuilder(
        int index) {
      if (playersBuilder_ == null) {
        return players_.get(index);  } else {
        return playersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder> 
         getPlayersOrBuilderList() {
      if (playersBuilder_ != null) {
        return playersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(players_);
      }
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder addPlayersBuilder() {
      return getPlayersFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder addPlayersBuilder(
        int index) {
      return getPlayersFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.getDefaultInstance());
    }
    /**
     * <code>repeated .TCompetitiveRaidDungeonPlayerRankInfo players = 2;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder> 
         getPlayersBuilderList() {
      return getPlayersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder> 
        getPlayersFieldBuilder() {
      if (playersBuilder_ == null) {
        playersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfo.Builder, com.supercat.growstone.network.messages.TCompetitiveRaidDungeonPlayerRankInfoOrBuilder>(
                players_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        players_ = null;
      }
      return playersBuilder_;
    }

    private int nextRank_ ;
    /**
     * <code>int32 next_rank = 3;</code>
     * @return The nextRank.
     */
    @java.lang.Override
    public int getNextRank() {
      return nextRank_;
    }
    /**
     * <code>int32 next_rank = 3;</code>
     * @param value The nextRank to set.
     * @return This builder for chaining.
     */
    public Builder setNextRank(int value) {
      
      nextRank_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 next_rank = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearNextRank() {
      
      nextRank_ = 0;
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


    // @@protoc_insertion_point(builder_scope:ZCompetitiveRaidDungeonRankResponse)
  }

  // @@protoc_insertion_point(class_scope:ZCompetitiveRaidDungeonRankResponse)
  private static final com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse();
  }

  public static com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZCompetitiveRaidDungeonRankResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZCompetitiveRaidDungeonRankResponse>() {
    @java.lang.Override
    public ZCompetitiveRaidDungeonRankResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZCompetitiveRaidDungeonRankResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZCompetitiveRaidDungeonRankResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZCompetitiveRaidDungeonRankResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZCompetitiveRaidDungeonRankResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

