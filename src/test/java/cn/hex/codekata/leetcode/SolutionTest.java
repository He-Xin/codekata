package cn.hex.codekata.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.*;

/**
 * Created by hex.
 */
public class SolutionTest {
    @Test
    public void testReverseWords() {
        String result = Solution.reverseWords("the sky is blue");
        assertThat(result, is("blue is sky the"));

        result = Solution.reverseWords("the");
        assertThat(result, is("the"));

        result = Solution.reverseWords("   a   b ");
        assertThat(result, is("b a"));
    }

    @Test
    public void testWordBreak() {
        Set<String> dict = new HashSet<>();
        dict.add("leet");
        dict.add("code");

        assertFalse(Solution.wordBreak("abc", dict));
        assertTrue(Solution.wordBreak("leetcode", dict));
        assertTrue(Solution.wordBreak("leet", dict));
    }

    @Test
    public void testWordBreak2() {
        Set<String> dict = new HashSet<>();
        dict.add("cat");
        dict.add("cats");
        dict.add("and");
        dict.add("sand");
        dict.add("dog");

        List<String> catsanddog = Solution.wordBreak2("catsanddog", dict);
        assertThat(catsanddog, containsInAnyOrder("cats and dog", "cat sand dog"));
    }


    @Test
    public void testMaxProfit() {
        int maxProfit = Solution.maxProfit(new int[]{2, 1});
        assertThat(maxProfit, is(0));

        maxProfit = Solution.maxProfit(new int[]{1, 2, 3});
        assertThat(maxProfit, is(2));

        maxProfit = Solution.maxProfit(new int[]{1, 4, 2, 3});
        assertThat(maxProfit, is(3));
    }

    @Test
    public void testSingleNumber() {
        assertThat(Solution.singleNumber(new int[]{1, 2, 1, 2, 3}), is(3));
        assertThat(Solution.singleNumber(new int[]{3}), is(3));
        assertThat(Solution.singleNumber(new int[]{1, 2, 1, 2, 3, 4, 5, 4, 5}), is(3));

    }
}
