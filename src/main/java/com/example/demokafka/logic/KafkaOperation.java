package com.example.demokafka.logic;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;


@Component
@RequiredArgsConstructor
public class KafkaOperation {

    private final Producer<String, String> kafkaProducer;
    private final Consumer<String, String> kafkaConsumer;

    //@EventListener(ContextRefreshedEvent.class)
    public void send() {
        for (int i = 100; i < 200; i++) {
            ProducerRecord<String, String> producerRecord = new ProducerRecord<>("test_topic2", 0, "key", "hello world " + i);
            kafkaProducer.send(producerRecord);
        }


    }

    @EventListener(ContextRefreshedEvent.class)
    public void read() {
        System.out.println("Start reading");

        ConsumerRecords<String, String> records = kafkaConsumer.poll(Duration.ofMillis(2000));
        for (ConsumerRecord<String, String> record : records) {
            System.out.println(record.value());
        }
    }
}
