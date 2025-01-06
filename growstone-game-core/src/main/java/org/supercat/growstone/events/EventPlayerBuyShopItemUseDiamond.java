package org.supercat.growstone.events;

public class EventPlayerBuyShopItemUseDiamond extends Event {
    public final long value;
    public final long shopDataId;
    public EventPlayerBuyShopItemUseDiamond(long value, long shopDataId) {
        super(EventType.PLAYER_BUY_SHOP_ITEM_USE_DIAMOND);
        this.value = value;
        this.shopDataId = shopDataId;
    }
}
