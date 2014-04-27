package cn.hex.codekata.algo.link;

/**
 * Created by hex.
 */
public class MergeSort {
    public static <T extends Comparable<T>>  Link<T> sort(Link<T> l) {
        int step = 1;
        int length = getListSize(l);
        Link<T> start = new Link<>();
        start.setNext(l);
        while (step < length) {
            sortByStep(start, step);
            step = step * 2;
        }

        return start.getNext();
    }

    public static <T extends Comparable<T>> void sortByStep(Link<T> start, int step) {
        while(start.getNext() != null) {
            start = doMergeSort(start, start.getNext(), step);
        }
    }

    // merge two sorted linked list
    public static <T extends Comparable<T>> Link<T> doMergeSort(Link<T> start, Link<T> l, int step) {
        Link<T> i = l, j= l, elem = null;

        for (int k = 0; k < step; k++) {
            // return last element if size of linked list is less than given step
            if (j.getNext() == null) {
                start.setNext(i);
                return j;
            }
            j = j.getNext();
        }

        // link element with smallest value to previous element
        start.setNext(i.getValue().compareTo(j.getValue()) <= 0 ? i : j);

        int ci = 0, cj = 0;
        while (ci < step && cj < step) {
            if (i.getValue().compareTo(j.getValue()) <= 0) {
                if (elem != null) {
                    elem.setNext(i);
                }
                elem = i;
                ci ++;
                if (ci < step) {
                    i = i.getNext();
                }
                continue;
            }

            if (i.getValue().compareTo(j.getValue()) > 0) {
                cj ++;
                if (elem != null) {
                    elem.setNext(j);
                }
                elem = j;
                j = j.getNext();
                elem.setNext(i);
                if (j == null)
                    break;
            }
        }

        // navigate to the last element and return
        if ( ci < step) {
            while (ci < step -1) {
                i = i.getNext();
                ci++;
            }
            i.setNext(j);
            return i;
        } else {
            if (j==null) {
                throw new IllegalStateException("j cannot be null when in else block");
            }

            i.setNext(j);
            while (cj < step -1 && j.getNext()!= null) {
                j = j.getNext();
                cj++;
            }
            return j;
        }
    }

    private static <T extends Comparable> int getListSize(Link<T> l) {
        int count = 0;
        while (l != null) {
            count ++;
            l = l.getNext();
        }
        return count;
    }

}
