package javatuning.ch4.jvmlocks;

import org.junit.Test;

public class TestSyncObject {

    public static Object lock = new Object();

    public static final int CIRCLE = 10000000;

    @Test
    public void test() {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            synchronized (lock) {
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sync in loop:" + (endTime - begTime));
    }

    @Test
    public void test1() {
        long begTime = System.currentTimeMillis();
        synchronized (lock) {
            for (int i = 0; i < CIRCLE; i++) {
            }
        }
        long endTime = System.currentTimeMillis();
        System.out.println("sync out loop:" + (endTime - begTime));
    }

    public void demoMethod() {
        synchronized (lock) {
            //do sth.
        }

        synchronized (lock) {
            //do sth.
        }
    }

}
