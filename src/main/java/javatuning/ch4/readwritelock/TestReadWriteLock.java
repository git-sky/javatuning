package javatuning.ch4.readwritelock;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {

    private static final int MAX_THREADS = 2000;
    private static final int TASK_COUNT = 4000;

    private static Lock lock = new ReentrantLock();
    private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private static Lock readLock = readWriteLock.readLock();
    private static Lock writeLock = readWriteLock.writeLock();

    private Random rand = new Random();

    private int value;

    public Object handleRead() throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1);
            return value;
        } finally {
            lock.unlock();
        }
    }

    public void handleWrite(int index) throws InterruptedException {
        try {
            lock.lock();
            Thread.sleep(1);
            value = index;
        } finally {
            lock.unlock();
        }
    }

    public Object handleRead2() throws InterruptedException {
        try {
            readLock.lock();
            Thread.sleep(1);
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public void handleWrite2(int index) throws InterruptedException {
        try {
            writeLock.lock();
            Thread.sleep(1);
            value = index;
        } finally {
            writeLock.unlock();
        }
    }


    public class ReadWriteTask1 implements Runnable {
        protected String name;

        public ReadWriteTask1() {
        }

        public ReadWriteTask1(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                handleRead();
                int v = rand.nextInt(100);
                if (v < 10) {
                    handleWrite(v);
                }
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public class ReadWriteTask2 implements Runnable {
        protected String name;

        public ReadWriteTask2() {
        }

        public ReadWriteTask2(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                handleRead2();
                int v = rand.nextInt(100);
                if (v < 10) {
                    handleWrite2(v);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public class CounterPoolExecutor extends ThreadPoolExecutor {
        private AtomicInteger count = new AtomicInteger(0);
        public long startTime = 0;
        public String funcName = "";

        public CounterPoolExecutor(int corePoolSize,
                                   int maximumPoolSize,
                                   long keepAliveTime,
                                   TimeUnit unit,
                                   BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        protected void afterExecute(Runnable r, Throwable t) {
            int l = count.addAndGet(1);
            if (l == TASK_COUNT) {
                System.out.println(funcName + " spend time:" + (System.currentTimeMillis() - startTime));
            }
        }
    }


    @Test
    public void testLock() throws InterruptedException {
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testLock";
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(new ReadWriteTask1());
        }
        Thread.sleep(10000);
    }

    @Test
    public void testLock2() throws InterruptedException {
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testLock2";
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(new ReadWriteTask2());
        }
        Thread.sleep(10000);
    }

}
