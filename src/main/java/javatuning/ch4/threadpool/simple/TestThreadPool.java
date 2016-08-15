package javatuning.ch4.threadpool.simple;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class TestThreadPool {

    public class MyThread implements Runnable {
        protected String name;

        public MyThread() {
        }

        public MyThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(100);
                //System.out.print(name+" ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testThreadPool() throws InterruptedException {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            ThreadPool.getInstance().start(new MyThread("testThreadPool" + Integer.toString(i)));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testThreadPool" + ": " + (endTime - begTime) + "ms");
        System.out.println("getCreatedThreadsCount:" + ThreadPool.getInstance().getCreatedThreadsCount());
        Thread.sleep(1000);
    }

    @Test
    public void testJDKThreadPool() throws InterruptedException {
        ExecutorService exe = Executors.newCachedThreadPool();

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            exe.execute(new MyThread("testJDKThreadPool" + Integer.toString(i)));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testJDKThreadPool" + ": " + (endTime - begTime) + "ms");
        System.out.println("newCachedThreadPool size:" + ((ThreadPoolExecutor) exe).getPoolSize());
        Thread.sleep(1000);
    }

    @Test
    public void testNoThreadPool() throws InterruptedException {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Thread(new MyThread("testNoThreadPool" + Integer.toString(i))).start();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testNoThreadPool" + ": " + (endTime - begTime) + "ms");
        Thread.sleep(1000);
    }

}
