package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.TExplorationQuest;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JMPlayerExplorationQuests {
    private Map<Integer, TExplorationQuest> quests;

    public static JMPlayerExplorationQuests of(Map<Integer, TExplorationQuest> quests) {
        var model = new JMPlayerExplorationQuests();
        model.quests = quests;

        return model;
    }

    public static Map<Integer, TExplorationQuest> ofTExplorationQuests(String data) {
        return ofJson(data).quests;
    }

    public static JMPlayerExplorationQuests ofJson(String data) {
        var model = new JMPlayerExplorationQuests();
        var jsonObject = new JSONObject(data);
        var quests = jsonObject.optJSONArray("quests");
        if (Objects.nonNull(quests)) {
            for (int i = 0; i < quests.length(); i++) {
                var jmQuest = JMPlayerExplorationQuest.ofJson(quests.getJSONObject(i));
                model.quests.put(jmQuest.ofTExplorationQuest().getSlotId(), jmQuest.ofTExplorationQuest());
            }
        }

        return model;
    }

    public JSONObject toJson() {
        var jsonObject = new JSONObject();

        var quests = new JSONArray();
        for (var quest : this.quests.values()) {
            var jmQuest = JMPlayerExplorationQuest.of(quest);
            quests.put(jmQuest.toJson());
        }

        jsonObject.put("quests", quests);

        return jsonObject;
    }

    private JMPlayerExplorationQuests() {
        this.quests = new HashMap<>();
    }

}
