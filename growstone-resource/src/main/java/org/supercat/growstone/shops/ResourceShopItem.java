package org.supercat.growstone.shops;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedMap;
import com.supercat.growstone.network.messages.ZShop;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Element;
import org.supercat.growstone.ResourceBase;
import org.supercat.growstone.UtcZoneDateTime;
import org.supercat.growstone.XMLHelper;
import org.supercat.growstone.containers.ResourceReward;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ResourceShopItem extends ResourceBase {
    public final ZShop.Type type;
    public final long maxBuyCount;
    public final boolean visible;
    public int adViewGachaMinCount;
    public int adViewGachaMaxCount;
    public int dailyViewMaxCount;
    public final ResourceBuyLimit buyLimit;
    public final ImmutableList<ResourcePayment> payments;
    public final ImmutableList<ResourceReward> addRewards;
    public final ImmutableSet<Integer> tryGachaCount;
    public final Instant startTime;
    public final Instant endTime;
    public final boolean isGetMail;
    public final long gachaLevelGroupId;
    public final long gachaGroupId;
    public final long gachaPickUpId;
    public final int continueGroupId;
    public final int continueStepId;
    public final long conditionPackageId;
    public final long shopPassId;

    public ResourceShopItem(Element e) {
        super(e);

        this.type = XMLHelper.getChildEnum(e, "Type", ZShop.Type.NONE);
        this.maxBuyCount = XMLHelper.getChildLong(e, "MaxBuyCount", 0);
        this.isGetMail = XMLHelper.getChildBoolean(e, "IsGetMail", false);
        this.visible = XMLHelper.getChildBoolean(e, "Visible", false);
        this.adViewGachaMinCount = XMLHelper.getChildInt(e, "AdViewGachaMinCount", 0);
        this.adViewGachaMaxCount = XMLHelper.getChildInt(e, "AdViewGachaMaxCount", 0);
        this.continueGroupId = XMLHelper.getChildInt(e, "Group", 0);
        this.continueStepId = XMLHelper.getChildInt(e, "Step", 0);
        this.conditionPackageId = XMLHelper.getChildLong(e, "ConditionalID", 0);
        this.gachaLevelGroupId = XMLHelper.getChildLong(e, "GachaLevelGroup", 0);
        this.gachaGroupId = XMLHelper.getChildLong(e, "GachaGroup", 0);
        this.gachaPickUpId = XMLHelper.getChildLong(e, "GachaPickUp", 0);
        this.dailyViewMaxCount = XMLHelper.getChildInt(e, "DailyAdViewCount", 0);
        this.shopPassId = XMLHelper.getChildLong(e, "ShopPassID", 0);
        var tempGachaCount = Arrays.stream(StringUtils.split(XMLHelper.getChildText(e, "PaymentGachaTryCountList", StringUtils.EMPTY), ","))
                .filter(x -> !Strings.isNullOrEmpty(x))
                .map(x -> Integer.parseInt(x.trim())).collect(Collectors.toSet());
        this.tryGachaCount = ImmutableSet.copyOf(tempGachaCount);
        var tempBuyLimit = e.getChild("BuyLimit");
        if (tempBuyLimit != null) {
            this.buyLimit = new ResourceBuyLimit(tempBuyLimit);
        } else {
            this.buyLimit = null;
        }

        var tempPaymentList = new ArrayList<ResourcePayment>();
        var tempPayment = e.getChildren("Payment");
        for (var payment : tempPayment) {
            tempPaymentList.add(new ResourcePayment(payment));
        }

        this.payments = ImmutableList.copyOf(tempPaymentList);

        var tempRewards = new ArrayList<ResourceReward>();
        var tempAddRewards = e.getChildren("AddRewards");
        for (var addReward : tempAddRewards) {
            var elements = addReward.getChildren("Reward");
            for(var ele : elements) {
                tempRewards.add(new ResourceReward(ele));
            }
        }

        this.addRewards = ImmutableList.copyOf(tempRewards);

        var startTime = XMLHelper.getChildText(e, "SaleStartAt", null);
        this.startTime = Strings.isNullOrEmpty(startTime)
                ? UtcZoneDateTime.SAFE_INSTANT_MIN
                : UtcZoneDateTime.ofyyyyMMddHHmmss(startTime).toInstant();

        var endTime = XMLHelper.getChildText(e, "SaleEndAt", null);
        this.endTime = Strings.isNullOrEmpty(endTime)
                ? UtcZoneDateTime.SAFE_INSTANT_MIN
                : UtcZoneDateTime.ofyyyyMMddHHmmss(endTime).toInstant();
    }
}
