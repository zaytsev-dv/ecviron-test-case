package com.ecviron.api.producers;

import com.ecviron.api.config.QueueConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import ecviron.generated.swaggerCodegen.model.PostEventRequest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public final class EventProducer {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final QueueConfig queueConfig;
    private final ObjectMapper objectMapper;

    public EventProducer(KafkaTemplate<String, byte[]> kafkaTemplate, QueueConfig queueConfig) {
        this.kafkaTemplate = kafkaTemplate;
        this.queueConfig = queueConfig;
        this.objectMapper = new ObjectMapper();
    }

    @SneakyThrows
    public final void produce(final PostEventRequest request) {
        log.info("send: " + request);
        kafkaTemplate.send(queueConfig.getProduceEventTopicName(), this.objectMapper.writeValueAsBytes(request));
    }
}
