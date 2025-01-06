package org.supercat.growstone.rules;

import org.supercat.growstone.Constants;
import org.supercat.growstone.ResourceManager;
import org.supercat.growstone.StatusCode;
import org.supercat.growstone.setups.SDB;

public class NameRules {
    public static int reviewName(String name) {
        if(name.contains(Constants.TEMP_PLAYER_NAME)) {
            return StatusCode.INVALID_CHANGE_NAME_LENGTH;
        }

        for (char c : name.toCharArray()) {
            if (Character.isWhitespace(c)) {
                return StatusCode.INVALID_CHANGE_NAME_LENGTH;
            }
        }

        if (ResourceManager.INSTANCE.name.isInvalidWord(name)) {
            return StatusCode.INVALID_CHANGE_NAME_LENGTH;
        }

        int count = SDB.dbContext.bannedName.get(name);
        if(count > 0) {
            return StatusCode.NAME_CONTAINS_BAD_CHARACTER;
        }

        int nameLength = calculateNicknameLength(name);
        if(nameLength > Constants.NAME_MAX_LENGTH) {
            return StatusCode.INVALID_CHANGE_NAME_LENGTH;
        }

        return StatusCode.SUCCESS;
    }

    private static int calculateNicknameLength(String nickname) {
        int length = 0;

        for (char c : nickname.toCharArray()) {
            if (isAsianCharacter(c)) {
                length += 2; // 한글, 중국어, 일본어는 2글자로 계산
            } else {
                length += 1; // 영어는 1글자로 계산
            }
        }

        return length;
    }

    private static boolean isAsianCharacter(char c) {
        return (c >= '\u4E00' && c <= '\u9FFF') ||  // 중국어 한자 범위
                (c >= '\u3040' && c <= '\u309F') ||  // 일본어 히라가나 범위
                (c >= '\u30A0' && c <= '\u30FF') ||  // 일본어 가타카나 범위
                (c >= '\uAC00' && c <= '\uD7AF');    // 한글 범위
    }

    private static boolean isKorean(String name) {
        for (int i = 0; i < name.length(); i++) {
            char c = name.charAt(i);
            // 한글 유니코드 범위: 0xAC00 ~ 0xD7A3
            if (c >= 0xAC00 && c <= 0xD7A3) {
                return true;
            }
        }

        return false;
    }
}

