package com.feibai.spring.study.listener;

import com.alibaba.fastjson.JSON;
import com.feibai.spring.study.model.MessageDemo;
import com.google.common.base.Charsets;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ListenerDemo {

  @RabbitListener(containerFactory = "rabbitListenerContainerFactory",
          bindings = @QueueBinding(
                  exchange = @Exchange("test.exchange"),
                  value = @Queue(value = "test.queue",
                          durable = "true"), key = {"6", "7", "8"}), admin = "rabbitAdmin")
  public void onChipsConsumeMessage(Message message) {
    String jsonStr = new String(message.getBody(), Charsets.UTF_8);
    try {
      MessageDemo orderMessage = JSON.parseObject(jsonStr, MessageDemo.class);

    } catch (Exception e) {
      log.error("handle consume message {} failed.", jsonStr, e);
    }
  }

}
