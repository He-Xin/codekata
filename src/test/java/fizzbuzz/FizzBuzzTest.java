package fizzbuzz;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class FizzBuzzTest {
    @Test
    public void testNumberIsSetForGame() {
        int[] inputs = {3, 4, 5};
        FizzBuzz fizzBuzz = new FizzBuzz(inputs);

        int[] numbers = fizzBuzz.getNumber();

        assertThat(numbers, equalTo(inputs));
    }
}
