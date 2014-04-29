package fizzbuzz;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class StudentTest {

    private FizzBuzz fizzBuzz;

    @Before
    public void before() {
        fizzBuzz = new FizzBuzz(new int[]{3, 5, 7});
    }

    @Test
    public void shoutFizzIfStudentNumberContainsFirstNumber() {
        Student student = new Student(13);

        assertThat(student.shout(fizzBuzz), is("Fizz"));
    }

    @Test
    public void shouldShoutFizzIfStudentNumberCanBeDividedExactlyByFirstNumberOnly() {
        Student student = new Student(6);
        assertThat(student.shout(fizzBuzz), is("Fizz"));

        student = new Student(35);
        assertThat(student.shout(fizzBuzz), is("Fizz"));
    }


    @Test
    public void shouldShoutBuzzIfStudentNumberCanBeDividedExactlyBySecondNumberOnly() {
        Student student = new Student(10);

        assertThat(student.shout(fizzBuzz), is("Buzz"));
    }

    @Test
    public void shouldShoutBuzzIfStudentNumberCanBeDividedExactlyByThirdNumberOnly() {
        Student student = new Student(14);

        assertThat(student.shout(fizzBuzz), is("Whizz"));
    }


    @Test
    public void shouldShoutFizzBuzzIfStudentNumberCanBeDividedExactlyByFirstAndSecondNumber() {
        Student student = new Student(15);

        assertThat(student.shout(fizzBuzz), is("FizzBuzz"));
    }

    @Test
    public void shouldShoutBuzzWhizzIfStudentNumberCanBeDividedExactlyBySecondAndThirdNumber() {
        Student student = new Student(70);

        assertThat(student.shout(fizzBuzz), is("BuzzWhizz"));
    }

    @Test
    public void shouldShoutFizzBuzzWhizzIfStudentNumberCanBeDividedExactlyByAllNumbers() {
        Student student = new Student(105);

        assertThat(student.shout(fizzBuzz), is("FizzBuzzWhizz"));
    }

    @Test
    public void testShouldReturnStudentNumberOtherwise() {
        Student student = new Student(17);

        assertThat(student.shout(fizzBuzz), is("17"));
    }
}
