package com.jacky.warframe.orderservice;

import com.jacky.warframe.orderservice.event.OrderMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    @KafkaListener(topics = "orderMessageTopic")
    public void handleNotification(OrderMessage orderMessage) {
        // send out an email notification
        log.info("Received Notification for Order - {}", orderMessage.getWhisperMessage());
    }
}