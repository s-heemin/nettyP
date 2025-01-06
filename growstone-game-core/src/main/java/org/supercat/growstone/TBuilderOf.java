package org.supercat.growstone;

import com.supercat.growstone.network.messages.*;
import org.supercat.growstone.jsons.JMPlayerMailReward;
import org.supercat.growstone.jsons.JMStoneStatueEnchantSlot;
import org.supercat.growstone.models.*;
import org.supercat.growstone.rules.ClanRules;
import org.supercat.growstone.rules.NetEnumRules;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;


public class TBuilderOf {
    public static TPlayer buildOf(DMPlayer model) {
        return TPlayer.newBuilder()
                .setLevel(model.level)
                .setExp(model.exp)
                .setName(model.name)
                .setStage(model.stage)
                .setStageGroup(model.stage_group)
                .setOnBossGauge(model.on_boss_gauge)
                .setPlayerId(model.id)
                .setPresetIndex(model.preset_index)
                .setPortraitId(model.portrait_id)
                .setAttackPower(model.attack_power)
                .setRemoveAd(model.remove_ad)
                .setFriendCode(model.friend_code)
                .build();
    }

    public static TItem buildOf(DMPlayerItem model) {
        return TItem.newBuilder()
                .setId(model.item_id)
                .setDataId(model.item_data_id)
                .setCount(model.count)
                .build();
    }

    public static TGrowth buildOf(DMPlayerGrowth model) {
        return TGrowth.newBuilder()
                .setGrowthId(model.growth_id)
                .setCount(model.count)
                .setLevel(model.level)
                .setPromoteLevel(model.promote_level)
                .setLimitBreakLevel(model.limit_break_level)
                .setType(NetEnumRules.ofGrowth(model.type))
                .build();
    }

    public static TAvatar buildOf(DMPlayerAvatar model) {
        return TAvatar.newBuilder()
                .setAvatarId(model.avatar_id)
                .setIsEquip(model.isEquip)
                .build();
    }

    public static TMail buildOf(DMPlayerMail model,List<TMailReward> rewards) {
        return TMail.newBuilder()
                .setMailId(model.id)
                .setMailType(NetEnumRules.ofMail(model.mail_type))
                .setIsRead(model.is_read)
                .setExpireTime(model.expire_at.getEpochSecond())
                .addAllRewards(rewards)
                .setSender(model.sender)
                .build();

    }

    public static TMailReward buildOf(JMPlayerMailReward reward) {
        return TMailReward.newBuilder()
                .setDataId(reward.data_id)
                .setCount(reward.count)
                .build();
    }

    public static TCollection buildOf(DMPlayerCollection model) {
        var type = ZResource.Type.forNumber(model.type);
        if(Objects.isNull(type)) {
            type = ZResource.Type.NONE;
        }

        return TCollection.newBuilder()
                .setDataId(model.collection_id)
                .setType(type)
                .setLevel(model.level)
                .build();
    }

    public static TPartsSlot buildOf(DMPlayerPartsSlot model) {
        return TPartsSlot.newBuilder()
                .setType(NetEnumRules.ofPartsSlot(model.type))
                .setLevel(model.level)
                .build();
    }

    public static TPortraitIcon buildOf(DMPlayerPortraitIcon model) {
        return TPortraitIcon.newBuilder()
                .setIconId(model.icon_id)
                .build();
    }

    public static TStat buildOf(ZStat.Type type, Double value) {
        return TStat.newBuilder()
                .setType(type)
                .setValue(value)
                .build();
    }

    public static TStatGrowth buildOf(DMPlayerStatGrowth model) {
        var type = ZStat.Type.forNumber(model.stat);
        return TStatGrowth.newBuilder()
                .setType(type)
                .setLevel(model.level)
                .build();
    }

    public static TStatGrowthPage buildOf(int page, List<TStatGrowth> statGrowths) {
        return TStatGrowthPage.newBuilder()
                .setPage(page)
                .addAllStatGrowths(statGrowths)
                .build();
    }
    public static TEquipPreset buildOf(DMPlayerEquipPreset model) {
        return TEquipPreset.newBuilder()
                .setDataId(model.data_id)
                .setEquipIndex(model.equip_index)
                .build();
    }

