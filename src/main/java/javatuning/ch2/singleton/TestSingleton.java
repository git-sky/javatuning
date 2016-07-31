package javatuning.ch2.singleton;

import junit.framework.Assert;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleton {

    @Test
    public void test() {
        Singleton s = Singleton.getInstance();
        Singleton s1 = null;
        Class singletonClass = Singleton.class;
        Constructor cons;
        try {
            cons = singletonClass.getDeclaredConstructor(null);
            cons.setAccessible(true);
            s1 = (Singleton) cons.newInstance(null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertNotSame(s, s1);
    }


    public static class AccessSingletonThread extends Thread {
        long begTime;

        public AccessSingletonThread(long begTime) {
            this.begTime = begTime;
        }

        @Override
        public void run() {
            System.out.println("try to get instance");
            for (int i = 0; i < 100000; i++) {
                //Singleton.getInstance();
                LazySingleton.getInstance();
            }
            System.out.println("spend:" + (System.currentTimeMillis() - begTime));
        }
    }


    @Test
    public void testPerformance() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(5);
        long begTime = System.currentTimeMillis();
        exe.submit(new AccessSingletonThread(begTime));
        exe.submit(new AccessSingletonThread(begTime));
        exe.submit(new AccessSingletonThread(begTime));
        exe.submit(new AccessSingletonThread(begTime));
        exe.submit(new AccessSingletonThread(begTime));

        Thread.sleep(10000);
    }

    @Test
    public void testSingleton() {
        StaticSingleton.createString();
        StaticSingleton.getInstance();
    }

}
