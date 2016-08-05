package javatuning.ch3.stringappend;

import org.junit.Test;

public class TestStringBuffer {

    private static final int CIRCLE = 500000;

    @Test
    public void testStringPlus() {
        String str = "";
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            str = str + i;
            //str+=i;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringPlus:" + (endTime - begTime) + " ms");
    }

    @Test
    public void testStringConcat() {
        long begTime = System.currentTimeMillis();
        String result = "";
        int c = CIRCLE;///10;
        for (int i = 0; i < CIRCLE; i++) {
            result = result.concat(String.valueOf(i));
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringConcat:" + (endTime - begTime) + " ms");
    }

    @Test
    public void testStringBuffer() {
        StringBuffer sb = new StringBuffer();

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            sb.append(i);
        }
        sb.toString();
        long endTime = System.currentTimeMillis();

        System.out.println("testStringBuffer:" + (endTime - begTime) + " ms");
    }

    @Test
    public void testStringBuilder() {
        StringBuilder sb = new StringBuilder();

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            sb.append(i);
        }
        sb.toString();
        long endTime = System.currentTimeMillis();

        System.out.println("testStringBuilder:" + (endTime - begTime) + " ms");
    }

    @Test
    public void testStringBuffer10() {
        StringBuffer sb = new StringBuffer(5888890);

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            sb.append(i);
        }
        sb.toString();
        long endTime = System.currentTimeMillis();

        System.out.println("testStringBuffer10:" + (endTime - begTime) + " ms");
    }

    @Test
    public void testStringBuilder10() {
        StringBuilder sb = new StringBuilder(5888890);

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            sb.append(i);
        }
        sb.toString();
        long endTime = System.currentTimeMillis();

        System.out.println("testStringBuilder10:" + (endTime - begTime) + " ms");
    }

}