    public static TEquipPresetsByType buildOf(ZPreset.Type type, List<TEquipPreset> equips) {
        return TEquipPresetsByType.newBuilder()
                .setType(type)
                .addAllEquips(equips)
                .build();
    }

    public static TEquipPresetName buildOf(DMPlayerEquipPresetName model) {
        return TEquipPresetName.newBuilder()
                .setIndex(model.index)
                .setName(model.name)
                .build();
    }

    public static TDailyDungeon buildOf(DMPlayerDailyDungeon model) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(model.dungeon_data_id);
        return TDailyDungeon.newBuilder()
                .setId(model.dungeon_data_id)
                .setStage(model.stage)
                .setRemainAdViewCount(resDungeon.adViewMaxCount - model.remain_view_ad_count)
                .setRemainTicketCount(Objects.nonNull(resDungeon.ticket) ? model.remain_ticket_count : 0)
                .build();
    }

    public static TRaidDungeon buildOf(DMPlayerRaidDungeon model) {
        var resDungeon = ResourceManager.INSTANCE.dungeon.get(model.raid_data_id);
        return TRaidDungeon.newBuilder()
                .setId(model.raid_data_id)
                .setScore(model.score)
                .setRemainAdViewCount(resDungeon.adViewMaxCount - model.remain_view_ad_count)
                .setRemainTicketCount(Objects.nonNull(resDungeon.ticket) ? model.remain_ticket_count : 0)
                .build();
    }

    public static TTower buildOf(DMPlayerTower model) {
        return TTower.newBuilder()
                .setId(model.tower_data_id)
                .setStage(model.stage)
                .build();
    }

    public static TContentReward buildOf(ZCategory.Type category, long dataId, long count, long bonus_count) {
        return TContentReward.newBuilder()
                .setCategory(category)
                .setDataId(dataId)
                .setCount(count)
                .setBonusCount(bonus_count)
                .build();
    }

    public static TCooperativeRaidDungeon buildOf(DMPlayerRaidDungeon model, String name, long serverScore) {
        return TCooperativeRaidDungeon.newBuilder()
                .setPlayerName(name)
                .setRemainAdViewCount(model.remain_view_ad_count)
                .setRemainTicketCount(model.remain_ticket_count)
                .setPlayerScore(model.score)
                .setServerScore(serverScore)
                .build();
    }

    public static TCompetitiveRaidDungeonPlayer buildOf(DMPlayerRaidDungeon model, String name, int rank, long avatarId) {
        return TCompetitiveRaidDungeonPlayer.newBuilder()
                .setPlayerScore(model.score)
                .setPlayerRank(rank)
                .setAvatarId(avatarId)
                .setPlayerName(name)
                .setRemainAdViewCount(model.remain_view_ad_count)
                .setRemainTicketCount(model.remain_ticket_count)
                .build();

    }

    public static TCompetitiveRaidDungeonTopPlayer buildOf(String name, long playerScore, long avatarId) {
        return TCompetitiveRaidDungeonTopPlayer.newBuilder()
                .setPlayerName(name)
                .setPlayerScore(playerScore)
                .setAvatarId(avatarId)
                .build();
    }

    public static TCompetitiveRaidDungeon buildOf(TCompetitiveRaidDungeonPlayer player, TCompetitiveRaidDungeonTopPlayer topPlayer) {
        return TCompetitiveRaidDungeon.newBuilder()
                .setPlayer(player)
                .setTopPlayer(topPlayer)
                .build();
    }

    public static TCompetitiveRaidDungeonPlayerRankInfo buildOfRankInfo(String name, long score, int rank) {
        return TCompetitiveRaidDungeonPlayerRankInfo.newBuilder()
                .setPlayerName(name)
                .setPlayerScore(score)
                .setPlayerRank(rank)
                .build();
    }

    public static TCurrencies buildOf(DMPlayerCurrency model) {
        return TCurrencies.newBuilder()
                .setGold(model.gold)
                .setFreeRuby(model.free_ruby)
                .setPaidRuby(model.paid_ruby)
                .setFreeDiamond(model.free_diamond)
                .setPaidDiamond(model.paid_diamond)
                .build();
    }


    public static TFarm ofTFarm(DMPlayerFarm model, String name, long attackPower, int level,
                                long portraitId, List<DMPlayerFarmPlant> plants) {
        var tPlants = plants.stream()
                .map(TBuilderOf::ofFarmPlant)
                .collect(Collectors.toList());

        return TFarm.newBuilder()
                .setPlayerId(model.player_id)
                .setName(name)
                .setLevel(level)
                .setExp(model.exp)
                .setAttackPower(attackPower)
                .setPortraitId(portraitId)
                .addAllFarmPlant(tPlants)
                .build();
    }

    public static TFarmPlant ofFarmPlant(DMPlayerFarmPlant model) {
        return TFarmPlant.newBuilder()
                .setSlotIndex(model.slot_index)
                .setPlantId(model.plant_id)
                .setStartAt(model.seed_start_at.getEpochSecond())
                .setEndAt(model.seed_end_at.getEpochSecond())
                .setTheftPlayerId(model.theft_player_id)
                .setTheftEndAt(model.theft_end_at.getEpochSecond())
                .setTheftLimitCount(model.theft_limit_count)
                .build();
    }

    public static TFarmHistory ofFarmHistory(DMPlayerFarmHistory model, long portraitId) {
        return TFarmHistory.newBuilder()
                .setPlayerId(model.player_id)
                .setType(model.type)
                .setData(model.data)
                .setPortraitId(portraitId)
                .setCreatedAt(model.created_at.getEpochSecond())
                .build();
    }

    public static TStoneStatue ofTStoneStatue(DMPlayerStoneStatue model) {
        return TStoneStatue.newBuilder()
                .setEnchantLevel(model.enchant_level)
                .setEnchantExp(model.enchant_exp)
                .setEnchantSafeGrade(ZTier.Type.forNumber(model.enchant_safe_grade))
                .setAvatarId(model.avatar_id)
                .build();
    }

    public static List<TStoneStatueEnchant> ofTStoneStatueEnchantAll(Map<Integer, DMPlayerStoneStatueEnchant> presets) {
        List<TStoneStatueEnchant> tStoneStatueEnchants = new ArrayList<>();
        for (var preset : presets.values()) {
            tStoneStatueEnchants.add(ofTStoneStatueEnchant(preset));
        }

        return tStoneStatueEnchants;
    }

    public static TStoneStatueEnchant ofTStoneStatueEnchant(DMPlayerStoneStatueEnchant model) {
        List<TStoneStatueEnchantSlot> tStoneStatueEnchantSlots = new ArrayList<>();

        var tempData = JsonConverter.of(model.data, JMStoneStatueEnchantSlot[].class);
        if (Objects.nonNull(tempData)) {
            for (var data : tempData) {
                if (data.tier == ZTier.Type.NONE || data.statId == ZStat.Type.NONE) {
                    continue;
                }

                tStoneStatueEnchantSlots.add(ofTStoneStatueEnchantSlot(data));
            }
        }

        return TStoneStatueEnchant.newBuilder()
                .setOrderId(model.order_id)
                .addAllStoneStatueEnchants(tStoneStatueEnchantSlots)
                .build();
    }

    public static TStoneStatueEnchantSlot ofTStoneStatueEnchantSlot(JMStoneStatueEnchantSlot model) {
        var tStat = buildOf(model.statId, model.statValue);

        return TStoneStatueEnchantSlot.newBuilder()
                .setSlotId(model.slotId)
                .setIsLocked(model.isLocked)
                .setTier(model.tier)
                .setStat(tStat)
                .build();
    }

    public static TGacha buildOf(DMPlayerGacha model) {
        return TGacha.newBuilder()
                .setLevel(model.level)
                .setExp(model.exp)
                .setSpawnGachaMaxCount(model.gacha_max_count)
                .setViewCommercialCount(model.ad_view_count)
                .build();
    }

    public static TPickUpGacha buildOfPickUpGacha(int point, List<Integer> getRewards ) {
        return TPickUpGacha.newBuilder()
                .setPoint(point)
                .addAllGetRewards(getRewards)
                .build();
    }

    public static TConditionPackage buildOf(DMPlayerConditionPackage model) {
        return TConditionPackage.newBuilder()
                .setPackageId(model.package_id)
                .setExpireAt(model.expire_at.getEpochSecond())
                .build();
    }

    public static TShopPass buildOf(DMPlayerShopPass model) {
        return TShopPass.newBuilder()
                .setShopPassId(model.shop_pass_id)
                .setOpenStep(model.open_step)
                .setIsPaid(model.is_paid)
                .setLastFreeRewardStep(model.last_free_reward_step)
                .setLastPaidRewardStep(model.last_paid_reward_step)
                .build();
    }

    public static TWorldEvent buildOf(DMEvent model) {
        return TWorldEvent.newBuilder()
                .setId(model.id)
                .setEventDataId(model.event_data_id)
                .setStartAt(model.start_at.getEpochSecond())
                .setEndAt(model.end_at.getEpochSecond())
                .setDisplayAt(model.display_at.getEpochSecond())
                .build();
    }
    public static TPlayerEvent buildOf(DMPlayerEvent model) {
        return TPlayerEvent.newBuilder()
                .setId(model.event_id)
                .setEventDataId(model.event_data_id)
                .setProgress(model.progress)
                .setState(NetEnumRules.ofEventProgress(model.state))
                .setRewards(model.rewards)
                .build();

    }

    public static TPlayerDailyContent buildOf(DMPlayerDailyContent model) {
        var now = Instant.now();
        var nextDay = UtcZoneDateTime.ofNextResetTime(0).toInstant();
        var remainTime = Math.max(0, nextDay.getEpochSecond() - now.getEpochSecond());
        return TPlayerDailyContent.newBuilder()
                .setType(NetEnumRules.ofDailyContent(model.type))
                .setProgress(model.progress)
                .setState(NetEnumRules.ofReward(model.state))
                .setRewards(model.rewards)
                .setRemainTime(remainTime)
                .build();
    }

    public static TQuest buildOf(DMPlayerQuest model) {
        return TQuest.newBuilder()
                .setStep(model.step)
                .setProgress(model.progress)
                .setState(NetEnumRules.ofClear(model.state))
                .setNextStageIdCondition(model.next_stage_id_condition)
                .setNextStageGroupIdCondition(model.next_stage_group_id_condition)
                .build();
    }

    public static List<TStoneStatueGem> ofTStoneStatueGemAll(Map<Long, DMPlayerStoneStatueGem> gems) {
        List<TStoneStatueGem> tStoneStatueGems = new ArrayList<>();
        for (var gem : gems.values()) {
            tStoneStatueGems.add(ofTStoneStatueGem(gem));
        }

        return tStoneStatueGems;
    }

    public static TStoneStatueGem ofTStoneStatueGem(DMPlayerStoneStatueGem model) {
        return TStoneStatueGem.newBuilder()
                .setId(model.gem_id)
                .setLevel(model.gem_level)
                .build();
    }
    public static TClan buildOf(DMClan dmClan, String name, int memberSize, int rank, boolean isClanMember, long totalAttackPower) {
        return TClan.newBuilder()
                .setClanId(dmClan.id)
                .setClanName(dmClan.name)
                .setClanExp(dmClan.exp)
                .setClanLevel(dmClan.level)
                .setJoinType(ClanRules.ofJoinType(dmClan.join_type))
                .setState(ClanRules.ofState(dmClan.state))
                .setNotice(isClanMember ? dmClan.notice : dmClan.introduction)
                .setRank(rank)
                .setMasterPlayerName(name)
                .setMemberCount(memberSize)
                .setTotalAttackPower(totalAttackPower)
                .build();
    }
    public static TClanMember buildOf(DMClanMember model, String name, long portraitId, int level, long attackPower) {
        return TClanMember.newBuilder()
                .setName(name)
                .setPortraitId(portraitId)
                .setLevel(level)
                .setContribution(model.donate_count)
                .setAttackPower(attackPower)
                .setRole(ClanRules.ofRole(model.role))
                .build();
    }

    public static TChatHistory buildOf(long playerId, String name, long portraitId, String text, Instant now) {
        return TChatHistory.newBuilder()
                .setPlayerId(playerId)
                .setName(name)
                .setPortraitId(portraitId)
                .setText(text)
                .setTime(now.getEpochSecond())
                .build();
    }
}
