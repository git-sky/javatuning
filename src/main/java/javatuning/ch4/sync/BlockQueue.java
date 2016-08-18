package javatuning.ch4.sync;

import java.util.ArrayList;
import java.util.List;

public class BlockQueue {

    private List list = new ArrayList();

    public synchronized Object pop() throws InterruptedException {
        while (list.size() == 0) { //如果队列为空则等待
            this.wait();
        }
        if (list.size() > 0) {
            return list.remove(0); //队列不为空则返回第一个对象
        } else {
            return null;
        }
    }

    public synchronized void put(Object o) {
        list.add(o);   //增加对象到队列中
        this.notify(); //通知一个pop方法,可以取得数据
    }

}
