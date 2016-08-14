package javatuning.ch4.immutable;

public final class Product {    //确保无子类

    private final String no;    //私有属性,不会被其它对象获取,final保证属性不会被2次赋值
    private final String name;
    private final double price;

    public Product(String no, String name, double price) { //在创建对象时,必须指定数据,因为创建之后无法进行修改
        super();
        this.no = no;
        this.name = name;
        this.price = price;
    }

    public String getNo() {
        return no;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

}
