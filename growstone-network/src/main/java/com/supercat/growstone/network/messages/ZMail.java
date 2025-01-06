// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NetEnum.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZMail}
 */
public final class ZMail extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZMail)
    ZMailOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZMail.newBuilder() to construct.
  private ZMail(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZMail() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZMail();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZMail(
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
    return com.supercat.growstone.network.messages.NetEnum.internal_static_ZMail_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.NetEnum.internal_static_ZMail_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZMail.class, com.supercat.growstone.network.messages.ZMail.Builder.class);
  }

  /**
   * Protobuf enum {@code ZMail.Type}
   */
  public enum Type
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>NONE = 0 [(.description) = "&#92;354&#92;225&#92;214 &#92;354&#92;210&#92;230 &#92;354&#92;227&#92;206&#92;354&#92;235&#92;214"];</code>
     */
    NONE(0),
    /**
     * <code>RAID_COOPERATIVE_REWARD = 1 [(.description) = "&#92;355&#92;230&#92;221&#92;353&#92;217&#92;231 &#92;352&#92;262&#92;275&#92;354&#92;237&#92;201&#92;354&#92;240&#92;204 &#92;353&#92;240&#92;210&#92;354&#92;235&#92;264&#92;353&#92;223&#92;234 &#92;354&#92;204&#92;234&#92;353&#92;262&#92;204 &#92;355&#92;217&#92;254&#92;354&#92;235&#92;270&#92;355&#92;212&#92;270 &#92;353&#92;263&#92;264&#92;354&#92;203&#92;201"];</code>
     */
    RAID_COOPERATIVE_REWARD(1),
    /**
     * <code>RAID_COMPETITIVE_REWARD = 2 [(.description) = "&#92;352&#92;262&#92;275&#92;354&#92;237&#92;201&#92;354&#92;240&#92;204 &#92;353&#92;240&#92;210&#92;354&#92;235&#92;264&#92;353&#92;223&#92;234 &#92;353&#92;236&#92;255&#92;355&#92;202&#92;271 &#92;353&#92;263&#92;264&#92;354&#92;203&#92;201"];</code>
     */
    RAID_COMPETITIVE_REWARD(2),
    /**
     * <code>DAILY_GIFT_FROM_FRIEND = 3 [(.description) = "&#92;354&#92;271&#92;234&#92;352&#92;265&#92;254 &#92;354&#92;204&#92;240&#92;353&#92;254&#92;274&#92;355&#92;225&#92;230&#92;352&#92;270&#92;260"];</code>
     */
    DAILY_GIFT_FROM_FRIEND(3),
    /**
     * <code>FARM_THEFT_REWARD = 4 [(.description) = "&#92;353&#92;206&#92;215&#92;354&#92;236&#92;245 &#92;355&#92;233&#92;224&#92;354&#92;271&#92;230&#92;352&#92;270&#92;260 &#92;353&#92;263&#92;264&#92;354&#92;203&#92;201"];</code>
     */
    FARM_THEFT_REWARD(4),
    /**
     * <code>SHOP_ITEM = 5 [(.description) = "&#92;354&#92;203&#92;201&#92;354&#92;240&#92;220 &#92;354&#92;225&#92;204&#92;354&#92;235&#92;264&#92;355&#92;205&#92;234 &#92;352&#92;265&#92;254&#92;353&#92;247&#92;244"];</code>
     */
    SHOP_ITEM(5),
    /**
     * <code>SHOP_GACHA_ITEM = 6 [(.description) = "&#92;354&#92;203&#92;201&#92;354&#92;240&#92;220 &#92;353&#92;275&#92;221&#92;352&#92;270&#92;260"];</code>
     */
    SHOP_GACHA_ITEM(6),
    /**
     * <code>DESTROY_CLAN = 7 [(.description) = "&#92;355&#92;201&#92;264&#92;353&#92;236&#92;234 &#92;355&#92;225&#92;264&#92;354&#92;262&#92;264"];</code>
     */
    DESTROY_CLAN(7),
    /**
     * <code>CHANGE_CLAN_LEADER = 8 [(.description) = "&#92;355&#92;201&#92;264&#92;353&#92;236&#92;234&#92;354&#92;236&#92;245 &#92;353&#92;263&#92;200&#92;352&#92;262&#92;275"];</code>
     */
    CHANGE_CLAN_LEADER(8),
    /**
     * <code>DISSOLUTION_CLAN = 9 [(.description) = "&#92;355&#92;201&#92;264&#92;353&#92;236&#92;234 &#92;355&#92;225&#92;264&#92;354&#92;262&#92;264"];</code>
     */
    DISSOLUTION_CLAN(9),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>NONE = 0 [(.description) = "&#92;354&#92;225&#92;214 &#92;354&#92;210&#92;230 &#92;354&#92;227&#92;206&#92;354&#92;235&#92;214"];</code>
     */
    public static final int NONE_VALUE = 0;
    /**
     * <code>RAID_COOPERATIVE_REWARD = 1 [(.description) = "&#92;355&#92;230&#92;221&#92;353&#92;217&#92;231 &#92;352&#92;262&#92;275&#92;354&#92;237&#92;201&#92;354&#92;240&#92;204 &#92;353&#92;240&#92;210&#92;354&#92;235&#92;264&#92;353&#92;223&#92;234 &#92;354&#92;204&#92;234&#92;353&#92;262&#92;204 &#92;355&#92;217&#92;254&#92;354&#92;235&#92;270&#92;355&#92;212&#92;270 &#92;353&#92;263&#92;264&#92;354&#92;203&#92;201"];</code>
     */
    public static final int RAID_COOPERATIVE_REWARD_VALUE = 1;
    /**
     * <code>RAID_COMPETITIVE_REWARD = 2 [(.description) = "&#92;352&#92;262&#92;275&#92;354&#92;237&#92;201&#92;354&#92;240&#92;204 &#92;353&#92;240&#92;210&#92;354&#92;235&#92;264&#92;353&#92;223&#92;234 &#92;353&#92;236&#92;255&#92;355&#92;202&#92;271 &#92;353&#92;263&#92;264&#92;354&#92;203&#92;201"];</code>
     */
    public static final int RAID_COMPETITIVE_REWARD_VALUE = 2;
    /**
     * <code>DAILY_GIFT_FROM_FRIEND = 3 [(.description) = "&#92;354&#92;271&#92;234&#92;352&#92;265&#92;254 &#92;354&#92;204&#92;240&#92;353&#92;254&#92;274&#92;355&#92;225&#92;230&#92;352&#92;270&#92;260"];</code>
     */
    public static final int DAILY_GIFT_FROM_FRIEND_VALUE = 3;
    /**
     * <code>FARM_THEFT_REWARD = 4 [(.description) = "&#92;353&#92;206&#92;215&#92;354&#92;236&#92;245 &#92;355&#92;233&#92;224&#92;354&#92;271&#92;230&#92;352&#92;270&#92;260 &#92;353&#92;263&#92;264&#92;354&#92;203&#92;201"];</code>
     */
    public static final int FARM_THEFT_REWARD_VALUE = 4;
    /**
     * <code>SHOP_ITEM = 5 [(.description) = "&#92;354&#92;203&#92;201&#92;354&#92;240&#92;220 &#92;354&#92;225&#92;204&#92;354&#92;235&#92;264&#92;355&#92;205&#92;234 &#92;352&#92;265&#92;254&#92;353&#92;247&#92;244"];</code>
     */
    public static final int SHOP_ITEM_VALUE = 5;
    /**
     * <code>SHOP_GACHA_ITEM = 6 [(.description) = "&#92;354&#92;203&#92;201&#92;354&#92;240&#92;220 &#92;353&#92;275&#92;221&#92;352&#92;270&#92;260"];</code>
     */
    public static final int SHOP_GACHA_ITEM_VALUE = 6;
    /**
     * <code>DESTROY_CLAN = 7 [(.description) = "&#92;355&#92;201&#92;264&#92;353&#92;236&#92;234 &#92;355&#92;225&#92;264&#92;354&#92;262&#92;264"];</code>
     */
    public static final int DESTROY_CLAN_VALUE = 7;
    /**
     * <code>CHANGE_CLAN_LEADER = 8 [(.description) = "&#92;355&#92;201&#92;264&#92;353&#92;236&#92;234&#92;354&#92;236&#92;245 &#92;353&#92;263&#92;200&#92;352&#92;262&#92;275"];</code>
     */
    public static final int CHANGE_CLAN_LEADER_VALUE = 8;
    /**
     * <code>DISSOLUTION_CLAN = 9 [(.description) = "&#92;355&#92;201&#92;264&#92;353&#92;236&#92;234 &#92;355&#92;225&#92;264&#92;354&#92;262&#92;264"];</code>
     */
    public static final int DISSOLUTION_CLAN_VALUE = 9;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static Type valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static Type forNumber(int value) {
      switch (value) {
        case 0: return NONE;
        case 1: return RAID_COOPERATIVE_REWARD;
        case 2: return RAID_COMPETITIVE_REWARD;
        case 3: return DAILY_GIFT_FROM_FRIEND;
        case 4: return FARM_THEFT_REWARD;
        case 5: return SHOP_ITEM;
        case 6: return SHOP_GACHA_ITEM;
        case 7: return DESTROY_CLAN;
        case 8: return CHANGE_CLAN_LEADER;
        case 9: return DISSOLUTION_CLAN;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<Type>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        Type> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<Type>() {
            public Type findValueByNumber(int number) {
              return Type.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.ZMail.getDescriptor().getEnumTypes().get(0);
    }

    private static final Type[] VALUES = values();

    public static Type valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private Type(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:ZMail.Type)
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
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof com.supercat.growstone.network.messages.ZMail)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZMail other = (com.supercat.growstone.network.messages.ZMail) obj;

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
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZMail parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZMail parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZMail parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZMail prototype) {
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
   * Protobuf type {@code ZMail}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZMail)
      com.supercat.growstone.network.messages.ZMailOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.NetEnum.internal_static_ZMail_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.NetEnum.internal_static_ZMail_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZMail.class, com.supercat.growstone.network.messages.ZMail.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZMail.newBuilder()
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
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.NetEnum.internal_static_ZMail_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZMail getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZMail.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZMail build() {
      com.supercat.growstone.network.messages.ZMail result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZMail buildPartial() {
      com.supercat.growstone.network.messages.ZMail result = new com.supercat.growstone.network.messages.ZMail(this);
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
      if (other instanceof com.supercat.growstone.network.messages.ZMail) {
        return mergeFrom((com.supercat.growstone.network.messages.ZMail)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZMail other) {
      if (other == com.supercat.growstone.network.messages.ZMail.getDefaultInstance()) return this;
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
      com.supercat.growstone.network.messages.ZMail parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZMail) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
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


    // @@protoc_insertion_point(builder_scope:ZMail)
  }

  // @@protoc_insertion_point(class_scope:ZMail)
  private static final com.supercat.growstone.network.messages.ZMail DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZMail();
  }

  public static com.supercat.growstone.network.messages.ZMail getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZMail>
      PARSER = new com.google.protobuf.AbstractParser<ZMail>() {
    @java.lang.Override
    public ZMail parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZMail(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZMail> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZMail> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZMail getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

