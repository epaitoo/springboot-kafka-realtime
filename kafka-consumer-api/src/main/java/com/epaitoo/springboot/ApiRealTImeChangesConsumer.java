package com.epaitoo.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ApiRealTImeChangesConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRealTImeChangesConsumer.class);

    @KafkaListener(topics = "wikimedia-stream-api", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Event message: %s ", eventMessage));
    }
}
