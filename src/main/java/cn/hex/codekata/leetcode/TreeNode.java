package cn.hex.codekata.leetcode;

import java.util.*;

/**
 * Created by hex.
 */
// Definition for binary tree
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int x) {
        val = x;
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


    // level order traversal
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

    // check if two trees are same
    public static boolean isSameTree(TreeNode r1, TreeNode r2) {
        if (r1 == null && r2 == null) return true;

        //noinspection SimplifiableIfStatement
        if (r1 == null || r2 == null) return false;

        return r1.val == r2.val && isSameTree(r1.left, r2.left) && isSameTree(r1.right, r2.right);

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

    /**
     * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
     * <p>
     * Initially, all next pointers are set to NULL.
     * <p>
     * Note:
     * <p>
     * You may only use constant extra space.
     * You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
     *
     * @param root of tree
     */
    public static void connect(TreeNode root) {
        TreeNode leftWall = root;
        while (leftWall != null) {
            TreeNode across = leftWall;
            while (across != null) {
                if (across.left != null) {
                    across.left.next = across.right;
                }
                if (across.right != null && across.next != null) {
                    across.right.next = across.next.left;
                }
                across = across.next;
            }
            leftWall = leftWall.left;
        }
    }

    //Follow up for problem "Populating Next Right Pointers in Each Node".
    //What if the given tree could be any binary tree?
    public static void connect2(TreeNode root) {
        if (root == null) return;
        root.next = null;
        connectSibling(root);
        connectNodesOfSubtrees(root);
    }

    private static void connectSibling(TreeNode node) {
        if (node.left != null) {
            node.left.next = node.right;
            connectSibling(node.left);
        }
        if (node.right != null) {
            node.right.next = null;
            connectSibling(node.right);
        }
    }

    private static void connectNodesOfSubtrees(TreeNode node) {
        if (node == null) return;
        TreeNode from = getNodeWithChild(node);
        while (from != null) {
            TreeNode to = getNodeWithChild(from.next);
            if (to == null) break;
            TreeNode lastChildOfFrom = lastChildOfNode(from);
            lastChildOfFrom.next = firstChildOfNode(to);
            from = to;
        }
        connectNodesOfSubtrees(node.left);
        connectNodesOfSubtrees(node.right);
    }

    private static TreeNode getNodeWithChild(TreeNode node) {
        while (node != null && node.left == null && node.right == null) {
            node = node.next;
        }
        return node;
    }

    private static TreeNode lastChildOfNode(TreeNode node) {
        return node.right == null ? node.left : node.right;
    }

    private static TreeNode firstChildOfNode(TreeNode node) {
        return node.left == null ? node.right : node.left;
    }
}
