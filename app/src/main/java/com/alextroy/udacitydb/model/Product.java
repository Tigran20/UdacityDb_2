package com.alextroy.udacitydb.model;

public class Product {

    private int id;
    private String name;
    private String price;
    private int quantity;

    public Product(int id, String name, String price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getId() {
        return id;
    }


}
