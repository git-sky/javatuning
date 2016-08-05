package javatuning.ch3.teststr.split.junit;

import org.junit.Test;

public class StrCharAtStartTest {

    @Test
    public void testCharAt() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        String orgStr = sb.toString();
        System.out.println(orgStr);

        int len = orgStr.length();

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            if (orgStr.charAt(0) == 'a'
                    && orgStr.charAt(1) == 'b'
                    && orgStr.charAt(2) == 'c') ;
            if (orgStr.charAt(len - 1) == 'a'
                    && orgStr.charAt(len - 2) == 'b'
                    && orgStr.charAt(len - 3) == 'c') ;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testCharAt: " + (endTime - begTime) + "ms");
    }

    @Test
    public void testStartwith() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        String orgStr = sb.toString();
        System.out.println(orgStr);

        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            orgStr.startsWith("abc");
            orgStr.endsWith("abc");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testStartwith: " + (endTime - begTime) + "ms");
    }

}
