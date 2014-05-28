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
}