package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZPreset;
import com.supercat.growstone.network.messages.ZStat;
import org.supercat.growstone.Constants;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.models.DMPlayerEquipPreset;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PresetRules {
    public static int review(int presetIndex, ZPreset.Type type, int equipIndex) {
        if(!PresetRules.isValidPresetIndex(presetIndex)) {
            return StatusCode.INVALID_PRESET_INDEX;
        }

        if(type == ZPreset.Type.NONE) {
            return StatusCode.INVALID_PRESET_TYPE;
        }

        if(ZPreset.Type.PET == type) {
            if(equipIndex < 0 || equipIndex > Constants.PET_TYPE_PRESET_EQUIP_SLOT_COUNT) {
                return StatusCode.INVALID_PRESET_INDEX;
            }
        } else {
            if(equipIndex < 0 || equipIndex > Constants.PRESET_EQUIP_SLOT_COUNT) {
                return StatusCode.INVALID_PRESET_INDEX;
            }
        }

        return StatusCode.SUCCESS;
    }

    private static boolean isValidPresetIndex(int presetIndex) {
        return presetIndex > 0 && presetIndex <= Constants.MAX_EQUIP_PRESET_SLOT_COUNT;
    }
}
