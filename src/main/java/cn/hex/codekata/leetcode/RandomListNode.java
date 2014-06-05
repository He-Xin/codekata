package cn.hex.codekata.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hex.
 */
public class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) return null;
        Map<RandomListNode, List<RandomListNode>> map = new HashMap<>();
        RandomListNode newHead = null, clone = null, temp = head;
        while (head != null) {
            RandomListNode node = new RandomListNode(head.label);
            if (newHead == null) {
                newHead = node;
                clone = node;
            } else {
                clone.next = node;
                clone = node;
            }

            addRandomLinkToMap(head, map, node);
            head = head.next;
        }

        clone = newHead;
        head = temp;

        while (head != null) {
            if (map.containsKey(head)) {
                List<RandomListNode> list = map.get(head);
                for (RandomListNode n : list) {
                    n.random = clone;
                }
            }
            head = head.next;
            clone = clone.next;
        }

        return newHead;
    }

    private static void addRandomLinkToMap(RandomListNode head, Map<RandomListNode, List<RandomListNode>> map, RandomListNode node) {
        if (head.random != null) {
            if (!map.containsKey(head.random)) {
                map.put(head.random, new ArrayList<>());
            }
            List<RandomListNode> list = map.get(head.random);
            list.add(node);
        }
    }
}
