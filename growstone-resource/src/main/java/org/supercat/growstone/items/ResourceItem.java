package org.supercat.growstone.items;

import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZResource;
import com.supercat.growstone.network.messages.ZTier;
import org.supercat.growstone.ResourceBase;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.util.Objects;

public class ResourceItem extends ResourceBase {
    private static final Logger logger = LoggerFactory.getLogger(ResourceItem.class);

    public final ZCategory.Type category;
    public final ZResource.Type type;
    public final ZTier.Type tier;
    public final ResourceReward duplicateReward;

    public ResourceItem(Element e) {
        super(e);

        this.category = XMLHelper.getChildEnum(e, "Category", ZCategory.Type.NONE);
        this.type = XMLHelper.getChildEnum(e, "Type", ZResource.Type.NONE);
        this.tier = XMLHelper.getChildEnum(e, "Tier", ZTier.Type.NONE);

        var eDuplicate = e.getChild("DuplicateReward");
        duplicateReward = Objects.nonNull(eDuplicate) ? new ResourceReward(eDuplicate.getChild("Reward")) : null;

    }
}
