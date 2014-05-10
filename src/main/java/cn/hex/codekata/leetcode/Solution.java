package cn.hex.codekata.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hex.
 */
public class Solution {
    public static String reverseWords(String s) {
        String[] words = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i > 0; i--) {
            sb.append(words[i]).append(" ");
        }
        sb.append(words[0]);
        return sb.toString();
    }

    public static boolean wordBreak(String s, Set<String> dict) {
        return doWordBreak(s, dict, new HashSet<>());
    }

    private static boolean doWordBreak(String s, Set<String> dict, Set<String> cannotBreakTOWords) {
        for (int i = s.length(); i > 0; i--) {
            String subStr = s.substring(0, i);
            if (dict.contains(subStr)) {
                if (i == s.length()) {
                    return true;
                }
                String remaining = s.substring(i);
                if (!cannotBreakTOWords.contains(remaining)) {
                    if (doWordBreak(remaining, dict, cannotBreakTOWords))
                        return true;
                    else
                        cannotBreakTOWords.add(remaining);
                }
            }
        }
        return false;
    }
}
