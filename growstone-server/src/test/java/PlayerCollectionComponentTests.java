import com.supercat.growstone.network.messages.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.World;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PlayerCollectionComponentTests extends BaseServerTests {
    @Test
    void collectionTest() {
        var player = TestPlayerUtils.of();
        Assertions.assertNotNull(player);

        var resStones = ResourceManager.INSTANCE.growth.getAll().stream()
                .filter(x -> x.type == ZGrowth.Type.STONE)
                .collect(Collectors.toList());
        Assertions.assertTrue(resStones.size() > 0);

        var resCollection = ResourceManager.INSTANCE.collection.getAll().stream()
                .filter(x -> x.type == ZResource.Type.STONE)
                .findFirst()
                .orElse(null);
        Assertions.assertNotNull(resCollection);
        var zLevelUpInfo = TCollectionLevelUp.newBuilder()
                .setType(ZResource.Type.STONE)
                .setDataId(-1)
                .setTargetLevel(1)
                .build();
        int status = player.collection.levelUp(List.of(zLevelUpInfo), List.of());
        Assertions.assertEquals(StatusCode.INVALID_RESOURCE, status);
        zLevelUpInfo = TCollectionLevelUp.newBuilder()
                .setType(ZResource.Type.NONE)
                .setDataId(resCollection.id)
                .setTargetLevel(2)
                .build();
        status = player.collection.levelUp(List.of(zLevelUpInfo), List.of());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        zLevelUpInfo = TCollectionLevelUp.newBuilder()
                .setType(ZResource.Type.NONE)
                .setDataId(resCollection.id)
                .setTargetLevel(resCollection.maxLevel + 1)
                .build();
        status = player.collection.levelUp(List.of(zLevelUpInfo), List.of());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        zLevelUpInfo = TCollectionLevelUp.newBuilder()
                .setType(ZResource.Type.NONE)
                .setDataId(resCollection.id)
                .setTargetLevel(resCollection.maxLevel)
                .build();
        status = player.collection.levelUp(List.of(zLevelUpInfo), List.of());
        Assertions.assertEquals(StatusCode.INVALID_REQUEST, status);

        zLevelUpInfo = TCollectionLevelUp.newBuilder()
                .setType(ZResource.Type.STONE)
                .setDataId(resCollection.id)
                .setTargetLevel(1)
                .build();
        status = player.collection.levelUp(List.of(zLevelUpInfo), List.of());
        Assertions.assertEquals(StatusCode.INVALID_GROWTH, status);

        for (var resStone : resStones) {
            status = player.categoryFilter.add(ZCategory.Type.GROWTH, resStone.id, 1);
            Assertions.assertEquals(StatusCode.SUCCESS, status);
        }

        status = player.collection.levelUp(List.of(zLevelUpInfo), List.of());
        Assertions.assertEquals(StatusCode.NOT_ENOUGH_COLLECTION_LEVEL_UP, status);

        for (var resStone : resStones) {
            var growth = player.growth.getGrowth(resStone.id);
            Assertions.assertNotNull(growth);
            ++growth.model.level;
        }


        var out = new ArrayList<TCollection>();
        status = player.collection.levelUp(List.of(zLevelUpInfo), out);
        Assertions.assertEquals(StatusCode.SUCCESS, status);

        var collection = player.collection.getOrDefault(ZResource.Type.STONE, resCollection.id);
        Assertions.assertEquals(resCollection.id, collection.collection_id);
        Assertions.assertEquals(ZResource.Type.STONE.getNumber(), collection.type);
        Assertions.assertEquals(collection.level, 1);
    }
}
