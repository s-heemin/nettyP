package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TExplorationQuest;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZTier;
import org.json.JSONArray;
import org.json.JSONObject;
import org.supercat.growstone.SLog;

import java.util.Objects;

public class JMPlayerExplorationQuest {
    private TExplorationQuest quest;

    public TExplorationQuest ofTExplorationQuest() {
        return this.quest;
    }

    public static JMPlayerExplorationQuest of(TExplorationQuest quest) {
        var model = new JMPlayerExplorationQuest();
        model.quest = quest;

        return model;
    }

    public static JMPlayerExplorationQuest ofJson(JSONObject data) {
        var model = new JMPlayerExplorationQuest();
        var quest = TExplorationQuest.newBuilder()
                .setSlotId(data.optInt("slot_id", 0))
                .setTier(ZTier.Type.forNumber(data.getInt("tier")))
                .setUntilAt(data.optLong("until_at", 0))
                .setElapsedTimeUnit(data.optInt("elapsed_time_unit", 0))
                .setEarnExp(data.optInt("earn_exp", 0))
                .setCreatedYmd(data.optInt("created_ymd", 0));

        var pet_ids = data.optJSONArray("pet_ids");
        if (Objects.nonNull(pet_ids)) {
            for (int i = 0; i < pet_ids.length(); i++) {
                quest.addPetId(pet_ids.getJSONObject(i).optInt("pet_id", 0));
            }
        }

        var need_pet_tiers = data.optJSONArray("need_pet_tiers");
        if (Objects.nonNull(need_pet_tiers)) {
            for (int i = 0; i < need_pet_tiers.length(); i++) {
                quest.addNeedPetTier(ZTier.Type.forNumber(
                        need_pet_tiers.getJSONObject(i).optInt("need_pet_tier", 0))
                );
            }
        }

        var jsonReward = data.getJSONObject("reward");
        if (Objects.nonNull(jsonReward)) {
            quest.setReward(TContentReward.newBuilder()
                    .setCategory(ZCategory.Type.forNumber(jsonReward.optInt("category", 0)))
                    .setDataId(jsonReward.optInt("data_id", 0))
                    .setCount(jsonReward.optLong("count", 0))
                    .setBonusCount(jsonReward.optLong("bonus_count"))
                    .build());
        }

        model.quest = quest.build();
        return model;
    }

    public JSONObject toJson() {
        try {
            var pet_ids = new JSONArray();
            for (var pet_id : quest.getPetIdList()) {
                var obj = new JSONObject();
                obj.put("pet_id", pet_id);
                pet_ids.put(obj);
            }

            var need_pet_tiers = new JSONArray();
            for (var need_pet_tier : quest.getNeedPetTierList()) {
                var obj = new JSONObject();
                obj.put("need_pet_tier", need_pet_tier.getNumber());
                need_pet_tiers.put(obj);
            }

            var reward = new JSONObject()
                    .put("category", quest.getReward().getCategory().getNumber())
                    .put("data_id", quest.getReward().getDataId())
                    .put("count", quest.getReward().getCount())
                    .put("bonus_count", quest.getReward().getBonusCount());

            return new JSONObject()
                    .put("slot_id", quest.getSlotId())
                    .put("tier", quest.getTier().getNumber())
                    .put("pet_ids", pet_ids)
                    .put("need_pet_tiers", need_pet_tiers)
                    .put("reward", reward)
                    .put("until_at", quest.getUntilAt())
                    .put("elapsed_time_unit", quest.getElapsedTimeUnit())
                    .put("earn_exp", quest.getEarnExp())
                    .put("created_ymd", quest.getCreatedYmd())
                    ;
        } catch (Exception e) {
            SLog.logException(e);
            return null;
        }
    }

    private JMPlayerExplorationQuest() {
    }

}
