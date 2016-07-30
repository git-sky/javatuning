package javatuning.ch2.proxy.dynamic;

import javatuning.ch2.proxy.IDBQuery;

public class DBQuery implements IDBQuery {

    public DBQuery() {
    }

    @Override
    public String request() {
        return "request string";
    }

}
