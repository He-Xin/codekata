package cn.hex.codekata.java8;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class ConsumerExampleTest {
    @Test
    public void testConsumer() {
        ConsumerExample ce = new ConsumerExample(Arrays.asList(1, 2, 3));

        assertThat(6, equalTo(ce.getSum()));
        
        assertThat(Arrays.asList("1", "2", "3"), equalTo(ce.getStringList()));
    }

}
