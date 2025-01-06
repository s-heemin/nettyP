package org.supercat.growstone.components.playerEventComponents;

import com.supercat.growstone.network.messages.TContentReward;
import com.supercat.growstone.network.messages.TPlayerEvent;
import com.supercat.growstone.network.messages.ZEventProgress;
import io.opencensus.resource.Resource;
import org.supercat.growstone.SavableObject;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.models.DMPlayerEvent;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;

import java.util.List;

public abstract class PlayerEvent {
    public final long eventId;
    public WorldPlayer player;
    private DMPlayerEvent model;
    public PlayerEvent(long eventId) {
        this.eventId = eventId;
    }

    public void setOwner(WorldPlayer player, DMPlayerEvent model) {
        this.player = player;
        this.model = model;
    }


    public void save() {
        SDB.dbContext.event.save(model);
    }

    public void setModel(DMPlayerEvent model) {
        this.model = model;
    }
    public abstract int getReward(int value, TPlayerEvent.Builder tEvent, List<TContentReward> outRewards);
    public DMPlayerEvent getModel() {
        return model;
    };

}
