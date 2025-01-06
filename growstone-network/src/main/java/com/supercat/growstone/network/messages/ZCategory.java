// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: NetEnum.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZCategory}
 */
public final class ZCategory extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZCategory)
    ZCategoryOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZCategory.newBuilder() to construct.
  private ZCategory(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZCategory() {
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZCategory();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZCategory(
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
    return com.supercat.growstone.network.messages.NetEnum.internal_static_ZCategory_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.NetEnum.internal_static_ZCategory_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZCategory.class, com.supercat.growstone.network.messages.ZCategory.Builder.class);
  }

  /**
   * Protobuf enum {@code ZCategory.Type}
   */
  public enum Type
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>NONE = 0 [(.description) = "&#92;354&#92;225&#92;214 &#92;354&#92;210&#92;230 &#92;354&#92;227&#92;206&#92;354&#92;235&#92;214"];</code>
     */
    NONE(0),
    /**
     * <code>CURRENCY = 1 [(.description) = "&#92;354&#92;236&#92;254&#92;355&#92;231&#92;224"];</code>
     */
    CURRENCY(1),
    /**
     * <code>GROWTH = 2 [(.description) = "&#92;354&#92;204&#92;261&#92;354&#92;236&#92;245"];</code>
     */
    GROWTH(2),
    /**
     * <code>AVATAR = 3 [(.description) = "&#92;354&#92;225&#92;204&#92;353&#92;260&#92;224&#92;355&#92;203&#92;200"];</code>
     */
    AVATAR(3),
    /**
     * <code>MATERIAL = 4 [(.description) = "&#92;354&#92;236&#92;254&#92;353&#92;243&#92;214"];</code>
     */
    MATERIAL(4),
    /**
     * <code>ITEM = 5 [(.description) = "&#92;354&#92;225&#92;204&#92;354&#92;235&#92;264&#92;355&#92;205&#92;234"];</code>
     */
    ITEM(5),
    /**
     * <code>PORTRAIT_ICON = 6 [(.description) = "&#92;354&#92;264&#92;210&#92;354&#92;203&#92;201&#92;355&#92;231&#92;224"];</code>
     */
    PORTRAIT_ICON(6),
    /**
     * <code>AVATAR_STONE_STATUE = 7 [(.description) = "&#92;354&#92;204&#92;235&#92;354&#92;203&#92;201 &#92;354&#92;231&#92;270&#92;355&#92;230&#92;225"];</code>
     */
    AVATAR_STONE_STATUE(7),
    UNRECOGNIZED(-1),
    ;

    /**
     * <code>NONE = 0 [(.description) = "&#92;354&#92;225&#92;214 &#92;354&#92;210&#92;230 &#92;354&#92;227&#92;206&#92;354&#92;235&#92;214"];</code>
     */
    public static final int NONE_VALUE = 0;
    /**
     * <code>CURRENCY = 1 [(.description) = "&#92;354&#92;236&#92;254&#92;355&#92;231&#92;224"];</code>
     */
    public static final int CURRENCY_VALUE = 1;
    /**
     * <code>GROWTH = 2 [(.description) = "&#92;354&#92;204&#92;261&#92;354&#92;236&#92;245"];</code>
     */
    public static final int GROWTH_VALUE = 2;
    /**
     * <code>AVATAR = 3 [(.description) = "&#92;354&#92;225&#92;204&#92;353&#92;260&#92;224&#92;355&#92;203&#92;200"];</code>
     */
    public static final int AVATAR_VALUE = 3;
    /**
     * <code>MATERIAL = 4 [(.description) = "&#92;354&#92;236&#92;254&#92;353&#92;243&#92;214"];</code>
     */
    public static final int MATERIAL_VALUE = 4;
    /**
     * <code>ITEM = 5 [(.description) = "&#92;354&#92;225&#92;204&#92;354&#92;235&#92;264&#92;355&#92;205&#92;234"];</code>
     */
    public static final int ITEM_VALUE = 5;
    /**
     * <code>PORTRAIT_ICON = 6 [(.description) = "&#92;354&#92;264&#92;210&#92;354&#92;203&#92;201&#92;355&#92;231&#92;224"];</code>
     */
    public static final int PORTRAIT_ICON_VALUE = 6;
    /**
     * <code>AVATAR_STONE_STATUE = 7 [(.description) = "&#92;354&#92;204&#92;235&#92;354&#92;203&#92;201 &#92;354&#92;231&#92;270&#92;355&#92;230&#92;225"];</code>
     */
    public static final int AVATAR_STONE_STATUE_VALUE = 7;


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
        case 1: return CURRENCY;
        case 2: return GROWTH;
        case 3: return AVATAR;
        case 4: return MATERIAL;
        case 5: return ITEM;
        case 6: return PORTRAIT_ICON;
        case 7: return AVATAR_STONE_STATUE;
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
      return com.supercat.growstone.network.messages.ZCategory.getDescriptor().getEnumTypes().get(0);
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

    // @@protoc_insertion_point(enum_scope:ZCategory.Type)
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZCategory)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZCategory other = (com.supercat.growstone.network.messages.ZCategory) obj;

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

  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZCategory parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZCategory prototype) {
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
   * Protobuf type {@code ZCategory}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZCategory)
      com.supercat.growstone.network.messages.ZCategoryOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.NetEnum.internal_static_ZCategory_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.NetEnum.internal_static_ZCategory_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZCategory.class, com.supercat.growstone.network.messages.ZCategory.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZCategory.newBuilder()
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
      return com.supercat.growstone.network.messages.NetEnum.internal_static_ZCategory_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCategory getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZCategory.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCategory build() {
      com.supercat.growstone.network.messages.ZCategory result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZCategory buildPartial() {
      com.supercat.growstone.network.messages.ZCategory result = new com.supercat.growstone.network.messages.ZCategory(this);
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
      if (other instanceof com.supercat.growstone.network.messages.ZCategory) {
        return mergeFrom((com.supercat.growstone.network.messages.ZCategory)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZCategory other) {
      if (other == com.supercat.growstone.network.messages.ZCategory.getDefaultInstance()) return this;
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
      com.supercat.growstone.network.messages.ZCategory parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZCategory) e.getUnfinishedMessage();
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


    // @@protoc_insertion_point(builder_scope:ZCategory)
  }

  // @@protoc_insertion_point(class_scope:ZCategory)
  private static final com.supercat.growstone.network.messages.ZCategory DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZCategory();
  }

  public static com.supercat.growstone.network.messages.ZCategory getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZCategory>
      PARSER = new com.google.protobuf.AbstractParser<ZCategory>() {
    @java.lang.Override
    public ZCategory parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZCategory(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZCategory> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZCategory> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZCategory getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

