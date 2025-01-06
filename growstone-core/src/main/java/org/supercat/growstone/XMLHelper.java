package org.supercat.growstone;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

public class XMLHelper {
    private static final Logger logger = LoggerFactory.getLogger(XMLHelper.class);

    public static ImmutableList<Element> loadAll(Set<String> absolutePaths, String cname) {
        if (absolutePaths.isEmpty()) {
            return ImmutableList.of();
        }

        var temp = new ConcurrentHashMap<File, Document>();
        var loadResults = absolutePaths.parallelStream()
                .map(absolutePath -> load(absolutePath, temp::put))
                .collect(Collectors.toList());

        boolean allLoaded = loadResults.stream().allMatch(x -> x);
        if (!allLoaded) {
            return ImmutableList.of();
        }

        return temp.values()
                .stream()
                .flatMap(doc -> XMLHelper.safeChildren(doc, cname).stream())
                .collect(ImmutableList.toImmutableList());
    }

    public static ImmutableList<Element> loadAllEmptyAllowed(Set<String> absolutePaths, String cname) {
        if (absolutePaths.isEmpty()) {
            return ImmutableList.of();
        }

        var temp = new ConcurrentHashMap<File, Document>();
        var loadResults = absolutePaths.parallelStream()
                .map(absolutePath -> load(absolutePath, temp::put))
                .collect(Collectors.toList());

        boolean allLoaded = loadResults.stream().allMatch(x -> x);
        if (!allLoaded) {
            return ImmutableList.of();
        }

        return temp.values()
                .stream()
                .flatMap(doc -> XMLHelper.children(doc, cname).stream())
                .collect(ImmutableList.toImmutableList());
    }
    public static Document load(String absolutePath) {
        File f = new File(absolutePath);
        logger.info("loading - file({})", f.getAbsolutePath());

        Document doc;
        try {
            SAXBuilder b = new SAXBuilder();
            doc = b.build(f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return doc;
    }

    public static boolean load(String absolutePath, BiConsumer<File, Document> action) {
        File f = new File(absolutePath);
        logger.info("loading - file({})", f.getAbsolutePath());

        Document doc;
        try {
            SAXBuilder b = new SAXBuilder();
            doc = b.build(f);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        action.accept(f, doc);
        return true;
    }

    public static ArrayList<Element> safeChildren(Document doc) {
        return safeChildren(doc, "");
    }

    public static ArrayList<Element> safeChildren(Document doc, String cname) {
        var elements = Strings.isNullOrEmpty(cname)
                ? doc.getRootElement().getChildren()
                : doc.getRootElement().getChildren(cname);

        var copied = new ArrayList<>(elements);

        if (copied.isEmpty()) {
            throw new RuntimeException("child is empty");
        }

        return copied;
    }

    public static Element safeChild(Document doc, String cname) {
        var root = doc.getRootElement();
        var e = root.getChild(cname);
        return e;
    }

    public static ArrayList<Element> children(Document doc, String cname) {
        var elements = Strings.isNullOrEmpty(cname)
                ? doc.getRootElement().getChildren()
                : doc.getRootElement().getChildren(cname);

        return new ArrayList<>(elements);
    }


    public static long getLongValue(Element e, long _default) {
        String value = e.getTextTrim();
        return (Objects.isNull(value)) ? _default : Long.parseLong(value);
    }

    public static int getIntValue(Element e, int _default) {
        String value = e.getTextTrim();
        return (Objects.isNull(value)) ? _default : Integer.parseInt(value);
    }
    public static String getChildText(Element e, String key, String _default) {
        String value = e.getChildText(key);

        if (Objects.isNull(value)) {
            return _default;
        }

        return value;
    }

    public static int getChildInt(Element e, String key, int _default) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? _default : Integer.parseInt(value);
    }

    public static int getChildYMD(Element e, String key, int _default) {
        String value = e.getChildText(key);
        if (Objects.isNull(value)) {
            return _default;
        }

        try {
            var dtFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
            var dt = dtFormat.parse(value);
        } catch (Exception ex) {
            return _default;
        }

        return Integer.parseInt(value);
    }

    public static <T extends Enum<T>> T getChildEnum(Element e, String key, T _default) {
        try {
            return T.valueOf((Class<T>) _default.getClass(), getChildText(e, key, _default.toString()));
        } catch (IllegalArgumentException ex) {
            return _default;
        }
    }

    public static int getChildHexColor(Element e, String key, int _default) {
        String value = e.getChildText(key);
        if (Objects.isNull(value)) {
            return _default;
        }
        int h = Integer.parseInt(value, 16);
        for (int i = value.length(); i < 8; i++) {
            h |= 0xf << (i * 4);
        }
        return h;
    }

    public static int getChildHex(Element e, String key, int _default) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? _default : Integer.parseUnsignedInt(value, 16);
    }

    public static float getChildFloat(Element e, String key, float _default) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? _default : Float.parseFloat(value);
    }

