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

    public static String IntToRoman(int n) {
        StringBuilder sb = new StringBuilder();
        RomanNumber[] values = RomanNumber.values();
        for (int i = values.length - 1; i >= 0; i--) {
            RomanNumber rn = values[i];
            int m = n / rn.getValue();
            if (m == 4 && i + 1 < values.length) {
                if (sb.length() > 0 && values[i + 1].name().equals(sb.substring(sb.length() - 1))) {
                    //value 9, 90, ...
                    sb.deleteCharAt(sb.length() - 1);
                    sb.append(rn).append(values[i + 2]);
                } else {
                    //value 4, 40, ...
                    sb.append(rn).append(values[i + 1]);
                }
            } else {
                for (int j = 0; j < m; j++) {
                    sb.append(rn);
                }
            }
            n = n % rn.getValue();
        }
        return sb.toString();
    }
}
