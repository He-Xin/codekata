package cn.hex.codekata.algo.link;

import org.junit.Test;

import static cn.hex.codekata.algo.link.Link.createLinkList;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class MergeSortTest {
    @Test
    public void testDoMergeSort() throws Exception {
        Link<Integer> link = createLinkList(1, 3, 2);

        Link<Integer> end = MergeSort.doMergeSort(link, link.getNext(), 1);

        assertNull(end.getNext());

        assertThat(end.getValue(), is(3));
        assertThat(link.getNext().getValue(), is(2));
        assertThat(link.toString(), is("1->2->3"));

        link = createLinkList(0, 1, 2, 3, 4);

        MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4"));

        link = createLinkList(0, 1, 3, 2, 4);

        MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4"));

        link = createLinkList(0, 1, 4, 2, 3);

        MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4"));

        link = createLinkList(0, 2, 3, 1, 4);

        MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4"));


        link = createLinkList(0, 2, 4, 1, 3);

        MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4"));


        link = createLinkList(0, 3, 4, 1, 2);

        MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4"));

        link = createLinkList(0, 3, 4, 1, 2, 7);

        end = MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->2->3->4->7"));
        assertThat(end.getValue(), is(4));

        link = createLinkList(0, 3, 4, 1);

        end = MergeSort.doMergeSort(link, link.getNext(), 2);
        assertThat(link.toString(), is("0->1->3->4"));
        assertThat(end.getValue(), is(4));


        link = createLinkList(0, 1, 2, 3, 4);

        end = MergeSort.doMergeSort(link, link.getNext(), 8);
        assertThat(link.toString(), is("0->1->2->3->4"));
        assertThat(end.getValue(), is(4));
    }

    @Test
    public void testMergeByStep() {
        Link<Integer> link = createLinkList(1, 7, 5, 4, 3);

        MergeSort.sortByStep(link, 1);

        assertThat(link.toString(), is("1->5->7->3->4"));

        MergeSort.sortByStep(link, 2);

        assertThat(link.toString(), is("1->3->4->5->7"));
    }

    @Test
    public void testMergeSort() {
        Link<Integer> link = createLinkList(7, 5, 4, 3);
        Link<Integer> result = MergeSort.sort(link);
        assertThat(result.toString(), is("3->4->5->7"));

        link = createLinkList(10, 9, 8, 7, 6, 5, 4, 3);
        result = MergeSort.sort(link);
        assertThat(result.toString(), is("3->4->5->6->7->8->9->10"));

        link = createLinkList(10, 9, 8, 7, 6, 5, 3, 3);
        result = MergeSort.sort(link);
        assertThat(result.toString(), is("3->3->5->6->7->8->9->10"));

        link = createLinkList(3, 3, 5, 5, 4, 6);
        result = MergeSort.sort(link);
        assertThat(result.toString(), is("3->3->4->5->5->6"));

        link = createLinkList(1, 2, 3, 2, 4, 4);
        result = MergeSort.sort(link);
        assertThat(result.toString(), is("1->2->2->3->4->4"));
    }

}
