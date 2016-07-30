package javatuning.ch2.singleton;

public class LazySingleton {

    private static LazySingleton instance = null;

    private LazySingleton() {
        System.out.println("LazySingleton is create");
    }

    public static synchronized LazySingleton getInstance() {
        if (instance == null)
            instance = new LazySingleton();
        return instance;
    }

}