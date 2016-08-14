package javatuning.ch4.gs.simple;

import java.util.LinkedList;

public class RequestQueue {

    private LinkedList queue = new LinkedList();

    public synchronized Request getRequest() {
        while (queue.size() == 0) {
            try {
                wait(); //等待直到有新的Request加入
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return (Request) queue.remove(); //返回Request队列中的第一个请求
    }

    public synchronized void addRequest(Request request) {
        queue.add(request); //加入新的Request请求
        notifyAll();        //通知getRequest()方法
    }

}
