package com.ecviron.api.config;

import lombok.AllArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class KafkaConfig {

   private final QueueConfig queueConfig;

    @Bean
    public NewTopic topic() {
        return new NewTopic(queueConfig.getProduceEventTopicName(), 1, (short)1);
    }
}
