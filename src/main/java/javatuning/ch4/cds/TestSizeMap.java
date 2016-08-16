package javatuning.ch4.cds;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestSizeMap {

    private static final int MAX_THREADS = 2000;
    private static final int TASK_COUNT = 4000;
    private Random rand = new Random();

    private static Object DUMMY = new Object();
    private Map map;

    public Object handleMap(int index) {
        //map.put(rand.nextInt(2000), DUMMY);
        map.size();
        //return map.get(index);
        return null;
    }

    public void initConcurrentHashMap() {
        map = new ConcurrentHashMap();
        for (int i = 0; i < 1000; i++) map.put(i, DUMMY);
    }

    public void initHashMap() {
        map = Collections.synchronizedMap(new HashMap());
        for (int i = 0; i < 1000; i++) map.put(i, DUMMY);
    }

    public class AccessMapTask implements Runnable {
        protected String name;

        public AccessMapTask() {
        }

        public AccessMapTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 500; i++) {
                    handleMap(rand.nextInt(1000));
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
    public void testConcurrentHashMap() throws InterruptedException {
        initConcurrentHashMap();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testConcurrentHashMap";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new AccessMapTask());

        Thread.sleep(10000);
    }

    @Test
    public void testHashMap() throws InterruptedException {
        initHashMap();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testHashMap";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new AccessMapTask());

        Thread.sleep(10000);
    }

}
