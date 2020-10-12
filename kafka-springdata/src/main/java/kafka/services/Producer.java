package kafka.services;

import kafka.JsonSerializable;
import kafka.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    private static final Logger logger = LoggerFactory.getLogger(Producer.class);
    private static final String TOPIC = "users";

    @Autowired
    private KafkaTemplate<String, JsonSerializable> kafkaTemplate;

    public void sendMessage(String message) {
        //this.kafkaTemplate.send(TOPIC, message);
        this.kafkaTemplate.send(TOPIC, new User("rudaks"));
    }
}