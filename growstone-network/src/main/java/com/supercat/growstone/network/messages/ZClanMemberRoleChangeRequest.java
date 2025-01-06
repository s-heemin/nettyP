// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Network.proto

package com.supercat.growstone.network.messages;

/**
 * Protobuf type {@code ZClanMemberRoleChangeRequest}
 */
public final class ZClanMemberRoleChangeRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ZClanMemberRoleChangeRequest)
    ZClanMemberRoleChangeRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ZClanMemberRoleChangeRequest.newBuilder() to construct.
  private ZClanMemberRoleChangeRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ZClanMemberRoleChangeRequest() {
    role_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ZClanMemberRoleChangeRequest();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ZClanMemberRoleChangeRequest(
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

            memberId_ = input.readInt32();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            role_ = rawValue;
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
    return com.supercat.growstone.network.messages.Network.internal_static_ZClanMemberRoleChangeRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.supercat.growstone.network.messages.Network.internal_static_ZClanMemberRoleChangeRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.class, com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.Builder.class);
  }

  public static final int MEMBER_ID_FIELD_NUMBER = 1;
  private int memberId_;
  /**
   * <code>int32 member_id = 1;</code>
   * @return The memberId.
   */
  @java.lang.Override
  public int getMemberId() {
    return memberId_;
  }

  public static final int ROLE_FIELD_NUMBER = 2;
  private int role_;
  /**
   * <code>.ZClanMember.Role role = 2;</code>
   * @return The enum numeric value on the wire for role.
   */
  @java.lang.Override public int getRoleValue() {
    return role_;
  }
  /**
   * <code>.ZClanMember.Role role = 2;</code>
   * @return The role.
   */
  @java.lang.Override public com.supercat.growstone.network.messages.ZClanMember.Role getRole() {
    @SuppressWarnings("deprecation")
    com.supercat.growstone.network.messages.ZClanMember.Role result = com.supercat.growstone.network.messages.ZClanMember.Role.valueOf(role_);
    return result == null ? com.supercat.growstone.network.messages.ZClanMember.Role.UNRECOGNIZED : result;
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
    if (memberId_ != 0) {
      output.writeInt32(1, memberId_);
    }
    if (role_ != com.supercat.growstone.network.messages.ZClanMember.Role.NONE.getNumber()) {
      output.writeEnum(2, role_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (memberId_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, memberId_);
    }
    if (role_ != com.supercat.growstone.network.messages.ZClanMember.Role.NONE.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, role_);
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
    if (!(obj instanceof com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest)) {
      return super.equals(obj);
    }
    com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest other = (com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest) obj;

    if (getMemberId()
        != other.getMemberId()) return false;
    if (role_ != other.role_) return false;
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
    hash = (37 * hash) + MEMBER_ID_FIELD_NUMBER;
    hash = (53 * hash) + getMemberId();
    hash = (37 * hash) + ROLE_FIELD_NUMBER;
    hash = (53 * hash) + role_;
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parseFrom(
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
  public static Builder newBuilder(com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest prototype) {
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
   * Protobuf type {@code ZClanMemberRoleChangeRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ZClanMemberRoleChangeRequest)
      com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZClanMemberRoleChangeRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZClanMemberRoleChangeRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.class, com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.Builder.class);
    }

    // Construct using com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.newBuilder()
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
      memberId_ = 0;

      role_ = 0;

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.supercat.growstone.network.messages.Network.internal_static_ZClanMemberRoleChangeRequest_descriptor;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest getDefaultInstanceForType() {
      return com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest build() {
      com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest buildPartial() {
      com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest result = new com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest(this);
      result.memberId_ = memberId_;
      result.role_ = role_;
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
      if (other instanceof com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest) {
        return mergeFrom((com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest other) {
      if (other == com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest.getDefaultInstance()) return this;
      if (other.getMemberId() != 0) {
        setMemberId(other.getMemberId());
      }
      if (other.role_ != 0) {
        setRoleValue(other.getRoleValue());
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
      com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int memberId_ ;
    /**
     * <code>int32 member_id = 1;</code>
     * @return The memberId.
     */
    @java.lang.Override
    public int getMemberId() {
      return memberId_;
    }
    /**
     * <code>int32 member_id = 1;</code>
     * @param value The memberId to set.
     * @return This builder for chaining.
     */
    public Builder setMemberId(int value) {
      
      memberId_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 member_id = 1;</code>
     * @return This builder for chaining.
     */
    public Builder clearMemberId() {
      
      memberId_ = 0;
      onChanged();
      return this;
    }

    private int role_ = 0;
    /**
     * <code>.ZClanMember.Role role = 2;</code>
     * @return The enum numeric value on the wire for role.
     */
    @java.lang.Override public int getRoleValue() {
      return role_;
    }
    /**
     * <code>.ZClanMember.Role role = 2;</code>
     * @param value The enum numeric value on the wire for role to set.
     * @return This builder for chaining.
     */
    public Builder setRoleValue(int value) {
      
      role_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.ZClanMember.Role role = 2;</code>
     * @return The role.
     */
    @java.lang.Override
    public com.supercat.growstone.network.messages.ZClanMember.Role getRole() {
      @SuppressWarnings("deprecation")
      com.supercat.growstone.network.messages.ZClanMember.Role result = com.supercat.growstone.network.messages.ZClanMember.Role.valueOf(role_);
      return result == null ? com.supercat.growstone.network.messages.ZClanMember.Role.UNRECOGNIZED : result;
    }
    /**
     * <code>.ZClanMember.Role role = 2;</code>
     * @param value The role to set.
     * @return This builder for chaining.
     */
    public Builder setRole(com.supercat.growstone.network.messages.ZClanMember.Role value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      role_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.ZClanMember.Role role = 2;</code>
     * @return This builder for chaining.
     */
    public Builder clearRole() {
      
      role_ = 0;
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


    // @@protoc_insertion_point(builder_scope:ZClanMemberRoleChangeRequest)
  }

  // @@protoc_insertion_point(class_scope:ZClanMemberRoleChangeRequest)
  private static final com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest();
  }

  public static com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ZClanMemberRoleChangeRequest>
      PARSER = new com.google.protobuf.AbstractParser<ZClanMemberRoleChangeRequest>() {
    @java.lang.Override
    public ZClanMemberRoleChangeRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ZClanMemberRoleChangeRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ZClanMemberRoleChangeRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ZClanMemberRoleChangeRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.supercat.growstone.network.messages.ZClanMemberRoleChangeRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

