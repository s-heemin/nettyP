package org.supercat.growstone.GameDatas;

import com.google.common.collect.ImmutableList;
import com.google.common.primitives.Floats;
import com.google.common.primitives.Longs;
import org.jdom2.Content;
import org.jdom2.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.XMLHelper;

import java.util.*;

public class GameDataLoader {
    private static final Logger logger = LoggerFactory.getLogger(GameDataLoader.class);

    private final Map<String, Object> scalarMap = new HashMap<>();
    private final Map<String, int[]> intArrayMap = new HashMap<>();
    private final Map<String, long[]> longArrayMap = new HashMap<>();
    private final Map<String, float[]> floatArrayMap = new HashMap<>();
    private final Map<String, String[]> stringArrayMap = new HashMap<>();
    private final Map<String, Map<String, String>> dictMap = new HashMap<>();
    private final Map<String, Map<String, int[]>> intArrayDictMap = new HashMap<>();
    public static GameDataLoader of() {
        return new GameDataLoader();
    }

    private Element root;

    private GameDataLoader() {
    }

    public boolean load(String absolutePath) {
        return XMLHelper.load(absolutePath, (f, doc) -> {
            logger.info("game data loading - path({})", f.getAbsolutePath());

            root = doc.getRootElement();

            //
            HashMap<String, Object> tempScalarMap = new HashMap<>();
            for (Element e : root.getChildren("Int")) {
                tempScalarMap.put(e.getAttributeValue("Key"), Integer.valueOf(e.getTextTrim()));
            }
            for (Element e : root.getChildren("Float")) {
                tempScalarMap.put(e.getAttributeValue("Key"), Float.valueOf(e.getTextTrim()));
            }
            for (Element e : root.getChildren("String")) {
                tempScalarMap.put(e.getAttributeValue("Key"), e.getTextTrim());
            }
            for (Element e : root.getChildren("Long")) {
                tempScalarMap.put(e.getAttributeValue("Key"), Long.valueOf(e.getTextTrim()));
            }
            for (Element e : root.getChildren("Double")) {
                tempScalarMap.put(e.getAttributeValue("Key"), Double.valueOf(e.getTextTrim()));
            }
            for (Element e : root.getChildren("Bool")){
                tempScalarMap.put(e.getAttributeValue("Key"), Boolean.valueOf(e.getTextTrim()));
            }

            scalarMap.putAll(tempScalarMap);

            //
            Map<String, int[]> tempIntArrayMap = new HashMap<>();
            for (Element e : root.getChildren("IntArray")) {
                String key = e.getAttributeValue("Key");

                int[] array = new int[e.getChildren("Int").size() + e.getChildren("Hex").size()];
                int i = 0;
                for (Element eInt : e.getChildren("Int")) {
                    array[i++] = Integer.parseInt(eInt.getTextTrim());
                }
                for (Element eInt : e.getChildren("Hex")) {
                    array[i++] = Integer.parseInt(eInt.getTextTrim(), 16);
                }

                tempIntArrayMap.put(key, array);
            }
            intArrayMap.putAll(tempIntArrayMap);

            //
            Map<String, long[]> tempLongArrayMap = new HashMap<>();
            for (Element e : root.getChildren("LongArray")) {
                String key = e.getAttributeValue("Key");

                List<Long> items = new ArrayList<>();
                for (Element c : e.getChildren()) {
                    if (c.getCType() == Content.CType.Element) {
                        items.add(Long.parseLong(c.getTextTrim()));
                    }
                }

                tempLongArrayMap.put(key, Longs.toArray(items));
            }
            longArrayMap.putAll(tempLongArrayMap);

            //
            Map<String, float[]> tempFloatArrayMap = new HashMap<>();
            for (Element e : root.getChildren("FloatArray")) {
                String key = e.getAttributeValue("Key");

                List<Float> items = new ArrayList<>();
                for (Element c : e.getChildren()) {
                    if (c.getCType() == Content.CType.Element) {
                        items.add(Float.parseFloat(c.getTextTrim()));
                    }
                }

                tempFloatArrayMap.put(key, Floats.toArray(items));
            }
            floatArrayMap.putAll(tempFloatArrayMap);

            //
            Map<String, String[]> tempStringArrayMap = new HashMap<>();
            for (Element e : root.getChildren("StringArray")) {
                String key = e.getAttributeValue("Key");

                List<String> items = new ArrayList<>();
                for (Element c : e.getChildren()) {
                    if (c.getCType() == Content.CType.Element) {
                        items.add(c.getTextTrim());
                    }
                }

                tempStringArrayMap.put(key, items.toArray(new String[0]));
            }
            stringArrayMap.putAll(tempStringArrayMap);

            Map<String, Map<String, String>> tempDictMap = new HashMap<>();
            for (Element e : root.getChildren("Dict")) {
                String key = e.getAttributeValue("Key");

                Map<String, String> map = new HashMap<>();
                for (Element eItem : e.getChildren("Item")) {
                    map.put(eItem.getAttributeValue("Key"), eItem.getAttributeValue("Value"));
                }

                tempDictMap.put(key, map);
            }
            dictMap.putAll(tempDictMap);



            Map<String, Map<String, int[]>> tempIntArrayDicts = new HashMap<>();
            for (Element e : root.getChildren("IntArrayDict")) {
                String key = e.getAttributeValue("Key");
                Map<String, int[]> tempItemMap = new HashMap<>();
                for (Element c : e.getChildren("ItemArray")) {

                    String itemKey = c.getAttributeValue("Key");
                    var cChildren = c.getChildren("Int");
                    int[] itemList = new int[cChildren.size()];
                    for (int index = 0; index < cChildren.size(); index++) {
                        itemList[index] = Integer.parseInt(cChildren.get(index).getTextTrim());
                    }
                    tempItemMap.put(itemKey, itemList);
                }
                tempIntArrayDicts.put(key, tempItemMap);
            }
            intArrayDictMap.putAll(tempIntArrayDicts);
        });
    }

