package javatuning.ch5.memory;

/**
 * -verbose:gc
 */
public class WordResueGC {

    public static void test1() {
        {
            byte[] b = new byte[6 * 1204 * 1024];
        }
        System.gc();
        System.out.println("first explict gc over");
    }

    public static void test2() {
        {
            byte[] b = new byte[6 * 1204 * 1024];
            b = null;
        }
        System.gc();
        System.out.println("first explict gc over");
    }

    public static void test3() {
        {
            byte[] b = new byte[6 * 1204 * 1024];
        }
        int a = 0;
        System.gc();
        System.out.println("first explict gc over");
    }

    public static void test4() {
        {
            int c = 0;
            byte[] b = new byte[6 * 1204 * 1024];
        }
        int a = 0;
        System.gc();
        System.out.println("first explict gc over");
    }

    public static void test5() {
        {
            int c = 0;
            byte[] b = new byte[6 * 1204 * 1024];
        }
        int a = 0;
        int d = 0;
        System.gc();
        System.out.println("first explict gc over");
    }

    public static void main(String args[]) {
        test1();
        System.gc();
        System.out.println("second explict gc over");
    }

}
