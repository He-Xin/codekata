package cn.hex.codekata.leetcode;

/**
 * Created by hex.
 */
public enum RomanNumber {
    I(1), V(5), X(10), L(50), C(100), D(500), M(1000);
    private int value;

    RomanNumber(int val) {
        this.value = val;
    }

    private int getValue() {
        return value;
    }

    public static int romanToInt(String s) {
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            RomanNumber roman = RomanNumber.valueOf(String.valueOf(s.charAt(i)));
            int value = roman.getValue();
            if (i + 1 < s.length()) {
                if (roman.compareTo(RomanNumber.valueOf(String.valueOf(s.charAt(i + 1)))) < 0) {
                    value = 0 - value;
                }
            }
            result += value;
        }
        return result;
    }
}
