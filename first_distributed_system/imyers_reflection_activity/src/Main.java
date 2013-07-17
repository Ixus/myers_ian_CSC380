import javafx.application.Application;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created with IntelliJ IDEA.
 * User: imyers
 * Date: 7/12/13
 * Time: 5:56 PM
 * To change this template use File | Settings | File Templates.
 */

public class Main {

    public static void main(String[] args) throws Exception {
        Class c = Class.forName("ReflectionActivity");

        Constructor[] constructors = c.getDeclaredConstructors();

        for(Constructor constructor : constructors) {
            System.out.print(constructor.getName());
            System.out.print("(");
            for(int i = 0; i < constructor.getParameterTypes().length; i++) {
                Class aClass = constructor.getParameterTypes()[i];
                System.out.print(aClass.getName());
                if(i < constructor.getParameterTypes().length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println(")");
        }

        Object instance = c.getConstructor(String.class, double.class).newInstance("sean", 4.5);

        for(int i = 0; i < c.getDeclaredFields().length; i++) {
            Field field = c.getDeclaredFields()[i];
            System.out.println(field.getType().getName() + " " + field.getName());
        }

        Method meth = c.getDeclaredMethod("addToNumber", double.class);
        System.out.println(meth.invoke(instance, 5));

        // EXTRA
        System.out.println("");

        Method[] methods = c.getDeclaredMethods();
        for(Method m : methods)      {

            System.out.println(m);
            System.out.println(m.getName());

            Annotation[] annotations = m.getDeclaredAnnotations();
            for(Annotation a : annotations) System.out.println(a);

            Class<?>[] params = m.getParameterTypes();
            for(Class<?> p : params) System.out.println(p);

            System.out.println("");
        }


    }

}
