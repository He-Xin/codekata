package cn.hex.codekata.overload;

/**
 * Created by hex.
 */
public class Overload {
    public void print(Parent p) {
        System.out.println("P");
        p.greet();
    }

    public void print(Child c) {
        System.out.println("C");
        c.greet();
    }

    public static void main(String[] args) {
        Parent p = new Child();

        Overload ol = new Overload();

        //will print "P" and "Child" as overloading is determined at compile time while overriding runtime
        ol.print(p);
    }
}
