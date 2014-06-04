package cn.hex.codekata.leetcode;

/**
 * Created by hex.
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }

    //Rotate list
    /*
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     */
    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null) {
            return null;
        }

        ListNode l1 = head, l2 = head;
        for (int i = 0; i < n; i++) {
            l2 = l2.next;
            if (l2 == null) {
                l2 = head;
            }
        }

        if (l2 == head) return head;

        while (l2.next != null) {
            l1 = l1.next;
            l2 = l2.next;
        }

        //noinspection ConstantConditions
        l2.next = head;
        head = l1.next;
        l1.next = null;

        return head;
    }


    //Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
    public static TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    private static TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == null) return null;
        ListNode n1 = getMiddleOfSortedList(head, tail);
        TreeNode root = new TreeNode(n1.val);
        if (n1.next != tail) {
            root.right = sortedListToBST(n1.next, tail);
        }
        if (n1 != head) {
            root.left = sortedListToBST(head, n1);
        }
        return root;
    }

    private static ListNode getMiddleOfSortedList(ListNode head, ListNode tail) {
        ListNode n1 = head, n2 = head;
        while (n2 != tail) {
            n2 = n2.next;
            if (n2 != tail) {
                n2 = n2.next;
                n1 = n1.next;
            }
        }
        return n1;
    }

    //Given a sorted linked list, delete all duplicates such that each element appear only once.
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;
        ListNode current = head;
        while (current.next != null) {
            ListNode next = current.next;
            if (current.val == next.val) {
                current.next = next.next;
                next.next = null;
            } else {
                current = next;
            }
        }

        return head;
    }

    /**
     * Given a linked list, swap every two adjacent nodes and return its head.
     *
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     *
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     * @param head of linked list
     * @return a new linked list
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode current = head , previous = null;
        ListNode newHead = head.next;

        while (current != null) {
            ListNode next = current.next;
            if (next == null)
                break;
            current.next = next.next;
            next.next = current;
            if (previous != null) {
                previous.next = next;
            }
            previous = current;
            current = current.next;
        }

        return newHead;
    }
}
