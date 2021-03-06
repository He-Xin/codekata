package cn.hex.codekata.leetcode;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;

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

    /**
     * Follow up for "Remove Duplicates":
     * What if duplicates are allowed at most twice?
     * <p>
     * For example,
     * Given sorted array A = [1,1,1,2,2,3],
     * <p>
     * Your function should return length = 5, and A is now [1,1,2,2,3].
     */
    public static int removeDuplicates2(int[] A) {
        if (A.length == 0) return 0;
        int count = 0, repeat = 0, current = A[0];
        for (int i = 0; i < A.length; i++) {
            if (A[i] == current) {
                repeat++;
                if (repeat <= 2) {
                    A[count++] = A[i];
                }
            } else {
                current = A[i];
                repeat = 1;
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

    //Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

    /**
     * For example,
     * If n = 4 and k = 2, a solution is:
     * <p>
     * [
     * [2,4],
     * [3,4],
     * [2,3],
     * [1,2],
     * [1,3],
     * [1,4],
     * ]
     */
    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> ki = new ArrayList<>();
        ki.add(new ArrayList<>());
        for (int m = k; m > 0; m--) {
            List<List<Integer>> temp = new ArrayList<>();
            for (List<Integer> previous : ki) {
                int i = previous.size() == 0 ? 1 : previous.get(previous.size() - 1) + 1;
                for (; i <= n - m + 1; i++) {
                    ArrayList<Integer> integers = new ArrayList<>(previous);
                    integers.add(i);
                    temp.add(integers);
                }
            }
            ki = temp;
        }
        return ki;
    }

    /**
     * The count-and-say sequence is the sequence of integers beginning as follows:
     * 1, 11, 21, 1211, 111221, ...
     * <p>
     * 1 is read off as "one 1" or 11.
     * 11 is read off as "two 1s" or 21.
     * 21 is read off as "one 2, then one 1" or 1211.
     * Given an integer n, generate the nth sequence.
     * <p>
     * Note: The sequence of integers will be represented as a string.
     */
    public String countAndSay(int n) {
        String s = "1";
        for (int i = 2; i <= n; i++) {
            int k = 1;
            StringBuilder temp = new StringBuilder();
            char current = s.charAt(0);
            for (int j = 1; j < s.length(); j++) {
                if (s.charAt(j) == current) {
                    k++;
                    continue;
                }
                temp.append(k).append(current);
                current = s.charAt(j);
                k = 1;
            }
            temp.append(k).append(current);
            s = temp.toString();
        }
        return s;
    }

    //Given two sorted integer arrays A and B, merge B into A as one sorted array.
    public static void mergeArray(int A[], int m, int B[], int n) {
        int index = m + n;
        while (m > 0 || n > 0) {
            index--;
            if (m == 0) {
                A[index] = B[n - 1];
                n--;
                continue;
            }
            if (n == 0) {
                A[index] = A[m - 1];
                m--;
                continue;
            }
            if (A[m - 1] <= B[n - 1]) {
                A[index] = B[n - 1];
                n--;
            } else {
                A[index] = A[m - 1];
                m--;
            }
        }
    }

    //Determine whether an integer is a palindrome. Do this without extra space.
    public static boolean isPalindrome(int x) {
        int result = 0, original = x;
        while (x > 0) {
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return original == result;
    }

    //Given two binary strings, return their sum (also a binary string).
    public static String addBinary(String a, String b) {
        char plus = '0';
        StringBuilder result = new StringBuilder();
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            if (i < 0) {
                if (plus == '0') {
                    result.append(b.charAt(j));
                    plus = '0';
                    continue;
                }
                if (b.charAt(j) == '1') {
                    result.append('0');
                    plus = '1';
                    continue;
                }
                result.append(plus);
                plus = '0';
                continue;
            }


            if (j < 0) {
                if (plus == '0') {
                    result.append(a.charAt(i));
                    plus = '0';
                    continue;
                }
                if (a.charAt(i) == '1') {
                    result.append('0');
                    plus = '1';
                    continue;
                }
                result.append(plus);
                plus = '0';
                continue;
            }

            if (a.charAt(i) == '1' && b.charAt(j) == '1') {
                result.append(plus);
                plus = '1';
                continue;
            }

            if (a.charAt(i) == '0' && b.charAt(j) == '0') {
                result.append(plus);
                plus = '0';
                continue;
            }

            if (plus == '1') {
                result.append('0');
                plus = '1';
                continue;
            }

            result.append('1');
            plus = '0';
        }

        if (plus == '1')
            result.append(plus);

        return result.reverse().toString();
    }

    /**
     * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
     * <p>
     * P   A   H   N
     * A P L S I I G
     * Y   I   R
     * And then read line by line: "PAHNAPLSIIGYIR"
     * Write the code that will take a string and make this conversion given a number of rows:
     * <p>
     * string convert(string text, int nRows);
     * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
     */
    public String convert(String s, int nRows) {
        if (nRows == 1) return s;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < nRows; i++) {
            int delta = (nRows - 1 - i) * 2;
            if (delta == 0) {
                delta = (nRows - 1) * 2;
            }
            int index = i;
            while (index < s.length()) {
                sb.append(s.charAt(index));
                index += delta;
                if (i != 0 && i != nRows - 1) {
                    if (index < s.length())
                        sb.append(s.charAt(index));

                    index += i * 2;
                }
            }
        }
        return sb.toString();
    }

    // Distinct Subsequences
    public static int numDistinct(String S, String T) {
        int[][] path = new int[T.length() + 1][S.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            path[0][i] = 1;
        }

        for (int i = 1; i <= T.length(); i++) {
            for (int j = 1; j <= S.length(); j++) {

                path[i][j] = path[i][j - 1] + (T.charAt(i - 1) == S.charAt(j - 1) ? path[i - 1][j - 1] : 0);
            }
        }

        return path[T.length()][S.length()];
    }

    //Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, current = 0, start = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c) || map.get(c) < start) {
                current++;
                map.put(c, i);
            } else {
                int index = map.get(c);
                max = current > max ? current : max;
                current = i - index;
                start = index + 1;
                map.put(c, i);
            }
        }
        return current > max ? current : max;
    }

    //Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
    //The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> matches = new HashMap<>();
        matches.put(')', '(');
        matches.put(']', '[');
        matches.put('}', '{');
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                case '[':
                case '{':
                    stack.push(s.charAt(i));
                    break;

                case ')':
                case ']':
                case '}':
                    if (!stack.empty() && stack.peek() == matches.get(s.charAt(i))) {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;

                default:
                    return false;
            }
        }

        return stack.empty();
    }

    //Longest Common Prefix
    //Write a function to find the longest common prefix string amongst an array of strings.
    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        if (strs.length == 1) return strs[0];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != c) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }

    //Given a sorted array and a target value, return the index if the target is found.
    // If not, return the index where it would be if it were inserted in order.
    public static int searchInsert(int[] A, int target) {
        if (A.length == 0) return 0;
        if (A[A.length - 1] < target) return A.length;

        int low = 0, high = A.length - 1;
        while (low <= high) {
            if (A[low] > target) return low;
            if (A[high] < target) return high + 1;

            int mid = low + (high - low) / 2;

            if (A[mid] == target) return mid;

            if (A[mid] < target) {
                low = mid + 1;

            } else {
                high = mid - 1;
            }
        }

        return -1;
    }

    //You are climbing a stair case. It takes n steps to reach to the top.
    //Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
    //fibonacci sequence
    public static int climbStairs(int n) {
        int n1 = 1, n2 = 2, count = 0;
        if (n == 1) return 1;
        if (n == 2) return 2;
        for (int i = 3; i <= n; i++) {
            count = n1 + n2;
            n1 = n2;
            n2 = count;
        }
        return count;
    }

    // Generate Parentheses
    // Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
    // The idea is to insert "()" at every position of each result string
    // n=1: ()
    // n=2: ()() | (())
    // n=3: ()()() | (())() | ()(()) | (()()) | ((()))
    public static List<String> generateParenthesis(int n) {
        List<String> list = new ArrayList<>();
        if (n == 0) return list;
        if (n == 1) {
            list.add("()");
            return list;
        }

        Set<String> results = new HashSet<>();
        results.add("()");
        for (int k = 2; k <= n; k++) {
            Set<String> set = new HashSet<>();
            for (String s : results) {
                for (int i = 0; i < s.length(); i++) {
                    StringBuilder sb = new StringBuilder(s);
                    sb.insert(i, "()");
                    String r = sb.toString();
                    if (!set.contains(r)) {
                        set.add(r);
                    }
                }
            }
            results = set;
        }
        list.addAll(results);
        return list;
    }

    // Minimum Path Sum
    // Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.
    // Note: You can only move either down or right at any point in time.
    public static int minPathSum(int[][] grid) {
        int m = grid.length;

        if (m == 0) return 0;

        int n = grid[0].length;

        if (n == 0) return 0;

        for (int i = 1; i < n; i++) {
            grid[0][i] = grid[0][i] + grid[0][i - 1];
        }

        for (int i = 1; i < m; i++) {
            grid[i][0] = grid[i][0] + grid[i - 1][0];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                int sum = grid[i - 1][j] < grid[i][j - 1] ? grid[i - 1][j] : grid[i][j - 1];
                grid[i][j] = grid[i][j] + sum;
            }
        }

        return grid[m - 1][n - 1];
    }

    // Permutations
    // Given a collection of numbers that might contain duplicates, return all possible unique permutations.
    public static List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<>();
        if (num.length == 0) return result;
        List<Integer> list = new ArrayList<>();
        list.add(num[0]);
        result.add(list);
        if (num.length == 1) {
            return result;
        }

        for (int i = 1; i < num.length; i++) {
            Set<List<Integer>> temp = new HashSet<>();

            for (List<Integer> perm : result) {
                for (int j = 0; j <= perm.size(); j++) {
                    List<Integer> newPerm = new ArrayList<>();
                    newPerm.addAll(perm);
                    newPerm.add(j, num[i]);
                    temp.add(newPerm);
                }
            }
            result = new ArrayList<>(temp);
        }
        return result;
    }

    //Search in Rotated Sorted Array
    public static int search(int[] A, int target) {
        int low = 0;
        int high = A.length - 1;

        while (low <= high) {
            if (A[low] == target) return low;
            if (A[high] == target) return high;

            int mid = low + (high - low) / 2;
            if (A[mid] == target) return mid;

            if (A[low] < A[mid]) {
                if (target > A[low] && target < A[mid]) {
                    return binarySearch(A, low, mid - 1, target);
                }
                low = mid + 1;
                continue;
            }

            if (target > A[mid] && target < A[high])
                return binarySearch(A, mid + 1, high, target);

            high = mid - 1;
        }

        return -1;
    }

    private static int binarySearch(int[] A, int l, int h, int target) {
        if (l > h) return -1;

        if (A[l] == target) return l;
        if (A[h] == target) return h;

        int m = l + (h - l) / 2;
        if (A[m] == target) return m;
        if (A[m] < target)
            return binarySearch(A, m + 1, h, target);
        return binarySearch(A, l, m - 1, target);
    }

    //Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.
    public static int trap(int[] A) {
        int start = 0;
        int end = A.length - 1;
        int left = 0, high = 0, sum = 0, blocks = 0;
        // left to highest block
        for (int i = start; i <= end; i++) {
            int current = A[i];
            if (current >= high) {
                sum += (high * (i - left) - blocks);
                blocks = 0;
                high = current;
                left = i;
            }
            blocks += A[i];
        }

        high = 0;
        blocks = 0;
        int right = end;
        //right to highest block
        for (int i = end; i >= left; i--) {
            int current = A[i];
            if (current >= high) {
                sum += (high * (right - i) - blocks);
                blocks = 0;
                high = current;
                right = i;
            }
            blocks += A[i];
        }
        return sum;
    }

    //Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if (m == 0) return;
        int n = matrix[0].length;
        if (n == 0) return;


        boolean row0 = false;
        boolean col0 = false;

        for (int[] row : matrix) {
            if (row[0] == 0) {
                col0 = true;
            }
        }

        for (int i = 0; i < n; i++) {
            if (matrix[0][i] == 0) {
                row0 = true;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
            }
        }

        if (col0)
            for (int i = 0; i < m; i++) matrix[i][0] = 0;
        if (row0)
            for (int i = 0; i < n; i++) matrix[0][i] = 0;

    }

    /*
     * Given a set of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
     *
     * The same repeated number may be chosen from C unlimited number of times.
     *
     * Note:
     * All numbers (including target) will be positive integers.
     * Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
     * The solution set must not contain duplicate combinations.
     *
     * For example, given candidate set 2,3,6,7 and target 7,
     * A solution set is:
     * [7]
     * [2, 2, 3]
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();

        List<Integer> cs = new ArrayList<>();
        for (int c : candidates) {
            cs.add(c);
        }

        Collections.sort(cs);

        if (target < cs.get(0)) {
            return result;
        }

        return combinationSum(cs, target, new HashMap<>());
    }

    private List<List<Integer>> combinationSum(List<Integer> cs, int target, Map<Integer, List<List<Integer>>> cache) {
        if (cache.containsKey(target)) {
            return cache.get(target);
        }

        List<List<Integer>> result = new ArrayList<>();
        int t = target;

        for (int c : cs) {
            target = t - c;
            if (target == 0) {
                result.add(asList(c));
                continue;
            }
            if (target < cs.get(0) || target < c) {
                continue;
            }

            List<List<Integer>> r = combinationSum(cs, target, cache);

            for (List<Integer> subPath : r) {
                if (c > subPath.get(0)) continue;
                List<Integer> temp = new ArrayList<>(subPath);
                temp.add(0, c);
                result.add(temp);
            }
        }

        if (result.size() != 0)
            cache.put(t, result);

        return result;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = numberOfRows(matrix);
        if (m == 0) return false;
        int n = numberOfCols(matrix);
        if (n == 0) return false;

        int row = getRowToCheck(matrix, target, 0, m - 1);

        return row != -1 && binarySearch(matrix[row], target);
    }

    private static int numberOfRows(int[][] matrix) {
        return matrix.length;
    }

    private static int numberOfCols(int[][] matrix) {
        return matrix[0].length;
    }

    private static int getRowToCheck(int[][] matrix, int target, int low, int high) {
        if (matrix[low][0] > target) {
            return -1;
        }

        if (matrix[high][0] < target) {
            return high;
        }

        if (low == high) return low;

        int mid = low + (high - low) / 2;
        int midValue = matrix[mid][0];

        if (midValue == target) {
            return mid;
        }

        if (midValue > target) {
            return getRowToCheck(matrix, target, low, mid - 1);
        }

        // mid value < target
        if (matrix[mid + 1][0] > target)
            return mid;

        return getRowToCheck(matrix, target, mid + 1, high);
    }

    private static boolean binarySearch(int[] row, int target) {
        int low = 0, high = row.length - 1;
        while (low <= high) {
            if (row[low] > target) return false;
            if (row[high] < target) return false;

            int mid = low + (high - low) / 2;
            if (row[mid] == target) return true;
            if (row[mid] < target) {
                low = mid + 1;
                continue;
            }
            high = mid - 1;
        }
        return false;
    }

    /**
     * Longest Consecutive Sequence
     * @param num an unsorted array of integers
     * @return he length of the longest consecutive elements sequence.
     */
    public static int longestConsecutive(int[] num) {
        if (num.length == 0) return 0;
        Set<Integer> set = new HashSet<>();
        for (int aNum : num) {
            set.add(aNum);
        }
        int max = 0;
        while (!set.isEmpty()) {
            int i = set.iterator().next();
            int length = 1;
            int temp = i;
            set.remove(i);
            while (set.contains(i + 1)) {
                length++;
                i++;
                set.remove(i);
            }
            i = temp;
            while (set.contains(i - 1)) {
                length++;
                i--;
                set.remove(i);
            }
            if (length > max) max = length;
        }

        return max;
    }

    public static double pow(double x, int n) {
        if (n==0) return 1;
        if (n==1) return x;
        if (n==-1) return 1/x;

        double result = 1;
        boolean neg = n < 0;
        if (neg) {
            n = 0 - n;
        }

        List<Double> ps = new ArrayList<>();
        ps.add(x);

        for (long i=1,j=1; j <n; i++,j*=2) {
            double prev = ps.get((int)i-1);
            ps.add(prev * prev);
        }

        int index = ps.size() -1;
        while (n > 0 && index >=0) {
            int t = 1 << index;
            if (n - t >= 0) {
                n = n - t;
                result *= ps.get(index);
            } else {
                index --;
            }
        }

        return neg ? 1/result : result;
    }

    //Letter Combinations of a Phone Number
    public static List<String> letterCombinations(String digits) {
        Map<Character, List<String>> map = new HashMap<>();
        map.put('2', Arrays.asList("a", "b", "c"));
        map.put('3', Arrays.asList("d", "e", "f"));
        map.put('4', Arrays.asList("g", "h", "i"));
        map.put('5', Arrays.asList("j", "k", "l"));
        map.put('6', Arrays.asList("m", "n", "o"));
        map.put('7', Arrays.asList("p", "q", "r", "s"));
        map.put('8', Arrays.asList("t", "u", "v"));
        map.put('9', Arrays.asList("w", "x", "y", "z"));

        List<String> results = new ArrayList<>();

        if (digits.length()==0) {
            results.add("");
        }

        for (int i=0; i<digits.length(); i++) {
            char digit = digits.charAt(i);
            List<String> chars = map.get(digit);
            if (results.size() == 0) {
                results = chars;
                continue;
            }
            List<String> temp = new ArrayList<>();
            for (String r : results) {
                for (String c: chars) {
                    temp.add(r+c);
                }
            }
            results = temp;
        }

        return results;
    }

    /**
     * Gas Station
     *
     * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
     *
     * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at one of the gas stations.
     *
     * Return the starting gas station's index if you can travel around the circuit once, otherwise return -1.
     *
     * Note:
     * The solution is guaranteed to be unique.
     */
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int start = 0, end = -1, length=gas.length, current=start, nextTry = nextIndexInCircle(start, length), tank=gas[0];

        while(end != start ) {
            if (!canGoToNextStation(tank, cost[current])) {
                nextTry = getNextStationToStartWith(gas, cost, nextTry);

                if (nextTry == 0) {
                    return -1;
                }

                start = nextTry;
                nextTry = nextIndexInCircle(start, length);
                current = start;
                end = -1;
                tank = gas[start];
            } else {
                tank = tank - cost[current];
                current = nextIndexInCircle(current, length);
                end = current;
                tank += gas[current];
            }
        }

        return start;
    }

    private static int getNextStationToStartWith(int[] gas, int[] cost, int next) {
        int circleLength = gas.length;
        while(gas[next] - cost[next] <=0 && next != 0) {
            next = nextIndexInCircle(next, circleLength);
        }
        return next;
    }

    private static int nextIndexInCircle(int current, int length) {
        return (current + 1) % length;
    }

    private static boolean canGoToNextStation(int gas, int cost) {
        return gas - cost >= 0;
    }

    //Interleaving String
    /*
     * Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
     *
     * For example,
     * Given:
     * s1 = "aabcc",
     * s2 = "dbbca",
     *
     * When s3 = "aadbbcbcac", return true.
     * When s3 = "aadbbbaccc", return false
     *
     * The idea to solve this problem is to keep state of s1[i], s2[j], s3[i+j]
     * .if s3[i+j} is interleaving of s1 and s2, then check s3[i+j+1]
     */
    public static boolean isInterleave(String s1, String s2, String s3) {
        if (s1.length() + s2.length() != s3.length()) return false;
        int m = s1.length();
        int n = s2.length();

        boolean[][] state = new boolean[n+1][m+1];
        state[0][0] = true;

        for (int i=0; i<m; i++) {
            state[0][i+1] = state[0][i] && s1.charAt(i) == s3.charAt(i);
        }

        for (int i=0; i<n; i++) {
            state[i+1][0] = state[i][0] && s2.charAt(i) == s3.charAt(i);
        }

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                int x = i+1, y=j+1;
                state[x][y] = state[x][y-1] && s1.charAt(j) == s3.charAt(x+y-1) || state[x-1][y] && s2.charAt(i) == s3.charAt(x+y-1);
            }
        }
        return state[n][m];
    }
}
