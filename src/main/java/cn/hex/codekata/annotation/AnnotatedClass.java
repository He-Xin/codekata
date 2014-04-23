package cn.hex.codekata.annotation;

import java.lang.annotation.Annotation;

/**
 * Created by hex.
 */
@MyAnnotation(value = "v1", num = 2)
public class AnnotatedClass {
    public static void main(String[] args) {
        AnnotatedClass ac = new AnnotatedClass();

        Annotation[] annotations = ac.getClass().getAnnotations();
        System.out.println(annotations.length);
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
        }

        if (ac.getClass().isAnnotationPresent(MyAnnotation.class)) {
            MyAnnotation annotation = ac.getClass().getAnnotation(MyAnnotation.class);
            System.out.println(annotation.value());
            System.out.println(annotation.num());
        }

        //Returns all annotations that are directly present on this element. Ignores inherited annotations
        annotations = ac.getClass().getDeclaredAnnotations();
        for (Annotation annotation : annotations) {
            System.out.println(annotation.annotationType());
        }
    }
}
