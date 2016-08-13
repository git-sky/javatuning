package javatuning.ch3.tech;

import org.junit.Test;

public class TestArray {

    @Test
    public void test1Array() {
        long begTime = System.currentTimeMillis();
        int[] array = new int[1000000];
        int size = array.length;
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < size; i++)
                array[i] = i;

        int re = 0;
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < size; i++)
                re = array[i];
        long endTime = System.currentTimeMillis();
        System.out.println("test1Array: " + (endTime - begTime) + "ms");
    }

    @Test
    public void test1Array_size() {
        long begTime = System.currentTimeMillis();
        int[] array = new int[1000000];
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < array.length; i++)
                array[i] = i;

        int re = 0;
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < array.length; i++)
                re = array[i];
        long endTime = System.currentTimeMillis();
        System.out.println("test1Array_size: " + (endTime - begTime) + "ms");
    }

    @Test
    public void test2Array() {
        long begTime = System.currentTimeMillis();
        int[][] array = new int[1000][1000];
        int size = array.length;
        int size1 = array[0].length;
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size1; j++)
                    array[i][j] = i;

        int re = 0;
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < size; i++)
                for (int j = 0; j < size1; j++)
                    re = array[i][j];
        long endTime = System.currentTimeMillis();
        System.out.println("test2Array: " + (endTime - begTime) + "ms");
    }

    @Test
    public void test2Array_size() {
        long begTime = System.currentTimeMillis();
        int[][] array = new int[1000][1000];
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < array.length; i++)
                for (int j = 0; j < array[0].length; j++)
                    array[i][j] = i;

        int re = 0;
        for (int k = 0; k < 100; k++)
            for (int i = 0; i < array.length; i++)
                for (int j = 0; j < array[0].length; j++)
                    re = array[i][j];
        long endTime = System.currentTimeMillis();
        System.out.println("test2Array_size: " + (endTime - begTime) + "ms");
    }

}
