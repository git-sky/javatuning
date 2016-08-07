package javatuning.ch3.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class MapHashcodeTest {

    public static class GoodHash {
        double d;

        public GoodHash(double d) {
            this.d = d;
        }
    }

    public static class BadHash {
        double d;

        public BadHash(double d) {
            this.d = d;
        }

        @Override
        public int hashCode() {
            return 1;
        }
    }

    private static final int CIRCLE1 = 10000;
    private Map map;

    @Test
    public void testGetGoodHash() {
        map = new HashMap();
        for (int i = 0; i < CIRCLE1; i++) {
            GoodHash key = new GoodHash(Math.random());
            map.put(key, key);
        }
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE1; i++) {
            GoodHash key = new GoodHash(Math.random());
            map.get(key);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testGetGoodHash" + ": " + (endTime - begTime) + "ms");
    }

    @Test
    public void testGetBadHash() {
        map = new HashMap();
        for (int i = 0; i < CIRCLE1; i++) {
            BadHash key = new BadHash(Math.random());
            map.put(key, key);
        }
        long begTime = System.currentTimeMillis();
        for (int i = 0; i < CIRCLE1; i++) {
            BadHash key = new BadHash(Math.random());
            map.get(key);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("testGetBadHash" + ": " + (endTime - begTime) + "ms");
    }

}
