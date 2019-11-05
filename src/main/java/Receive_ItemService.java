import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

public class Receive_ItemService {
    private static final String EXCHANGE_NAME = "performPayment";
    private static final String QUEUE_NAME = "ItemService";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        channel.queueDeclare(QUEUE_NAME, true, false, true, null);
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        System.out.println(" [" + QUEUE_NAME + "] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [" + QUEUE_NAME + "] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
