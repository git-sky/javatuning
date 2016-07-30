package javatuning.ch3.list;

import org.junit.Test;

import java.util.LinkedList;

public class TestLinkedList {

    @Test
    public void test() {
        LinkedList l = new LinkedList();
        l.add(1);
        l.add(2);
        l.add(3);
        System.out.println(l);
    }

}
