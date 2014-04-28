package cn.hex.codekata.algo.link;

/**
 * Created by hex.
 */
public class QuickSort {
    public static <T extends Comparable<T>> Link<T> sort(Link<T> link) {
        if (link == null)
            return null;

        return doSort(link).header;
    }

    private static <T extends Comparable<T>> HeaderTail<T> doSort(Link<T> link) {
        if (link.getNext() == null) {
            return new HeaderTail<>(link, link);
        }

        Link<T> pivot = link; // use first element as pivot

        HeaderTail<T> l = null, g = null;

        Link<T> t = link.getNext(), less = null, greater = null, lessStart = null, greaterStart = null;

        while (t != null) {
            if (t.getValue().compareTo(pivot.getValue()) <= 0) {
                if (lessStart == null) {
                    lessStart = t;
                    less = t;
                } else {
                    less.setNext(t);
                    less = t;
                }
            } else {
                if (greaterStart == null) {
                    greaterStart = t;
                    greater = t;
                } else {
                    greater.setNext(t);
                    greater = t;
                }
            }

            t = t.getNext();
        }

        pivot.setNext(null);

        if (lessStart != null) {
            less.setNext(null);
            l = doSort(lessStart);
        }

        if (greaterStart != null) {
            greater.setNext(null);
            g = doSort(greaterStart);
        }


        if (l != null) {
            l.tail.setNext(pivot);
        }

        if (g != null) {
            pivot.setNext(g.header);
        }

        return new HeaderTail<>(l == null ? pivot : l.header, g == null ? pivot : g.tail);
    }

    private static class HeaderTail<T> {
        Link<T> header;
        Link<T> tail;

        private HeaderTail(Link<T> header, Link<T> tail) {
            this.header = header;
            this.tail = tail;
        }
    }
}
