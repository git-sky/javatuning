package javatuning.ch5.gc;

import java.util.HashMap;

/**
 * test on dell 1950 server
 * <p/>
 * -Xmx512M -Xms512M  -XX:+UseParNewGC  -Xloggc:gc.log -XX:+PrintGCDetails
 * <p/>
 * 3203
 * <p/>
 * -Xmx512M -Xms512M  -XX:+UseParallelOldGC -XX:ParallelGCThreads=8 -Xloggc:gc.log
 * -XX:+PrintGCDetails
 * <p/>
 * 3593
 * <p/>
 * -Xmx512M -Xms512M -XX:+UseSerialGC -Xloggc:gc.log -XX:+PrintGCDetails
 * <p/>
 * 3360
 * <p/>
 * -Xmx512M -Xms512M -XX:+UseConcMarkSweepGC  -Xloggc:gc.log -XX:+PrintGCDetails
 * <p/>
 * 2516
 * <p/>
 * -XX:+UnlockExperimentalVMOptions -XX:+UseG1GC
 */
public class GCTimeTest {

    static HashMap map = new HashMap();

    public static void main(String args[]) {
        long begintime = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            if (map.size() * 512 / 1024 / 1024 >= 400) {
                map.clear();
                System.out.println("clean map");
            }
            byte[] b1;
            for (int j = 0; j < 100; j++) {
                b1 = new byte[512];
                map.put(System.nanoTime(), b1);
            }
        }
        long endtime = System.currentTimeMillis();
        System.out.println(endtime - begintime);
    }

}
