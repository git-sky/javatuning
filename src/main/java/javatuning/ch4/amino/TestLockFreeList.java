package javatuning.ch4.amino;

import org.amino.ds.lockfree.LockFreeList;
import org.amino.ds.lockfree.LockFreeVector;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestLockFreeList {

    private static final int MAX_THREADS = 2000;
    private static final int TASK_COUNT = 4000;

    private List list;

    public Object handleList(int index) {
        list.add(index);
        list.remove(index % list.size());
        return null;
    }

    public void initLinkedList() {
        List l = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            l.add(i);
        }
        list = Collections.synchronizedList(new LinkedList(l));
    }

    public void initVector() {
        List l = new ArrayList();
        for (int i = 0; i < 1000; i++) {
            l.add(i);
        }
        list = new Vector(l);
    }

    public void initFreeLockList() {
        list = new LockFreeList();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }

    public void initFreeLockVector() {
        list = new LockFreeVector();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
        }
    }


    public class AccessListTask implements Runnable {
        private String name;
        private Random rand = new Random();

        public AccessListTask() {
        }

        public AccessListTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 1000; i++) {
                    handleList(rand.nextInt(1000));
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
    public void testFreeLockList() throws InterruptedException {
        initFreeLockList();
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testFreeLockList";
        Runnable t = new AccessListTask();
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(t);
        }
        Thread.sleep(10000);
    }

    @Test
    public void testFreeLockVector() throws InterruptedException {
        initFreeLockVector();
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testFreeLockVector";
        Runnable t = new AccessListTask();
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(t);
        }
        Thread.sleep(10000);
    }

    @Test
    public void testLinkedList() throws InterruptedException {
        initLinkedList();
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testLinkedList";
        Runnable t = new AccessListTask();
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(t);
        }
        Thread.sleep(10000);
    }

    @Test
    public void testVector() throws InterruptedException {
        initVector();
        CounterPoolExecutor exe = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        exe.startTime = System.currentTimeMillis();
        exe.funcName = "testVector";
        Runnable t = new AccessListTask();
        for (int i = 0; i < TASK_COUNT; i++) {
            exe.submit(t);
        }
        Thread.sleep(10000);
    }

}
