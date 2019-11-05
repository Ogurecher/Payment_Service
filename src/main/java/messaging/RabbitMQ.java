package messaging;

import com.google.gson.JsonObject;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import entity.enums.CardAuthorizationInfo;
import util.CustomLogger;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RabbitMQ {
    private final static Logger logger = Logger.getLogger(CustomLogger.class.getName());
    private final static String EXCHANGE_NAME = "performPayment";

    public void broadcast(CardAuthorizationInfo cardAuthorizationInfo, long orderId) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

            JsonObject json = new JsonObject();
            json.addProperty("type", "performPayment");
            json.addProperty("cardAuthorizationInfo", cardAuthorizationInfo.toString());
            json.addProperty("orderId", orderId);

            String message = json.toString();

            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
            logger.log(Level.INFO," [Payment Service] Sent '" + message + "'");
        }
    }
}
