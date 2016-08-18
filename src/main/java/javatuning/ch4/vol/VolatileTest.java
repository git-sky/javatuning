package javatuning.ch4.vol;

import org.junit.Test;

public class VolatileTest {

    private volatile boolean isExit;
    //private boolean isExit;

    public void tryExit() {
        if (isExit == !isExit) {
            System.exit(0);
        }
    }

    public void swapValue() {
        isExit = !isExit;
    }

    @Test
    public void test() throws InterruptedException {
        final VolatileTest volObj = new VolatileTest();

        Thread mainThread = new Thread() {
            @Override
            public void run() {
                System.out.println("mainThread start");
                int i = 0;
                while (true) {
                    System.out.println("mainThread " + i++);
                    volObj.tryExit();
                }
            }
        };
        mainThread.start();

        Thread swapThread = new Thread() {
            @Override
            public void run() {
                System.out.println("swapThread start");
                while (true) {
                    volObj.swapValue();
                }
            }
        };
        swapThread.start();

        Thread.sleep(1000);
    }

}