    public Element getRoot() {
        return root;
    }

    public Double getDouble(String key) {
        Double v = (Double) scalarMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public Long getLong(String key) {
        Long v = (Long) scalarMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public Integer getInt(String key) {
        Integer v = (Integer) scalarMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public Float getFloat(String key) {
        Float v = (Float) scalarMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public String getString(String key) {
        String v = (String) scalarMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public Boolean getBoolean(String key) {
        Boolean v = (Boolean) scalarMap.get(key);

        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public int[] getIntArray(String key) {
        int[] v = intArrayMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public long[] getLongArray(String key) {
        long[] v = longArrayMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public float[] getFloatArray(String key) {
        float[] v = floatArrayMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public String[] getStringArray(String key) {
        String[] v = stringArrayMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public Map<String, String> getDict(String key) {
        Map<String, String> v = dictMap.get(key);

        // 없을 경우, 예외 던저서 아예 켜지지 않게 만든다.
        if (Objects.isNull(v)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return v;
    }

    public HashMap<String, List<List<Integer>>> getMapByStringInteger(String[] strArr, String parseKey, int times) {
        HashMap<String, List<List<Integer>>> map = new HashMap<>();

        // String Array로 된 key 추출
        for (String key : strArr) {
            String[] items = this.getStringArray(key);
            List<List<Integer>> valueLists = new ArrayList<>();
            // 추출한 key의 내용을 추출
            for (String item : items) {
                String[] parseStr = item.split(parseKey);
                List<Integer> values = new ArrayList<>();
                // 추출한 데이터를 Int형으로 저장
                for (String value : parseStr) {
                    if (value.contains(".")) {
                        values.add((int) (Float.parseFloat(value) * (times > 0 ? times : 1)));
                    } else {
                        values.add(Integer.parseInt(value));
                    }
                }
                valueLists.add(values);
            }
            map.put(key, valueLists);
        }
        return map;
    }

    public Map<String, int[]> getIntArrayDict(String key) {
        Map<String, int[]> listMap = intArrayDictMap.get(key);

        if (Objects.isNull(listMap)) {
            throw new RuntimeException("unknown key - " + key);
        }

        return listMap;
    }
}
