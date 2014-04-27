package cn.hex.codekata.algo.link;

/**
 * Created by hex.
 */
public class MergeSort {
    public static <T extends Comparable<T>> Link<T> sort(Link<T> l) {
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
        while (start.getNext() != null) {
            start = doMergeSort(start, start.getNext(), step);
        }
    }

    // merge two sorted linked list
    public static <T extends Comparable<T>> Link<T> doMergeSort(Link<T> start, Link<T> l, int step) {
        Link<T> i = l, j = l, prevElementPointer = null;

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
                ci++;
                prevElementPointer = updatePrevElementPointer(i, prevElementPointer);
                if (ci < step) {
                    i = i.getNext();
                }
                continue;
            }

            if (i.getValue().compareTo(j.getValue()) > 0) {
                cj++;
                prevElementPointer = updatePrevElementPointer(j, prevElementPointer);
                j = j.getNext();
                prevElementPointer.setNext(i);
                if (j == null)
                    break;
            }
        }

        // navigate to the last element and return
        if (ci < step) {
            i = goToLastElement(i, ci, step);
            i.setNext(j);
            return i;
        } else {
            i.setNext(j);
            j = goToLastElement(j, cj, step);
            return j;
        }
    }

    private static <T extends Comparable<T>> Link<T> goToLastElement(Link<T> currentLink, int currentIndex, int length) {
        while (currentIndex < length - 1 && currentLink.getNext() != null) {
            currentLink = currentLink.getNext();
            currentIndex++;
        }
        return currentLink;
    }

    private static <T extends Comparable<T>> Link<T> updatePrevElementPointer(Link<T> i, Link<T> elem) {
        if (elem != null) {
            elem.setNext(i);
        }
        elem = i;
        return elem;
    }

    private static <T extends Comparable> int getListSize(Link<T> l) {
        int count = 0;
        while (l != null) {
            count++;
            l = l.getNext();
        }
        return count;
    }
}
