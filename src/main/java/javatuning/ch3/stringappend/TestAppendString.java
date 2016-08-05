package javatuning.ch3.stringappend;

import org.junit.Test;

public class TestAppendString {

    private static final int CIRCLE = 50000;

    @Test
    public void testStringAppend() {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            String result = "String" + "and" + "String" + "append";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringAppend:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testStringAppend2_i() {
        long begTime = System.currentTimeMillis();
        String str1 = "String";
        String str2 = "and";
        String str3 = "String";
        String str4 = "append";
        for (int i = 0; i < CIRCLE; i++) {
            String result = str1 + str2 + str3 + str4;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringAppend2_i:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testStringAppend2() {
        long begTime = System.currentTimeMillis();
        String str1 = "String";
        String str2 = "and";
        String str3 = "String";
        String str4 = "append";
        for (int i = 0; i < CIRCLE; i++) {
            String result = str1 + str2 + str3 + str4;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringAppend2:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testStringConcat() {
        long begTime = System.currentTimeMillis();
        String result;
        for (int i = 0; i < CIRCLE; i++) {
            result = "String".concat("and").concat("String").concat("append");
            //String result = "String"+ "and"+ "String"+"append";
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringConcat:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testStringBufferAppend() {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            StringBuffer result = new StringBuffer();
            result.append("String");
            result.append("and");
            result.append("String");
            result.append("append");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringBufferAppend:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testStringBuilderAppend() {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE; i++) {
            StringBuilder result = new StringBuilder();
            result.append("String");
            result.append("and");
            result.append("String");
            result.append("append");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStringBuilderAppend:" + (endTime - begTime) + "ms");
    }

}
