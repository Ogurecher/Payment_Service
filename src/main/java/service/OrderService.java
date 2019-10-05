package service;

import dto.OrderDTO;
import entity.enums.Status;

public class OrderService {
    public OrderDTO getOrderById(long id) {
        return new OrderDTO("user");
    }

    public void changeOrderStatus(long id, Status status) {
        System.out.printf("Order Service: changed status of order %d to %s\n", id, status.name());
    }
}
