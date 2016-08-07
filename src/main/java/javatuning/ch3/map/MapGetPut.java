package javatuning.ch3.map;

import org.junit.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapGetPut {

    private static final int CIRCLE1 = 100000;

    private Map map;

    protected void testGet(String funcName) {
        for (int i = 0; i < CIRCLE1; i++) {
            String key = Double.toString(Math.random());
            map.put(key, key);
        }
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE1; i++) {
            String key = Double.toString(Math.random());
            map.get(key);
        }
        long endTime = System.currentTimeMillis();
        System.out.println(funcName + ": " + (endTime - begTime) + "ms");
    }

    @Test
    public void testHashtableGet() {
        map = new Hashtable();
        testGet("testHashtableGet");
    }

    @Test
    public void testSyncHashMapGet() {
        map = Collections.synchronizedMap(new HashMap());
        testGet("testSyncHashMapGet");
    }

    @Test
    public void testHashMapGet() {
        map = new HashMap();
        testGet("testHashMapGet");
    }

}
