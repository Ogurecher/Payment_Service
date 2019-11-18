package service;

import dao.PaymentDAO;
import dto.OrderDTO;
import dto.UserDetailsDTO;
import entity.Payment;
import entity.enums.CardAuthorizationInfo;
import messaging.RabbitMQ;
import util.CustomLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class PaymentService {
    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private PaymentDAO paymentDAO;

    public PaymentService(PaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    public OrderDTO performPayment(long orderId, UserDetailsDTO userDetails) {
        logger.log(Level.INFO, "performPayment initiated");

        RabbitMQ rabbitMQ = new RabbitMQ();
        try {
            if(userDetails.getCardAuthorizationInfo() == CardAuthorizationInfo.AUTHORIZED) {
                rabbitMQ.broadcast(true, orderId);
            } else {
                rabbitMQ.broadcast(false, orderId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // push payment to payment database                                                                 async call
        Payment payment = new Payment(orderId, userDetails.getCardAuthorizationInfo(), userDetails.getUsername());
        paymentDAO.save(payment);

        return new OrderDTO(orderId, userDetails.getUsername());
    }
}
