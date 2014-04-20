package cn.hex.codekata.java8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by hex.
 */
public class ForEachExample<T> {
    private List<T> list;

    public ForEachExample(List<T> list) {
        this.list = list;
    }

    public void iterateWithForEach(Consumer<T> consumer) {
        list.forEach(consumer);
    }

    public static void main(String[] args) {
        List<Integer> intArray = Arrays.asList(1, 2, 3, 4);

        intArray.forEach(integer -> System.out.println("item is: " + integer));
    }
}
