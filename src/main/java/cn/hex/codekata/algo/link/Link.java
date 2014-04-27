package cn.hex.codekata.algo.link;

/**
 * Created by hex.
 */
public class Link<T> {
    private T value;
    private Link<T> next;

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
