package com.feibai.spring.study.config.rabbitmq;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class RabbitConfig {

//  @Bean(name = "connectionFactory")
//  @Primary
//  public ConnectionFactory localConnectionFactory(
//          @Value("${spring.rabbitmq.host}") String host,
//          @Value("${spring.rabbitmq.port}") int port,
//          @Value("${spring.rabbitmq.username}") String username,
//          @Value("${spring.rabbitmq.password}") String password,
//          @Value("${spring.rabbitmq.cache.channel.size}") int cacheChannelSize) {
//    CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//    connectionFactory.setHost(host);
//    connectionFactory.setPort(port);
//    connectionFactory.setUsername(username);
//    connectionFactory.setPassword(password);
//    connectionFactory.setChannelCacheSize(cacheChannelSize);
//    return connectionFactory;
//  }
//
//  @Bean(name = "rabbitTemplate")
//  @Primary
//  public RabbitTemplate localRabbitTemplate(
//          @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//    return new RabbitTemplate(connectionFactory);
//  }
//
//  @Bean(name = "rabbitListenerContainerFactory")
//  public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
//          SimpleRabbitListenerContainerFactoryConfigurer configurer,
//          @Qualifier("connectionFactory") ConnectionFactory connectionFactory) {
//    SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//    configurer.configure(factory, connectionFactory);
//    return factory;
//  }
//
//  @Bean("rabbitAdmin")
//  public RabbitAdmin localRabbitAdmin(@Qualifier("connectionFactory") ConnectionFactory containerFactory) {
//    return new RabbitAdmin(containerFactory);
//  }

}

