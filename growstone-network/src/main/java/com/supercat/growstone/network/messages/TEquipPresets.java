// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code TEquipPresets}
 */
public final class TEquipPresets extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:TEquipPresets)
    TEquipPresetsOrBuilder {
private static final long serialVersionUID = 0L;
  // Use TEquipPresets.newBuilder() to construct.
  private TEquipPresets(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private TEquipPresets() {
    presetName_ = "";
    equips_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new TEquipPresets();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private TEquipPresets(
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

            presetIndex_ = input.readInt32();
            break;
          }
          case 18: {
            java.lang.String s = input.readStringRequireUtf8();

            presetName_ = s;
            break;
          }
          case 26: {
            if (!((mutable_bitField0_ & 0x00000001) != 0)) {
              equips_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TEquipPresetsByType>();
              mutable_bitField0_ |= 0x00000001;
            }
            equips_.add(
                input.readMessage(com.supercat.growstone.network.messages.TEquipPresetsByType.parser(), extensionRegistry));
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
        equips_ = java.util.Collections.unmodifiableList(equips_);
      }
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.supercat.growstone.network.messages.Network.internal_static_TEquipPresets_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_TEquipPresets_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.TEquipPresets.class, com.supercat.growstone.network.messages.TEquipPresets.Builder.class);
  }

  public static final int PRESET_INDEX_FIELD_NUMBER = 1;
  private int presetIndex_;
  /**
   * <code>int32 preset_index = 1;</code>
   * @return The presetIndex.
   */
  @java.lang.Override
  public int getPresetIndex() {
    return presetIndex_;
  }

  public static final int PRESET_NAME_FIELD_NUMBER = 2;
  private volatile java.lang.Object presetName_;
  /**
   * <code>string preset_name = 2;</code>
   * @return The presetName.
   */
  @java.lang.Override
  public java.lang.String getPresetName() {
    java.lang.Object ref = presetName_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      presetName_ = s;
      return s;
    }
  }
  /**
   * <code>string preset_name = 2;</code>
   * @return The bytes for presetName.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPresetNameBytes() {
    java.lang.Object ref = presetName_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      presetName_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int EQUIPS_FIELD_NUMBER = 3;
  private java.util.List<com.supercat.growstone.network.messages.TEquipPresetsByType> equips_;
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  @java.lang.Override
  public java.util.List<com.supercat.growstone.network.messages.TEquipPresetsByType> getEquipsList() {
    return equips_;
  }
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder> 
      getEquipsOrBuilderList() {
    return equips_;
  }
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  @java.lang.Override
  public int getEquipsCount() {
    return equips_.size();
  }
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TEquipPresetsByType getEquips(int index) {
    return equips_.get(index);
  }
  /**
   * <code>repeated .TEquipPresetsByType equips = 3;</code>
   */
  @java.lang.Override
  public com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder getEquipsOrBuilder(
      int index) {
    return equips_.get(index);
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
    if (presetIndex_ != 0) {
      output.writeInt32(1, presetIndex_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(presetName_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, presetName_);
    }
    for (int i = 0; i < equips_.size(); i++) {
      output.writeMessage(3, equips_.get(i));
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (presetIndex_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, presetIndex_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(presetName_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, presetName_);
    }
    for (int i = 0; i < equips_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(3, equips_.get(i));
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
    if (!(obj instanceof com.supercat.growstone.network.messages.TEquipPresets)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.TEquipPresets other = (com.supercat.growstone.network.messages.TEquipPresets) obj;

    if (getPresetIndex()
        != other.getPresetIndex()) return false;
    if (!getPresetName()
        .equals(other.getPresetName())) return false;
    if (!getEquipsList()
        .equals(other.getEquipsList())) return false;
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
    hash = (37 * hash) + PRESET_INDEX_FIELD_NUMBER;
    hash = (53 * hash) + getPresetIndex();
    hash = (37 * hash) + PRESET_NAME_FIELD_NUMBER;
    hash = (53 * hash) + getPresetName().hashCode();
    if (getEquipsCount() > 0) {
      hash = (37 * hash) + EQUIPS_FIELD_NUMBER;
      hash = (53 * hash) + getEquipsList().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.TEquipPresets parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.TEquipPresets prototype) {
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
   * Protobuf type {@code TEquipPresets}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:TEquipPresets)
      com.supercat.growstone.network.messages.TEquipPresetsOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_TEquipPresets_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_TEquipPresets_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.TEquipPresets.class, com.supercat.growstone.network.messages.TEquipPresets.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.TEquipPresets.newBuilder()
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
        getEquipsFieldBuilder();
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      presetIndex_ = 0;

      presetName_ = "";

      if (equipsBuilder_ == null) {
        equips_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
      } else {
        equipsBuilder_.clear();
      }
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_TEquipPresets_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TEquipPresets getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.TEquipPresets.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TEquipPresets build() {
      com.supercat.growstone.network.messages.TEquipPresets result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.TEquipPresets buildPartial() {
      com.supercat.growstone.network.messages.TEquipPresets result = new com.supercat.growstone.network.messages.TEquipPresets(this);
      int from_bitField0_ = bitField0_;
      result.presetIndex_ = presetIndex_;
      result.presetName_ = presetName_;
      if (equipsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          equips_ = java.util.Collections.unmodifiableList(equips_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.equips_ = equips_;
      } else {
        result.equips_ = equipsBuilder_.build();
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
      if (other instanceof com.supercat.growstone.network.messages.TEquipPresets) {
        return mergeFrom((com.supercat.growstone.network.messages.TEquipPresets)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.TEquipPresets other) {
      if (other == com.supercat.growstone.network.messages.TEquipPresets.getDefaultInstance()) return this;
      if (other.getPresetIndex() != 0) {
        setPresetIndex(other.getPresetIndex());
      }
      if (!other.getPresetName().isEmpty()) {
        presetName_ = other.presetName_;
        onChanged();
      }
      if (equipsBuilder_ == null) {
        if (!other.equips_.isEmpty()) {
          if (equips_.isEmpty()) {
            equips_ = other.equips_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureEquipsIsMutable();
            equips_.addAll(other.equips_);
          }
          onChanged();
        }
      } else {
        if (!other.equips_.isEmpty()) {
          if (equipsBuilder_.isEmpty()) {
            equipsBuilder_.dispose();
            equipsBuilder_ = null;
            equips_ = other.equips_;
            bitField0_ = (bitField0_ & ~0x00000001);
            equipsBuilder_ = 
              com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders ?
                 getEquipsFieldBuilder() : null;
          } else {
            equipsBuilder_.addAllMessages(other.equips_);
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
      com.supercat.growstone.network.messages.TEquipPresets parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.TEquipPresets) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }
    private int bitField0_;

    private int presetIndex_ ;
    /**
     * <code>int32 preset_index = 1;</code>
     * @return The presetIndex.
     */
    @java.lang.Override
    public int getPresetIndex() {
      return presetIndex_;
    }
    /**
     * <code>int32 preset_index = 1;</code>
     * @param value The presetIndex to set.
     * @return This builder for chaining.
     */
    public Builder setPresetIndex(int value) {
      
      presetIndex_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 preset_index = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearPresetIndex() {
      
      presetIndex_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object presetName_ = "";
    /**
     * <code>string preset_name = 2;</code>
     * @return The presetName.
     */
    public java.lang.String getPresetName() {
      java.lang.Object ref = presetName_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        presetName_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string preset_name = 2;</code>
     * @return The bytes for presetName.
     */
    public com.google.protobuf.ByteString
        getPresetNameBytes() {
      java.lang.Object ref = presetName_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        presetName_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string preset_name = 2;</code>
     * @param value The presetName to set.
     * @return This builder for chaining.
     */
    public Builder setPresetName(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      presetName_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string preset_name = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearPresetName() {
      
      presetName_ = getDefaultInstance().getPresetName();
      onChanged();
      return this;
    }
    /**
     * <code>string preset_name = 2;</code>
     * @param value The bytes for presetName to set.
     * @return This builder for chaining.
     */
    public Builder setPresetNameBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      presetName_ = value;
      onChanged();
      return this;
    }

    private java.util.List<com.supercat.growstone.network.messages.TEquipPresetsByType> equips_ =
      java.util.Collections.emptyList();
    private void ensureEquipsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        equips_ = new java.util.ArrayList<com.supercat.growstone.network.messages.TEquipPresetsByType>(equips_);
        bitField0_ |= 0x00000001;
       }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TEquipPresetsByType, com.supercat.growstone.network.messages.TEquipPresetsByType.Builder, com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder> equipsBuilder_;

    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TEquipPresetsByType> getEquipsList() {
      if (equipsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(equips_);
      } else {
        return equipsBuilder_.getMessageList();
      }
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public int getEquipsCount() {
      if (equipsBuilder_ == null) {
        return equips_.size();
      } else {
        return equipsBuilder_.getCount();
      }
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public com.supercat.growstone.network.messages.TEquipPresetsByType getEquips(int index) {
      if (equipsBuilder_ == null) {
        return equips_.get(index);
      } else {
        return equipsBuilder_.getMessage(index);
      }
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder setEquips(
        int index, com.supercat.growstone.network.messages.TEquipPresetsByType value) {
      if (equipsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEquipsIsMutable();
        equips_.set(index, value);
        onChanged();
      } else {
        equipsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder setEquips(
        int index, com.supercat.growstone.network.messages.TEquipPresetsByType.Builder builderForValue) {
      if (equipsBuilder_ == null) {
        ensureEquipsIsMutable();
        equips_.set(index, builderForValue.build());
        onChanged();
      } else {
        equipsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder addEquips(com.supercat.growstone.network.messages.TEquipPresetsByType value) {
      if (equipsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEquipsIsMutable();
        equips_.add(value);
        onChanged();
      } else {
        equipsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder addEquips(
        int index, com.supercat.growstone.network.messages.TEquipPresetsByType value) {
      if (equipsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureEquipsIsMutable();
        equips_.add(index, value);
        onChanged();
      } else {
        equipsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder addEquips(
        com.supercat.growstone.network.messages.TEquipPresetsByType.Builder builderForValue) {
      if (equipsBuilder_ == null) {
        ensureEquipsIsMutable();
        equips_.add(builderForValue.build());
        onChanged();
      } else {
        equipsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder addEquips(
        int index, com.supercat.growstone.network.messages.TEquipPresetsByType.Builder builderForValue) {
      if (equipsBuilder_ == null) {
        ensureEquipsIsMutable();
        equips_.add(index, builderForValue.build());
        onChanged();
      } else {
        equipsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder addAllEquips(
        java.lang.Iterable<? extends com.supercat.growstone.network.messages.TEquipPresetsByType> values) {
      if (equipsBuilder_ == null) {
        ensureEquipsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(
            values, equips_);
        onChanged();
      } else {
        equipsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder clearEquips() {
      if (equipsBuilder_ == null) {
        equips_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        equipsBuilder_.clear();
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public Builder removeEquips(int index) {
      if (equipsBuilder_ == null) {
        ensureEquipsIsMutable();
        equips_.remove(index);
        onChanged();
      } else {
        equipsBuilder_.remove(index);
      }
      return this;
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public com.supercat.growstone.network.messages.TEquipPresetsByType.Builder getEquipsBuilder(
        int index) {
      return getEquipsFieldBuilder().getBuilder(index);
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder getEquipsOrBuilder(
        int index) {
      if (equipsBuilder_ == null) {
        return equips_.get(index);  } else {
        return equipsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public java.util.List<? extends com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder> 
         getEquipsOrBuilderList() {
      if (equipsBuilder_ != null) {
        return equipsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(equips_);
      }
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public com.supercat.growstone.network.messages.TEquipPresetsByType.Builder addEquipsBuilder() {
      return getEquipsFieldBuilder().addBuilder(
          com.supercat.growstone.network.messages.TEquipPresetsByType.getDefaultInstance());
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public com.supercat.growstone.network.messages.TEquipPresetsByType.Builder addEquipsBuilder(
        int index) {
      return getEquipsFieldBuilder().addBuilder(
          index, com.supercat.growstone.network.messages.TEquipPresetsByType.getDefaultInstance());
    }
    /**
     * <code>repeated .TEquipPresetsByType equips = 3;</code>
     */
    public java.util.List<com.supercat.growstone.network.messages.TEquipPresetsByType.Builder> 
         getEquipsBuilderList() {
      return getEquipsFieldBuilder().getBuilderList();
    }
    private com.google.protobuf.RepeatedFieldBuilderV3<
        com.supercat.growstone.network.messages.TEquipPresetsByType, com.supercat.growstone.network.messages.TEquipPresetsByType.Builder, com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder> 
        getEquipsFieldBuilder() {
      if (equipsBuilder_ == null) {
        equipsBuilder_ = new com.google.protobuf.RepeatedFieldBuilderV3<
            com.supercat.growstone.network.messages.TEquipPresetsByType, com.supercat.growstone.network.messages.TEquipPresetsByType.Builder, com.supercat.growstone.network.messages.TEquipPresetsByTypeOrBuilder>(
                equips_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        equips_ = null;
      }
      return equipsBuilder_;
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


    // @@protoc_insertion_point(builder_scope:TEquipPresets)
  }

  // @@protoc_insertion_point(class_scope:TEquipPresets)
  private static final com.supercat.growstone.network.messages.TEquipPresets DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.TEquipPresets();
  }

  public static com.supercat.growstone.network.messages.TEquipPresets getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<TEquipPresets>
      PARSER = new com.google.protobuf.AbstractParser<TEquipPresets>() {
    @java.lang.Override
    public TEquipPresets parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new TEquipPresets(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<TEquipPresets> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<TEquipPresets> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.TEquipPresets getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

