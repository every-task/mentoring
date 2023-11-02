package com.playdata.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicConfig {
    public final static String MEMBER = "member";

    @Bean
    public NewTopic member() {
        return new NewTopic(MEMBER, 1, (short) 1);
    }


}
