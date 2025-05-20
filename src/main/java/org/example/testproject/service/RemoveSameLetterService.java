package org.example.testproject.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
 * characters are identical, remove them from the string. Repeat this process until
 * there is no more than 3 identical characters sitting beside each other.
 */
@Component
public class RemoveSameLetterService {

    public List<String> getStepChars(String str) {
        List<String> list = new ArrayList<String>();
        if (str == null || str.length() == 0) {
            return list;
        }

        while (true) {
            String removedStr = removeSameChar(str);
            if (removedStr.equals(str)) {
                break;
            }
            list.add(removedStr);
            str = removedStr;
        }

        return list;
    }

    private String removeSameChar(String str) {
        if (str.length() < 3) {
            return str;
        }

        int startPos = -1;
        int count = 1;
        for (int pos = 1; pos <= str.length(); pos++) {
            if (pos < str.length() && str.charAt(pos) == str.charAt(pos - 1)) {
                count++;
            } else {
                if (count >= 3) {
                    startPos = pos - count;
                    break;
                }
                count = 1;
            }
        }

        if (startPos != -1) {
            return str.substring(0, startPos) + str.substring(startPos + count);
        }
        return str;
    }
}
