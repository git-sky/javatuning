package javatuning.ch3.collectionvisittuning;

import org.junit.Test;

import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import java.util.Vector;

public class TestRandomAccess {

    private List list = null;

    @Test
    public void testVector() {
        list = new Vector();
        initList();
        testRandomAccess("testVector");
        testIterator();
    }

    public void initList() {
        for (int i = 0; i < 1000; i++)
            list.add(i);
    }

    public void testIterator() {
        long begTime = System.nanoTime();
        for (int k = 0; k < 10000; k++) {
            Object o;
            Iterator itr = list.iterator();
            for (int i = 0, n = list.size(); i < n; i++) {
                o = itr.next();
            }
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

    public void testRandomAccess(String name) {
        long begTime = System.nanoTime();
        for (int k = 0; k < 10000; k++) {
            Object o;
            if (list instanceof RandomAccess) {
                for (int i = 0, n = list.size(); i < n; i++) {
                    o = list.get(i);
                }
            } else {
                Iterator itr = list.iterator();
                for (int i = 0, n = list.size(); i < n; i++) {
                    o = itr.next();
                }
            }
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

}
