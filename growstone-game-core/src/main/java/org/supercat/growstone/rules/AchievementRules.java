package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.TAchievements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.SLog;
import org.supercat.growstone.ZipUtils;
import org.supercat.growstone.models.DMPlayerAchievement;

public class AchievementRules {
    private static final Logger logger = LoggerFactory.getLogger(AchievementRules.class);

    public static TAchievements extractAchievements(DMPlayerAchievement model) {
        try {
            byte[] bytes = ZipUtils.decompressBytes(model.data);
            return TAchievements.parseFrom(bytes);
        } catch (Exception e) {
            SLog.logException(e);
        }

        return null;
    }
}
