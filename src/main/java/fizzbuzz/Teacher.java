package fizzbuzz;


import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hex.
 */
public class Teacher {
    public int[] gameNumber() {
        int[] results = new int[3];

        for (int i = 0; i < 3; i++) {
            results[i] = ThreadLocalRandom.current().nextInt(1, 10);
        }

        return results;
    }
}