    public static double getChildDouble(Element e, String key, double _default) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? _default : Double.parseDouble(value);
    }

    public static long getChildLong(Element e, String key, long _default) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? _default : Long.parseLong(value);
    }

    public static boolean getChildBoolean(Element e, String key, boolean _default) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? _default : Boolean.parseBoolean(value);
    }

    public static Boolean getChildBooleanContainsNull(Element e, String key) {
        String value = e.getChildText(key);
        return (Objects.isNull(value)) ? null : Boolean.parseBoolean(value);
    }

    public static String getAttributeText(Element e, String key) {
        return getAttributeText(e, key, "");
    }

    public static String getAttributeText(Element e, String key, String _default) {
        String value = e != null ? e.getAttributeValue(key) : null;
        if (Objects.isNull(value)) {
            return _default;
        }

        return value;
    }

    public static int getAttributeInt(Element e, String key, int _default) {
        String value = e != null ? e.getAttributeValue(key) : null;
        return (Objects.isNull(value)) ? _default : Integer.parseInt(value);
    }

    public static long getAttributeLong(Element e, String key, long _default) {
        String value = e != null ? e.getAttributeValue(key) : null;
        return (Objects.isNull(value)) ? _default : Long.parseLong(value);
    }

    public static float getAttributeFloat(Element e, String key, float _default) {
        String value = e != null ? e.getAttributeValue(key) : null;
        return (Objects.isNull(value)) ? _default : Float.parseFloat(value);
    }

    public static double getAttributeDouble(Element e, String key, double _default) {
        String value = e != null ? e.getAttributeValue(key) : null;
        return (Objects.isNull(value)) ? _default : Double.parseDouble(value);
    }

    public static boolean getAttributeBoolean(Element e, String key, boolean _default) {
        String value = e != null ? e.getAttributeValue(key) : null;
        return (Objects.isNull(value)) ? _default : Boolean.parseBoolean(value);
    }

    public static <T extends Enum<T>> T getAttributeEnum(Element e, String key, T _default) {
        try {
            return T.valueOf((Class<T>) _default.getClass(), getAttributeText(e, key, _default.toString()));
        } catch (IllegalArgumentException ex) {
            return _default;
        }
    }

    public static boolean hasAttribute(Element e, String key) {
        return Objects.nonNull(e.getAttribute(key));
    }

    public static String traceName(Element e) {
        var s = new ArrayList<String>();

        var p = e.getParentElement();
        var pID = XMLHelper.getAttributeLong(p, "ID", -1);
        s.add("ID(" + pID + ")");

        Element next = e;
        do {
            s.add(next.getName());
            var tempNext = next.getParentElement();
            if (Objects.isNull(tempNext)) {
                var doc = (Document) next.getParent();
                s.add(doc.getBaseURI());
                break;
            }

            next = tempNext;
        } while (true);

        Collections.reverse(s);
        return s.toString();
    }
}
