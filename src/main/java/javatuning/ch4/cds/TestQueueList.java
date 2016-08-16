package javatuning.ch4.cds;

import org.junit.Test;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestQueueList {

    private static final int MAX_THREADS = 2000;
    private static final int TASK_COUNT = 4000;
    private Queue q;

    public Object handleQueue(int index) {
        q.add(index); //测试add
        q.poll();     //测试poll
        return null;
    }

    public void initConcurrentLinkedQueue() {
        q = new ConcurrentLinkedQueue();
        for (int i = 0; i < 300; i++) q.add(i);
    }

    public void initLinkedBlockingQueue() {
        q = new LinkedBlockingQueue();
        for (int i = 0; i < 300; i++) q.add(i);
    }

    public void initArrayBlockingQueue() {
        q = new ArrayBlockingQueue(10000);
        for (int i = 0; i < 300; i++) q.add(i);
    }


    public class HandleQueueTask implements Runnable {
        protected Random rand = new Random();
        protected String name;

        public HandleQueueTask() {
        }

        public HandleQueueTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 500; i++) { //高并发
                    handleQueue(rand.nextInt(1000));
                }
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public class CounterPoolExecutor extends ThreadPoolExecutor {
        private AtomicInteger count = new AtomicInteger(0);
        private long startTime = 0;
        private String funcName = "";

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
    public void testConcurrentLinkedQueue() throws InterruptedException {
        initConcurrentLinkedQueue();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testConcurrentLinkedQueue";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new HandleQueueTask());

        Thread.sleep(1000);
    }

    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
        initLinkedBlockingQueue();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testLinkedBlockingQueue";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new HandleQueueTask());

        Thread.sleep(1000);
    }

    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        initArrayBlockingQueue();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testArrayBlockingQueue";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new HandleQueueTask());

        Thread.sleep(1000);
    }

}
