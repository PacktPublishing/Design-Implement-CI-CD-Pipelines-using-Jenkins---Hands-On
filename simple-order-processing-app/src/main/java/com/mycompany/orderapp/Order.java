package com.mycompany.orderapp;

public class Order {
    private int id;
    private int quantity;
    private double price;

    public Order(int id, int quantity, double price) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
    }

    public int getQuantity() { return quantity; }
    public double getPrice() { return price; }
}
