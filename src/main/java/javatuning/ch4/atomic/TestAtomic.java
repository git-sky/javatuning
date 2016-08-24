package javatuning.ch4.atomic;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class TestAtomic {

    private static final int MAX_THREADS = 3;
    private static final int TASK_COUNT = 3;
    private static final int TARGET_COUNT = 1000000;

    private AtomicInteger acount = new AtomicInteger(0);
    private int count = 0;

    protected synchronized int inc() {
        return ++count;
    }

    protected synchronized int getCount() {
        return count;
    }

    public class SyncTask implements Runnable {
        private String name;
        private long startTime;
        TestAtomic out;

        public SyncTask(TestAtomic o, long startTime) {
            out = o;
            this.startTime = startTime;
        }

        @Override
        public void run() {
            int v = out.inc();
            while (v < TARGET_COUNT) {
                v = out.inc();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("SyncTask spend:" + (endTime - startTime) + "ms" + " v=" + v);
        }
    }

    public class AtomicTask implements Runnable {
        private String name;
        private long startTime;

        public AtomicTask(long startTime) {
            this.startTime = startTime;
        }

        @Override
        public void run() {
            int v = acount.incrementAndGet();
            while (v < TARGET_COUNT) {
                v = acount.incrementAndGet();
            }
            long endTime = System.currentTimeMillis();
            System.out.println("AtomicTask spend:" + (endTime - startTime) + "ms" + " v=" + v);
        }
    }

    @Test
    public void testAtomic() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        AtomicTask atomic = new AtomicTask(startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(atomic);
        }
        Thread.sleep(10000);
    }

    @Test
    public void testSync() throws InterruptedException {
        ExecutorService exe = Executors.newFixedThreadPool(MAX_THREADS);
        long startTime = System.currentTimeMillis();
        SyncTask sync = new SyncTask(this, startTime);
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(sync);
        }
        Thread.sleep(10000);
    }

}
