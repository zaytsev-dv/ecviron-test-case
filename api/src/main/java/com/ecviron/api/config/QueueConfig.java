package com.ecviron.api.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "queue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueueConfig {
    private String produceEventTopicName;
}
