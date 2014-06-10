package cn.hex.codekata.leetcode;

import java.util.ArrayList;
import java.util.List;

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
     * <p>
     * For example,
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     * <p>
     * Your algorithm should use only constant space. You may not modify the values in the list, only nodes itself can be changed.
     *
     * @param head of linked list
     * @return a new linked list
     */
    public ListNode swapPairs(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return head;

        ListNode current = head, previous = null;
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

    //Merge k sorted lists
    public static ListNode mergeKLists(List<ListNode> lists) {
        if (lists.size() == 0) return null;
        if (lists.size() == 1) return lists.get(0);

        while (lists.size() > 1) {
            List<ListNode> temp = new ArrayList<>();
            int i = 0;
            while (i < lists.size()) {
                ListNode merged = lists.get(i);
                i++;
                if (i < lists.size()) {
                    merged = mergeTwoLists(merged, lists.get(i));
                    i++;
                }
                temp.add(merged);
            }
            lists = temp;
        }
        return lists.get(0);
    }

    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode current = head;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                current = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                current = l2;
                l2 = l2.next;
            }
        }

        if (l1 == null) {
            current.next = l2;
        } else {
            //l2 is null
            current.next = l1;
        }

        return head.next;
    }

    public static ListNode sortList(ListNode head) {
        int length = computeLength(head);
        int step = 1;
        ListNode newHead = new ListNode(0);
        newHead.next = head;
        while (step < length) {
            ListNode start = newHead;
            while (start != null) {
                ListNode i = start.next, j = i;

                for (int k = 0; k < step; k++) {
                    if (j != null) j = j.next;
                }
                start = merge(start, i, j, step);
            }
            step *= 2;
        }
        return newHead.next;
    }

    private static ListNode merge(ListNode head, ListNode i, ListNode j, int k) {
        ListNode ret = null, current = null;
        if (j == null) return null;

        if (i.val > j.val) {
            head.next = j;
        }

        int m = 0, n = 0;
        while (m < k || n < k) {
            if (m == k) {
                current.next = j;
                for (; n < k - 1 && j.next != null; n++) {
                    j = j.next;
                }
                return j;
            }

            if (n == k || j == null) {
                for (; m < k - 1; m++) {
                    i = i.next;
                }
                i.next = ret;
                return i;
            }

            if (i.val <= j.val) {
                current = i;
                i = i.next;
                m++;
                continue;
            }

            if (i.val > j.val) {
                if (current != null) {
                    current.next = j;
                }
                ret = j.next;
                j.next = i;
                current = j;
                j = ret;
                n++;
            }
        }

        return null;
    }

    private static int computeLength(ListNode head) {
        int i = 0;
        while (head != null) {
            i++;
            head = head.next;
        }
        return i;
    }
}
