package cn.hex.codekata.collection;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by hex.
 */
public class RandomCharGenerator implements CharProvider {
    @Override
    public char getChar() {
        return (char) ThreadLocalRandom.current().nextInt('A', 'Z' + 1);
    }
}
