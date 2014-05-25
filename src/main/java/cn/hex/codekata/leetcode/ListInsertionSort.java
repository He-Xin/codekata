package cn.hex.codekata.leetcode;

/**
 * Created by hex.
 */
public class ListInsertionSort {
    public ListNode sort(ListNode head) {
        if (head == null) return null;

        ListNode newHead = head, current = head.next;
        newHead.next = null;
        while (current != null) {
            ListNode next = current.next;
            current.next = null;
            newHead = insertNode(newHead, current);
            current = next;
        }
        return newHead;
    }

    private ListNode insertNode(ListNode head, ListNode node) {
        if (node.val <= head.val) {
            node.next = head;
            return node;
        }
        ListNode current = head;
        while (current.next != null) {
            ListNode next = current.next;
            if (node.val > current.val && node.val <= next.val) {
                break;
            }
            current = next;
        }
        node.next = current.next;
        current.next = node;
        return head;
    }
}
