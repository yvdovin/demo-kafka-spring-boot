package com.example.demokafka.config;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Properties;

@Configuration
public class KafkaConsumerConfig {

    //@Value(value = "${kafka.server}")
    private String kafkaServer = "http://localhost:9092";

    //@Value("${kafka.group.id}")
    private String kafkaGroupId = "group_1";

    @Bean
    public Consumer<String, String> consumer() {
        final var props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServer);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1000);
        //props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 1);
        //props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        TopicPartition topicPartition = new TopicPartition("test_topic2", 0);
        Consumer<String, String> kafkaConsumer = new KafkaConsumer<>(props);
        kafkaConsumer.assign(Collections.singleton(topicPartition));
        kafkaConsumer.seek(topicPartition, 0);
        return kafkaConsumer;
    }
}
