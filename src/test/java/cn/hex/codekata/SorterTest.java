package cn.hex.codekata;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by hex on 2014/4/15.
 */
public class SorterTest {
    @Test
    public void testSort() throws Exception {
        List<String> words = Arrays.asList("cd", "aa", "ac", "ab", "ba");

        List<String> sorted = new Sorter().sort(words);

        assertEquals(Arrays.asList("aa", "ab", "ba", "ac", "cd"), sorted);

        words = Arrays.asList("cd", "de", "dx", "xy", "yd");

        sorted = new Sorter().sort(words);

        assertEquals(Arrays.asList("cd", "dx", "xy", "yd", "de"), sorted);
    }

    @Test
    public void testSortNoResult() throws Exception {
        List<String> words = Arrays.asList("cd", "ab");

        List<String> sorted = new Sorter().sort(words);

        assertEquals(sorted.size(), 0);
    }

}
