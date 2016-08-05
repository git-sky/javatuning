package javatuning.ch3.teststr.split.junit;

import org.junit.Test;

import java.util.StringTokenizer;

public class StringSplitTest {

    @Test
    public void testSplit_i() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        String orgStr = sb.toString();
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            orgStr.split(";");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testSplit_i: " + (endTime - begTime) + "ms");
    }

    @Test
    public void testSplit() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        String orgStr = sb.toString();
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            orgStr.split(";");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testSplit:" + (endTime - begTime) + "ms");
    }

    @Test
    public void testToken() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        String orgStr = sb.toString();
        long begTime = System.currentTimeMillis();
        StringTokenizer st = new StringTokenizer(orgStr, ";");
        //StringTokenizer st=orgs;
        for (int i = 0; i < 10000; i++) {
            while (st.hasMoreTokens()) {
                st.nextToken();
                //System.out.println(a);
            }
            st = new StringTokenizer(orgStr, ";");
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testToken: " + (endTime - begTime) + "ms");
    }

    @Test
    public void testIndexOf() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            sb.append(i);
            sb.append(";");
        }
        String orgStr = sb.toString();
        long begTime = System.currentTimeMillis();
        String tmp = orgStr;
        for (int i = 0; i < 10000; i++) {
            while (true) {
                int j = tmp.indexOf(';');
                if (j < 0) break;
                String splitStr = tmp.substring(0, j);
                tmp = tmp.substring(j + 1);
            }
            tmp = orgStr;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testIndexOf: " + (endTime - begTime) + "ms");
    }

}
