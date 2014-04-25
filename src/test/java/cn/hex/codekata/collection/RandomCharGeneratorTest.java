package cn.hex.codekata.collection;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by hex.
 */
public class RandomCharGeneratorTest {
    @Test
    public void testGetChar() throws Exception {
        CharProvider charProvider = new RandomCharGenerator();
        char c = charProvider.getChar();

        assertThat(c, greaterThanOrEqualTo('A'));
        assertThat(c, lessThanOrEqualTo('Z'));
    }
}
