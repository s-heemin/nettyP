package org.supercat.growstone.components.playerComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.TItem;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.components.Category;
import org.supercat.growstone.events.EventPlayerGetItem;
import org.supercat.growstone.events.EventPlayerUseItem;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.containers.ResourceReward;
import org.supercat.growstone.rules.ItemRules;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class PlayerCategoryFilterComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerCategoryFilterComponent.class);
    public WorldPlayer player;

    public ImmutableMap<ZCategory.Type, Category.Add> addActions;
    public ImmutableMap<ZCategory.Type, Category.Use> useActions;


    public PlayerCategoryFilterComponent(WorldPlayer player) {
        this.player = player;

        var addHandlers = new HashMap<ZCategory.Type, Category.Add>();
        addHandlers.put(ZCategory.Type.ITEM, this::addItem);
        addHandlers.put(ZCategory.Type.PORTRAIT_ICON, this::addItem);
        addHandlers.put(ZCategory.Type.GROWTH, this::addGrowth);
        addHandlers.put(ZCategory.Type.AVATAR, this::addAvatar);
        addHandlers.put(ZCategory.Type.AVATAR_STONE_STATUE, this::addAvatarStoneStatue);
        addActions = ImmutableMap.copyOf(addHandlers);


        var useHandlers = new HashMap<ZCategory.Type, Category.Use>();
        useHandlers.put(ZCategory.Type.ITEM, this::useItem);
        useHandlers.put(ZCategory.Type.GROWTH, this::useGrowth);
        useActions = ImmutableMap.copyOf(useHandlers);
    }

    public int add(ResourceReward reward, long multiple) {
        return add(reward.type, reward.rewardId, reward.count * multiple);
    }
    public int add(ZCategory.Type type, long dataId, long count) {
        var action = addActions.get(type);
        if(Objects.isNull(action)) {
            logger.error("invalid addAction type - playerId({}), type({})", player.getId(), type);
            return StatusCode.INVALID_REQUEST;
        }

        int status = action.add(dataId, count);
        if(!StatusCode.isSuccess(status)) {
            logger.error("add action fail - playerId({}), type({}), dataId({})", player.getId(), type, dataId);
            return status;
        }

        player.topic.publish(new EventPlayerGetItem(type, dataId, count));

        return StatusCode.SUCCESS;

    }

    public int use(ZCategory.Type type, long dataId, long count) {
        var action = useActions.get(type);
        if(Objects.isNull(action)) {
            logger.error("invalid useAction type - playerId({}), type({})", player.getId(), type);
            return StatusCode.INVALID_REQUEST;
        }

        int status = action.use(dataId, count);
        if(!StatusCode.isSuccess(status)) {
            logger.error("add action fail - playerId({}), type({}), dataId({})", player.getId(), type, dataId);
            return status;
        }

        player.topic.publish(new EventPlayerUseItem(type, dataId, count));

        return StatusCode.SUCCESS;
    }

    private int addItem(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.itemFilter.add(resItem.type, dataId, count);
    }
    private int addGrowth(long dataId, long count) {
        var resGrowth = ResourceManager.INSTANCE.growth.get(dataId);
        if(Objects.isNull(resGrowth)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.growth.add(dataId, count, true);
    }
    private int addAvatar(long dataId, long count) {
        var resAvatar = ResourceManager.INSTANCE.avatar.get(dataId);
        if(Objects.isNull(resAvatar)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.avatar.add(dataId, count);
    }

    private int addAvatarStoneStatue(long dataId, long count) {
        var resAvatar = ResourceManager.INSTANCE.avatar.get(dataId);
        if (Objects.isNull(resAvatar)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.stoneStatueAvatar.add(dataId, count);
    }


    private int useItem(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.itemFilter.use(resItem.type, dataId, count);
    }
    private int useGrowth(long dataId, long count) {
        var resGrowth = ResourceManager.INSTANCE.growth.get(dataId);
        if(Objects.isNull(resGrowth)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.growth.use(dataId, count);
    }

    public void syncItem(List<TItem> addItems) {
        for(var item : addItems) {
            var resItem = ResourceManager.INSTANCE.item.getItem(item.getDataId());
            if(Objects.isNull(resItem)) {
                logger.error("invalid item dataID : ({}), playerId : ({})", item.getDataId(), player.getId());
                continue;
            }

            if(resItem.type == ZResource.Type.EXP) {
                int status = player.itemFilter.addExp(item.getDataId(), item.getCount());
                if(!StatusCode.isSuccess(status)) {
                    logger.error("add exp fail - playerId({}), dataId({})", player.getId(), item.getDataId());
                }
                continue;
            }

            if(ItemRules.currencyTypes.contains(resItem.type)) {
                int status = player.currency.addCurrency(resItem.type,  item.getCount());
                if(!StatusCode.isSuccess(status)) {
                    logger.error("add currency fail - playerId({}), dataId({})", player.getId(), item.getDataId());
                }
            } else if(ItemRules.itemTypes.contains(resItem.type)) {
                int status = player.itemFilter.add(resItem.type, item.getDataId(), item.getCount());
                if(!StatusCode.isSuccess(status)) {
                    logger.error("add item fail - playerId({}), dataId({})", player.getId(), item.getDataId());
                }
            }

        }
    }


    public long getMaterial(ZCategory.Type type, long dataId) {
        if(type == ZCategory.Type.ITEM) {
            var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
            if(Objects.isNull(resItem)) {
                return 0;
            }

            if(ItemRules.currencyTypes.contains(resItem.type)) {
                return player.currency.getCurrency(resItem.type);
            } else {
                return player.itemBag.getItemCount(dataId);
            }

        } else if(type == ZCategory.Type.GROWTH) {
            // 성장이 재료이면 안되는데..혹시나 성장을 사용하게 될 수 있으니까 만들어놓는다.
            var resGrowth = ResourceManager.INSTANCE.growth.get(dataId);
            if(Objects.isNull(resGrowth)) {
                return 0;
            }

            var model = player.growth.getGrowth(dataId);

            return Objects.isNull(model) ? 0 : model.model.count;
        }

        return 0;
    }
}
