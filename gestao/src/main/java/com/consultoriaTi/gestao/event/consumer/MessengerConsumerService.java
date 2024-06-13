package com.consultoriaTi.gestao.event.consumer;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class MessengerConsumerService {

    public record ConsumeDTO(Channel channel, DeliverCallback deliverCallback) {}
    public void directConsumer() {

        try {
            consume().channel.basicConsume("Queue-1", true, consume().deliverCallback, consumerTag -> { });
            consume().channel.basicConsume("Aprovado", true, consume().deliverCallback, consumerTag -> { });
            consume().channel.basicConsume("Reprovado", true, consume().deliverCallback, consumerTag -> { });
            consume().channel.basicConsume("Empate", true, consume().deliverCallback, consumerTag -> { });
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public void consumerAfterPublisher(String queueName) {
        try {
            consume().channel.basicConsume(queueName, true, consume().deliverCallback, consumerTag -> { });
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    public ConsumeDTO consume() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        Connection connection = null;

        connection = factory.newConnection();

        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" Message received '" + message) ;
        };

        return new ConsumeDTO(channel, deliverCallback);

    }

//    @RabbitListener(queues = "Aprovado")
//    public void receiveMessageAprovado(String message) {
//        System.out.println("Received Message: " + message);
//    }
//    @RabbitListener(queues = "Reprovado")
//    public void receiveMessageReprovado(String message) {
//        System.out.println("Received Message: " + message);
//    }
//    @RabbitListener(queues = "Empate")
//    public void receiveMessageEmpate(String message) {
//        System.out.println("Received Message: " + message);
//    }

}