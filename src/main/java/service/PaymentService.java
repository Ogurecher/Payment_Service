package service;

import dao.PaymentDAO;
import dto.OrderDTO;
import dto.UserDetailsDTO;
import entity.Payment;
import entity.enums.CardAuthorizationInfo;
import entity.enums.Status;

public class PaymentService {
    private PaymentDAO paymentDAO = new PaymentDAO();
    private OrderService orderService = new OrderService();
    private ItemService itemService = new ItemService();

    public OrderDTO performPayment(long orderId, UserDetailsDTO userDetails) {
        userDetails.getCardAuthorizationInfo();
        System.out.println("Payment Service: performPayment initiated");

        // Order order = request -> OrderService: getOrderById(orderId)
        OrderDTO orderDTO = orderService.getOrderById(orderId);
        if (orderDTO == null) {
            System.out.printf("Payment Service: no order returned from Order Service. There is no order with id: %d\n", orderId);
            return null;
        }
        System.out.printf("Payment Service: got order from Order Service by id: %d\n", orderId);

        if (userDetails.getCardAuthorizationInfo() == CardAuthorizationInfo.AUTHORIZED) {
            System.out.println("Payment Service: card authorized");
            // request -> OrderService: changeOrderStatus(orderId, Status.PAID);
            orderService.changeOrderStatus(orderId, Status.PAID);
            System.out.println("Payment Service: order status change requested: paid");
            // request -> ItemService: changeItemAmount(order.getOrderItems());
            itemService.changeItemAmount(orderDTO.getItems());
            System.out.println("Payment Service: item subtraction requested");
        } else {
            // request -> OrderService: changeOrderStatus(orderId, Status.FAILED);
            orderService.changeOrderStatus(orderId, Status.FAILED);
            System.out.println("Payment Service: order status change requested: failed");
            // request -> ItemService: releaseItem(order.getOrderItems());
            itemService.releaseItems(orderDTO.getItems());
            System.out.println("Payment Service: item release requested");
        }

        // push payment to payment database
        Payment payment = new Payment(orderId, userDetails.getCardAuthorizationInfo(), orderDTO.getUsername());
        paymentDAO.save(payment);
        System.out.printf("Payment Service: saved Payment{orderId: %d, cardAuthorizationInfo: %s, username: %s} to payment database\n", orderId,userDetails.getCardAuthorizationInfo().name(),orderDTO.getUsername());

        return orderDTO;
    }
}
