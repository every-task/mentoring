package com.playdata.kafka;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.converter.JsonMessageConverter;
import org.springframework.kafka.support.converter.RecordMessageConverter;

@Configuration
@RequiredArgsConstructor
public class ConsumerConfig {

    private KafkaProperties kafkaProperties;

    @Bean
    public RecordMessageConverter converter() {
        return new JsonMessageConverter();
    }

}
