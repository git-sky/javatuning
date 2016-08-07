package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * -Xmx512M -Xms512M
 */
public class TestListCapacity {

    protected List list;

    protected void testAddTail(String funcName) {
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    @Test
    public void testAddTailArrayList() {
        list = new ArrayList();
        testAddTail("testAddTailArrayList");
    }

    @Test
    public void testAddTailArrayListCapacity() {
        list = new ArrayList(1000000);
        testAddTail("testAddTailArrayListCapacity");
    }

}
