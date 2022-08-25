package tr.com.argela.whatsapp.controller;

import java.util.concurrent.BlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import tr.com.argela.whatsapp.model.Message;
import tr.com.argela.whatsapp.service.KafkaConsumerService;

@RestController
public class ConsumerController {

    @Autowired
    KafkaConsumerService kafkaConsumerService;

    @GetMapping("/receive/{topicName}")
    public BlockingQueue<ConsumerRecord<String, Message>>  receiveMessage(@PathVariable String topicName){
        return kafkaConsumerService.consume(topicName);
    }
}
