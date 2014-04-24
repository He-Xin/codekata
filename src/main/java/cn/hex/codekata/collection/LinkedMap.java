package cn.hex.codekata.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hex.
 */
public class LinkedMap {
    public static void main(String[] args) {
        Map<Character, Integer> map = new LinkedHashMap<>();
        map.put('A', 1);
        map.put('B', 2);

        // order is the same as input order
        map.keySet().forEach(System.out::println);

        // put 'A' again
        map.put('A', 2);

        // the order will not change
        map.keySet().forEach(System.out::println);
    }
}
