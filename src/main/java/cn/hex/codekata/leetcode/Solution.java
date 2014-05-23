package cn.hex.codekata.leetcode;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hex.
 */
@SuppressWarnings("UnusedDeclaration")
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

    public static List<String> wordBreak2(String s, Set<String> dict) {
        List<String> words = doWordBreak2(s, dict, new HashSet<>());
        if (words == null) return new ArrayList<>();
        return words;
    }

    private static List<String> doWordBreak2(String s, Set<String> dict, Set<String> cannotBreakTOWords) {
        List<String> words = new ArrayList<>();
        for (int i = 1; i <= s.length(); i++) {
            String subStr = s.substring(0, i);
            if (dict.contains(subStr)) {
                if (i == s.length()) {
                    words.add(subStr);
                }
                String remaining = s.substring(i);
                if (!cannotBreakTOWords.contains(remaining)) {
                    List<String> remainingWords = doWordBreak2(remaining, dict, cannotBreakTOWords);
                    if (remainingWords != null) {
                        words.addAll(remainingWords.stream().map(remainingWord -> subStr + " " + remainingWord).collect(Collectors.toList()));
                    } else
                        cannotBreakTOWords.add(remaining);
                }
            }
        }
        return words.size() > 0 ? words : null;
    }

    public static int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int high = prices[prices.length - 1], low = high, max = 0;
        for (int i = prices.length - 2; i >= 0; i--) {
            if (prices[i] < low) {
                low = prices[i];
                int profit = high - low;
                max = max < profit ? profit : max;
            }
            if (prices[i] > high) {
                high = prices[i];
                low = high;
            }
        }
        return max;
    }

    //Given an array of integers, every element appears twice except for one. Find that single one.
    public static int singleNumber(int A[]) {
        int result = A[0];
        for (int i = 1; i < A.length; i++) {
            result = result ^ A[i];
        }
        return result;
    }

    /**
     * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
     * For example, this binary tree is symmetric:
     * 1
     * / \
     * 2   2
     * / \ / \
     * 3  4 4  3
     * <p>
     * But the following is not:
     * 1
     * / \
     * 2   2
     * \   \
     * 3   3
     *
     * @param root root of tree
     * @return true if tree is symmetric
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        TreeNode left = root.left;
        TreeNode right = root.right;

        mirror(left);

        return isSameTree(left, right);
    }

    private static void mirror(TreeNode node) {
        if (node == null) return;
        TreeNode temp = node.left;
        node.left = node.right;
        node.right = temp;
        mirror(node.left);
        mirror(node.right);
    }

    private static boolean isSameTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) return true;

        //noinspection SimplifiableIfStatement
        if (r1 == null || r2 == null) return false;

        return r1.val == r2.val && isSameTree(r1.left, r2.left) && isSameTree(r1.right, r2.right);

    }

    //Binary Tree Postorder Traversal
    public static List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        Stack<TreeNode> stack = new Stack<>();
        Set<TreeNode> visited = new HashSet<>();
        stack.push(root);
        while (!stack.empty()) {
            TreeNode node = stack.peek();
            if (node.right != null && !visited.contains(node.right)) {
                stack.push(node.right);
            }
            if (node.left != null && !visited.contains(node.left)) {
                stack.push(node.left);
            }
            if ((node.left == null || visited.contains(node.left)) && (node.right == null || visited.contains(node.right))) {
                results.add(node.val);
                visited.add(node);
                stack.pop();
            }
        }
        return results;
    }

    //Binary Tree Preorder Traversal
    public static List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            results.add(node.val);
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }

        return results;
    }

    //Binary Tree inorder Traversal
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> results = new ArrayList<>();
        if (root == null) return results;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        stack.push(root);
        while (!stack.empty()) {
            while (node != null && node.left != null) {
                node = node.left;
                stack.push(node);
            }
            node = stack.pop();
            results.add(node.val);
            node = node.right;
            if (node != null) {
                stack.push(node);
            }
        }
        return results;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        List<TreeNode> nodesOfSameLevel = Arrays.asList(root);

        while (nodesOfSameLevel.size() > 0) {
            List<TreeNode> nextLevel = new ArrayList<>();
            List<Integer> levelValues = new ArrayList<>();
            for (TreeNode node : nodesOfSameLevel) {
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
                levelValues.add(node.val);
            }
            results.add(levelValues);
            nodesOfSameLevel = nextLevel;
        }
        return results;
    }

    //Given a binary tree, find the maximum path sum.The path may start and end at any node in the tree.
    public static int maxPathSum(TreeNode root) {
        List<Integer> pathSums = new ArrayList<>();
        if (root == null) return 0;
        maxPathSumFromNode(root, pathSums);
        return Collections.max(pathSums);
    }

    private static int maxPathSumFromNode(TreeNode node, List<Integer> pathSums) {
        if (node == null) return 0;
        int leftMaxSum = maxPathSumFromNode(node.left, pathSums);
        int rightMaxSum = maxPathSumFromNode(node.right, pathSums);
        leftMaxSum = leftMaxSum > 0 ? leftMaxSum : 0;
        rightMaxSum = rightMaxSum > 0 ? rightMaxSum : 0;
        pathSums.add(leftMaxSum + node.val + rightMaxSum);
        return leftMaxSum > rightMaxSum ? node.val + leftMaxSum : node.val + rightMaxSum;
    }

    //Minimum Depth of Binary Tree
    //The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        if (leftDepth == 0) return rightDepth + 1;
        if (rightDepth == 0) return leftDepth + 1;
        return leftDepth < rightDepth ? leftDepth + 1 : rightDepth + 1;
    }

    //Divide two integers without using multiplication, division and mod operator.
    public static int divide(int dividend, int divisor) {
        int result = 0;
        boolean sign = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;
        // use long to avoid overflow. Math.abs(Integer.MIN_VALUE) will cause overflow
        List<Long> values = new ArrayList<>();
        long ldividend = Math.abs((long) dividend);
        long temp = Math.abs((long) divisor);

        while (temp <= ldividend) {
            values.add(temp);
            temp = temp << 1;
        }

        for (int i = values.size() - 1; i >= 0; i--) {
            temp = values.get(i);
            if (ldividend == temp) {
                result += 1 << i;
                break;
            }
            if (ldividend > temp) {
                result += 1 << i;
                ldividend -= temp;
            }
        }
        return sign ? result : 0 - result;
    }

    //Valid Palindrome
    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        if (s.isEmpty()) return true;
        s = s.replaceAll("\\W", "").toLowerCase();
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i))
                return false;
        }
        return true;
    }

    //Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
    public static List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) return results;
        traverse(root, sum, new ArrayList<>(), results);
        return results;
    }

    private static void traverse(TreeNode node, int sum, List<Integer> list, List<List<Integer>> results) {
        list.add(node.val);
        if (node.left != null) traverse(node.left, sum, list, results);
        if (node.right != null) traverse(node.right, sum, list, results);
        if (node.left == null && node.right == null) {
            int total = list.stream().mapToInt(i -> i).sum();
            if (sum == total) {
                results.add(new ArrayList<>(list));
            }
        }
        list.remove(list.size() - 1);
    }

    //Rotate list
    /*
     * Given a list, rotate the list to the right by k places, where k is non-negative.
     * For example:
     * Given 1->2->3->4->5->NULL and k = 2,
     * return 4->5->1->2->3->NULL.
     */
    public ListNode rotateRight(ListNode head, int n) {
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

        l2.next = head;
        head = l1.next;
        l1.next = null;

        return head;
    }

    //Longest Valid Parentheses
    //Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.
    public static int longestValidParentheses(String s) {
        List<State> states = new ArrayList<>();
        int max = 0;
        states.add(new State(max, false));
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                states.add(new State(max, false));
                continue;
            }

            int prev = i - 1, l = 0;
            State state = states.get(i - 1);
            if (state.endOfValidSeq) {
                prev = prev - state.length;
                l = state.length;
            }
            if (isValidPair(s, prev, i)) {
                l = l + 2;
                if (prev > 0 && states.get(prev - 1).endOfValidSeq) {
                    l += states.get(prev - 1).length;
                }
                states.add(new State(l, true));
                if (l > max) {
                    max = l;
                }
            } else {
                states.add(new State(max, false));
            }
        }
        return max;
    }

    private static boolean isValidPair(String s, int i, int j) {
        return i >= 0 && s.charAt(i) == '(' && s.charAt(j) == ')';
    }

    private static class State {
        int length = 0;
        boolean endOfValidSeq = false;

        private State(int length, boolean endOfValidSeq) {
            this.length = length;
            this.endOfValidSeq = endOfValidSeq;
        }
    }

    //Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.
    public static TreeNode sortedListToBST(ListNode head) {
        return sortedListToBST(head, null);
    }

    public static TreeNode sortedListToBST(ListNode head, ListNode tail) {
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

    /**
     * Jump game
     * <p>
     * Given an array of non-negative integers, you are initially positioned at the first index of the array.
     * Each element in the array represents your maximum jump length at that position.
     * Determine if you are able to reach the last index.
     * For example:
     * A = [2,3,1,1,4], return true.
     * A = [3,2,1,0,4], return false
     */
    public static boolean canJump(int[] A) {
        List<Range> ranges = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int high = i + A[i] >= A.length ? A.length - 1 : i + A[i];
            ranges.add(new Range(i, high));
        }

        Range r = new Range(0, 0);
        for (int i = 0; i < ranges.size() - 1; i++) {
            r = merge(r, ranges.get(i));
            if (r.high == A.length - 1)
                return true;
        }
        return r.high == A.length - 1;
    }

    private static Range merge(Range r1, Range r2) {
        //precondition: r1.low < r2.low
        if (r1.high < r2.low || r1.high > r2.high) {
            return new Range(r1.low, r1.high);
        }
        return new Range(r1.low, r2.high);
    }

    //subset. Given a set of distinct integers, S, return all possible subsets.
    //Elements in a subset must be in non-descending order.
    //The solution set must not contain duplicate subsets.
    public static List<List<Integer>> subsets(int[] S) {
        return subsets(S, 0);
    }

    private static List<List<Integer>> subsets(int[] S, int start) {
        List<List<Integer>> results = new ArrayList<>();
        if (S.length == start) {
            results.add(new ArrayList<>());
            return results;
        }
        List<List<Integer>> r = subsets(S, start + 1);
        int val = S[start];
        for (List<Integer> list : r) {
            ArrayList<Integer> newList = new ArrayList<>();
            boolean addedVal = false;
            for (int value : list) {
                if (value > val && !addedVal) {
                    newList.add(val);
                    addedVal = true;
                }
                newList.add(value);
            }
            if (!addedVal) newList.add(val);
            results.add(newList);
        }
        results.addAll(r);
        return results;
    }

    //Maximum Subarray
    public static int maxSubArray(int[] A) {
        if (A.length == 0) return 0;
        int pre = A[0], sum = A[0];
        for (int i = 1; i < A.length; i++) {
            if (pre < 0) {
                pre = A[i];
                if (A[i] > sum) {
                    sum = A[i];
                }
            } else {
                if (pre + A[i] > 0) {
                    pre = pre + A[i];
                    if (pre > sum)
                        sum = pre;
                } else {
                    pre = A[i];
                }
            }
        }
        return sum;
    }

    //Remove Duplicates from Sorted Array
    public static int removeDuplicates(int[] A) {
        int count = 0;
        int len = A.length;
        for (int i = 0; i < len; i++) {
            if (count == 0 || A[i] != A[count - 1]) {
                A[count++] = A[i];
            }
        }
        return count;
    }

    //Pascal's Triangle
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> results = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            if (i == 0) {
                row.add(i + 1);
                results.add(row);
                continue;
            }

            List<Integer> previousRow = results.get(i - 1);
            for (int k = 0; k <= i; k++) {
                if (k == 0) {
                    row.add(previousRow.get(k));
                    continue;
                }
                if (k == i) {
                    row.add(previousRow.get(k - 1));
                    continue;
                }
                row.add(previousRow.get(k) + previousRow.get(k - 1));
            }
            results.add(row);
        }
        return results;
    }
}
