package cn.hex.codekata.algo.link;

import org.junit.Test;

import static cn.hex.codekata.algo.link.Link.createLinkList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class QuickSortTest {
    @Test
    public void testSort() throws Exception {
        Link<Integer> link = createLinkList(7, 5, 4, 3);
        Link<Integer> result = QuickSort.sort(link);
        assertThat(result.toString(), is("3->4->5->7"));

        link = createLinkList(10, 9, 8, 7, 6, 5, 4, 3);
        result = QuickSort.sort(link);
        assertThat(result.toString(), is("3->4->5->6->7->8->9->10"));

        link = createLinkList(10, 9, 8, 7, 6, 5, 3, 3);
        result = QuickSort.sort(link);
        assertThat(result.toString(), is("3->3->5->6->7->8->9->10"));

        link = createLinkList(3, 3, 5, 5, 4, 6);
        result = QuickSort.sort(link);
        assertThat(result.toString(), is("3->3->4->5->5->6"));

        link = createLinkList(1, 2, 3, 2, 4, 4);
        result = QuickSort.sort(link);
        assertThat(result.toString(), is("1->2->2->3->4->4"));
    }
}
