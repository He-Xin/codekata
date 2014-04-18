package cn.hex.codekata.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

import static org.junit.Assert.assertEquals;

/**
 * Created by hex.
 */
public class ForEachExampleTest {
    @Test
    public void testIterateWithForEach() throws Exception {
        ForEachExample<Integer> example = new ForEachExample<Integer>(Arrays.asList(1, 2, 3));

        final List<Integer> result = new ArrayList<Integer>();

        example.iterateWithForEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) {
                result.add(integer * 2);
            }
        });

        assertEquals(Arrays.asList(2, 4, 6), result);
    }
}
