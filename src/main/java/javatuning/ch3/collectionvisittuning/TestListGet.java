package javatuning.ch3.collectionvisittuning;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TestListGet {

    private static final int CIRCLE1 = 100;

    private List list;

    protected void initList() {
        for (int i = 0; i < 10000; i++)
            list.add(i);
    }

    protected void testGet(String funcName) {
        int s = list.size();
        long begTime = System.currentTimeMillis();
        for (int k = 0; k < CIRCLE1; k++) {
            for (int i = 0; i < s; i++)
                list.get(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime));
    }

    @Test
    public void testArrayList() {
        list = new ArrayList();
        initList();
        testGet("testArrayList");
    }

    @Test
    public void testLinkedList() {
        list = new LinkedList();
        initList();
        testGet("testLinkedList");
    }

}
