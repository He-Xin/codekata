package cn.hex.codekata.leetcode;

/**
 * Created by hex.
 */
public class SpiralMatrix {
    public int[][] generateMatrix(int n) {
        Dir d = Dir.E;
        int[][] result = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
            }
        }
        int i = 0;
        int j = 0;
        for (int k = 1; k <= n * n; k++) {
            result[i][j] = k;
            int nexti = nextI(d, i);
            int nextj = nextJ(d, j);
            if (nexti < 0 || nextj < 0 || nexti >= n || nextj >= n || result[nexti][nextj] != 0) {
                d = d.getRight();
            }
            i = nextI(d, i);
            j = nextJ(d, j);
        }

        return result;
    }

    private int nextJ(Dir d, int j) {
        return j + d.getJ();
    }

    private int nextI(Dir d, int i) {
        return i + d.getI();
    }


    private static enum Dir {
        E(0, 1), S(1, 0), W(0, -1), N(-1, 0);

        private int i;
        private int j;

        Dir(int i, int j) {
            this.i = i;
            this.j = j;
        }

        Dir getRight() {
            if (this == E) {
                return S;
            }
            if (this == S) {
                return W;
            }
            if (this == W) {
                return N;
            }
            if (this == N) {
                return E;
            }
            throw new RuntimeException("should not reach here");
        }

        int getI() {
            return i;
        }

        int getJ() {
            return j;
        }
    }

}
