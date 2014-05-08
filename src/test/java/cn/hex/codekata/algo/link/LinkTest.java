package cn.hex.codekata.algo.link;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class LinkTest {
    @Test
    public void testReverseList() throws Exception {
        Link<Integer> linkList = Link.createLinkList(1, 2, 3, 4, 5);

        Link<Integer> link = Link.reverseList(linkList);
        assertThat(link.getValue(), is(5));
        assertThat(link.toString(), is("5->4->3->2->1"));

        linkList = Link.createLinkList(1);
        link = Link.reverseList(linkList);
        assertThat(link.toString(), is("1"));
    }
}
