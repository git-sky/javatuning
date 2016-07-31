package javatuning.ch6.toolscheck;

/**
 * 锁测试程序
 */
public class HoldLockMain {

    public static Object[] lock = new Object[10];
    public static java.util.Random r = new java.util.Random();

    static {
        for (int i = 0; i < lock.length; i++) {
            lock[i] = new Object();
        }
    }

    public static class HoldLockTask implements Runnable {
        private int i;
        private String name;

        public HoldLockTask(int i, String name) {
            this.i = i;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    synchronized (lock[i]) {
                        if (i % 2 == 0) {
                            int waitMills = r.nextInt(10);
                            System.out.println("Task" + name + " occupy resource lock[" + i + "] for" + waitMills + "ms");
                            lock[i].wait(waitMills);
                        } else {
                            lock[i].notifyAll();
                            System.out.println("Task" + name + " notifyAll for resource lock[" + i + "]");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < lock.length * 2; i++) {
            new Thread(new HoldLockTask(i / 2, String.valueOf(i))).start();
        }
    }

}
