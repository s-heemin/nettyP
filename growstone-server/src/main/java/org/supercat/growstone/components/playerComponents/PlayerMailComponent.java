package org.supercat.growstone.components.playerComponents;

import com.supercat.growstone.network.messages.TMail;
import com.supercat.growstone.network.messages.TMailReward;
import com.supercat.growstone.network.messages.ZMailNotify;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.*;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.jsons.JMPlayerMailReward;
import org.supercat.growstone.models.DMPlayerMail;
import org.supercat.growstone.rules.MailRules;
import org.supercat.growstone.setups.SDB;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class PlayerMailComponent {
    private static final Logger logger = LoggerFactory.getLogger(PlayerMailComponent.class);
    private WorldPlayer player;
    private ConcurrentHashMap<Long, DMPlayerMail> models = new ConcurrentHashMap<>();

    public PlayerMailComponent(WorldPlayer player) {
        this.player = player;
    }

    public void initialize() {
        var mails = SDB.dbContext.mail.getAllByNoReadMail(player.getId());
        if (Objects.isNull(models)) {
            return;
        }

        models = mails.stream()
                .collect(ConcurrentHashMap::new, (map, mail) -> map.put(mail.id, mail), ConcurrentHashMap::putAll);
    }

    public void insertMail(int mailType, String sender, List<JMPlayerMailReward> rewards, Instant expireAt) {
        var json = JsonConverter.to(rewards);
        var model = DMPlayerMail.of(player.getId(), mailType, sender, json, expireAt);
        SDB.dbContext.mail.insert(model);

        models.put(model.id, model);

        var mailRewardsBuilder = MailRules.getTMailRewards(rewards);
        var mailBuilder = TBuilderOf.buildOf(model, mailRewardsBuilder);

        player.sendPacket(0, ZMailNotify.newBuilder()
                .setMail(mailBuilder));
    }

    public void insertMail(int mailType, String sender, Instant expireAt) {
        var model = DMPlayerMail.of(player.getId(), mailType, sender, expireAt);
        SDB.dbContext.mail.insert(model);

        models.put(model.id, model);

        var mailBuilder = TBuilderOf.buildOf(model, List.of());

        player.sendPacket(0, ZMailNotify.newBuilder()
                .setMail(mailBuilder));
    }

    public List<TMail> getMails(Instant now) {
        return models.values().stream()
                .filter(x -> now.isBefore(x.expire_at))
                .map(x -> TBuilderOf.buildOf(x, MailRules.getTMailRewards(x.rewards)))
                .collect(Collectors.toList());
    }

    public void readMails(List<Long> mailIds, List<TMail> outMails) {
        var now = Instant.now();
        for (var mailId : mailIds) {
            var model = models.get(mailId);
            if (Objects.isNull(model)) {
                logger.error("invalid mailId : ({}), playerId : ({})", mailId, player.getId());
                continue;
            }

            if (now.isAfter(model.expire_at)) {
                logger.error("expired mail : ({}), playerId : ({})", mailId, player.getId());
                continue;
            }

            // 메일 저장
            model.is_read = true;
            SDB.dbContext.mail.updateRead(model);

            var mailRewardsBuilder = MailRules.getTMailRewards(model.rewards);

            addRewards(mailRewardsBuilder);
            outMails.add(TBuilderOf.buildOf(model, mailRewardsBuilder));
        }
    }

    private void addRewards(List<TMailReward> rewards) {
        rewards.stream()
                .forEach(x -> player.categoryFilter.add(
                        x.getCategory(), x.getDataId(), x.getCount()));
    }

}
