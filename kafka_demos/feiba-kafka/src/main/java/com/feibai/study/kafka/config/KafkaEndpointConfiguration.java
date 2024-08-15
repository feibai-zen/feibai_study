package com.feibai.study.kafka.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(prefix = "feibai.kafka", name = "endpoint", havingValue = "true", matchIfMissing = true)
@ComponentScan(basePackages = {"com.feibai.study.kafka.controller"})
public class KafkaEndpointConfiguration {


}
