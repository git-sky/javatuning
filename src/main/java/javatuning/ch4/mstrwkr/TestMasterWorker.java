package javatuning.ch4.mstrwkr;

import org.junit.Test;

import java.util.Map;
import java.util.Set;

public class TestMasterWorker {

    public class PlusWorker extends Worker {
        @Override
        public Object handle(Object input) {
            Integer i = (Integer) input;
            return i * i * i;
        }
    }

    @Test
    public void testMasterWorker() {
        //固定使用5个worker,并指定worker
        Master m = new Master(new PlusWorker(), 5);
        for (int i = 0; i < 100; i++) m.submit(i); //提交100个子任务
        //开始计算
        m.execute();
        //最终计算结果保存在此
        int re = 0;
        Map<String, Object> resultMap = m.getResultMap();
        //不需要等待所有worker都执行完,即可开始计算最终结果
        while (resultMap.size() > 0 || !m.isComplete()) {
            Set<String> keys = resultMap.keySet();
            String key = null;
            for (String k : keys) {
                key = k;
                break;
            }
            Integer i = null;
            if (key != null) i = (Integer) resultMap.get(key);
            if (i != null) re += i;                 //最终结果
            if (key != null) resultMap.remove(key); //移除已经被计算过的项
        }

        System.out.println("testMasterWorker:" + re);
    }

    @Test
    public void testPlus() {
        int re = 0;
        for (int i = 0; i < 100; i++) {
            re += i * i * i;
        }
        System.out.println("testPlus:" + re);
    }

}
