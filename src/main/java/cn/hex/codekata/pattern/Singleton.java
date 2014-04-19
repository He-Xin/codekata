package cn.hex.codekata.pattern;

/**
 * Created by hex.
 */
public class Singleton {

    private Singleton() {

    }

    private static class SingletonHolder {
        public static Singleton instance = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.instance;
    }
    
}
