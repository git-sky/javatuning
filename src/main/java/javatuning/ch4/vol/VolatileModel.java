package javatuning.ch4.vol;

import org.junit.Test;

public class VolatileModel {

    public static class MyThread extends Thread {
        private boolean stop = false;
        //private volatile boolean stop = false;

        public void stopMe() {
            stop = true;
        }

        @Override
        public void run() {
            long i = 0;
            while (!stop) {
                i++;
            }
            System.out.println("i=" + i);
            System.out.println("Stop Thread");
        }
    }

    @Test
    public void test() throws InterruptedException {
        MyThread t = new MyThread();
        t.start();
        Thread.sleep(1000);
        t.stopMe();
        Thread.sleep(1000);
    }

    @Deprecated
    public static void main(String[] args) throws Exception {
        MyThread t1 = new MyThread();
        MyThread t2 = new MyThread();

        t1.start();
        //t2.start();
        Thread.sleep(1000);

        t1.stopMe();
        //t2.stopMe();
        Thread.sleep(1000);
    }

    public static class MyThread2 extends Thread {
        @Override
        public void run() {
            while (true) {
                ;
            }
        }
    }

    @Test
    public void testDeadLoop() throws Exception {
        MyThread2 t = new MyThread2();
        t.start();
        Thread.sleep(1000);
    }

}
