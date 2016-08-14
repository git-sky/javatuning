package javatuning.ch4.future.pattern;

public class Main {

    public static void main(String[] args) {
        Client client = new Client();

        Data data = client.request("a");
        System.out.println("请求完毕");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("数据 = " + data.getResult());
    }

}
