package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TestIterator {

    private static final int CIRCLE = 1000000;

    private List<String> list = null;

    public void initList(List<String> list) {
        list.clear();
        for (int i = 0; i < CIRCLE; i++) {
            list.add(String.valueOf(i));
        }
    }

    public void internalTest() {
        String tmp;
        long begTime = System.currentTimeMillis();
        for (String s : list) {
            tmp = s;
        }
        long endTime = System.currentTimeMillis();
        System.out.println("foreach spend: " + (endTime - begTime) + "ms");

        begTime = System.currentTimeMillis();
        for (Iterator<String> it = list.iterator(); it.hasNext(); ) {
            tmp = it.next();
        }
        endTime = System.currentTimeMillis();
        System.out.println("iterator spend: " + (endTime - begTime) + "ms");

        if (list instanceof LinkedList) return;

        begTime = System.currentTimeMillis();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            tmp = list.get(i);
        }
        endTime = System.currentTimeMillis();
        System.out.println("for spend: " + (endTime - begTime) + "ms");
    }

    @Test
    public void testArrayList() {
        list = new ArrayList<String>();
        initList(list);
        internalTest();
    }

    @Test
    public void testLinkedList() {
        list = new LinkedList<String>();
        initList(list);
        internalTest();
    }

}
