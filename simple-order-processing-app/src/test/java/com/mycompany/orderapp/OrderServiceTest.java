package com.mycompany.orderapp;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTest {

    @Test
    public void testTotal() {
        OrderService service = new OrderService();
        Order order = new Order(1001, 3, 100.0);

        assertEquals(300.0, service.calculateTotal(order));
    }
}
