package com.epaitoo.springboot;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class ApiRealTImeChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiRealTImeChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public ApiRealTImeChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia-stream-api";

        BackgroundEventHandler backgroundEventHandler = new ApiRealTImeChangesHandler(kafkaTemplate, topic);
        String url = "https://stream.wikimedia.org/v2/stream/recentchange";
        EventSource.Builder eventSourceBuilder = new EventSource.Builder(URI.create(url));
        BackgroundEventSource.Builder backgroundEventSourceBuilder = new BackgroundEventSource.Builder(backgroundEventHandler, eventSourceBuilder);
        backgroundEventSourceBuilder.threadPriority(Thread.NORM_PRIORITY);
        BackgroundEventSource backgroundEventSource = backgroundEventSourceBuilder.build();
        backgroundEventSource.start();

        TimeUnit.MINUTES.sleep(8);

    }
}
