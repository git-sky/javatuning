package javatuning.ch3.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap {

    private Map<String, String> map;

    public void testOutputMap(String funcName) {
        map.put("1", "aa");
        map.put("2", "bb");
        map.put("3", "cc");
        map.put("4", "dd");
        map.get("3");
        for (Iterator iterator = map.keySet().iterator(); iterator.hasNext(); ) {
            String name = (String) iterator.next();
            System.out.println(funcName + ": " + name + "->" + map.get(name));
            // System.out.println(name);
        }
    }

    @Test
    public void testLinkedHashMap() {
        map = new LinkedHashMap<String, String>(16, 0.75f, true);
        testOutputMap("LinkedHashMap_false");
    }

    @Test
    public void testHashMap() {
        map = new HashMap<String, String>();
        testOutputMap("testHashMap");
    }

}
