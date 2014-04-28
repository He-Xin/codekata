package cn.hex.codekata.algo.link;

/**
 * Created by hex.
 */
public class Link<T> {
    private T value;
    private Link<T> next;

    @SafeVarargs
    static <T> Link<T> createLinkList(T... values) {
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
