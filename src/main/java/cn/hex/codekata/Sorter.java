package cn.hex.codekata;

import java.util.*;

/**
 * Created by hex on 2014/4/15.
 */
public class Sorter {

    public List<String> sort(List<String> words) {
        Map<Character, List<String>> map = new HashMap<Character, List<String>>();
        for (String word : words) {
            char key = word.charAt(0);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<String>());
            }
            map.get(key).add(word);
        }

        for (String word : words) {
            List<String> results = visit(word, map, new ArrayList<String>(), words.size());
            if (results != null) {
                return results;
            }
        }
        return new ArrayList<String>();
    }

    private List<String> visit(String word, Map<Character, List<String>> map, List<String> sorted, int size) {
        sorted.add(word);
        int newSize = size - 1;
        if (newSize == 0) {
            return sorted;
        }

        List<String> next = map.get(word.charAt(word.length() - 1));
        if (next == null || sorted.containsAll(next)) {
            return null;
        }

        for (String s : next) {
            if (!sorted.contains(s)) {
                List<String> success = visit(s, map, new ArrayList<String>(sorted), newSize);
                if (success != null) {
                    return success;
                }
            }
        }
        return null;
    }
}
