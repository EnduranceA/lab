package ru.itis.demo.consumers;

import com.rabbitmq.client.ConnectionFactory;

public abstract class Consumer {

    public static ConnectionFactory connectionFactory;

    public Consumer() {
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("localhost");
    }

    public abstract void startConsuming();
}
