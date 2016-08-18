package javatuning.ch4.vol;

public class VolatileTest2 {

    public static void main(String[] args) throws Exception {
        Control control = new Control();

        Thread tryExitThread = new TryExitThread("tryExitThread", control);
        tryExitThread.start();

        Thread swapValueThread = new SwapValueThread("swapValueThread", control);
        swapValueThread.start();

        Thread.sleep(1000);
    }

    static class TryExitThread extends Thread {
        private String tName;
        private Control control;

        public TryExitThread(String tName, Control control) {
            this.tName = tName;
            this.control = control;
        }

        @Override
        public void run() {
            System.out.println(tName + " start");
            int i = 0;
            while (true) {
                System.out.println(tName + " " + i++);
                control.tryExit();
            }
        }
    }

    static class SwapValueThread extends Thread {
        private String tName;
        private Control control;

        public SwapValueThread(String tName, Control control) {
            this.tName = tName;
            this.control = control;
        }

        @Override
        public void run() {
            System.out.println(tName + " start");
            while (true) {
                control.swapValue();
            }
        }
    }

    static class Control {
        //private volatile boolean isExit;
        private boolean isExit;

        public void tryExit() {
            if (isExit == !isExit) {
                System.exit(0);
            }
        }

        public void swapValue() {
            isExit = !isExit;
        }
    }

}
