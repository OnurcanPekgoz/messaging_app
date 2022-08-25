package tr.com.argela.whatsapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.apache.kafka.clients.admin.NewTopic;;

@Configuration
public class KafkaTopicConfig {
    public NewTopic buildTopic(String name){
        return TopicBuilder.name(name).build();
    }
}