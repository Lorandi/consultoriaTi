package com.consultoriaTi.gestao.event.producer;

import com.consultoriaTi.gestao.event.consumer.MessengerConsumerService;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class MessengerPublisherService {
//    private final MessengerConsumerService consumerService;
//
//    public record PublisherDTO(Channel channel,Connection connection, JSONObject resultJson) {}
//
//        public void send(String message) {
//
//            try {
//            ConnectionFactory factory = new ConnectionFactory();
//            Connection connection = factory.newConnection();
//            Channel channel = connection.createChannel();
//
//            channel.basicPublish("", "Queue-1", null, message.getBytes());
//
//            channel.close();
//            connection.close();
//            } catch (IOException | TimeoutException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        public void propagateResult(ResultDTO resultDTO)  {
//            try {
//                PublisherDTO publisher = publisher(resultDTO);
//
//                publisher.channel.basicPublish("", "Queue-1", null, publisher.resultJson.toString().getBytes());
//
//                publisher.channel.close();
//                publisher.connection.close();
//            } catch (IOException | TimeoutException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        public void directPublisher(ResultDTO resultDTO){
//            try {
//                PublisherDTO publisher = publisher(resultDTO);
//
//                publisher.channel.basicPublish("Direct-Exchange",  resultDTO.result(), null, publisher.resultJson.toString().getBytes());
//
//                publisher.channel.close();
//                publisher.connection.close();
//            } catch (IOException | TimeoutException e) {
//                throw new RuntimeException(e);
//            }
//
//        }
//
//    public void fanoutPublisher(ResultDTO resultDTO){
//        try {
//            PublisherDTO publisher = publisher(resultDTO);
//
//            publisher.channel.basicPublish("Fanout-Exchange",  "", null, publisher.resultJson.toString().getBytes());
//
//            publisher.channel.close();
//            publisher.connection.close();
//        } catch (IOException | TimeoutException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void topicPublisher(ResultDTO resultDTO){
//        try {
//            PublisherDTO publisher = publisher(resultDTO);
//
//            publisher.channel.basicPublish("Topic-Exchange",  "aprovado.reprovado.empate", null, publisher.resultJson.toString().getBytes());
//
//            publisher.channel.close();
//            publisher.connection.close();
//        } catch (IOException | TimeoutException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    public void headersPublisher(ResultDTO resultDTO){
//        try {
//            PublisherDTO publisher = publisher(resultDTO);
//
//            Map<String,Object> headersMap = new HashMap<String,Object>();
//            headersMap.put("item1", "aprovado");
//            headersMap.put("item2", "empate");
//
//            AMQP.BasicProperties br = new AMQP.BasicProperties();
//            br = br.builder().headers(headersMap).build();
//
//            publisher.channel.basicPublish("Headers-Exchange",  "", br, publisher.resultJson.toString().getBytes());
//
//            publisher.channel.close();
//            publisher.connection.close();
//        } catch (IOException | TimeoutException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//
//
//    public PublisherDTO publisher(ResultDTO resultDTO) throws IOException, TimeoutException {
//
//        ConnectionFactory factory = new ConnectionFactory();
//        Connection connection = null;
//
//        connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        JSONObject resultJson = new JSONObject();
//        resultJson.put("surveyId", resultDTO.survey().id());
//        resultJson.put("question", resultDTO.survey().question());
//        resultJson.put("result", resultDTO.result());
//
//        return new PublisherDTO(channel, connection, resultJson);
//
//    }



}
