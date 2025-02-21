// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZAttackPowerRankResponse}
 */
public final class ZAttackPowerRankResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZAttackPowerRankResponse)
    ZAttackPowerRankResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZAttackPowerRankResponse.newBuilder() to construct.
  private ZAttackPowerRankResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZAttackPowerRankResponse() {
    players_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZAttackPowerRankResponse();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZAttackPowerRankResponse(
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
            com.supercat.growstone.network.messages.TAttackRank.Builder subBuilder = null;
            if (myRank_ != null) {
              subBuilder = myRank_.toBuilder();
            }
            myRank_ = input.readMessage(com.supercat.growstone.network.messages.TAttackRank.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(myRank_);
              myRank_ = subBuilder.buildPartial();
            }

            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              players_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TAttackRank>();
              mutable_bitField0_ |= 0x00000001;
            }
            players_.add(
                input.readMessage(com.supercat.growstone.network.messages.TAttackRank.parser(), extensionRegistry));
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZAttackPowerRankResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZAttackPowerRankResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZAttackPowerRankResponse.class, com.supercat.growstone.network.messages.ZAttackPowerRankResponse.Builder.class);
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

  public static final int MY_RANK_FIELD_NUMBER = 2;
  private com.supercat.growstone.network.messages.TAttackRank myRank_;
  /**
   * <code>.TAttackRank my_rank = 2;</code>
   * @return Whether the myRank field is set.
   */
  @java.lang.Override
  public boolean hasMyRank() {
    return myRank_ != null;
  }
  /**
   * <code>.TAttackRank my_rank = 2;</code>
   * @return The myRank.
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TAttackRank getMyRank() {
    return myRank_ == null ? com.supercat.growstone.network.messages.TAttackRank.getDefaultInstance() : myRank_;
  }
  /**
   * <code>.TAttackRank my_rank = 2;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TAttackRankOrBuilder getMyRankOrBuilder() {
    return getMyRank();
  }

  public static final int PLAYERS_FIELD_NUMBER = 3;
  private java.util.List<com.supercat.growstone.network.messages.TAttackRank> players_;
  /**
   * <code>repeated .TAttackRank players = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TAttackRank> getPlayersList() {
    return players_;
  }
  /**
   * <code>repeated .TAttackRank players = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TAttackRankOrBuilder> 
      getPlayersOrBuilderList() {
    return players_;
  }
  /**
   * <code>repeated .TAttackRank players = 3;</code>
   */
  @java.lang.Override
  public int getPlayersCount() {
    return players_.size();
  }
  /**
   * <code>repeated .TAttackRank players = 3;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TAttackRank getPlayers(int index) {
    return players_.get(index);
  }
  /**
   * <code>repeated .TAttackRank players = 3;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TAttackRankOrBuilder getPlayersOrBuilder(
      int index) {
    return players_.get(index);
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
    if (myRank_ != null) {
      output.writeMessage(2, getMyRank());
    }
    for (int i = 0; i < players_.size(); i++) {
      output.writeMessage(3, players_.get(i));
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
    if (myRank_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(2, getMyRank());
    }
    for (int i = 0; i < players_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, players_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZAttackPowerRankResponse)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZAttackPowerRankResponse other = (com.supercat.growstone.network.messages.ZAttackPowerRankResponse) obj;

    if (getStatus()
        != other.getStatus()) return false;
    if (hasMyRank() != other.hasMyRank()) return false;
    if (hasMyRank()) {
      if (!getMyRank()
          .equals(other.getMyRank())) return false;
    }
    if (!getPlayersList()
        .equals(other.getPlayersList())) return false;
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
    if (hasMyRank()) {
      hash = (37 * hash) + MY_RANK_FIELD_NUMBER;
      hash = (53 * hash) + getMyRank().hashCode();
    }
    if (getPlayersCount() > 0) {
      hash = (37 * hash) + PLAYERS_FIELD_NUMBER;
      hash = (53 * hash) + getPlayersList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZAttackPowerRankResponse prototype) {
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
   * Protobuf type {@code ZAttackPowerRankResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZAttackPowerRankResponse)
      com.supercat.growstone.network.messages.ZAttackPowerRankResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZAttackPowerRankResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZAttackPowerRankResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZAttackPowerRankResponse.class, com.supercat.growstone.network.messages.ZAttackPowerRankResponse.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZAttackPowerRankResponse.newBuilder()
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

      if (myRankBuilder_ == null) {
        myRank_ = null;
      } else {
        myRank_ = null;
        myRankBuilder_ = null;
      }
      if (playersBuilder_ == null) {
        players_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        playersBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZAttackPowerRankResponse_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZAttackPowerRankResponse getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZAttackPowerRankResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZAttackPowerRankResponse build() {
      com.supercat.growstone.network.messages.ZAttackPowerRankResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZAttackPowerRankResponse buildPartial() {
      com.supercat.growstone.network.messages.ZAttackPowerRankResponse result = new com.supercat.growstone.network.messages.ZAttackPowerRankResponse(this);
      int from_bitField0_ = bitField0_;
      result.status_ = status_;
      if (myRankBuilder_ == null) {
        result.myRank_ = myRank_;
      } else {
        result.myRank_ = myRankBuilder_.build();
      }
      if (playersBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          players_ = java.util.Collections.unmodifiableList(players_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.players_ = players_;
      } else {
        result.players_ = playersBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.ZAttackPowerRankResponse) {
        return mergeFrom((com.supercat.growstone.network.messages.ZAttackPowerRankResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZAttackPowerRankResponse other) {
      if (other == com.supercat.growstone.network.messages.ZAttackPowerRankResponse.getDefaultInstance()) return this;
      if (other.getStatus() != 0) {
        setStatus(other.getStatus());
      }
      if (other.hasMyRank()) {
        mergeMyRank(other.getMyRank());
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
      com.supercat.growstone.network.messages.ZAttackPowerRankResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZAttackPowerRankResponse) e.getUnfinishedMessage();
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

    private com.supercat.growstone.network.messages.TAttackRank myRank_;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TAttackRank, com.supercat.growstone.network.messages.TAttackRank.Builder, com.supercat.growstone.network.messages.TAttackRankOrBuilder> myRankBuilder_;
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     * @return Whether the myRank field is set.
     */
    public boolean hasMyRank() {
      return myRankBuilder_ != null || myRank_ != null;
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     * @return The myRank.
     */
    public com.supercat.growstone.network.messages.TAttackRank getMyRank() {
      if (myRankBuilder_ == null) {
        return myRank_ == null ? com.supercat.growstone.network.messages.TAttackRank.getDefaultInstance() : myRank_;
      } else {
        return myRankBuilder_.getMessage();
      }
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    public Builder setMyRank(com.supercat.growstone.network.messages.TAttackRank value) {
      if (myRankBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        myRank_ = value;
        onChanged();
      } else {
        myRankBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    public Builder setMyRank(
        com.supercat.growstone.network.messages.TAttackRank.Builder builderForValue) {
      if (myRankBuilder_ == null) {
        myRank_ = builderForValue.build();
        onChanged();
      } else {
        myRankBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    public Builder mergeMyRank(com.supercat.growstone.network.messages.TAttackRank value) {
      if (myRankBuilder_ == null) {
        if (myRank_ != null) {
          myRank_ =
            com.supercat.growstone.network.messages.TAttackRank.newBuilder(myRank_).mergeFrom(value).buildPartial();
        } else {
          myRank_ = value;
        }
        onChanged();
      } else {
        myRankBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    public Builder clearMyRank() {
      if (myRankBuilder_ == null) {
        myRank_ = null;
        onChanged();
      } else {
        myRank_ = null;
        myRankBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRank.Builder getMyRankBuilder() {
      
      onChanged();
      return getMyRankFieldBuilder().getBuilder();
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRankOrBuilder getMyRankOrBuilder() {
      if (myRankBuilder_ != null) {
        return myRankBuilder_.getMessageOrBuilder();
      } else {
        return myRank_ == null ?
            com.supercat.growstone.network.messages.TAttackRank.getDefaultInstance() : myRank_;
      }
    }
    /**
     * <code>.TAttackRank my_rank = 2;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.supercat.growstone.network.messages.TAttackRank, com.supercat.growstone.network.messages.TAttackRank.Builder, com.supercat.growstone.network.messages.TAttackRankOrBuilder> 
        getMyRankFieldBuilder() {
      if (myRankBuilder_ == null) {
        myRankBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.supercat.growstone.network.messages.TAttackRank, com.supercat.growstone.network.messages.TAttackRank.Builder, com.supercat.growstone.network.messages.TAttackRankOrBuilder>(
                getMyRank(),
                getParentForChildren(),
                isClean());
        myRank_ = null;
      }
      return myRankBuilder_;
    }

    private java.util.List<com.supercat.growstone.network.messages.TAttackRank> players_ =
      java.util.Collections.emptyList();
    private void ensurePlayersIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        players_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TAttackRank>(players_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TAttackRank, com.supercat.growstone.network.messages.TAttackRank.Builder, com.supercat.growstone.network.messages.TAttackRankOrBuilder> playersBuilder_;

    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TAttackRank> getPlayersList() {
      if (playersBuilder_ == null) {
        return java.util.Collections.unmodifiableList(players_);
      } else {
        return playersBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public int getPlayersCount() {
      if (playersBuilder_ == null) {
        return players_.size();
      } else {
        return playersBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRank getPlayers(int index) {
      if (playersBuilder_ == null) {
        return players_.get(index);
      } else {
        return playersBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder setPlayers(
        int index, com.supercat.growstone.network.messages.TAttackRank value) {
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder setPlayers(
        int index, com.supercat.growstone.network.messages.TAttackRank.Builder builderForValue) {
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder addPlayers(com.supercat.growstone.network.messages.TAttackRank value) {
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder addPlayers(
        int index, com.supercat.growstone.network.messages.TAttackRank value) {
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder addPlayers(
        com.supercat.growstone.network.messages.TAttackRank.Builder builderForValue) {
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder addPlayers(
        int index, com.supercat.growstone.network.messages.TAttackRank.Builder builderForValue) {
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public Builder addAllPlayers(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TAttackRank> values) {
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
     * <code>repeated .TAttackRank players = 3;</code>
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
     * <code>repeated .TAttackRank players = 3;</code>
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
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRank.Builder getPlayersBuilder(
        int index) {
      return getPlayersFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRankOrBuilder getPlayersOrBuilder(
        int index) {
      if (playersBuilder_ == null) {
        return players_.get(index);  } else {
        return playersBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TAttackRankOrBuilder> 
         getPlayersOrBuilderList() {
      if (playersBuilder_ != null) {
        return playersBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(players_);
      }
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRank.Builder addPlayersBuilder() {
      return getPlayersFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TAttackRank.getDefaultInstance());
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public com.supercat.growstone.network.messages.TAttackRank.Builder addPlayersBuilder(
        int index) {
      return getPlayersFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TAttackRank.getDefaultInstance());
    }
    /**
     * <code>repeated .TAttackRank players = 3;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TAttackRank.Builder> 
         getPlayersBuilderList() {
      return getPlayersFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TAttackRank, com.supercat.growstone.network.messages.TAttackRank.Builder, com.supercat.growstone.network.messages.TAttackRankOrBuilder> 
        getPlayersFieldBuilder() {
      if (playersBuilder_ == null) {
        playersBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TAttackRank, com.supercat.growstone.network.messages.TAttackRank.Builder, com.supercat.growstone.network.messages.TAttackRankOrBuilder>(
                players_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        players_ = null;
      }
      return playersBuilder_;
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


    // @@protoc_insertion_point(builder_scope:ZAttackPowerRankResponse)
  }

  // @@protoc_insertion_point(class_scope:ZAttackPowerRankResponse)
  private static final com.supercat.growstone.network.messages.ZAttackPowerRankResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZAttackPowerRankResponse();
  }

  public static com.supercat.growstone.network.messages.ZAttackPowerRankResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZAttackPowerRankResponse>
      PARSER = new com.google.protobuf.AbstractParser<ZAttackPowerRankResponse>() {
    @java.lang.Override
    public ZAttackPowerRankResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZAttackPowerRankResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZAttackPowerRankResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZAttackPowerRankResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZAttackPowerRankResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

