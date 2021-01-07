package components.consumers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.json.simple.JSONObject;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

//consumer для формирования PDF-увольнение
public class DismissalPdfConsumer extends Consumer {

    public void consume() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            // создаем временную очередь со случайным названием
            String queue = channel.queueDeclare().getQueue();

            // привязали очередь к EXCHANGE_NAME
            channel.queueBind(queue, EXCHANGE_NAME, "");

            DeliverCallback deliverCallback = (consumerTag, message) -> {
                try {
                    String msg = new String(message.getBody());
                    System.out.println("Получены данные пользователя");
                    JSONObject object = jsonHelper.getUser(msg);
                    pdfGenerationHelper.createDismissalPdf(object);
                    System.out.println("Заявление на увольнение сформировано");
                    //подтверждение выполнения сообщения
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            };
            channel.basicConsume(queue, false, deliverCallback, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
