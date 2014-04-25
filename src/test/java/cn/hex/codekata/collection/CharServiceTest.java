package cn.hex.codekata.collection;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class CharServiceTest {
    @Test
    public void testGetUniq() {
        CharService cs = new CharService(new MyCharProvider(Arrays.asList('A','B','A', 'C', 'B', 'C')));

        cs.read();
        assertThat(cs.getFirstUniq(), is('A'));

        cs.read();
        assertThat(cs.getFirstUniq(), is('A'));

        cs.read();
        assertThat(cs.getFirstUniq(), is('B'));

        cs.read();
        assertThat(cs.getFirstUniq(), is('B'));

        cs.read();
        assertThat(cs.getFirstUniq(), is('C'));

        cs.read();
        assertThat(cs.getFirstUniq(), nullValue());

    }

    private static class MyCharProvider implements CharProvider {
        private Iterator<Character> iter;

        public MyCharProvider(List<Character> chars) {
            iter = chars.iterator();
        }

        @Override
        public char getChar() {
            return iter.next();
        }
    }
}
