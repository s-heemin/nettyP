package org.supercat.growstone;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.supercat.growstone.config.SConfig;

import java.io.File;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourcePath {
    private static final Logger logger = LoggerFactory.getLogger(ResourcePath.class);

    public static final String RELATIVE_PATH = "";
    public static final String RELATIVE_SERVER_DATA_PATH = "ServerData/";
    public static final String FILE_EXTENSIONS = ".xml";

    private static void log(String title, Set<String> files) {
        logger.error("{} =================================================================================", title);
        for (var file : files) {
            logger.error("{}", file);
        }
        logger.error("{} =================================================================================", title);
    }

    // 파일의 절대 경로 추출
    public static Set<String> absoluteBy(String path, ResourceFile resFile) {
        return absoluteAll(path, resFile.files);
    }

    // 파일들의 절대 경로를 찾는다
    public static Set<String> absoluteAll(String path, Set<String> files) {
        // 공용 접두사를 찾고 못 찾았을 경우, 파일 자체로 돌린다.
        final var prefix = commonPrefix(files);
        var prefixFiles = !Strings.isNullOrEmpty(prefix)
                ? searchPrefix(path, prefix)
                : files;

        // 찾은 파일 목록과 지정한 파일목록이 서로 같지 않을 경우, 문제가 있으므로 로그를 찍는다
        if (!prefixFiles.containsAll(files) || !files.containsAll(prefixFiles)) {
            log(prefix + "prefix files : ", prefixFiles);
            log(prefix + "source files : ", files);

            // 디버그 모드가 아닐 때면, 서버는 켜지지 않아야 한다.
            if (!SConfig.getConfig().isDebugMode()) {
                throw new RuntimeException("can not found files - " + files);
            }
        }

        final var realFiles = SConfig.getConfig().isDebugMode()
                ? prefixFiles
                : files;

        return realFiles.stream()
                .map(x -> absolute(path, x))
                .collect(Collectors.toSet());
    }

    public static String absolute(String path, ResourceConfigFile file) {
        return absolute(path, file.file);
    }

    // 요소들 결합하여 절대 경로를 찾는다
    public static String absolute(String path, String file) {
        return Utility.combine(path, file);
    }

    public static String convertToNormal(String file) {
        if (Strings.isNullOrEmpty(file)) {
            return "";
        }

        int index = file.indexOf(".");
        if (0 > index) {
            return file;
        }

        return file.substring(0, index) + FILE_EXTENSIONS;
    }

    // 공용 접두사 찾기
    public static String commonPrefix(Set<String> files) {
        var prefix = files.stream()
                .reduce(Strings::commonPrefix)
                .orElse("");

        // 공용 접두사가 없을 경우
        if (Strings.isNullOrEmpty(prefix)) {
            return "";
        }

        // 끝이 _ 이 아닐 경우 에러
        if ('_' != prefix.charAt(prefix.length() - 1)) {
            return "";
        }

        return prefix.substring(0, prefix.length() - 1);
    }

    // 접두사로 리소스 파일 찾기
    public static ImmutableSet<String> searchPrefix(String rootPath, String prefix) {
        var list = Utility.searchPath(rootPath, "^" + prefix + "_.*xml$");
        if (list.isEmpty()) {
            return ImmutableSet.of();
        }

        return ImmutableSet.copyOf(list.stream()
                .map(path -> path.getFileName().toString())
                .map(ResourcePath::convertToNormal)
                .filter(s -> !Strings.isNullOrEmpty(s))
                .collect(Collectors.toSet())
        );
    }

    // 프로파일과 결합할 수 있으면 결합한다
    public static String combineWithProfile(String file, String profile) {
        // .xml 확장자 시작 인덱스 검색
        int index = file.lastIndexOf(FILE_EXTENSIONS);
        if (index < 0) {
            logger.error("file name doesn't have extension 'xml' - file({})", file);
            return "";
        }

        if (Strings.isNullOrEmpty(profile)) {
            return file;
        }
        return file.substring(0, index) + "." + profile + file.substring(index);
    }

    public static String getServerDataRootPath() {
        return Utility.combine(SConfig.getConfig().resourceDir, RELATIVE_SERVER_DATA_PATH);
    }
}
