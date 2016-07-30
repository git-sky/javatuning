package javatuning.ch6.toolscheck;

/**
 * CPU占用率测试程序
 */
public class HoldCPUMain {
    public static class HoldCPUTask implements Runnable {
        @Override
        public void run() {
            while (true) {
                double a = Math.random() * Math.random();
            }
        }
    }

    public static class LazyTask implements Runnable {
        public void run() {
            try {
                while (true) {
                    Thread.sleep(1000);
                }
            } catch (Exception e) {

            }
        }
    }

    public static void main(String[] args) {
        new Thread(new HoldCPUTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
        new Thread(new LazyTask()).start();
    }
}

