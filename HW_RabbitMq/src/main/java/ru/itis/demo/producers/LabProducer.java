package ru.itis.demo.producers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.demo.dto.LabUserDto;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class LabProducer {

    @Value("${exchange.lab.name}")
    private String exchangeName;
    @Value("${exchange.lab.type}")
    private String exchangeType;

    @Value("${enrollment.binding.key}")
    private String enrollmentBindingKey;
    @Value("${enrollment.queue.name}")
    private String enrollmentQueue;

    @Value("${deduction.queue.name}")
    private String deductionQueue;
    @Value("${deduction.binding.key}")
    private String deductionBindingKey;

    private Channel channel;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    private void init() {
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
            channel = connection.createChannel();
            // создаем exchange
            channel.exchangeDeclare(exchangeName, exchangeType);
            // привязали очереди под определенным routingKey
            channel.queueBind(deductionQueue, exchangeName, deductionBindingKey);
            channel.queueBind(enrollmentQueue, exchangeName, enrollmentBindingKey);
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void produce(LabUserDto labUserDto, String routingKey) {
        try {
            String message = objectMapper.writeValueAsString(labUserDto);
            //публикуем сообщение в exchange
            channel.basicPublish(exchangeName, routingKey, null, message.getBytes());
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


}
