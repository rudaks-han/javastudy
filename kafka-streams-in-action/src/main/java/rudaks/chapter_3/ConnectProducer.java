package rudaks;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.connect.json.JsonSerializer;

public class ConnectProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        Properties properties = new Properties();

        // kafka bootstrap server
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // producer acks
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "all"); // strongest producing guarantee
        properties.setProperty(ProducerConfig.RETRIES_CONFIG, "3");
        properties.setProperty(ProducerConfig.LINGER_MS_CONFIG, "1");
        // leverage idempotent producer from Kafka 0.11 !
        properties.setProperty(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, "true"); // ensure we don't push duplicates
        Producer<String, String> producer = new KafkaProducer<>(properties);


        UserInfo userInfo = new UserInfo("a1", "rudaks");
        UserInfo userInfo2 = new UserInfo("a1", null);

        producer.send(connectRecord(null, userInfo.toString())).get();
        producer.send(disconnectRecord(null, userInfo2.toString())).get();

        Thread.sleep(1000);

        producer.send(connectRecord(null, userInfo.toString())).get();
        producer.send(disconnectRecord(null, userInfo2.toString())).get();


        System.out.println("End of demo");
        producer.close();
    }

    private static ProducerRecord<String, String> connectRecord(String key, String value) {
        return new ProducerRecord<>("connect", key, value);
    }


    private static ProducerRecord<String, String> disconnectRecord(String key, String value){
        return new ProducerRecord<>("disconnect", key, value);
    }
}
