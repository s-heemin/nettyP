package org.supercat.growstone;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public final class ResourceCollectors {
    public static <K, V> boolean isNullOrEmpty(Map<K, V> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <V> boolean isNullOrEmpty(List<V> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <V> boolean isNullOrEmpty(V[] collection) {
        return Objects.isNull(collection) || 0 == collection.length;
    }

    public static <V> boolean isNullOrEmpty(Set<V> collection) {
        return Objects.isNull(collection) || collection.isEmpty();
    }

    public static <V extends ResourceBase> boolean tryPut(ConcurrentHashMap<Long, V> map, V res) {
        return tryPut(map, res, null);
    }

    public static <V extends ResourceBase> boolean tryPut(ConcurrentHashMap<Long, V> map, V res, ConcurrentHashMap<Integer, V> excludeMap) {
        // fixed : 리소스 아이디가 0 미만일 경우, 에러로 간주한다.
        if (0 > res.id) {
            throw new RuntimeException(String.format("Resource collapse - type(%s) id(%d)", res.getClass(), res.id));
        }

        // 이미 존재할 경우, 에러로 간주 한다
        if (map.containsKey(res.id)) {
            throw new RuntimeException(String.format("Resource collapse - type(%s) id(%d)", res.getClass(), res.id));
        }

        map.put(res.id, res);
        return true;
    }

}
