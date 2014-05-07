package cn.hex.codekata.algo.search;

/**
 * Created by hex.
 */
public class BinarySearch {
    public static <T extends Comparable<T>> int indexOf(T[] sortedList, T item) {
        return indexOf(sortedList, 0, sortedList.length - 1, item);
    }

    // search for element in a rotated sorted array
    public static <T extends Comparable<T>> int indexOf2(T[] rotatedSortedList, T item) {
        return indexOf2(rotatedSortedList, 0, rotatedSortedList.length - 1, item);
    }

    private static <T extends Comparable<T>> int indexOf2(T[] rotatedSortedList, int low, int high, T item) {
        if (low > high) {
            return -1;
        }

        if (rotatedSortedList[low].compareTo( rotatedSortedList[high]) < 0) {
            return indexOf(rotatedSortedList, low, high, item);
        }

        int middle = low + (high - low) / 2;

        if (item.equals(rotatedSortedList[middle]))
            return middle;

        int index = indexOf(rotatedSortedList, low, middle - 1, item);

        return index != -1 ? index : indexOf2(rotatedSortedList, middle + 1, high, item);
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
