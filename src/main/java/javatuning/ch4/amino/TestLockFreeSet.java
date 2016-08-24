package javatuning.ch4.amino;

import org.amino.ds.lockfree.LockFreeSet;
import org.junit.Test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLockFreeSet {

    private static final int MAX_THREADS = 2000;
    private static final int TASK_COUNT = 4000;
    private Random rand = new Random();

    private Set set;

    public Object handleSet(int index) {
        set.add(rand.nextInt(2000));
        if (set.size() > 10000) set.clear();
        return null;
    }

    public void initSet() {
        set = Collections.synchronizedSet(new HashSet());
    }

    public void initFreeLockSet() {
        set = new LockFreeSet();
    }


    public class AccessSetTask implements Runnable {
        protected String name;

        public AccessSetTask() {
        }

        public AccessSetTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 500; i++) {
                    handleSet(rand.nextInt(1000));
                }
                Thread.sleep(rand.nextInt(100));
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
    public void testSet() throws InterruptedException {
        initSet();
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testSet";
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(new AccessSetTask());
        }
        Thread.sleep(5000);
    }

    @Test
    public void testLockFreeSet() throws InterruptedException {
        initFreeLockSet();
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testLockFreeSet";
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(new AccessSetTask());
        }
        Thread.sleep(5000);
    }

}
