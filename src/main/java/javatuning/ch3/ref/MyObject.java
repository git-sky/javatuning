package javatuning.ch3.ref;

public class MyObject {

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("MyObject's finalize called");
    }

    @Override
    public String toString() {
        return "I am MyObject";
    }

    @Deprecated
    public static void main(String[] args) {
        System.out.println(new MyObject());
    }

}
