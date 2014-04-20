package cn.hex.codekata.java8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Consumer;

/**
 * Created by hex.
 */
public class ConsumerExample {
    private static class ComputeSum implements Consumer<Integer> {
        private int sum = 0;
        @Override
        public void accept(Integer integer) {
            sum += integer;
        }

        public int getSum() {
            return sum;
        }
    }


    private static class ConvertToString implements Consumer<Integer> {
        private List<String> strList = new ArrayList<>();

        @Override
        public void accept(Integer integer) {
            strList.add(integer.toString());
        }

        public List<String> getStrList() {
            return Collections.unmodifiableList(strList);
        }
    }

    private List<String> stringList = new ArrayList<>();
    private int sum = 0;

    public ConsumerExample(List<Integer> inputs) {
        ComputeSum consumer1 = new ComputeSum();
        ConvertToString consumer2 = new ConvertToString();

        Consumer<Integer> consumer = consumer1.andThen(consumer2);

        inputs.forEach(consumer);

        this.sum = consumer1.getSum();
        stringList = consumer2.getStrList();
    }

    public int getSum() {
        return sum;
    }

    public List<String> getStringList() {
        return Collections.unmodifiableList(stringList);
    }
}
