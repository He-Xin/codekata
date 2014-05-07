package cn.hex.codekata.algo.search;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class BinarySearchTest {
    @Test
    public void shouldReturnMinus1IfItemNotInArray() {
        Integer[] ints = new Integer[0];

        assertThat(BinarySearch.indexOf(ints, 0), is(-1));

        ints = new Integer[]{1, 2, 4};

        assertThat(BinarySearch.indexOf(ints, 3), is(-1));
    }

    @Test
    public void testShouldReturnIndexOfElementInArray() {
        Integer[] ints = new Integer[] {1,2,3,4,5};

        assertThat(BinarySearch.indexOf(ints, 3), is(2));
        assertThat(BinarySearch.indexOf(ints, 1), is(0));
        assertThat(BinarySearch.indexOf(ints, 5), is(4));

        ints = new Integer[] {1,2,3,4};
        assertThat(BinarySearch.indexOf(ints, 1), is(0));
        assertThat(BinarySearch.indexOf(ints, 2), is(1));
        assertThat(BinarySearch.indexOf(ints, 3), is(2));
        assertThat(BinarySearch.indexOf(ints, 4), is(3));
        assertThat(BinarySearch.indexOf(ints, 0), is(-1));
        assertThat(BinarySearch.indexOf(ints, 5), is(-1));

        ints = new Integer[] {3};
        assertThat(BinarySearch.indexOf(ints, 3), is(0));
        assertThat(BinarySearch.indexOf(ints, 0), is(-1));

    }

    @Test
    public void shouldReturnIndexOfElementInARotatedSortedArray() {
        Integer[] ints = new Integer[] {2,3,4,5,1};

        assertThat(BinarySearch.indexOf2(ints, 3), is(1));
        assertThat(BinarySearch.indexOf2(ints, 1), is(4));
        assertThat(BinarySearch.indexOf(ints, 5), is(3));
        assertThat(BinarySearch.indexOf2(ints, 0), is(-1));
        assertThat(BinarySearch.indexOf2(ints, 6), is(-1));
    }
}
