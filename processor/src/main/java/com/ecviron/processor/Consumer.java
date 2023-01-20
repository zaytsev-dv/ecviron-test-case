package com.ecviron.processor;

import com.ecviron.processor.dto.PostEventRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class Consumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @KafkaListener(topics = "${queue.produce-event-name}", groupId = "event-group")
    public void recievedMessage(byte[] msg) {
        PostEventRequest postEventRequest = objectMapper.readValue(msg, PostEventRequest.class);
        System.out.println("Recieved Message: " + postEventRequest);

        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.completeAsync(() -> {
            UUID uuid = null;
            try {
                uuid = UUID.randomUUID();
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
            log.info(String.format("Process with UUID: %s - is DONE", uuid));
            return "DONE";
        });
    }
}
