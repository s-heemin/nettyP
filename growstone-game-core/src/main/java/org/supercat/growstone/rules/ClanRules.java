package org.supercat.growstone.rules;

import com.google.common.base.Strings;
import com.supercat.growstone.network.messages.ZClan;
import com.supercat.growstone.network.messages.ZClanJoin;
import com.supercat.growstone.network.messages.ZClanMember;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.models.DMClan;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class ClanRules {

    public static boolean isClanActiveRole(int role) {
        return role != ZClanMember.Role.NONE.getNumber() && role != ZClanMember.Role.MEMBER.getNumber();
    }

    public static int reviewClanText(String text, int role) {
        if (role != ZClanMember.Role.LEADER.getNumber() && role != ZClanMember.Role.DEPUTY_LEADER.getNumber()) {
            return StatusCode.NOT_AVAILABLE_CLAN_ROLE;
        }

        if (text.length() > 100) {
            return StatusCode.OVER_TEXT_LENGTH;
        }

        if (ResourceManager.INSTANCE.name.isInvalidWord(text)) {
            return StatusCode.INVALID_CHANGE_NAME_LENGTH;
        }

        return StatusCode.SUCCESS;
    }

    public static boolean isEnableJoinClan(int state) {
        return state == ZClan.State.ACTIVE.getNumber();
    }

    public static boolean IsEnableJoinClanType(int joinType) {
        return joinType == ZClanJoin.Type.AUTOMATIC.getNumber() || joinType == ZClanJoin.Type.APPROVAL_REQUIRED.getNumber();
    }

    public static int checkClanName(String clanName) {
        if (Strings.isNullOrEmpty(clanName)) {
            return StatusCode.INVALID_REQUEST;
        }

        int status = NameRules.reviewName(clanName);
        if (status != StatusCode.SUCCESS) {
            return status;
        }

        return StatusCode.SUCCESS;
    }

    public static ZClanJoin.Type ofJoinType(int type) {
        var joinType = ZClanJoin.Type.forNumber(type);
        return Objects.isNull(joinType) ? ZClanJoin.Type.NONE : joinType;
    }

    public static ZClan.State ofState(int state) {
        var clanState = ZClan.State.forNumber(state);
        return Objects.isNull(clanState) ? ZClan.State.NONE : clanState;
    }

    public static ZClanMember.Role ofRole(int role) {
        var clanRole = ZClanMember.Role.forNumber(role);
        return Objects.isNull(clanRole) ? ZClanMember.Role.NONE : clanRole;
    }
}
