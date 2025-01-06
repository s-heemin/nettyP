package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZStat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.GameDatas.GameData;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StatRules {
    private static final Logger logger = LoggerFactory.getLogger(StatRules.class);

    public static HashMap<ZStat.Type, Double> calculateStats(
            Map<ZStat.Type, Double> growths,
            Map<ZStat.Type, Double> avatars,
            Map<ZStat.Type, Double> collections,
            Map<ZStat.Type, Double> statGrowths,
            Map<ZStat.Type, Double> presets,
            Map<ZStat.Type, Double> stoneStatueEnchants,
            Map<ZStat.Type, Double> stoneStatueGems,
            Map<ZStat.Type, Double> stoneStatueAvatars
    ) {
        var result = new HashMap<ZStat.Type, Double>();
        growths.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        avatars.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        collections.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        statGrowths.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        presets.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        stoneStatueEnchants.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        stoneStatueGems.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });
        stoneStatueAvatars.forEach((key, value) -> {
            result.compute(key, (k, v) -> Objects.isNull(v) ? value : v + value);
        });

        return result;
    }

    public static long calculateAttackPower(HashMap<ZStat.Type, Double> stats) {
        stats.compute(ZStat.Type.BASE_ATTACK, (k, v) -> Objects.isNull(v) ? GameData.FORMULA_DATA.defaultAttack : v + GameData.FORMULA_DATA.defaultAttack);
        stats.compute(ZStat.Type.BASE_DEFENSE, (k, v) -> Objects.isNull(v) ? GameData.FORMULA_DATA.defaultDefense : v + GameData.FORMULA_DATA.defaultDefense);
        stats.compute(ZStat.Type.BASE_HP, (k, v) -> Objects.isNull(v) ? GameData.FORMULA_DATA.defaultHp : v + GameData.FORMULA_DATA.defaultHp);
        stats.compute(ZStat.Type.BASE_MOVE_SPEED, (k, v) -> Objects.isNull(v) ? GameData.FORMULA_DATA.defaultMoveSpeed : v + GameData.FORMULA_DATA.defaultMoveSpeed);
        stats.compute(ZStat.Type.BASE_ATTACK_SPEED, (k, v) -> Objects.isNull(v) ? GameData.FORMULA_DATA.defaultAttackSpeed : v + GameData.FORMULA_DATA.defaultAttackSpeed);

        double attack = stats.getOrDefault(ZStat.Type.BASE_ATTACK, 0.0) * (1 + stats.getOrDefault(ZStat.Type.ATTACK_PERCENT, 0.0)) + stats.getOrDefault(ZStat.Type.ATTACK, 0.0);
        stats.put(ZStat.Type.FINAL_ATTACK, attack);
        double defense = stats.getOrDefault(ZStat.Type.BASE_DEFENSE, 0.0) * (1 + stats.getOrDefault(ZStat.Type.DEFENSE_PERCENT, 0.0)) + stats.getOrDefault(ZStat.Type.DEFENSE, 0.0);;
        stats.put(ZStat.Type.FINAL_DEFENSE, defense);
        double hp = stats.getOrDefault(ZStat.Type.BASE_HP, 0.0) * (1 + stats.getOrDefault(ZStat.Type.HP_PERCENT, 0.0)) + stats.getOrDefault(ZStat.Type.HP, 0.0);;
        stats.put(ZStat.Type.FINAL_HP, hp);

        double baseAttack = stats.getOrDefault(ZStat.Type.FINAL_ATTACK, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.FINAL_ATTACK, 1.0);
        double baseDefense = stats.getOrDefault(ZStat.Type.FINAL_DEFENSE, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.FINAL_DEFENSE, 1.0);
        double baseHp = stats.getOrDefault(ZStat.Type.FINAL_HP, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.FINAL_HP, 1.0);
        double criticalPercent = stats.getOrDefault(ZStat.Type.CRITICAL_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.CRITICAL_PERCENT, 1.0);
        double criticalResistPercent = stats.getOrDefault(ZStat.Type.CRITICAL_RESIST_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.CRITICAL_RESIST_PERCENT, 1.0);
        double criticalPowerResistPercent = stats.getOrDefault(ZStat.Type.CRITICAL_POWER_RESIST_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.CRITICAL_POWER_RESIST_PERCENT, 1.0);
        double damagePercent = stats.getOrDefault(ZStat.Type.ADD_DAMAGE_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.ADD_DAMAGE_PERCENT, 1.0);
        double skillDamagePercent = stats.getOrDefault(ZStat.Type.ADD_STONE_SKILL_DAMAGE_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.ADD_STONE_SKILL_DAMAGE_PERCENT, 1.0);
        double petSkillDamagePercent = stats.getOrDefault(ZStat.Type.ADD_PET_SKILL_DAMAGE_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.ADD_PET_SKILL_DAMAGE_PERCENT, 1.0);
        double addNormalMonsterDamagePercent = stats.getOrDefault(ZStat.Type.ADD_NORMAL_MONSTER_DAMAGE_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.ADD_NORMAL_MONSTER_DAMAGE_PERCENT, 1.0);
        double addBossMonsterDamagePercent = stats.getOrDefault(ZStat.Type.ADD_BOSS_MONSTER_DAMAGE_PERCENT, 0.0) * GameData.FORMULA_DATA.formulaDatas.getOrDefault(ZStat.Type.ADD_BOSS_MONSTER_DAMAGE_PERCENT, 1.0);

        return (long) (baseAttack + baseDefense + baseHp + criticalPercent + criticalResistPercent + criticalPowerResistPercent + damagePercent +
                skillDamagePercent + petSkillDamagePercent + addNormalMonsterDamagePercent + addBossMonsterDamagePercent);
    }
}
