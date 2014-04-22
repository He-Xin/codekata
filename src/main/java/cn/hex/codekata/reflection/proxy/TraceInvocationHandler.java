package cn.hex.codekata.reflection.proxy;

import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by hex.
 */
public class TraceInvocationHandler implements InvocationHandler {
    private Object obj;
    private PrintStream out;


    public TraceInvocationHandler(Object o, PrintStream out) {
        this.obj = o;
        this.out = out;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        out.println("before invoke method " + method.getName() + " of object " + obj.toString());
        return method.invoke(obj, args);
    }

    public static void main(String[] args) {
        GreetingImpl greeting = new GreetingImpl();
        InvocationHandler handler = new TraceInvocationHandler(greeting, System.out);

        Greeting proxy = (Greeting) Proxy.newProxyInstance(greeting.getClass().getClassLoader(), new Class[]{Greeting.class}, handler);

        proxy.sayHello();
    }
}
