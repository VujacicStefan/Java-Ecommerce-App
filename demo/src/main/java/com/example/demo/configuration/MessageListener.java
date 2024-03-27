package com.example.demo.configuration;

import com.example.demo.Entity.CustomMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public class MessageListener {
       @RabbitListener(queues = MqConfigurations.QUEUE)
        public void listener(CustomMessage message) {
            System.out.println(message);
        }

    }


