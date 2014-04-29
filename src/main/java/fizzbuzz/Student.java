package fizzbuzz;

/**
 * Created by hex.
 */
public class Student {
    public static final String[] MESSAGES = new String[]{"Fizz", "Buzz", "Whizz"};

    private int studentNum;

    public Student(int studentNum) {
        this.studentNum = studentNum;
    }

    public String shout(FizzBuzz fizzBuzz) {
        int[] numFromTeacher = fizzBuzz.getNumber();
        int firstNumber = numFromTeacher[0];

        if (String.valueOf(studentNum).contains(String.valueOf(firstNumber))) {
            return MESSAGES[0];
        }

        StringBuilder answer = new StringBuilder();
        for (int i = 0; i < numFromTeacher.length; i++) {
            if (studentNum % numFromTeacher[i] == 0) {
                answer.append(MESSAGES[i]);
            }
        }

        if (answer.length() > 0) {
            return answer.toString();
        }

        return String.valueOf(studentNum);
    }

}
