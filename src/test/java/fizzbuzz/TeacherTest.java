package fizzbuzz;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by hex.
 */
public class TeacherTest {
    @Test
    public void testGameNumber() throws Exception {
        Teacher teacher = new Teacher();
        int[] number = teacher.gameNumber();

        for (int i : number) {
            assertTrue(i >= 1 && i <= 9);
        }
    }

}
