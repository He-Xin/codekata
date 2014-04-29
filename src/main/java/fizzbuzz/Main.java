package fizzbuzz;

import com.google.common.primitives.Ints;

import java.util.stream.Collectors;

/**
 * Created by hex.
 */
public class Main {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        int[] number = teacher.gameNumber();

        System.out.println("Input is: " + getInputString(number));

        FizzBuzz fizzBuzz = new FizzBuzz(number);

        for (int i = 1; i <= 100; i++) {
            Student student = new Student(i);
            System.out.println(student.shout(fizzBuzz));
        }
    }

    private static String getInputString(int[] number) {
        return String.join(",", Ints.asList(number).stream().map(String::valueOf).collect(Collectors.toList()));
    }
}
