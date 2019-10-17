package service;

import dao.PaymentDAO;
import dto.OrderDTO;
import dto.UserDetailsDTO;
import entity.Payment;
import entity.enums.CardAuthorizationInfo;
import entity.enums.Status;
import util.CustomLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentService {
    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private PaymentDAO paymentDAO = new PaymentDAO();
    private OrderService orderService = new OrderService();
    private ItemService itemService = new ItemService();

    public OrderDTO performPayment(long orderId, UserDetailsDTO userDetails) {
        userDetails.getCardAuthorizationInfo();
        logger.log(Level.INFO, "performPayment initiated");

        // Order order = request -> OrderService: getOrderById(orderId)                                     sync call
        OrderDTO orderDTO = orderService.getOrderById(orderId);
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
        }

        // push payment to payment database                                                                 async call
        Payment payment = new Payment(orderId, userDetails.getCardAuthorizationInfo(), orderDTO.getUsername());
        paymentDAO.save(payment);

        return orderDTO;
    }
}
