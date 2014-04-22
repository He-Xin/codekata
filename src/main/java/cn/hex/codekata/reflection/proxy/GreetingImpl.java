package cn.hex.codekata.reflection.proxy;

/**
 * Created by hex.
 */
public class GreetingImpl implements Greeting{
    @Override
    public String sayHello() {
        return "Hello";
    }

    @Override
    public String toString() {
        return "HelloGreeting";
    }
}
