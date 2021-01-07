package ru.itis.demo.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.SocialUserDto;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class SocialProducer {

    @Value("${exchange.help.name}")
    private String exchangeName;
    @Value("${exchange.help.type}")
    private String exchangeType;

    @Value("${material_help.queue.name}")
    private String materialHelpQueue;
    @Value("${social_food.queue.name}")
    private String socialFoodQueue;
    @Value("${transport.queue.name}")
    private String transportQueue;

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private ObjectMapper objectMapper;

    private Channel channel;

    @PostConstruct
    private void init() {
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            // создаем exchange
            channel.exchangeDeclare(exchangeName, exchangeType);
            channel.queueBind(materialHelpQueue, exchangeName, "");
            channel.queueBind(socialFoodQueue, exchangeName, "");
            channel.queueBind(transportQueue, exchangeName, "");
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public void produce(SocialUserDto socialUserDto) {
        try {
            String message = objectMapper.writeValueAsString(socialUserDto);
            //публикуем сообщение в exchange
            channel.basicPublish(exchangeName, "", null, message.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
