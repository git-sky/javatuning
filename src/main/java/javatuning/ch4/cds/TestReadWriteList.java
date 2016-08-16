package javatuning.ch4.cds;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class TestReadWriteList {

    private static final int MAX_THREADS = 2000;
    private static final int TASK_COUNT = 4000;
    List list;

    public class AccessListTask implements Runnable {
        protected Random rand = new Random();
        protected String name;

        public AccessListTask() {
        }

        public AccessListTask(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            try {
                handleList(rand.nextInt(1000));
                Thread.sleep(rand.nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Object handleList(int index) {
        list.add(index);
        return null;
    }

    public void initListCopyOnWriteContent() {
        List l = new ArrayList();
        for (int i = 0; i < 1000; i++)
            l.add(i);
        list = new CopyOnWriteArrayList(l);
    }

    public void initVector() {
        List l = new ArrayList();
        for (int i = 0; i < 1000; i++)
            l.add(i);
        list = new Vector(l);
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
    public void testWriteCopyOnWriteList() throws InterruptedException {
        initListCopyOnWriteContent();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testGetCopyOnWriteList";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new AccessListTask());

        Thread.sleep(1000);
    }

    @Test
    public void testWriteVector() throws InterruptedException {
        initVector();
        CounterPoolExecutor es = new CounterPoolExecutor(
                MAX_THREADS,
                MAX_THREADS,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        es.startTime = System.currentTimeMillis();
        es.funcName = "testGetVector";
        for (int i = 0; i < TASK_COUNT; i++)
            es.submit(new AccessListTask());

        Thread.sleep(1000);
    }

}
