package javatuning.ch2.singleton;

public class SingletonFactory {

    private static SingletonFactory instance = new SingletonFactory();

    private SingletonFactory() {
        System.out.println("Singleton is create");
    }

    public static SingletonFactory getInstance() {
        return instance;
    }

}