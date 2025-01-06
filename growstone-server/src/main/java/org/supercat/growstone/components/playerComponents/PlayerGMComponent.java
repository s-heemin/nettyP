package org.supercat.growstone.components.playerComponents;

import com.google.common.collect.ImmutableMap;
import com.supercat.growstone.network.messages.TGrowth;
import com.supercat.growstone.network.messages.ZCategory;
import com.supercat.growstone.network.messages.ZGrowthNotify;
import com.supercat.growstone.network.messages.ZStageNotify;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.config.SConfig;
import org.supercat.growstone.events.EventPlayerDailyResetSchedule;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.setups.SDB;
import org.supercat.growstone.types.CheatCmd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Function;

public class PlayerGMComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerGMComponent.class);

    private final WorldPlayer player;

    private ImmutableMap<CheatCmd, Function<String[], Integer>> cheatCommands = null;

    public PlayerGMComponent(WorldPlayer player) {
        this.player = player;
    }

    private ImmutableMap<CheatCmd, Function<String[], Integer>> createCheatCommands() {
        var commands = new HashMap<CheatCmd, Function<String[], Integer>>();

        commands.put(CheatCmd.ITEMS, this::cheat_ITEMS);
        commands.put(CheatCmd.CURRENCY, this::cheat_CURRENCY);
        commands.put(CheatCmd.GROWTHS, this::cheat_GROWTHS);
        commands.put(CheatCmd.GROWTHS_CLEAR, this::cheat_GROWTHS_CLEAR);
        commands.put(CheatCmd.GROWTH_ALL, this::cheat_GROWTH_ALL);
        commands.put(CheatCmd.GROWTHS_ALL_UPGRADE, this::cheat_GROWTHS_ALL_UPGRADE);
        commands.put(CheatCmd.AVATAR, this::cheat_AVATAR);
        commands.put(CheatCmd.AVATAR_ALL, this::cheat_AVATAR_ALL);
        commands.put(CheatCmd.AVATAR_CLEAR, this::cheat_AVATAR_CLEAR);
        commands.put(CheatCmd.PORTRAIT, this::cheat_PORTRAIT);
        commands.put(CheatCmd.PORTRAIT_ALL, this::cheat_PORTRAIT_ALL);
        commands.put(CheatCmd.PORTRAIT_CLEAR, this::cheat_PORTRAIT_CLEAR);
        commands.put(CheatCmd.STAGE, this::cheat_STAGE);
        commands.put(CheatCmd.DAILY_RESET, this::cheat_DAILY_RESET);
        commands.put(CheatCmd.STAT_GROWTH_INIT, this::cheat_STAT_GROWTH_INIT);
        commands.put(CheatCmd.COLLECTION_ALL, this::cheat_COLLECTION_ALL);
        commands.put(CheatCmd.COLLECTION_CLEAR, this::cheat_COLLECTION_CLEAR);
        commands.put(CheatCmd.ATTACK_RANK, this::cheat_ATTACK_RANK);


        return ImmutableMap.copyOf(commands);
    }

    private String[] fetchCheatArguments(String command) {
        String[] args = StringUtils.split(command, " ");
        args[0] = StringUtils.remove(args[0], "/");
        args[0] = StringUtils.lowerCase(args[0]);

        return args;
    }

    public int cheatShell(String command) {
        if (!SConfig.getConfig().isDebugMode()) {
            return StatusCode.FAIL;
        }
        try {
            logger.info("cheat - PlayerID({}) name({}) command({})", player.getId(), player.getName(), command);

            var args = fetchCheatArguments(command);

            if (Objects.isNull(cheatCommands)) {
                cheatCommands = createCheatCommands();
            }

            var cheatCmd = CheatCmd.stringTo.get(args[0]);
            if (Objects.isNull(cheatCmd)) {
                logger.error("unknown cheat command - command({})", command);
                return StatusCode.FAIL;
            }

            var cheatMethod = cheatCommands.get(cheatCmd);
            if (Objects.isNull(cheatMethod)) {
                logger.error("unknown cheat command - command({})", command);
                return StatusCode.FAIL;
            }

            // 성공일 경우, 토스트 메세지 띄움
            int status = cheatMethod.apply(args);

            var text = "";
            if (StatusCode.isSuccess(status)) {
                text = player.getName() + "이(가) 치트 " + command + "을(를) 사용 했습니다.";
            } else {
                text = player.getName() + "이(가) 치트 " + command + "을(를) 잘못 사용 했습니다.";
            }


            return status;
        } catch (Exception e) {
            SLog.logException(e);
        }

        return StatusCode.FAIL;
    }

    private int cheat_ITEMS(String[] args) {
        if (args.length != 3) {
            return StatusCode.FAIL;
        }

        long itemId = Long.parseLong(args[1]);
        long count = Long.parseLong(args[2]);

        return player.categoryFilter.add(ZCategory.Type.ITEM, itemId, count);
    }

    private int cheat_CURRENCY(String[] args) {
        if (args.length != 3) {
            return StatusCode.FAIL;
        }

        long itemId = Long.parseLong(args[1]);
        long count = Long.parseLong(args[2]);

        var resItem = ResourceManager.INSTANCE.item.getItem(itemId);
        return player.currency.addCurrency(resItem.type, count);
    }

    private int cheat_GROWTHS(String[] args) {
        if (args.length != 3) {
            return StatusCode.FAIL;
        }

        long growthId = Long.parseLong(args[1]);
        long count = Long.parseLong(args[2]);

        return player.categoryFilter.add(ZCategory.Type.GROWTH, growthId, count);
    }

    private int cheat_GROWTHS_CLEAR(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        player.growth.clearForCheat();

        return StatusCode.SUCCESS;
    }

    private int cheat_AVATAR(String[] args) {
        if (args.length != 3) {
            return StatusCode.FAIL;
        }

        long avatarId = Long.parseLong(args[1]);
        long count = Long.parseLong(args[2]);

        return player.categoryFilter.add(ZCategory.Type.AVATAR, avatarId, count);
    }

    private int cheat_AVATAR_ALL(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        for (var resAvatar : ResourceManager.INSTANCE.avatar.getAll()) {
            player.categoryFilter.add(ZCategory.Type.AVATAR, resAvatar.id, 1);
        }

        return StatusCode.SUCCESS;
    }

    private int cheat_AVATAR_CLEAR(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        player.avatar.clearAllForCheat();

        return StatusCode.SUCCESS;
    }

    private int cheat_PORTRAIT(String[] args) {
        if (args.length != 2) {
            return StatusCode.FAIL;
        }

        long portrait = Long.parseLong(args[1]);

        return player.categoryFilter.add(ZCategory.Type.PORTRAIT_ICON, portrait, 1);
    }

    private int cheat_PORTRAIT_ALL(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        var resPortraits = ResourceManager.INSTANCE.item.getAllByCategory(ZCategory.Type.PORTRAIT_ICON);
        for (var resPortrait : resPortraits) {
            player.categoryFilter.add(ZCategory.Type.PORTRAIT_ICON, resPortrait.id, 1);
        }

        return StatusCode.SUCCESS;
    }

    private int cheat_PORTRAIT_CLEAR(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        player.portraitIcon.clearPortraitIcon();

        return StatusCode.SUCCESS;
    }

    private int cheat_STAGE(String[] args) {
        if (args.length != 3) {
            return StatusCode.FAIL;
        }

        long stageGroupId = Long.parseLong(args[1]);
        int stageId = Integer.parseInt(args[2]);

        var resStageGroup = ResourceManager.INSTANCE.stageGroup.get(stageGroupId);
        if (Objects.isNull(stageGroupId)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var resStage = resStageGroup.stages.get(stageId);
        if (Objects.isNull(resStage)) {
            return StatusCode.INVALID_RESOURCE;
        }

        var model = player.getModel();
        model.stage_group = stageGroupId;
        model.stage = stageId;

        SDB.dbContext.player.updateStage(model);

        player.sendPacket(0, ZStageNotify.newBuilder()
                .setStageGroup(stageGroupId)
                .setStage(stageId));

        return StatusCode.SUCCESS;
    }

    private int cheat_GROWTH_ALL(String[] args) {
        if (args.length != 2) {
            return StatusCode.FAIL;
        }

        long count = Integer.parseInt(args[1]);

        var l = new ArrayList<TGrowth>();
        var growths = ResourceManager.INSTANCE.growth.getAll();
        for (var growth : growths) {
            int status = player.growth.add(growth.id, count, false);
            if (!StatusCode.isSuccess(status)) {
                continue;
            }

            l.add(player.growth.getTGrowth(growth.id));
        }

        player.sendPacket(0, ZGrowthNotify.newBuilder().addAllGrowths(l));
        return StatusCode.SUCCESS;
    }

    private int cheat_GROWTHS_ALL_UPGRADE(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        player.growth.upgradeAllForCheat();

        return StatusCode.SUCCESS;

    }

    private int cheat_STAT_GROWTH_INIT(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        player.statGrowth.clearForCheat();

        return StatusCode.SUCCESS;
    }

    private int cheat_ATTACK_RANK(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        World.INSTANCE.worldSchedule.dailyResetTaskByTest().resetDailyAttackRankForTest();

        return StatusCode.SUCCESS;
    }


    private int cheat_DAILY_RESET(String[] args) {
        if (args.length != 1 && args.length != 2) {
            return StatusCode.FAIL;
        }

        final long delaySec = args.length == 1 ? 0L : Integer.parseInt(args[1]) * 1000L;
        Async.delayAsync(() -> {
            player.topic.publish(new EventPlayerDailyResetSchedule());
        }, delaySec);

        return StatusCode.SUCCESS;
    }

    private int cheat_COLLECTION_ALL(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }


        player.collection.allCompleteByCheat();

        return StatusCode.SUCCESS;

    }

    private int cheat_COLLECTION_CLEAR(String[] args) {
        if (args.length != 1) {
            return StatusCode.FAIL;
        }

        player.collection.allClearByCheat();

        return StatusCode.SUCCESS;

    }
}
