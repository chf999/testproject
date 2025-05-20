package org.example.testproject.service;

import org.example.testproject.bean.ReplaceResultBean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
 * characters are identical, replace them with a single character that comes before it alphabetically.
 * Repeat this process until there is no more than 3 identical characters sitting beside each other.
 */
@Component
public class ReplaceSameLetterWithPreService {

    public List<ReplaceResultBean> getStepChars(String str) {
        List<ReplaceResultBean> list = new ArrayList<ReplaceResultBean>();
        if (str == null || str.length() == 0) {
            return list;
        }

        String currentStr = str;
        while (true) {
            ReplaceResultBean bean = replaceSameChar(currentStr);
            if (bean == null) {
                break;
            }
            list.add(bean);
            currentStr = bean.getOutPutStr();
        }

        return list;
    }

    private ReplaceResultBean replaceSameChar(String str) {
        if (str.length() < 3) {
            return null;
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
            return getReplaceResultBean(str, startPos, count);
        }
        return null;
    }

    private static ReplaceResultBean getReplaceResultBean(String str, int startPos, int count) {
        ReplaceResultBean bean = new ReplaceResultBean();
        int endPos = startPos + count;
        //when before character is null, replace by ""
        if (startPos - 1 < 0) {
            bean.setReplaceTargetStr("");
        } else {
            bean.setReplaceTargetStr(String.valueOf(str.charAt(startPos - 1)));
        }
        bean.setOutPutStr(str.substring(0, startPos) + bean.getReplaceTargetStr() + str.substring(endPos));
        bean.setReplaceSourceStr(str.substring(startPos, endPos));

        return bean;
    }
}
