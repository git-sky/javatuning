package javatuning.ch3.collectionvisittuning;

import org.junit.Test;

import java.util.List;
import java.util.Vector;

public class TestCollectionVisitTuning extends Vector {

    static List collection = new Vector();

    static {
        collection.add("north65");
        collection.add("west20");
        collection.add("east30");
        collection.add("south40");
        collection.add("west33");
        collection.add("south20");
        collection.add("north10");
        collection.add("east9");
    }

    public TestCollectionVisitTuning() {
        this.add("north65");
        this.add("west20");
        this.add("east30");
        this.add("south40");
        this.add("west33");
        this.add("south20");
        this.add("north10");
        this.add("east9");
    }

    @Test
    public void testCount() {
        long begTime = System.nanoTime();
        int count = 0;
        for (int i = 0; i < collection.size(); i++) {
            if ((((String) collection.get(i)).indexOf("north") != -1)
                    || (((String) collection.get(i)).indexOf("west") != -1)
                    || (((String) collection.get(i)).indexOf("south") != -1))
                count++;
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

    @Test
    public void testCount1() {
        long begTime = System.nanoTime();
        int count = 0;
        int collectionSize = collection.size();
        for (int i = 0; i < collectionSize; i++) {
            if ((((String) collection.get(i)).indexOf("north") != -1)
                    || (((String) collection.get(i)).indexOf("west") != -1)
                    || (((String) collection.get(i)).indexOf("south") != -1))
                count++;
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

    @Test
    public void testCount2() {
        long begTime = System.nanoTime();
        int count = 0;
        String s = null;
        int collectionSize = collection.size();
        for (int i = 0; i < collectionSize; i++) {
            if (((s = (String) collection.get(i)).indexOf("north") != -1)
                    || (s.indexOf("west") != -1)
                    || (s.indexOf("south") != -1))
                count++;
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

    @Test
    public void testCount2_5() {
        long begTime = System.nanoTime();
        int count = 0;
        String s = null;
        int collectionSize = this.elementCount;
        for (int i = 0; i < collectionSize; i++) {
            if (((s = (String) this.get(i)).indexOf("north") != -1)
                    || (s.indexOf("west") != -1)
                    || (s.indexOf("south") != -1))
                count++;
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

    @Test
    public void testCount3() {
        long begTime = System.nanoTime();
        int count = 0;
        String s = null;
        int collectionSize = this.elementCount;
        for (int i = 0; i < collectionSize; i++) {
            if (((s = (String) elementData[i]).indexOf("north") != -1)
                    || (s.indexOf("west") != -1)
                    || (s.indexOf("south") != -1))
                count++;
        }
        long endTime = System.nanoTime();
        System.out.println(endTime - begTime);
    }

}
