package ru.itis.demo.consumers.topic;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.demo.consumers.Consumer;
import ru.itis.demo.dto.GrantUserDto;
import ru.itis.demo.factories.PdfDocumentsFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class LetterOfGuaranteePdfGeneration extends Consumer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PdfDocumentsFactory pdfDocumentsFactory;
    @Value("${grant.all.queue}")
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
                    GrantUserDto grantUserDto = objectMapper.readValue(msg, GrantUserDto.class);
                    pdfDocumentsFactory.createLetterOfGuaranteePdf(grantUserDto);
                    System.out.println("");
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
