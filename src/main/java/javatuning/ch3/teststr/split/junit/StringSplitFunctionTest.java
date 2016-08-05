package javatuning.ch3.teststr.split.junit;

import org.junit.Test;

public class StringSplitFunctionTest {

    @Test
    public void testSplit() {
        String str = "a;b,c:d";
        String re[] = str.split("[;|,|:]");
        for (String s : re) {
            System.out.println(s);
        }
    }

}
