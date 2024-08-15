package com.feibai.study.kafka;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(KafkaProjectConfigurationSelector.class)
public class KafkaAutoConfiguration{

}