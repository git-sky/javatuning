package javatuning.ch4.gs.future;

import javatuning.ch4.future.pattern.FutureData;
import javatuning.ch4.future.pattern.RealData;


public class ServerThread extends Thread {

    private RequestQueue requestQueue;

    public ServerThread(RequestQueue requestQueue, String name) {
        super(name);
        this.requestQueue = requestQueue;
    }

    @Override
    public void run() {
        while (true) {
            final Request request = requestQueue.getRequest();
            final FutureData future = (FutureData) request.getResponse();
            RealData realdata = new RealData(request.getName());
            future.setRealData(realdata);
            System.out.println(Thread.currentThread().getName() + " handles  " + request);
        }
    }

}
