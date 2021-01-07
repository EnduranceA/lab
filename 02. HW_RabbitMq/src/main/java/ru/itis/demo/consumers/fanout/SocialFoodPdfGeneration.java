package ru.itis.demo.consumers.fanout;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.demo.consumers.Consumer;
import ru.itis.demo.dto.SocialUserDto;
import ru.itis.demo.factories.PdfDocumentsFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

//consumer для генерации заявления на социальное питание
@Component
public class SocialFoodPdfGeneration extends Consumer {

    @Autowired
    private PdfDocumentsFactory pdfDocumentsFactory;
    @Autowired
    private ObjectMapper objectMapper;

    @Value("${social_food.queue.name}")
    private String queue;

    @PostConstruct
    public void init(){
        startConsuming();
    }

    @Override
    public void startConsuming() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            channel.queueDeclare(queue, true, false, true, null);
            channel.basicConsume(queue, false, (consumerTag, message) -> {
                try {
                    String msg = new String(message.getBody());
                    SocialUserDto socialUserDto = objectMapper.readValue(msg, SocialUserDto.class);
                    pdfDocumentsFactory.createSocialFoodPdf(socialUserDto);
                    System.out.println("Заявление на получение соц. питания сформировано");
                    //подтверждение выполнения сообщения
                    channel.basicAck(message.getEnvelope().getDeliveryTag(), false);
                } catch (IOException e) {
                    System.err.println("FAILED");
                    channel.basicReject(message.getEnvelope().getDeliveryTag(), false);
                }
            }, consumerTag -> {});
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
