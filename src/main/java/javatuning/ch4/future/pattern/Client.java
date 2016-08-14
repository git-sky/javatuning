package javatuning.ch4.future.pattern;

public class Client {

    public Data request(final String queryStr) {
        final FutureData future = new FutureData();

        new Thread() {
            public void run() {
                RealData realdata = new RealData(queryStr);
                future.setRealData(realdata);
            }
        }.start();

        return future;
    }

}
