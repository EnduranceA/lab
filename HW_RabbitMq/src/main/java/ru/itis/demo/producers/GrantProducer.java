package ru.itis.demo.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.GrantUserDto;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class GrantProducer {

    @Value("${exchange.grant.name}")
    private String exchangeName;
    @Value("${exchange.grant.type}")
    private String exchangeType;

    @Value("${grant_all.binding.key}")
    private String grantAllBindingKey;
    @Value("${grant_first.binding.key}")
    private String grantFirstBindingKey;

    @Value("${grant.all.queue}")
    private String queueAllName;
    @Value("${grant.first.queue}")
    private String queueFirstCourseName;

    @Autowired
    private ConnectionFactory connectionFactory;
    @Autowired
    private ObjectMapper objectMapper;

    private Channel channel;

    @PostConstruct
    public void init() {
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            // создаем exchange
            channel.exchangeDeclare(exchangeName, exchangeType);
            // привязали очереди под определенным bindingKey
            channel.queueBind(queueAllName, exchangeName, grantAllBindingKey);
            channel.queueBind(queueFirstCourseName, exchangeName, grantFirstBindingKey);
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void produce(GrantUserDto grantUserDto, String routingKey) {
        try {
            String message = objectMapper.writeValueAsString(grantUserDto);
            //публикуем сообщение в exchange
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
