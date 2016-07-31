package javatuning.ch2.proxy;

public class DBQueryProxy implements IDBQuery {

    private DBQuery real = null;

    public DBQueryProxy() {
        System.out.println("DBQueryProxy is create");
    }

    @Override
    public String request() {
        if (real == null)
            real = new DBQuery();
        return real.request();
    }

}
