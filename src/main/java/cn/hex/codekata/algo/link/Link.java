package cn.hex.codekata.algo.link;

/**
 * Created by hex.
 */
public class Link<T> {
    private T value;
    private Link<T> next;


    public static <T> Link<T> reverseList(Link<T> head) {
        Link<T> newHead = head;
        Link<T> next = head.getNext();
        newHead.setNext(null);

        while (next != null) {
            Link<T> temp = next.getNext();
            next.setNext(newHead);
            newHead = next;
            next = temp;
        }

        return newHead;
    }

    @SafeVarargs
    public static <T> Link<T> createLinkList(T... values) {
        Link<T> start = null, current = null;
        for (T value : values) {
            Link<T> l = new Link<>();
            if (start == null) {
                start = l;
            }
            l.setValue(value);
            if (current != null) {
                current.setNext(l);
            }
            current = l;
        }
        return start;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Link<T> getNext() {
        return next;
    }

    public void setNext(Link<T> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.value);
        Link<T> link = this.next;
        while (link != null) {
            sb.append("->").append(link.getValue());
            link = link.getNext();
        }
        return sb.toString();
    }
}
