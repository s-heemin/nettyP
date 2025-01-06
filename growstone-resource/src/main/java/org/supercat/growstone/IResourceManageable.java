package org.supercat.growstone;

import com.supercat.growstone.network.messages.ZCategory;
import org.jdom2.Element;
import com.google.common.collect.ImmutableMap;


import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public interface IResourceManageable {
    boolean verify();
    default <T extends ResourceBase> ImmutableMap<Long, T> load(Function<Element, T> fun, Set<String> absolutePaths, String tagName) {
        var temp = new ConcurrentHashMap<Long, T>();
        var elements = XMLHelper.loadAll(absolutePaths, tagName);
        for (Element e : elements) {
            var res = fun.apply(e);
            ResourceCollectors.tryPut(temp, res);
        }

        return ImmutableMap.copyOf(temp);
    }

   default boolean checkReward(ResourceContext ctx, ZCategory.Type category, long dataId, long count) {
        if(category == ZCategory.Type.ITEM ||  category == ZCategory.Type.PORTRAIT_ICON) {
            var resItem = ctx.item.getItem(dataId);
            if(Objects.isNull(resItem)) {
                return false;
            }
        } else if(category == ZCategory.Type.GROWTH) {
            var resGrowth = ctx.growth.get(dataId);
            if(Objects.isNull(resGrowth)) {
                return false;
            }
        } else if(category == ZCategory.Type.AVATAR) {
            var resGrowth = ctx.avatar.get(dataId);
            if(Objects.isNull(resGrowth)) {
                return false;
            }
        } else {
            return false;
        }

        if(count <= 0) {
            return false;
        }

        return true;
   }
}
