package tr.com.argela.whatsapp.service;

import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;
import org.springframework.kafka.listener.KafkaMessageListenerContainer;
import org.springframework.kafka.listener.MessageListener;
import org.springframework.stereotype.Service;

import tr.com.argela.whatsapp.config.KafkaConsumerConfig;
import tr.com.argela.whatsapp.model.Message;

@Service
public class KafkaConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KafkaProducerService.class);
    @Autowired
    KafkaConsumerConfig kafkaConsumerConfig;

    public BlockingQueue<ConsumerRecord<String, Message>>  consume(String topicName) {
        Map<String, Object> consumerProps = kafkaConsumerConfig.consumerConfig();
        DefaultKafkaConsumerFactory<String, Message> cf = new DefaultKafkaConsumerFactory<>(consumerProps);
        ContainerProperties containerProperties = new ContainerProperties(topicName);
        KafkaMessageListenerContainer container = new KafkaMessageListenerContainer<>(cf, containerProperties);
        final BlockingQueue<ConsumerRecord<String, Message>> records = new LinkedBlockingQueue<>();
        container.setupMessageListener((MessageListener<String, Message>) record -> {
            logger.error("Message received: " + record);
            records.add(record);
        });
        container.start();
        return records;
    }
}
