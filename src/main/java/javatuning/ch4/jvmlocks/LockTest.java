package javatuning.ch4.jvmlocks;

/**
 * -server -XX:-DoEscapeAnalysis -XX:-EliminateLocks
 */
public class LockTest {

    private static final int CIRCLE = 20000000;

    public static void main(String args[]) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            createStringBuffer("Java", "Performance");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("createStringBuffer: " + bufferCost + " ms");
    }

    public static String createStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }

}