package javatuning.ch2.singleton;

public class Singleton {

    private static Singleton instance = new Singleton();

    private Singleton() {
        System.out.println("Singleton is create");
    }

    public static Singleton getInstance() {
        return instance;
    }

    public static void createString() {
        System.out.println("createString in Singleton");
    }

}