package org.supercat.growstone.jsons;

import com.supercat.growstone.network.messages.TFarmCookSlot;
import org.json.JSONObject;
import org.supercat.growstone.SLog;

public class JMPlayerFarmCookSlot {
    private TFarmCookSlot slot;

    public TFarmCookSlot ofTFarmSlot() {
        return this.slot;
    }

    public static JMPlayerFarmCookSlot of(TFarmCookSlot slot) {
        var model = new JMPlayerFarmCookSlot();
        model.slot = slot;

        return model;
    }

    public static JMPlayerFarmCookSlot ofJson(JSONObject data) {
        var model = new JMPlayerFarmCookSlot();
        var slot = TFarmCookSlot.newBuilder()
                .setSlotId(data.optInt("slot_id", 0))
                .setUntilAt(data.optLong("until_at", 0));

        model.slot = slot.build();
        return model;
    }

    public JSONObject toJson() {
        try {
            return new JSONObject()
                    .put("slot_id", slot.getSlotId())
                    .put("until_at", slot.getUntilAt())
                    ;
        } catch (Exception e) {
            SLog.logException(e);
            return null;
        }
    }

    private JMPlayerFarmCookSlot() {
    }

}
