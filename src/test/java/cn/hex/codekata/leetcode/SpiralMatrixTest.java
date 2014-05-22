package cn.hex.codekata.leetcode;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by hex.
 */
public class SpiralMatrixTest {
    @Test
    public void testGenerateMatrix() throws Exception {
        SpiralMatrix spiralMatrix = new SpiralMatrix();
        int[][] matrix = spiralMatrix.generateMatrix(3);

        assertThat(matrix, is(new int[][]{
                {1, 2, 3},
                {8, 9, 4},
                {7, 6, 5}
        }));
    }
}
