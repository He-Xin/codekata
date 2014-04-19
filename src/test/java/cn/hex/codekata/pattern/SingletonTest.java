package cn.hex.codekata.pattern;

import org.junit.Test;

import static org.junit.Assert.assertSame;

/**
 * Created by hex.
 */
public class SingletonTest {
    @Test
    public void canOnlyGetOneInstance() {
        Singleton s = Singleton.getInstance();

        Singleton s2 = Singleton.getInstance();

        assertSame(s, s2);
    }

}
