package org.supercat.growstone;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;

public class JsonConverter {
    private static final Logger logger = LoggerFactory.getLogger(JsonConverter.class);
    private static final Object lock = new Object();
    private static ObjectMapper mapper;

    // 스트링맵 대용
    public static class StringHashMap extends HashMap<String, Object> {
    }

    private static ObjectMapper getLazyMapper() {
        if (Objects.nonNull(mapper)) {
            return mapper;
        }

        synchronized (lock) {
            if (Objects.nonNull(mapper)) {
                return mapper;
            }

            mapper = JsonMapper.builder()
                    .findAndAddModules()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false) // 작업 특성상 키가 먼저 들어갈 수 있기 때문에, 허용해야 한다
                    .build();
        }

        return mapper;
    }


    public static <T extends Object> Optional<T> optOf(String value, Class<T> modelClass) {
        return Optional.ofNullable(of(value, modelClass));
    }

    public static <T extends Object> T of(String value, Class<T> modelClass) {
        try {
            // fixed : 스트링이 없을 수도 있다
            if (Strings.isNullOrEmpty(value)) {
                return null;
            }

            return getLazyMapper().readValue(value, modelClass);
        } catch (Exception e) {
            SLog.logException(e);
            return null;
        }
    }

    public static <T extends Object> String to(T object) {
        try {
            return getLazyMapper().writeValueAsString(object);
        } catch (Exception e) {
            SLog.logException(e);
            return StringUtils.EMPTY;
        }
    }

    // 단일
    public static <T extends Object> JSONObject toObject(T object) {
        try {
            var jsonString = to(object);
            return new JSONObject(jsonString);
        } catch (Exception e) {
            SLog.logException(e);
            return new JSONObject();
        }
    }

    // 컬렉션
    public static <T extends Object> JSONArray toObject(Collection<T> object) {
        try {
            var jsonString = to(object);
            return new JSONArray(jsonString);
        } catch (Exception e) {
            SLog.logException(e);
            return new JSONArray();
        }
    }

    public static <T extends Object> HashMap<String, Object> toMap(T object) {
        try {
            return getLazyMapper().convertValue(object, StringHashMap.class);
        } catch (Exception e) {
            SLog.logException(e);
            return new HashMap<>();
        }
    }

}
