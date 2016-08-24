package javatuning.ch4.deadlock;

import java.util.concurrent.locks.ReentrantLock;

public class DeadLockCarThread extends Thread {

    protected Object myDirect;

    static ReentrantLock south = new ReentrantLock();
    static ReentrantLock north = new ReentrantLock();
    static ReentrantLock west = new ReentrantLock();
    static ReentrantLock east = new ReentrantLock();

    public DeadLockCarThread(Object obj) {
        this.myDirect = obj;
        if (myDirect == south) {
            this.setName("south");
        }
        if (myDirect == north) {
            this.setName("north");
        }
        if (myDirect == west) {
            this.setName("west");
        }
        if (myDirect == east) {
            this.setName("east");
        }
    }

    @Override
    public void run() {
        if (myDirect == south) {
            try {
                west.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                south.lockInterruptibly();
                System.out.println("car to south has passed");
            } catch (InterruptedException e1) {
                System.out.println("car to south is killed");
            } finally {
                if (west.isHeldByCurrentThread())
                    west.unlock();
                if (south.isHeldByCurrentThread())
                    south.unlock();
            }

        }
        if (myDirect == north) {
            try {
                east.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                north.lockInterruptibly();
                System.out.println("car to north has passed");
            } catch (InterruptedException e1) {
                System.out.println("car to north is killed");
            } finally {
                if (north.isHeldByCurrentThread())
                    north.unlock();
                if (east.isHeldByCurrentThread())
                    east.unlock();
            }

        }
        if (myDirect == west) {
            try {
                north.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                west.lockInterruptibly();
                System.out.println("car to west has passed");
            } catch (InterruptedException e1) {
                System.out.println("car to west is killed");
            } finally {
                if (north.isHeldByCurrentThread())
                    north.unlock();
                if (west.isHeldByCurrentThread())
                    west.unlock();
            }

        }
        if (myDirect == east) {
            try {
                south.lockInterruptibly();
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                east.lockInterruptibly();
                System.out.println("car to east has passed");
            } catch (InterruptedException e1) {
                System.out.println("car to east is killed");
            } finally {
                if (south.isHeldByCurrentThread())
                    south.unlock();
                if (east.isHeldByCurrentThread())
                    east.unlock();
            }
        }
    }

    @Deprecated
    public static void main(String[] args) throws InterruptedException {
        DeadLockCarThread car2south = new DeadLockCarThread(south);
        DeadLockCarThread car2north = new DeadLockCarThread(north);
        DeadLockCarThread car2west = new DeadLockCarThread(west);
        DeadLockCarThread car2east = new DeadLockCarThread(east);

        car2south.start();
        car2north.start();
        car2west.start();
        car2east.start();

        Thread.sleep(1000);
        car2north.interrupt();
    }

}
