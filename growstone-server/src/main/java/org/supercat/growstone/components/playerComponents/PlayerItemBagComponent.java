package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.TItem;
import com.supercat.growstone.network.messages.ZItemNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.models.DMPlayerItem;
import org.supercat.growstone.setups.SDB;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerItemBagComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerItemBagComponent.class);

    private WorldPlayer player;
    private ConcurrentHashMap<Long, DMPlayerItem> models = new ConcurrentHashMap<>();
    private final CloseableLock lock = new CloseableLock();


    public PlayerItemBagComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var items = SDB.dbContext.item.getByPlayerId(player.getId());
        if (items.isEmpty()) {
            return;
        }

        for (var item : items) {
            models.put(item.item_data_id, item);
        }
    }

    private DMPlayerItem getOrCreate(long itemDataId) {
        var model = models.get(itemDataId);
        if (Objects.isNull(model)) {
            model = DMPlayerItem.of(player.getId(), itemDataId, 0);
            model.item_id = World.itemIDGenerator.nextId();
            models.put(itemDataId, model);
        }

        return model;
    }

    public TItem getSyncTItem(long itemDataId) {
        var model = models.get(itemDataId);
        if (Objects.isNull(model)) {
            return null;
        }

        return TBuilderOf.buildOf(model);
    }

    public List<TItem> getTItems() {
        return models.entrySet().stream()
                .map(x -> TBuilderOf.buildOf(x.getValue()))
                .collect(Collectors.toList());
    }

    public int use(long dataId, long count) {
        try (var ignored = lock.begin()) {
            if (count <= 0) {
                return StatusCode.FAIL;
            }

            var model = models.get(dataId);
            if (Objects.isNull(model) || model.count < count) {
                return StatusCode.NOT_ENOUGH_ITEM;
            }

            model.count = Math.max(0, model.count - count);

            save();

            player.sendPacket(0, ZItemNotify.newBuilder()
                    .addItems(TItem.newBuilder()
                            .setId(model.item_id)
                            .setDataId(model.item_data_id)
                            .setCount(model.count)));
            return StatusCode.SUCCESS;

        } catch (InterruptedException e) {
            SLog.logException(e);
            return StatusCode.FAIL;
        }
    }

    public int add(long dataId, long count) {
        try (var ignored = lock.begin()) {

            if (count <= 0) {
                return StatusCode.FAIL;
            }

            var model = getOrCreate(dataId);
            model.count += count;

            save();

            player.sendPacket(0, ZItemNotify.newBuilder()
                    .addItems(TItem.newBuilder()
                            .setId(model.item_id)
                            .setDataId(model.item_data_id)
                            .setCount(model.count)));

            return StatusCode.SUCCESS;
        } catch (InterruptedException e) {
            SLog.logException(e);
            return StatusCode.FAIL;
        }
    }

    public long getItemCount(long dataId) {
        return getOrCreate(dataId).count;
    }

    public void save() {
        models.values().stream()
                .forEach(x -> SDB.dbContext.item.save(x));
    }
}
