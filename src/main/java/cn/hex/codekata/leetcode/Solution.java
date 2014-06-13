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
        for (int i=0; i<S.length();i++) {
            path[0][i] = 1;
        }

        for (int i=1; i<=T.length();i++) {
            for (int j=1; j<=S.length(); j++) {

                path[i][j] = path[i][j-1] + ( T.charAt(i-1) == S.charAt(j-1) ? path[i-1][j-1] : 0);
            }
        }

        return path[T.length()][S.length()];
    }

    //Longest Substring Without Repeating Characters
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int max = 0, current = 0, start = 0;
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!map.containsKey(c) || map.get(c) < start) {
                current ++;
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
        Map<Character,Character> matches = new HashMap<>();
        matches.put(')', '(');
        matches.put(']', '[');
        matches.put('}', '{');
        for (int i=0; i<s.length(); i++) {
            switch(s.charAt(i)) {
                case '(': case '[': case '{':
                    stack.push(s.charAt(i));
                    break;

                case ')': case ']': case '}':
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
        StringBuilder sb= new StringBuilder();
        for (int i=0; i<strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j=1; j < strs.length; j++) {
                if (strs[j].length() <= i || strs[j].charAt(i) != c) {
                    return sb.toString();
                }
            }
            sb.append(c);
        }
        return sb.toString();
    }
}
