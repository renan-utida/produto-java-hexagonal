package Modelo;

public class Product {
    private String name;
    private double price;
    private final String code;

    public Product(String code, String name, double price) {
        this.name = name;
        this.price = price;
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}