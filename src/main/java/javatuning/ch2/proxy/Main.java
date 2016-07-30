package javatuning.ch2.proxy;

public class Main {

    public static void main(String args[]) {
        IDBQuery q = new DBQueryProxy();
        q.request();
    }

}
