package javatuning.ch3.tech;

import org.junit.Test;

public class TestStaticMethod {

    public static void staticMethod() {
    }

    public void instanceMethod() {
    }

    @Test
    public void test() {
        int CIRCLE = 100000000;

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            staticMethod();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("staticMethod:" + (endTime - begTime) + "ms");

        begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            instanceMethod();
        }
        endTime = System.currentTimeMillis();
        System.out.println("instanceMethod:" + (endTime - begTime) + "ms");
    }

}
