package fizzbuzz;


import com.google.common.primitives.Ints;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hex.
 */
public class Teacher {
    public int[] gameNumber() {
        Set<Integer> set = new HashSet<>();
        while (set.size() < 3) {
            int num = ThreadLocalRandom.current().nextInt(1, 10);
            set.add(num);
        }
        return Ints.toArray(set);
    }
}
