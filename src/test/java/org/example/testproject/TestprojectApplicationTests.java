package org.example.testproject;

import org.example.testproject.bean.ReplaceResultBean;
import org.example.testproject.service.RemoveSameLetterService;
import org.example.testproject.service.ReplaceSameLetterWithPreService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
class TestprojectApplicationTests {

    @Autowired
    RemoveSameLetterService removeSameLetterService;

    @Autowired
    ReplaceSameLetterWithPreService replaceSameLetterWithPreService;

    @Test
    void contextLoads() {
    }

    /**
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical, remove them from the string. Repeat this process until
     * there is no more than 3 identical characters sitting beside each other.
     */
    @Test
    void remove3SameLetterTest() {
        remove3SameLetter("aabcccbbad");
        remove3SameLetter(null);
        remove3SameLetter("");
        remove3SameLetter("a");
        remove3SameLetter("abcd");
        remove3SameLetter("aabccccbbad");
        remove3SameLetter("你好的的的的");
        remove3SameLetter("sfddgerf你好的的的的");
    }

    private void remove3SameLetter(String str) {
        List<String> list = removeSameLetterService.getStepChars(str);
        if (list == null || list.size() == 0) {
            System.out.println("No replacement occurred:" + str);
            return;
        }
        String result = list.stream().map(s -> "-> " + s).collect(Collectors.joining("\n"));
        System.out.println(result);
    }

    /**
     * For a given string that only contains alphabet characters a-z, if 3 or more consecutive
     * characters are identical, remove them from the string. Repeat this process until
     * there is no more than 3 identical characters sitting beside each other.
     */
    @Test
    void replace3SameLetterWithPreTest() {
        replace3SameLetterWithPre("aabcccbbad");
        replace3SameLetterWithPre(null);
        replace3SameLetterWithPre("");
        replace3SameLetterWithPre("a");
        replace3SameLetterWithPre("aabccccbbad");
        replace3SameLetterWithPre("你好的的的的");
        replace3SameLetterWithPre("sfddgerf你好的的的的");
    }

    private void replace3SameLetterWithPre(String str) {
        List<ReplaceResultBean> list = replaceSameLetterWithPreService.getStepChars(str);
        if (list == null || list.size() == 0) {
            System.out.println("No replacement occurred:" + str);
            return;
        }
        String result = list.stream().map(s -> {
            return s.getOutPutStr() + ", " + s.getReplaceSourceStr() + " is replaced by " + s.getReplaceTargetStr();
        }).collect(Collectors.joining("\n"));
        System.out.println(result);
    }


}
