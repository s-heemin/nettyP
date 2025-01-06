package org.supercat.growstone.worldComponents;

import com.supercat.growstone.network.messages.ZClan;
import com.supercat.growstone.network.messages.ZClanJoin;
import com.supercat.growstone.network.messages.ZClanMember;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Async;
import org.supercat.growstone.Out;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.models.DMClan;
import org.supercat.growstone.models.DMClanMember;
import org.supercat.growstone.rules.ClanRules;
import org.supercat.growstone.setups.SDB;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class WorldClanComponent {
    private static final Logger logger = LoggerFactory.getLogger(WorldClanComponent.class);
    private ConcurrentHashMap<Long, WorldClan> clans = new ConcurrentHashMap<>();
    public void initialize() {
        var models = SDB.dbContext.clan.getAll().stream()
                .filter(model -> model.state == ZClan.State.ACTIVE.getNumber())
                .collect(Collectors.toList());

        models.forEach(model -> clans.put(model.id, WorldClan.of(model)));
        start();
    }

    public void dailyReset() {
        for(WorldClan clan : clans.values()) {
            clan.dailyReset();
        }
    }
    private void start() {
        Async.repeat(() -> update(), 0, 60, TimeUnit.MILLISECONDS);
    }
    private void update() {
        clans.values().forEach(WorldClan::update);
    }

    public synchronized int createClan(long playerId, String clanName, Out<Long> clanId) {
        int status = ClanRules.checkClanName(clanName);
        if(!StatusCode.isSuccess(status)) {
            return status;
        }

        status = checkDuplicateClanName(clanName);
        if(!StatusCode.isSuccess(status)) {
            return status;
        }

        var model = DMClan.of(playerId, clanName, ZClan.State.ACTIVE.getNumber(), ZClanJoin.Type.AUTOMATIC.getNumber());
        int affected = SDB.dbContext.clan.insert(model);
        if(affected <= 0) {
            // save 실패면 이름이 겹쳐서이다.
            return StatusCode.ALREADY_EXIST_CLAN_NAME;
        }

        var clan = WorldClan.of(model);
        clans.put(model.id, clan);

        // 클랜장을 클랜에 넣어준다.
        var member = SDB.dbContext.clanMember.getByPlayerId(playerId);
        if(Objects.isNull(member)) {
            member = DMClanMember.of(playerId, model.id, ZClanMember.Role.LEADER.getNumber());
        } else {
            member.clan_id = model.id;
            member.role = ZClanMember.Role.LEADER.getNumber();
        }

        clan.insertClanMember(playerId, member);

        affected = SDB.dbContext.clanMember.save(member);
        if(affected < 0) {
            return StatusCode.FAIL;
        }

        clanId.set(model.id);

        return StatusCode.SUCCESS;
    }

    private int checkDuplicateClanName(String clanName) {
        for(WorldClan clan : clans.values()) {
            if(clan.getName().equals(clanName)) {
                return StatusCode.ALREADY_EXIST_CLAN_NAME;
            }
        }

        return StatusCode.SUCCESS;
    }

    // 클랜 가입은 여러 클랜에서 동시에 진행될 수 있기 때문에 이곳에서 한번에 처리해준다.
    public synchronized int joinClan(long playerId, WorldClan clan) {
        var pm = SDB.dbContext.player.get(playerId);
        if(Objects.isNull(pm)) {
            return StatusCode.NOT_FOUND_PLAYER;
        }

        var member = SDB.dbContext.clanMember.getByPlayerId(playerId);
        if(Objects.isNull(member)) {
            member = DMClanMember.of(playerId, 0, ZClanMember.Role.MEMBER.getNumber());
        }

        if(member.clan_id > 0) {
            return StatusCode.ALREADY_JOIN_CLAN;
        }

        member.clan_id = clan.getClanId();

        int affected = SDB.dbContext.clanMember.save(member);
        if(affected < 0) {
            return StatusCode.FAIL;
        }

        clan.insertClanMember(playerId, member);

        return StatusCode.SUCCESS;
    }

    public WorldClan getClan(long clanId) {
        return clans.get(clanId);
    }
    public List<WorldClan> getClans() {
        return new CopyOnWriteArrayList<>(clans.values());
    }
}
