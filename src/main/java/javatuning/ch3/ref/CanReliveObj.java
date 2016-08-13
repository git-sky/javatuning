package javatuning.ch3.ref;

public class CanReliveObj {

    public static CanReliveObj obj;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("CanReliveObj finalize called");
        obj = this; //在finalize()中拯救了将要被回收的对象
    }

    @Override
    public String toString() {
        return "I am CanReliveObj";
    }

    public static void main(String[] args) throws InterruptedException {
        obj = new CanReliveObj();
        obj = null;         //删除对象
        System.gc();        //第一次gc
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj是null");
        } else {
            System.out.println("obj可用");
        }
        System.out.println("第二次gc");
        //obj = null;      //finalize()只会被调用一次
        System.gc();
        Thread.sleep(1000);
        if (obj == null) {
            System.out.println("obj是null");
        } else {
            System.out.println("obj可用");
        }
    }

}
