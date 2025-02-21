// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code TFriendInfo}
 */
public final class TFriendInfo extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TFriendInfo)
    TFriendInfoOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TFriendInfo.newBuilder() to construct.
  private TFriendInfo(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TFriendInfo() {
    name_ = "";
    state_ = 0;
    friendCode_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TFriendInfo();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TFriendInfo(
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

            playerId_ = input.readInt64();
            break;
          }
          case 16: {

            friendId_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 32: {

            portraitId_ = input.readInt64();
            break;
          }
          case 40: {

            level_ = input.readInt32();
            break;
          }
          case 48: {

            attackPower_ = input.readInt64();
            break;
          }
          case 56: {
            int rawValue = input.readEnum();

            state_ = rawValue;
            break;
          }
          case 64: {

            giftExpireTime_ = input.readInt64();
            break;
          }
          case 72: {

            timeSinceLastLogoutTime_ = input.readInt64();
            break;
          }
          case 82: {
            java.lang.String s = input.readStringRequireUtf8();

            friendCode_ = s;
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
    return com.supercat.growstone.network.messages.Network.internal_static_TFriendInfo_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_TFriendInfo_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.TFriendInfo.class, com.supercat.growstone.network.messages.TFriendInfo.Builder.class);
  }

  public static final int PLAYER_ID_FIELD_NUMBER = 1;
  private long playerId_;
  /**
   * <code>int64 player_id = 1;</code>
   * @return The playerId.
   */
  @java.lang.Override
  public long getPlayerId() {
    return playerId_;
  }

  public static final int FRIEND_ID_FIELD_NUMBER = 2;
  private long friendId_;
  /**
   * <code>int64 friend_id = 2;</code>
   * @return The friendId.
   */
  @java.lang.Override
  public long getFriendId() {
    return friendId_;
  }

  public static final int NAME_FIELD_NUMBER = 3;
  private volatile java.lang.Object name_;
  /**
   * <code>string name = 3;</code>
   * @return The name.
   */
  @java.lang.Override
  public java.lang.String getName() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      name_ = s;
      return s;
    }
  }
  /**
   * <code>string name = 3;</code>
   * @return The bytes for name.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getNameBytes() {
    java.lang.Object ref = name_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      name_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PORTRAIT_ID_FIELD_NUMBER = 4;
  private long portraitId_;
  /**
   * <code>int64 portrait_id = 4;</code>
   * @return The portraitId.
   */
  @java.lang.Override
  public long getPortraitId() {
    return portraitId_;
  }

  public static final int LEVEL_FIELD_NUMBER = 5;
  private int level_;
  /**
   * <code>int32 level = 5;</code>
   * @return The level.
   */
  @java.lang.Override
  public int getLevel() {
    return level_;
  }

  public static final int ATTACK_POWER_FIELD_NUMBER = 6;
  private long attackPower_;
  /**
   * <code>int64 attack_power = 6;</code>
   * @return The attackPower.
   */
  @java.lang.Override
  public long getAttackPower() {
    return attackPower_;
  }

  public static final int STATE_FIELD_NUMBER = 7;
  private int state_;
  /**
   * <code>.ZFriend.State state = 7;</code>
   * @return The enum numeric value on the wire for state.
   */
  @java.lang.Override public int getStateValue() {
    return state_;
  }
  /**
   * <code>.ZFriend.State state = 7;</code>
   * @return The state.
   */
  @java.lang.Override public com.supercat.growstone.network.messages.ZFriend.State getState() {
    @SuppressWarnings("deprecation")
    com.supercat.growstone.network.messages.ZFriend.State result = com.supercat.growstone.network.messages.ZFriend.State.valueOf(state_);
    return result == null ? com.supercat.growstone.network.messages.ZFriend.State.UNRECOGNIZED : result;
  }

  public static final int GIFT_EXPIRE_TIME_FIELD_NUMBER = 8;
  private long giftExpireTime_;
  /**
   * <code>int64 gift_expire_time = 8;</code>
   * @return The giftExpireTime.
   */
  @java.lang.Override
  public long getGiftExpireTime() {
    return giftExpireTime_;
  }

  public static final int TIME_SINCE_LAST_LOGOUT_TIME_FIELD_NUMBER = 9;
  private long timeSinceLastLogoutTime_;
  /**
   * <code>int64 time_since_last_logout_time = 9;</code>
   * @return The timeSinceLastLogoutTime.
   */
  @java.lang.Override
  public long getTimeSinceLastLogoutTime() {
    return timeSinceLastLogoutTime_;
  }

  public static final int FRIEND_CODE_FIELD_NUMBER = 10;
  private volatile java.lang.Object friendCode_;
  /**
   * <code>string friend_code = 10;</code>
   * @return The friendCode.
   */
  @java.lang.Override
  public java.lang.String getFriendCode() {
    java.lang.Object ref = friendCode_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      friendCode_ = s;
      return s;
    }
  }
  /**
   * <code>string friend_code = 10;</code>
   * @return The bytes for friendCode.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getFriendCodeBytes() {
    java.lang.Object ref = friendCode_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      friendCode_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    if (playerId_ != 0L) {
      output.writeInt64(1, playerId_);
    }
    if (friendId_ != 0L) {
      output.writeInt64(2, friendId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
    }
    if (portraitId_ != 0L) {
      output.writeInt64(4, portraitId_);
    }
    if (level_ != 0) {
      output.writeInt32(5, level_);
    }
    if (attackPower_ != 0L) {
      output.writeInt64(6, attackPower_);
    }
    if (state_ != com.supercat.growstone.network.messages.ZFriend.State.NONE.getNumber()) {
      output.writeEnum(7, state_);
    }
    if (giftExpireTime_ != 0L) {
      output.writeInt64(8, giftExpireTime_);
    }
    if (timeSinceLastLogoutTime_ != 0L) {
      output.writeInt64(9, timeSinceLastLogoutTime_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(friendCode_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 10, friendCode_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (playerId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(1, playerId_);
    }
    if (friendId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, friendId_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
    }
    if (portraitId_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, portraitId_);
    }
    if (level_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, level_);
    }
    if (attackPower_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(6, attackPower_);
    }
    if (state_ != com.supercat.growstone.network.messages.ZFriend.State.NONE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(7, state_);
    }
    if (giftExpireTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(8, giftExpireTime_);
    }
    if (timeSinceLastLogoutTime_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(9, timeSinceLastLogoutTime_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(friendCode_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(10, friendCode_);
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
    if (!(obj instanceof com.supercat.growstone.network.messages.TFriendInfo)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.TFriendInfo other = (com.supercat.growstone.network.messages.TFriendInfo) obj;

    if (getPlayerId()
        != other.getPlayerId()) return false;
    if (getFriendId()
        != other.getFriendId()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (getPortraitId()
        != other.getPortraitId()) return false;
    if (getLevel()
        != other.getLevel()) return false;
    if (getAttackPower()
        != other.getAttackPower()) return false;
    if (state_ != other.state_) return false;
    if (getGiftExpireTime()
        != other.getGiftExpireTime()) return false;
    if (getTimeSinceLastLogoutTime()
        != other.getTimeSinceLastLogoutTime()) return false;
    if (!getFriendCode()
        .equals(other.getFriendCode())) return false;
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
    hash = (37 * hash) + PLAYER_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPlayerId());
    hash = (37 * hash) + FRIEND_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getFriendId());
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + PORTRAIT_ID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getPortraitId());
    hash = (37 * hash) + LEVEL_FIELD_NUMBER;
    hash = (53 * hash) + getLevel();
    hash = (37 * hash) + ATTACK_POWER_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getAttackPower());
    hash = (37 * hash) + STATE_FIELD_NUMBER;
    hash = (53 * hash) + state_;
    hash = (37 * hash) + GIFT_EXPIRE_TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getGiftExpireTime());
    hash = (37 * hash) + TIME_SINCE_LAST_LOGOUT_TIME_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getTimeSinceLastLogoutTime());
    hash = (37 * hash) + FRIEND_CODE_FIELD_NUMBER;
    hash = (53 * hash) + getFriendCode().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TFriendInfo parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.TFriendInfo prototype) {
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
   * Protobuf type {@code TFriendInfo}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TFriendInfo)
      com.supercat.growstone.network.messages.TFriendInfoOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_TFriendInfo_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_TFriendInfo_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.TFriendInfo.class, com.supercat.growstone.network.messages.TFriendInfo.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.TFriendInfo.newBuilder()
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
      playerId_ = 0L;

      friendId_ = 0L;

      name_ = "";

      portraitId_ = 0L;

      level_ = 0;

      attackPower_ = 0L;

      state_ = 0;

      giftExpireTime_ = 0L;

      timeSinceLastLogoutTime_ = 0L;

      friendCode_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_TFriendInfo_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TFriendInfo getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.TFriendInfo.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TFriendInfo build() {
      com.supercat.growstone.network.messages.TFriendInfo result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TFriendInfo buildPartial() {
      com.supercat.growstone.network.messages.TFriendInfo result = new com.supercat.growstone.network.messages.TFriendInfo(this);
      result.playerId_ = playerId_;
      result.friendId_ = friendId_;
      result.name_ = name_;
      result.portraitId_ = portraitId_;
      result.level_ = level_;
      result.attackPower_ = attackPower_;
      result.state_ = state_;
      result.giftExpireTime_ = giftExpireTime_;
      result.timeSinceLastLogoutTime_ = timeSinceLastLogoutTime_;
      result.friendCode_ = friendCode_;
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
      if (other instanceof com.supercat.growstone.network.messages.TFriendInfo) {
        return mergeFrom((com.supercat.growstone.network.messages.TFriendInfo)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.TFriendInfo other) {
      if (other == com.supercat.growstone.network.messages.TFriendInfo.getDefaultInstance()) return this;
      if (other.getPlayerId() != 0L) {
        setPlayerId(other.getPlayerId());
      }
      if (other.getFriendId() != 0L) {
        setFriendId(other.getFriendId());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.getPortraitId() != 0L) {
        setPortraitId(other.getPortraitId());
      }
      if (other.getLevel() != 0) {
        setLevel(other.getLevel());
      }
      if (other.getAttackPower() != 0L) {
        setAttackPower(other.getAttackPower());
      }
      if (other.state_ != 0) {
        setStateValue(other.getStateValue());
      }
      if (other.getGiftExpireTime() != 0L) {
        setGiftExpireTime(other.getGiftExpireTime());
      }
      if (other.getTimeSinceLastLogoutTime() != 0L) {
        setTimeSinceLastLogoutTime(other.getTimeSinceLastLogoutTime());
      }
      if (!other.getFriendCode().isEmpty()) {
        friendCode_ = other.friendCode_;
        onChanged();
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
      com.supercat.growstone.network.messages.TFriendInfo parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.TFriendInfo) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private long playerId_ ;
    /**
     * <code>int64 player_id = 1;</code>
     * @return The playerId.
     */
    @java.lang.Override
    public long getPlayerId() {
      return playerId_;
    }
    /**
     * <code>int64 player_id = 1;</code>
     * @param value The playerId to set.
     * @return This builder for chaining.
     */
    public Builder setPlayerId(long value) {
      
      playerId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 player_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPlayerId() {
      
      playerId_ = 0L;
      onChanged();
      return this;
    }

    private long friendId_ ;
    /**
     * <code>int64 friend_id = 2;</code>
     * @return The friendId.
     */
    @java.lang.Override
    public long getFriendId() {
      return friendId_;
    }
    /**
     * <code>int64 friend_id = 2;</code>
     * @param value The friendId to set.
     * @return This builder for chaining.
     */
    public Builder setFriendId(long value) {
      
      friendId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 friend_id = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearFriendId() {
      
      friendId_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object name_ = "";
    /**
     * <code>string name = 3;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        name_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string name = 3;</code>
     * @param value The name to set.
     * @return This builder for chaining.
     */
    public Builder setName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      name_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     * @return This builder for chaining.
     */
    public Builder clearName() {
      
      name_ = getDefaultInstance().getName();
      onChanged();
      return this;
    }
    /**
     * <code>string name = 3;</code>
     * @param value The bytes for name to set.
     * @return This builder for chaining.
     */
    public Builder setNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      name_ = value;
      onChanged();
      return this;
    }

    private long portraitId_ ;
    /**
     * <code>int64 portrait_id = 4;</code>
     * @return The portraitId.
     */
    @java.lang.Override
    public long getPortraitId() {
      return portraitId_;
    }
    /**
     * <code>int64 portrait_id = 4;</code>
     * @param value The portraitId to set.
     * @return This builder for chaining.
     */
    public Builder setPortraitId(long value) {
      
      portraitId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 portrait_id = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearPortraitId() {
      
      portraitId_ = 0L;
      onChanged();
      return this;
    }

    private int level_ ;
    /**
     * <code>int32 level = 5;</code>
     * @return The level.
     */
    @java.lang.Override
    public int getLevel() {
      return level_;
    }
    /**
     * <code>int32 level = 5;</code>
     * @param value The level to set.
     * @return This builder for chaining.
     */
    public Builder setLevel(int value) {
      
      level_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 level = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearLevel() {
      
      level_ = 0;
      onChanged();
      return this;
    }

    private long attackPower_ ;
    /**
     * <code>int64 attack_power = 6;</code>
     * @return The attackPower.
     */
    @java.lang.Override
    public long getAttackPower() {
      return attackPower_;
    }
    /**
     * <code>int64 attack_power = 6;</code>
     * @param value The attackPower to set.
     * @return This builder for chaining.
     */
    public Builder setAttackPower(long value) {
      
      attackPower_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 attack_power = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearAttackPower() {
      
      attackPower_ = 0L;
      onChanged();
      return this;
    }

    private int state_ = 0;
    /**
     * <code>.ZFriend.State state = 7;</code>
     * @return The enum numeric value on the wire for state.
     */
    @java.lang.Override public int getStateValue() {
      return state_;
    }
    /**
     * <code>.ZFriend.State state = 7;</code>
     * @param value The enum numeric value on the wire for state to set.
     * @return This builder for chaining.
     */
    public Builder setStateValue(int value) {
      
      state_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.ZFriend.State state = 7;</code>
     * @return The state.
     */
    @java.lang.Override
    public com.supercat.growstone.network.messages.ZFriend.State getState() {
      @SuppressWarnings("deprecation")
      com.supercat.growstone.network.messages.ZFriend.State result = com.supercat.growstone.network.messages.ZFriend.State.valueOf(state_);
      return result == null ? com.supercat.growstone.network.messages.ZFriend.State.UNRECOGNIZED : result;
    }
    /**
     * <code>.ZFriend.State state = 7;</code>
     * @param value The state to set.
     * @return This builder for chaining.
     */
    public Builder setState(com.supercat.growstone.network.messages.ZFriend.State value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      state_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.ZFriend.State state = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearState() {
      
      state_ = 0;
      onChanged();
      return this;
    }

    private long giftExpireTime_ ;
    /**
     * <code>int64 gift_expire_time = 8;</code>
     * @return The giftExpireTime.
     */
    @java.lang.Override
    public long getGiftExpireTime() {
      return giftExpireTime_;
    }
    /**
     * <code>int64 gift_expire_time = 8;</code>
     * @param value The giftExpireTime to set.
     * @return This builder for chaining.
     */
    public Builder setGiftExpireTime(long value) {
      
      giftExpireTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 gift_expire_time = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearGiftExpireTime() {
      
      giftExpireTime_ = 0L;
      onChanged();
      return this;
    }

    private long timeSinceLastLogoutTime_ ;
    /**
     * <code>int64 time_since_last_logout_time = 9;</code>
     * @return The timeSinceLastLogoutTime.
     */
    @java.lang.Override
    public long getTimeSinceLastLogoutTime() {
      return timeSinceLastLogoutTime_;
    }
    /**
     * <code>int64 time_since_last_logout_time = 9;</code>
     * @param value The timeSinceLastLogoutTime to set.
     * @return This builder for chaining.
     */
    public Builder setTimeSinceLastLogoutTime(long value) {
      
      timeSinceLastLogoutTime_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 time_since_last_logout_time = 9;</code>
     * @return This builder for chaining.
     */
    public Builder clearTimeSinceLastLogoutTime() {
      
      timeSinceLastLogoutTime_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object friendCode_ = "";
    /**
     * <code>string friend_code = 10;</code>
     * @return The friendCode.
     */
    public java.lang.String getFriendCode() {
      java.lang.Object ref = friendCode_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        friendCode_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string friend_code = 10;</code>
     * @return The bytes for friendCode.
     */
    public com.google.protobuf.ByteString
        getFriendCodeBytes() {
      java.lang.Object ref = friendCode_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        friendCode_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string friend_code = 10;</code>
     * @param value The friendCode to set.
     * @return This builder for chaining.
     */
    public Builder setFriendCode(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      friendCode_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string friend_code = 10;</code>
     * @return This builder for chaining.
     */
    public Builder clearFriendCode() {
      
      friendCode_ = getDefaultInstance().getFriendCode();
      onChanged();
      return this;
    }
    /**
     * <code>string friend_code = 10;</code>
     * @param value The bytes for friendCode to set.
     * @return This builder for chaining.
     */
    public Builder setFriendCodeBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      friendCode_ = value;
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


    // @@protoc_insertion_point(builder_scope:TFriendInfo)
  }

  // @@protoc_insertion_point(class_scope:TFriendInfo)
  private static final com.supercat.growstone.network.messages.TFriendInfo DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.TFriendInfo();
  }

  public static com.supercat.growstone.network.messages.TFriendInfo getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TFriendInfo>
      PARSER = new com.google.protobuf.AbstractParser<TFriendInfo>() {
    @java.lang.Override
    public TFriendInfo parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TFriendInfo(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TFriendInfo> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TFriendInfo> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.TFriendInfo getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

