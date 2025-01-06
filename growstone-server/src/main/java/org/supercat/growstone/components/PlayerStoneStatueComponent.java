package org.supercat.growstone.components;

import com.supercat.growstone.network.messages.TStoneStatue;
import com.supercat.growstone.network.messages.ZTier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.TBuilderOf;
import org.supercat.growstone.models.DMPlayerStoneStatue;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.util.Objects;

public class PlayerStoneStatueComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerStoneStatueComponent.class);
    private final WorldPlayer player;
    private DMPlayerStoneStatue model;

    public PlayerStoneStatueComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        model = getOrCreate();
    }

    private DMPlayerStoneStatue getOrCreate() {
        var model = SDB.dbContext.playerStoneStatue.get(player.getId());
        if (Objects.isNull(model)) {
            model = DMPlayerStoneStatue.of(player.getId(), 1, 0, ZTier.Type.COMMON.getNumber(), GameData.STONE_STATUE.DEFAULT_STONE_STATUE_AVATAR_ID);
        }

        return model;
    }

    public TStoneStatue getTStoneStatue() {
        return TBuilderOf.ofTStoneStatue(model);
    }

    public int getEnchantLevel() {
        return model.enchant_level;
    }

    public int getEnchantExp() {
        return model.enchant_exp;
    }

    public int changeSafeGrade(ZTier.Type safeGrade) {
        if (safeGrade == ZTier.Type.NONE) {
            return StatusCode.INVALID_REQUEST;
        }

        if (model.enchant_safe_grade == safeGrade.getNumber()) {
            return StatusCode.INVALID_REQUEST;
        }

        model.enchant_safe_grade = safeGrade.getNumber();
        SDB.dbContext.playerStoneStatue.save(model);

        return StatusCode.SUCCESS;
    }

    public int addExp(long amount) {
        if (amount <= 0) {
            return StatusCode.INVALID_REQUEST;
        }

        if (model.enchant_level == GameData.STONE_STATUE.ENCHANT_MAX_LEVEL) {
            return StatusCode.INVALID_REQUEST;
        }

        model.enchant_exp += amount;

        for (var entry : GameData.STONE_STATUE.ENCHANT_EXP_PER_LEVEL.entrySet()) {
            if (model.enchant_exp < entry.getValue()) {
                break;
            }

            model.enchant_level = entry.getKey();
        }

        SDB.dbContext.playerStoneStatue.save(model);

        return StatusCode.SUCCESS;
    }

    public int equipAvatar(int avatarId) {
        var resAvatar = ResourceManager.INSTANCE.avatar.get(avatarId);
        if (Objects.isNull(resAvatar)) {
            logger.error("invalid avatar stone statue - playerId({}), itemId({})", player.getId(), avatarId);
            return StatusCode.INVALID_RESOURCE;
        }

        if (model.avatar_id == avatarId) {
            return StatusCode.INVALID_REQUEST;
        }

        if (!player.stoneStatueAvatar.isExist(avatarId)) {
            return StatusCode.INVALID_REQUEST;
        }

        model.avatar_id = avatarId;
        SDB.dbContext.playerStoneStatue.save(model);

        return StatusCode.SUCCESS;
    }

    public DMPlayerStoneStatue getModel() {
        return model;
    }
}
