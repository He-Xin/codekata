package cn.hex.codekata.leetcode;

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
            for (int i=1; i<=s.length();i++) {
                String subStr = s.substring(0, i);
                if (dict.contains(subStr)) {
                    if (i == s.length()) {
                        return true;
                    }
                    if (wordBreak(s.substring(i), dict)) {
                        return true;
                    }
                }
            }
        return false;
    }
}
