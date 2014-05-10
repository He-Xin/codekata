package cn.hex.codekata.leetcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

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
}
