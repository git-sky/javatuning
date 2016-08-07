package javatuning.ch3.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class TestList {

    private static final int CIRCLE1 = 100000;

    private List list;

    protected void testAddTail(String funcName) {
        Object obj = new Object();
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < 500000; i++) {
            list.add(obj);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    protected void testDelTail(String funcName) {
        Object obj = new Object();
        for (int i = 0; i < CIRCLE1; i++) {
            list.add(obj);
        }

        long begTime = System.currentTimeMillis();
        while (list.size() > 0) {
            list.remove(list.size() - 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    protected void testDelFirst(String funcName) {
        Object obj = new Object();
        for (int i = 0; i < CIRCLE1; i++) {
            list.add(obj);
        }

        long begTime = System.currentTimeMillis();
        while (list.size() > 0) {
            list.remove(0);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    protected void testDelMiddle(String funcName) {
        Object obj = new Object();
        for (int i = 0; i < CIRCLE1; i++) {
            list.add(obj);
        }

        long begTime = System.currentTimeMillis();
        while (list.size() > 0) {
            list.remove(list.size() >> 1);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    protected void testAddFirst(String funcName) {
        Object obj = new Object();
        long begTime = System.currentTimeMillis();

        for (int i = 0; i < 50000; i++) {
            list.add(0, obj);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    //====add tail
    @Test
    public void testAddTailArrayList() {
        list = new ArrayList();
        testAddTail("testAddTailArrayList");
    }

    @Test
    public void testAddTailVector() {
        list = new Vector();
        testAddTail("testAddTailVector");
    }

    @Test
    public void testAddTailLinkedList() {
        list = new LinkedList();
        testAddTail("testAddTailLinkedList");
    }

    //====add first
    @Test
    public void testAddFirstArrayList() {
        list = new ArrayList();
        testAddFirst("testAddFirstArrayList");
    }

    @Test
    public void testAddFirstVector() {
        list = new Vector();
        testAddFirst("testAddFirstVector");
    }

    @Test
    public void testAddFirstLinkedList() {
        list = new LinkedList();
        testAddFirst("testAddFirstLinkedList");
    }

    //====delete tail
    @Test
    public void testDeleteTailArrayList() {
        list = new ArrayList();
        testDelTail("testDeleteTailArrayList");
    }

    @Test
    public void testDeleteTailVector() {
        list = new Vector();
        testDelTail("testDeleteTailVector");
    }

    @Test
    public void testDeleteTailLinkedList() {
        list = new LinkedList();
        testDelTail("testDeleteTailLinkedList");
    }

    //====delete first
    @Test
    public void testDeleteFirstArrayList() {
        list = new ArrayList();
        testDelFirst("testDeleteFirstArrayList");
    }

    @Test
    public void testDeleteFirstVector() {
        list = new Vector();
        testDelFirst("testDeleteFirstVector");
    }

    @Test
    public void testDeleteFirstLinkedList() {
        list = new LinkedList();
        testDelFirst("testDeleteFirstLinkedList");
    }

    //====delete middle
    @Test
    public void testDeleteMiddleLinkedList() {
        list = new LinkedList();
        testDelMiddle("testDeleteMiddleLinkedList");
    }

    @Test
    public void testDeleteMiddleArrayList() {
        list = new ArrayList();
        testDelMiddle("testDeleteMiddleArrayList");
    }

}
