package service;

import dao.PaymentDAO;
import dto.OrderDTO;
import dto.UserDetailsDTO;
import entity.Payment;
import entity.enums.CardAuthorizationInfo;
import entity.enums.Status;
import messaging.RabbitMQ;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentService {
    private Logger logger;
    private PaymentDAO paymentDAO;
    private OrderService orderService;
    private ItemService itemService;

    public PaymentService(Logger logger, PaymentDAO paymentDAO, OrderService orderService, ItemService itemService) {
        this.logger = logger;
        this.paymentDAO = paymentDAO;
        this.orderService = orderService;
        this.itemService = itemService;
    }

    public OrderDTO performPayment(long orderId, UserDetailsDTO userDetails) {
        logger.log(Level.INFO, "performPayment initiated");

        // Order order = request -> OrderService: getOrderById(orderId)                                     sync call
        /*OrderDTO orderDTO = orderService.getOrderById(orderId);
        logger.log(Level.INFO, "Requested orderDTO by id = " + orderId);

        if (orderDTO == null) {
            logger.log(Level.INFO, "No orderDTO received ");
            return orderDTO;
        } else {
            logger.log(Level.INFO, "Got orderDTO " + orderDTO.toString());
        }

        if (userDetails.getCardAuthorizationInfo() == CardAuthorizationInfo.AUTHORIZED) {
            logger.log(Level.INFO, "Card authorized");
            // request -> OrderService: changeOrderStatus(orderId, Status.PAID);                            async call
            orderService.changeOrderStatus(orderId, Status.PAID);
            logger.log(Level.INFO, "Order status change requested. New status: " + Status.PAID);
            // request -> ItemService: changeItemAmount(order.getOrderItems());                             async call
            itemService.changeItemAmount(orderDTO.getItems());
            logger.log(Level.INFO, "Item subtraction requested");
        } else {
            // request -> OrderService: changeOrderStatus(orderId, Status.FAILED);                          async call
            orderService.changeOrderStatus(orderId, Status.FAILED);
            logger.log(Level.INFO, "Order status change requested. New status: " + Status.FAILED);
            // request -> ItemService: releaseItem(order.getOrderItems());                                  async call
            itemService.releaseItems(orderDTO.getItems());
            logger.log(Level.INFO, "Item release requested");
        }*/

        RabbitMQ rabbitMQ = new RabbitMQ();
        try {
            rabbitMQ.broadcast(userDetails.getCardAuthorizationInfo(), orderId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // push payment to payment database                                                                 async call
        Payment payment = new Payment(orderId, userDetails.getCardAuthorizationInfo(), userDetails.getUsername());
        paymentDAO.save(payment);

        return new OrderDTO(orderId, userDetails.getUsername());
    }
}
