package org.supercat.growstone.rules;

import com.supercat.growstone.network.messages.ZResource;
import org.supercat.growstone.JsonConverter;
import org.supercat.growstone.jsons.JMPlayerItem;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ItemRules {
    public static Set<ZResource.Type> itemTypes = Set.of(
            ZResource.Type.MATERIAL,
            ZResource.Type.EXP);

    public static Set<ZResource.Type> currencyTypes = Set.of(
            ZResource.Type.GOLD,
            ZResource.Type.FREE_RUBY,
            ZResource.Type.PAY_RUBY,
            ZResource.Type.FREE_DIAMOND,
            ZResource.Type.PAY_DIAMOND);
}
