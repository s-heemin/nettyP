package org.supercat.growstone.controllers;

import com.supercat.growstone.network.messages.Packet;
import com.supercat.growstone.network.messages.PacketType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.Async;
import org.supercat.growstone.player.WorldPlayer;
import org.supercat.growstone.WorldSession;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class ControllerSet {
    private static final Logger logger = LoggerFactory.getLogger(ControllerSet.class);
    private final WorldSession session;
    private final Map<Class<? extends IMappingController>, IMappingController> controllers;
    private final Map<PacketType, Consumer<Packet>> responsors;

    public ControllerSet(WorldSession session) {
        var entries = Map.ofEntries(
                composeController(new LoginController(session), this::loginAsync),
                composeController(new WorldController(session), this::worldAsync),
                composeController(new GrowthController(session), this::worldAsync),
                composeController(new CollectionController(session), this::worldAsync),
                composeController(new PresetController(session), this::worldAsync),
                composeController(new DungeonController(session), this::worldAsync),
                composeController(new FriendController(session), this::worldAsync),
                composeController(new ExplorationController(session), this::worldAsync),
                composeController(new FarmController(session), this::worldAsync),
                composeController(new DiggingController(session), this::worldAsync),
                composeController(new ShopController(session), this::worldAsync),
                composeController(new EventController(session), this::worldAsync),
                composeController(new StoneStatueController(session), this::worldAsync),
                composeController(new ClanController(session), this::worldAsync),
                composeController(new ChatController(session), this::chatAsync)
        );

        HashMap<Class<? extends IMappingController>, IMappingController> controllers = new HashMap<>();
        HashMap<PacketType, Consumer<Packet>> responsors = new HashMap<>();

        // 컨트롤러 추출
        entries.keySet().forEach(controller -> controllers.put(controller.getClass(), controller));

        // 모든 리스폰서 추출
        entries.values().forEach(composedResponsors -> {
            composedResponsors.forEach((type, composedResponse) -> {
                var old = responsors.put(type, composedResponse);
                if (Objects.nonNull(old)) {
                    logger.error("there is already a handler - key({})", type);
                }
            });
        });


        this.session = session;
        this.controllers = controllers;
        this.responsors = responsors;
    }

    // 패킷 당 쓰레드 + 응답자 결합
    public static Map.Entry<IMappingController, HashMap<PacketType, Consumer<Packet>>> composeController(
            IMappingController controller,
            BiConsumer<Packet, Consumer<Packet>> executor
    ) {

        HashMap<PacketType, Consumer<Packet>> composedResponsors = new HashMap<>();

        controller.handlers().forEach((key, responsor) -> {
            // 레이턴시 + 디버깅 패킷 처리 리스폰서 만들고
            var composedResponsor = makeResponsor(responsor);

            // 패킷 타입과 쓰레드 리스폰스 결합
            var old = composedResponsors.put(key, p -> executor.accept(p, composedResponsor));
            if (Objects.nonNull(old)) {
                logger.error("there is already a handler - key({})", key);
            }
        });

        return Map.entry(controller, composedResponsors);
    }

    private static Consumer<Packet> makeResponsor(Consumer<Packet> responsor) {
        return packet -> {
            responsor.accept(packet);
        };
    }

    private void loginAsync(Packet packet, Consumer<Packet> responsor) {
        Async.loginAsync(() -> responsor.accept(packet));
    }
    private void worldAsync(Packet packet, Consumer<Packet> responsor) {
        var player = session.getPlayer();
        if (Objects.isNull(player)) {
            return;
        }

        if (player.getSession() != session) {
            return;
        }

        Async.worldAsync(player.getId(), () -> responsor.accept(packet));
    }

    private void chatAsync(Packet packet, Consumer<Packet> responsor) {
        var player = session.getPlayer();
        if (Objects.isNull(player)) {
            return;
        }

        if (player.getSession() != session) {
            return;
        }

        Async.chatAsync(() -> responsor.accept(packet));
    }


    public void setPlayer(WorldPlayer player) {
        controllers.values().forEach(x -> x.setPlayer(player));
    }

    public void response(Packet packet) {
        var responsor = responsors.get(packet.getType());
        if (Objects.isNull(responsor)) {
            // 로그 찍을 필요가 있음
            return;
        }


        responsor.accept(packet);
    }
}
