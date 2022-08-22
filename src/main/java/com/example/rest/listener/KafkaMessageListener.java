package com.example.rest.listener;


import com.example.rest.entity.DataClass;
import com.example.rest.service.DataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@EnableKafka
@AllArgsConstructor
public class KafkaMessageListener {

    private final DataService dataService;

    @KafkaListener(topics = "message")
    public void messageListener(String message) {

        log.info("Kafka message: {}", message);

        dataService.saveData(new DataClass(message));
    }
}
