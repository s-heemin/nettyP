package org.supercat.growstone.components.playerComponents;


import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.components.ItemFlow;
import org.supercat.growstone.player.WorldPlayer;

import java.util.HashMap;
import java.util.Objects;

public class PlayerItemFilterComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerItemFilterComponent.class);

    private WorldPlayer player;

    public ImmutableMap<ZResource.Type, ItemFlow.Add> addActions;
    public ImmutableMap<ZResource.Type, ItemFlow.Use> useActions;

    public PlayerItemFilterComponent(WorldPlayer player) {
        var addHandlers = new HashMap<ZResource.Type, ItemFlow.Add>();
        addHandlers.put(ZResource.Type.MATERIAL, this::addItem);
        addHandlers.put(ZResource.Type.EXP, this::addExp);
        addHandlers.put(ZResource.Type.REMOVE_AD, this::addRemoveAdvertiseItem);
        addHandlers.put(ZResource.Type.PORTRAIT_ICON, this::addPortrait);
        addHandlers.put(ZResource.Type.GOLD, this::addCurrency);
        addHandlers.put(ZResource.Type.PAY_RUBY, this::addCurrency);
        addHandlers.put(ZResource.Type.FREE_RUBY, this::addCurrency);
        addHandlers.put(ZResource.Type.PAY_DIAMOND, this::addCurrency);
        addHandlers.put(ZResource.Type.FREE_DIAMOND, this::addCurrency);
        addActions = ImmutableMap.copyOf(addHandlers);


        var useHandlers = new HashMap<ZResource.Type, ItemFlow.Use>();
        useHandlers.put(ZResource.Type.MATERIAL, this::useItem);
        useHandlers.put(ZResource.Type.GOLD, this::useCurrency);
        useHandlers.put(ZResource.Type.PAY_RUBY, this::useCurrency);
        useHandlers.put(ZResource.Type.FREE_RUBY, this::useCurrency);
        useHandlers.put(ZResource.Type.PAY_DIAMOND, this::useCurrency);
        useHandlers.put(ZResource.Type.FREE_DIAMOND, this::useCurrency);

        useActions = ImmutableMap.copyOf(useHandlers);


        this.player = player;
    }

    public int addItem(long dataId, long count) {
        return player.itemBag.add(dataId, count);
    }

    public int useItem(long dataId, long count) {
        return player.itemBag.use(dataId, count);
    }

    private int addCurrency(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.currency.addCurrency(resItem.type, count);
    }

    private int useCurrency(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.currency.useCurrency(resItem.type, count);
    }

    private int addPortrait(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        return player.portraitIcon.add(dataId, count);
    }
    public int addExp(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resItem.type != ZResource.Type.EXP) {
            return StatusCode.INVALID_RESOURCE;
        }

        player.levelUp(count);

        return StatusCode.SUCCESS;
    }

    public int addRemoveAdvertiseItem(long dataId, long count) {
        var resItem = ResourceManager.INSTANCE.item.getItem(dataId);
        if(Objects.isNull(resItem)) {
            return StatusCode.INVALID_RESOURCE;
        }

        if(resItem.type != ZResource.Type.REMOVE_AD) {
            return StatusCode.INVALID_RESOURCE;
        }

        player.activeRemoveAdvertise();

        return StatusCode.SUCCESS;
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public int add(ZResource.Type type, long dataId, long count) {
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

        return StatusCode.SUCCESS;

    }

    public int use(ZResource.Type type, long dataId, long count) {
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

        return StatusCode.SUCCESS;
    }
}
