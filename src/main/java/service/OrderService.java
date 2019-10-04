package service;

import entity.Order;
import entity.enums.Status;

public class OrderService {
    public Order getOrderById(long id) {
        return new Order("user");
    }

    public void changeOrderStatus(long id, Status status) {
        System.out.printf("Order Service: changed status of order %d to %s\n", id, status.name());
    }
}
