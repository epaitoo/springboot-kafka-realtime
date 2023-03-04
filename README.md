# Springboot-kafka Realtime API Stream

Spring Boot and Apache Kafka to consume real time data changes from 
Wikimedia API 

## Features
- Uses the [Wikimedia API](https://stream.wikimedia.org/v2/stream/recentchange) 
to stream recent changes in real-time
- Publish events to Kafka topic
- Consume events from Kafka topic using Spring Kafka
- Stream events to a client (browser) using WebFlux SSE (Server-Sent Events)

### Prerequisites
- [Apache Kafka](https://kafka.apache.org/quickstart) installed on your machine

###  Getting Started
This will help get started locally amd do well to check the `application.properties`
of each module for the kafka configurations

- Click on the 'Clone or download' button and select 'Download Zip.'
- At the root of the project, Build the project with Maven: `mvn clean install`

- Start the Kafka producer module:
``` 
cd kafka-producer-api
mvn spring-boot:run
```

- Start the Kafka consumer module:
``` 
cd ../kafka-consumer-api
mvn spring-boot:run
```

- Open your web browser and go to http://localhost:8081/stream to see the real-time data streaming.

