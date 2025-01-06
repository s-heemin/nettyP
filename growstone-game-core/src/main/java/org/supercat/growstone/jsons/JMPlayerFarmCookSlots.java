package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.TFarmCookSlot;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JMPlayerFarmCookSlots {
    private Map<Integer, TFarmCookSlot> slots;

    public static JMPlayerFarmCookSlots of(Map<Integer, TFarmCookSlot> slots) {
        var model = new JMPlayerFarmCookSlots();
        model.slots = slots;

        return model;
    }

    public static Map<Integer, TFarmCookSlot> ofTFarmCookSlots(String data) {
        return ofJson(data).slots;
    }

    public static JMPlayerFarmCookSlots ofJson(String data) {
        var model = new JMPlayerFarmCookSlots();
        var jsonObject = new JSONObject(data);
        var slots = jsonObject.optJSONArray("slots");
        if (Objects.nonNull(slots)) {
            for (int i = 0; i < slots.length(); i++) {
                var jmSlot = JMPlayerFarmCookSlot.ofJson(slots.getJSONObject(i));
                model.slots.put(jmSlot.ofTFarmSlot().getSlotId(), jmSlot.ofTFarmSlot());
            }
        }

        return model;
    }

    public JSONObject toJson() {
        var jsonObject = new JSONObject();

        var slots = new JSONArray();
        for (var slot : this.slots.values()) {
            var jmSlot = JMPlayerFarmCookSlot.of(slot);
            slots.put(jmSlot.toJson());
        }

        jsonObject.put("slots", slots);

        return jsonObject;
    }

    private JMPlayerFarmCookSlots() {
        this.slots = new HashMap<>();
    }

}
