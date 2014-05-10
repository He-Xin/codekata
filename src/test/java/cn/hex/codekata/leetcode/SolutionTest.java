package cn.hex.codekata.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by hex.
 */
public class SolutionTest {
    @Test
    public void testReverseWords(){
        String result = Solution.reverseWords("the sky is blue");
        assertThat(result, is("blue is sky the"));

        result = Solution.reverseWords("the");
        assertThat(result, is("the"));

        result = Solution.reverseWords("   a   b ");
        assertThat(result, is("b a"));
    }

    @Test
    public void testWordBreak() {
        Set<String> dict = new HashSet<String>();
        dict.add("leet");
        dict.add("code");

        assertFalse(Solution.wordBreak("abc", dict));
        assertTrue(Solution.wordBreak("leetcode", dict));
        assertTrue(Solution.wordBreak("leet", dict));
    }
}
