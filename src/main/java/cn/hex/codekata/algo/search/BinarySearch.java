package cn.hex.codekata.algo.search;

/**
 * Created by hex.
 */
public class BinarySearch {
    public static <T extends Comparable<T>> int indexOf(T[] sortedList, T item) {
        return indexOf(sortedList, 0, sortedList.length - 1, item);
    }

    private static <T extends Comparable<T>> int indexOf(T[] sortedList, int low, int high, T item) {
        if (low > high) {
            return -1;
        }

        int index = low + (high - low) / 2;

        if (item.equals(sortedList[index])) {
            return index;
        }

        if (item.compareTo(sortedList[index]) > 0) {
            low = index + 1;
            return indexOf(sortedList, low, high, item);
        }

        high = index - 1;
        return indexOf(sortedList, low, high, item);
    }

}
