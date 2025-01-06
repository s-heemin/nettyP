package org.supercat.growstone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.components.worldComponents.*;
import org.supercat.growstone.models.DMServer;
import org.supercat.growstone.worldComponents.WorldClanComponent;

public class World {
    private static final Logger logger = LoggerFactory.getLogger(World.class);

    public static World INSTANCE;
    public DMServer model;
    public WorldPlayerComponent worldPlayers;
    public WorldScheduleTaskComponent worldSchedule;
    public WorldRankComponent worldRank;
    public WorldFarmComponent worldFarm;
    public WorldDuelComponent worldDuel;
    public WorldEventComponent event;
    public WorldClanComponent worldClan;
    public WorldChatComponent chat;
    public WorldCommunityComponent community;

    public static Generator itemIDGenerator;
    public static World of(DMServer model) {
        return new World(model);
    }

    private World(DMServer model) {
        this.model = model;
        this.itemIDGenerator = new Generator(model.id);
        this.worldPlayers = new WorldPlayerComponent(model.id);
        this.worldSchedule = new WorldScheduleTaskComponent(model.id);
        this.worldRank = new WorldRankComponent();
        this.worldFarm = new WorldFarmComponent(model.id);
        this.worldDuel = new WorldDuelComponent();
        this.event = new WorldEventComponent();
        this.worldClan = new WorldClanComponent();
        this.chat = new WorldChatComponent();
        this.community = new WorldCommunityComponent();

    }

    public void initialize() {
        worldPlayers.initialize();
        worldSchedule.initialize();
        worldFarm.initalize();
        event.initialize();
        worldClan.initialize();
        community.initialize();
    }
}
