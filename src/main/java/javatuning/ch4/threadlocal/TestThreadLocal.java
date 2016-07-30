package javatuning.ch4.threadlocal;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestThreadLocal {

    @Test
    public void test() throws InterruptedException {
        ExecutorService exe = Executors.newCachedThreadPool();
        exe.submit(new MyThread(1));
        exe.submit(new MyThread(2));
        Thread.sleep(10000);
    }

}
