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
        TreeNode t = root;
        while (!stack.empty() || t!=null) {
            TreeNode node = t;
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            results.add(node.val);
            t = node.right;
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
}
