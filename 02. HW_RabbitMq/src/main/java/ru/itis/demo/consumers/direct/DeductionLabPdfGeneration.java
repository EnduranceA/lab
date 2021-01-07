package ru.itis.demo.consumers.direct;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.itis.demo.consumers.Consumer;
import ru.itis.demo.dto.LabUserDto;
import ru.itis.demo.factories.PdfDocumentsFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

//consumer для генерации заявления об отчислении из лаборатории
@Component
public class DeductionLabPdfGeneration extends Consumer {

    @Value("${deduction.queue.name}")
    private String queue;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private PdfDocumentsFactory pdfDocumentsFactory;

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
                    LabUserDto labUserDto = objectMapper.readValue(msg, LabUserDto.class);
                    pdfDocumentsFactory.createDeductionLabPdf(labUserDto);
                    System.out.println("Заявление на отчисление из лаборатории сформировано");
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

