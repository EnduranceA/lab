package components.producers;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import dto.UserDto;
import helpers.JsonHelper;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class DocumentsProducer {

    // есть exchange "documents"
    private final static String EXCHANGE_NAME = "documents";
    // тип exchange - fanout
    private final static String EXCHANGE_TYPE = "fanout";

    private ConnectionFactory connectionFactory;

    public DocumentsProducer(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
        produce();
    }

    public void produce() {
        try {
            Connection connection = connectionFactory.newConnection();
            Channel channel = connection.createChannel();
            // создаем exchange
            channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);
            UserDto userDto = createUserDto();
            String message = JsonHelper.buildJsonFromUser(userDto);
            //публикуем сообщение в exchange
            channel.basicPublish(EXCHANGE_NAME, "",null, message.getBytes());
        } catch (IOException | TimeoutException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public UserDto createUserDto() {
        Scanner sc = new Scanner(System.in);
        UserDto dto = UserDto.builder().build();
        System.out.println("Введите Ваше имя:");
        dto.setFirstName(sc.nextLine());
        System.out.println("Введите Вашу фамилию:");
        dto.setLastName(sc.nextLine());
        System.out.println("Введите дату выдачи паспорта:");
        dto.setDate(sc.nextLine());
        System.out.println("Введите Ваш возраст:");
        dto.setAge(sc.nextInt());
        return dto;
    }


}