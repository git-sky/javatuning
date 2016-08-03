package javatuning.ch2.timespace;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * -Xmx512M -Xms512M
 */
public class SpaceSort {

    public static int arrayLen = 1000000;

    public static void main(String[] args) {
        int[] a = new int[arrayLen];
        int[] old = new int[arrayLen];
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        int count = 0;
        while (count < a.length) {
            int value = (int) (Math.random() * arrayLen * 10) + 1;
            if (map.get(value) == null) {
                map.put(value, value);
                a[count] = value;
                count++;
            }
        }
        System.arraycopy(a, 0, old, 0, a.length);
        long beg = System.currentTimeMillis();
        Arrays.sort(a);
        long end = System.currentTimeMillis();
        System.out.println("Arrays.sort spend:" + (end - beg) + " ms");
        // outputArray(a);
        System.arraycopy(old, 0, a, 0, old.length);
        beg = System.currentTimeMillis();
        spaceToTime(a);
        end = System.currentTimeMillis();
        System.out.println("spaceToTime spend:" + (end - beg) + " ms");
        // outputArray(a);
    }

    public static void outputArray(int[] a) {
        for (int anA : a) {
            System.out.print(anA + " ");
        }
        System.out.println();
    }

    public static void spaceToTime(int[] array) {
        int i = 0;
        int max = array[i];
        int l = array.length;
        for (i = 1; i < l; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        int[] temp = new int[max + 1];
        for (i = 0; i < l; i++) {
            temp[array[i]] = array[i];
        }
        int j = 0;
        int max1 = max + 1;
        for (i = 0; i < max1; i++) {
            if (temp[i] > 0) {
                array[j++] = temp[i];
            }
        }
    }

}
