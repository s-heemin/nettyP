package org.supercat.growstone;


import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.net.http.HttpRequest;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.CRC32;

public class Utility {
    private static final Logger logger = LoggerFactory.getLogger(Utility.class);

    public static final String GUEST_TOKEN_PREFIX = "1";
    public static final String FRIEND_TOKEN_PREFIX = "2";
    public static String hash(String value) {
        try {
            // SHA-256 해시 생성
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(value.getBytes());

            // 해시를 16진수 문자열로 변환
            String hexString = String.format("%064x", new BigInteger(1, hash));

            // 16진수 문자열을 Base36으로 변환
            String base36String = new BigInteger(hexString, 16).toString(36).toUpperCase();

            // 32자리로 조정
            // 친구 코드 길이를 체크하는 곳이 있어서 변경이 되면 같이 수정이 되어야한다.
            String result = String.format("%32s", base36String).replace(' ', '0').substring(0, 32);

            return result;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static String combine(String... paths) {
        File file = new File(paths[0]);

        for (int i = 1; i < paths.length; i++) {
            file = new File(file, paths[i]);
        }

        return file.getPath();
    }

    public static List<Path> searchPath(String rootName, String regex) {
        try {
            Pattern p = Pattern.compile(regex);
            return Files.find(Path.of(rootName), 1, (path, attr) -> p.matcher(path.getFileName().toString()).matches())
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return ImmutableList.of();
        }
    }
}
