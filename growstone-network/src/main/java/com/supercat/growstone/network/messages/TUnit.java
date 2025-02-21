// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code TUnit}
 */
public final class TUnit extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TUnit)
    TUnitOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TUnit.newBuilder() to construct.
  private TUnit(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TUnit() {
    name_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TUnit();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TUnit(
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

            type_ = input.readInt32();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            name_ = s;
            break;
          }
          case 32: {

            unitID_ = input.readInt64();
            break;
          }
          case 40: {

            posX_ = input.readInt32();
            break;
          }
          case 48: {

            posY_ = input.readInt32();
            break;
          }
          case 56: {

            hp_ = input.readInt32();
            break;
          }
          case 64: {

            maxHP_ = input.readInt32();
            break;
          }
          case 72: {

            teamTag_ = input.readInt32();
            break;
          }
          case 80: {

            dead_ = input.readBool();
            break;
          }
          case 88: {

            state_ = input.readInt32();
            break;
          }
          case 96: {

            heroPetItemID_ = input.readInt64();
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
    return com.supercat.growstone.network.messages.Network.internal_static_TUnit_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_TUnit_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.TUnit.class, com.supercat.growstone.network.messages.TUnit.Builder.class);
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

  public static final int TYPE_FIELD_NUMBER = 2;
  private int type_;
  /**
   * <code>int32 type = 2;</code>
   * @return The type.
   */
  @java.lang.Override
  public int getType() {
    return type_;
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

  public static final int UNITID_FIELD_NUMBER = 4;
  private long unitID_;
  /**
   * <code>int64 unitID = 4;</code>
   * @return The unitID.
   */
  @java.lang.Override
  public long getUnitID() {
    return unitID_;
  }

  public static final int POSX_FIELD_NUMBER = 5;
  private int posX_;
  /**
   * <code>int32 posX = 5;</code>
   * @return The posX.
   */
  @java.lang.Override
  public int getPosX() {
    return posX_;
  }

  public static final int POSY_FIELD_NUMBER = 6;
  private int posY_;
  /**
   * <code>int32 posY = 6;</code>
   * @return The posY.
   */
  @java.lang.Override
  public int getPosY() {
    return posY_;
  }

  public static final int HP_FIELD_NUMBER = 7;
  private int hp_;
  /**
   * <code>int32 hp = 7;</code>
   * @return The hp.
   */
  @java.lang.Override
  public int getHp() {
    return hp_;
  }

  public static final int MAXHP_FIELD_NUMBER = 8;
  private int maxHP_;
  /**
   * <code>int32 maxHP = 8;</code>
   * @return The maxHP.
   */
  @java.lang.Override
  public int getMaxHP() {
    return maxHP_;
  }

  public static final int TEAMTAG_FIELD_NUMBER = 9;
  private int teamTag_;
  /**
   * <code>int32 teamTag = 9;</code>
   * @return The teamTag.
   */
  @java.lang.Override
  public int getTeamTag() {
    return teamTag_;
  }

  public static final int DEAD_FIELD_NUMBER = 10;
  private boolean dead_;
  /**
   * <code>bool dead = 10;</code>
   * @return The dead.
   */
  @java.lang.Override
  public boolean getDead() {
    return dead_;
  }

  public static final int STATE_FIELD_NUMBER = 11;
  private int state_;
  /**
   * <code>int32 state = 11;</code>
   * @return The state.
   */
  @java.lang.Override
  public int getState() {
    return state_;
  }

  public static final int HEROPETITEMID_FIELD_NUMBER = 12;
  private long heroPetItemID_;
  /**
   * <code>int64 heroPetItemID = 12;</code>
   * @return The heroPetItemID.
   */
  @java.lang.Override
  public long getHeroPetItemID() {
    return heroPetItemID_;
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
    if (type_ != 0) {
      output.writeInt32(2, type_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, name_);
    }
    if (unitID_ != 0L) {
      output.writeInt64(4, unitID_);
    }
    if (posX_ != 0) {
      output.writeInt32(5, posX_);
    }
    if (posY_ != 0) {
      output.writeInt32(6, posY_);
    }
    if (hp_ != 0) {
      output.writeInt32(7, hp_);
    }
    if (maxHP_ != 0) {
      output.writeInt32(8, maxHP_);
    }
    if (teamTag_ != 0) {
      output.writeInt32(9, teamTag_);
    }
    if (dead_ != false) {
      output.writeBool(10, dead_);
    }
    if (state_ != 0) {
      output.writeInt32(11, state_);
    }
    if (heroPetItemID_ != 0L) {
      output.writeInt64(12, heroPetItemID_);
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
    if (type_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, type_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(name_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, name_);
    }
    if (unitID_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(4, unitID_);
    }
    if (posX_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(5, posX_);
    }
    if (posY_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(6, posY_);
    }
    if (hp_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(7, hp_);
    }
    if (maxHP_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(8, maxHP_);
    }
    if (teamTag_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(9, teamTag_);
    }
    if (dead_ != false) {
      size += com.google.protobuf.CodedOutputStream
        .computeBoolSize(10, dead_);
    }
    if (state_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(11, state_);
    }
    if (heroPetItemID_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(12, heroPetItemID_);
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
    if (!(obj instanceof com.supercat.growstone.network.messages.TUnit)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.TUnit other = (com.supercat.growstone.network.messages.TUnit) obj;

    if (getId()
        != other.getId()) return false;
    if (getType()
        != other.getType()) return false;
    if (!getName()
        .equals(other.getName())) return false;
    if (getUnitID()
        != other.getUnitID()) return false;
    if (getPosX()
        != other.getPosX()) return false;
    if (getPosY()
        != other.getPosY()) return false;
    if (getHp()
        != other.getHp()) return false;
    if (getMaxHP()
        != other.getMaxHP()) return false;
    if (getTeamTag()
        != other.getTeamTag()) return false;
    if (getDead()
        != other.getDead()) return false;
    if (getState()
        != other.getState()) return false;
    if (getHeroPetItemID()
        != other.getHeroPetItemID()) return false;
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
    hash = (37 * hash) + TYPE_FIELD_NUMBER;
    hash = (53 * hash) + getType();
    hash = (37 * hash) + NAME_FIELD_NUMBER;
    hash = (53 * hash) + getName().hashCode();
    hash = (37 * hash) + UNITID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getUnitID());
    hash = (37 * hash) + POSX_FIELD_NUMBER;
    hash = (53 * hash) + getPosX();
    hash = (37 * hash) + POSY_FIELD_NUMBER;
    hash = (53 * hash) + getPosY();
    hash = (37 * hash) + HP_FIELD_NUMBER;
    hash = (53 * hash) + getHp();
    hash = (37 * hash) + MAXHP_FIELD_NUMBER;
    hash = (53 * hash) + getMaxHP();
    hash = (37 * hash) + TEAMTAG_FIELD_NUMBER;
    hash = (53 * hash) + getTeamTag();
    hash = (37 * hash) + DEAD_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
        getDead());
    hash = (37 * hash) + STATE_FIELD_NUMBER;
    hash = (53 * hash) + getState();
    hash = (37 * hash) + HEROPETITEMID_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getHeroPetItemID());
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TUnit parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TUnit parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TUnit parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.TUnit prototype) {
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
   * Protobuf type {@code TUnit}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TUnit)
      com.supercat.growstone.network.messages.TUnitOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_TUnit_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_TUnit_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.TUnit.class, com.supercat.growstone.network.messages.TUnit.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.TUnit.newBuilder()
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

      type_ = 0;

      name_ = "";

      unitID_ = 0L;

      posX_ = 0;

      posY_ = 0;

      hp_ = 0;

      maxHP_ = 0;

      teamTag_ = 0;

      dead_ = false;

      state_ = 0;

      heroPetItemID_ = 0L;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_TUnit_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TUnit getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.TUnit.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TUnit build() {
      com.supercat.growstone.network.messages.TUnit result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TUnit buildPartial() {
      com.supercat.growstone.network.messages.TUnit result = new com.supercat.growstone.network.messages.TUnit(this);
      result.id_ = id_;
      result.type_ = type_;
      result.name_ = name_;
      result.unitID_ = unitID_;
      result.posX_ = posX_;
      result.posY_ = posY_;
      result.hp_ = hp_;
      result.maxHP_ = maxHP_;
      result.teamTag_ = teamTag_;
      result.dead_ = dead_;
      result.state_ = state_;
      result.heroPetItemID_ = heroPetItemID_;
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
      if (other instanceof com.supercat.growstone.network.messages.TUnit) {
        return mergeFrom((com.supercat.growstone.network.messages.TUnit)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.TUnit other) {
      if (other == com.supercat.growstone.network.messages.TUnit.getDefaultInstance()) return this;
      if (other.getId() != 0L) {
        setId(other.getId());
      }
      if (other.getType() != 0) {
        setType(other.getType());
      }
      if (!other.getName().isEmpty()) {
        name_ = other.name_;
        onChanged();
      }
      if (other.getUnitID() != 0L) {
        setUnitID(other.getUnitID());
      }
      if (other.getPosX() != 0) {
        setPosX(other.getPosX());
      }
      if (other.getPosY() != 0) {
        setPosY(other.getPosY());
      }
      if (other.getHp() != 0) {
        setHp(other.getHp());
      }
      if (other.getMaxHP() != 0) {
        setMaxHP(other.getMaxHP());
      }
      if (other.getTeamTag() != 0) {
        setTeamTag(other.getTeamTag());
      }
      if (other.getDead() != false) {
        setDead(other.getDead());
      }
      if (other.getState() != 0) {
        setState(other.getState());
      }
      if (other.getHeroPetItemID() != 0L) {
        setHeroPetItemID(other.getHeroPetItemID());
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
      com.supercat.growstone.network.messages.TUnit parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.TUnit) e.getUnfinishedMessage();
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

    private int type_ ;
    /**
     * <code>int32 type = 2;</code>
     * @return The type.
     */
    @java.lang.Override
    public int getType() {
      return type_;
    }
    /**
     * <code>int32 type = 2;</code>
     * @param value The type to set.
     * @return This builder for chaining.
     */
    public Builder setType(int value) {
      
      type_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 type = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearType() {
      
      type_ = 0;
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

    private long unitID_ ;
    /**
     * <code>int64 unitID = 4;</code>
     * @return The unitID.
     */
    @java.lang.Override
    public long getUnitID() {
      return unitID_;
    }
    /**
     * <code>int64 unitID = 4;</code>
     * @param value The unitID to set.
     * @return This builder for chaining.
     */
    public Builder setUnitID(long value) {
      
      unitID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 unitID = 4;</code>
     * @return This builder for chaining.
     */
    public Builder clearUnitID() {
      
      unitID_ = 0L;
      onChanged();
      return this;
    }

    private int posX_ ;
    /**
     * <code>int32 posX = 5;</code>
     * @return The posX.
     */
    @java.lang.Override
    public int getPosX() {
      return posX_;
    }
    /**
     * <code>int32 posX = 5;</code>
     * @param value The posX to set.
     * @return This builder for chaining.
     */
    public Builder setPosX(int value) {
      
      posX_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 posX = 5;</code>
     * @return This builder for chaining.
     */
    public Builder clearPosX() {
      
      posX_ = 0;
      onChanged();
      return this;
    }

    private int posY_ ;
    /**
     * <code>int32 posY = 6;</code>
     * @return The posY.
     */
    @java.lang.Override
    public int getPosY() {
      return posY_;
    }
    /**
     * <code>int32 posY = 6;</code>
     * @param value The posY to set.
     * @return This builder for chaining.
     */
    public Builder setPosY(int value) {
      
      posY_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 posY = 6;</code>
     * @return This builder for chaining.
     */
    public Builder clearPosY() {
      
      posY_ = 0;
      onChanged();
      return this;
    }

    private int hp_ ;
    /**
     * <code>int32 hp = 7;</code>
     * @return The hp.
     */
    @java.lang.Override
    public int getHp() {
      return hp_;
    }
    /**
     * <code>int32 hp = 7;</code>
     * @param value The hp to set.
     * @return This builder for chaining.
     */
    public Builder setHp(int value) {
      
      hp_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 hp = 7;</code>
     * @return This builder for chaining.
     */
    public Builder clearHp() {
      
      hp_ = 0;
      onChanged();
      return this;
    }

    private int maxHP_ ;
    /**
     * <code>int32 maxHP = 8;</code>
     * @return The maxHP.
     */
    @java.lang.Override
    public int getMaxHP() {
      return maxHP_;
    }
    /**
     * <code>int32 maxHP = 8;</code>
     * @param value The maxHP to set.
     * @return This builder for chaining.
     */
    public Builder setMaxHP(int value) {
      
      maxHP_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 maxHP = 8;</code>
     * @return This builder for chaining.
     */
    public Builder clearMaxHP() {
      
      maxHP_ = 0;
      onChanged();
      return this;
    }

    private int teamTag_ ;
    /**
     * <code>int32 teamTag = 9;</code>
     * @return The teamTag.
     */
    @java.lang.Override
    public int getTeamTag() {
      return teamTag_;
    }
    /**
     * <code>int32 teamTag = 9;</code>
     * @param value The teamTag to set.
     * @return This builder for chaining.
     */
    public Builder setTeamTag(int value) {
      
      teamTag_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 teamTag = 9;</code>
     * @return This builder for chaining.
     */
    public Builder clearTeamTag() {
      
      teamTag_ = 0;
      onChanged();
      return this;
    }

    private boolean dead_ ;
    /**
     * <code>bool dead = 10;</code>
     * @return The dead.
     */
    @java.lang.Override
    public boolean getDead() {
      return dead_;
    }
    /**
     * <code>bool dead = 10;</code>
     * @param value The dead to set.
     * @return This builder for chaining.
     */
    public Builder setDead(boolean value) {
      
      dead_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>bool dead = 10;</code>
     * @return This builder for chaining.
     */
    public Builder clearDead() {
      
      dead_ = false;
      onChanged();
      return this;
    }

    private int state_ ;
    /**
     * <code>int32 state = 11;</code>
     * @return The state.
     */
    @java.lang.Override
    public int getState() {
      return state_;
    }
    /**
     * <code>int32 state = 11;</code>
     * @param value The state to set.
     * @return This builder for chaining.
     */
    public Builder setState(int value) {
      
      state_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 state = 11;</code>
     * @return This builder for chaining.
     */
    public Builder clearState() {
      
      state_ = 0;
      onChanged();
      return this;
    }

    private long heroPetItemID_ ;
    /**
     * <code>int64 heroPetItemID = 12;</code>
     * @return The heroPetItemID.
     */
    @java.lang.Override
    public long getHeroPetItemID() {
      return heroPetItemID_;
    }
    /**
     * <code>int64 heroPetItemID = 12;</code>
     * @param value The heroPetItemID to set.
     * @return This builder for chaining.
     */
    public Builder setHeroPetItemID(long value) {
      
      heroPetItemID_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 heroPetItemID = 12;</code>
     * @return This builder for chaining.
     */
    public Builder clearHeroPetItemID() {
      
      heroPetItemID_ = 0L;
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


    // @@protoc_insertion_point(builder_scope:TUnit)
  }

  // @@protoc_insertion_point(class_scope:TUnit)
  private static final com.supercat.growstone.network.messages.TUnit DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.TUnit();
  }

  public static com.supercat.growstone.network.messages.TUnit getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TUnit>
      PARSER = new com.google.protobuf.AbstractParser<TUnit>() {
    @java.lang.Override
    public TUnit parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TUnit(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TUnit> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TUnit> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.TUnit getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

