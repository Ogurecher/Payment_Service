import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receive_ItemService {
    private static final String EXCHANGE_NAME = "performPayment";                                           // you'll have one of these for each exchange type (== each message type)
    private static final String QUEUE_NAME = "ItemService";                                                 // don't change

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");                                               // change fanout to direct if required
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);      // once
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");                                           // once for every exchange

        System.out.println(" [" + QUEUE_NAME + "] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME + "] Received '" + message + "'");
            try {
                                                                                                                // check message type -> do stuff
            } finally {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);                       // send acknowledgement
            }
        };
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> { });
    }
}
