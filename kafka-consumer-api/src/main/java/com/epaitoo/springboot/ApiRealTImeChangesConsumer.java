package com.epaitoo.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Service
public class ApiRealTImeChangesConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRealTImeChangesConsumer.class);

    private ApiEventListener apiEventListener;

    public void register(ApiEventListener apiEventListener) {
        this.apiEventListener = apiEventListener;
    }

    public void onEvent(String event) {
        if (apiEventListener != null) apiEventListener.onData(event);
    }

    public void onComplete() {
        if (apiEventListener != null) apiEventListener.processComplete();
    }

    @KafkaListener(topics = "wikimedia-stream-api", groupId = "myGroup")
    public void consume(String eventMessage) {
        LOGGER.info(String.format("Event message: %s ", eventMessage));
        onEvent(eventMessage);
    }
}
