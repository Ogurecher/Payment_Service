package service;

import dto.OrderDTO;
import dto.UserDetailsDTO;
import entity.Order;
import entity.enums.CardAuthorizationInfo;

public class PaymentService {

    public OrderDTO performPayment(long orderId, UserDetailsDTO userDetails) {

        // Order order = request -> OrderService: getOrderById(orderId)

        if (userDetails.getCardAuthorizationInfo() == CardAuthorizationInfo.AUTHORIZED) {
            // request -> OrderService: changeOrderStatus(orderId, PAID);
        } else {
            // request -> OrderService: changeOrderStatus(orderId, FAILED);
            // for (orderItem in order) {
            //      request -> ItemService: releaseItem(item.getId(), item.getAmount());
            // }
        }

        // push paymentDTO to payment database

        Order order = new Order(orderId, "placeholder_username");
        return new OrderDTO(order);
    }
}
