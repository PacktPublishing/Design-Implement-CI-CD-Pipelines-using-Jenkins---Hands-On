package com.mycompany.orderapp;

public class OrderService {
    public double calculateTotal(Order order) {
        return order.getQuantity() * order.getPrice();
    }
}
