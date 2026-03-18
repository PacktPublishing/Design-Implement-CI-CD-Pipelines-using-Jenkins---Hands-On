// Add new comment to test webhook
package com.mycompany.orderapp;

public class App {
    public static void main(String[] args) {
        OrderService service = new OrderService();
        Order order = new Order(1001, 5, 299.0);

        System.out.println("Processing Order...");
        System.out.println("Order Total = " + service.calculateTotal(order));
    }
}
