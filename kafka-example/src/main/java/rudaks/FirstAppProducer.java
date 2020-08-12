package rudaks;

import org.apache.kafka.clients.producer.*;

import java.util.Properties;

public class FirstAppProducer {

    private static String topicName = "test-topic";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.setProperty("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<Integer, String> producer = new KafkaProducer<>(properties);

        int key;
        String value;

        for (int i = 1; i <= 100; i++) {
            key = i;
            value = String.valueOf(i);

            ProducerRecord<Integer, String> record = new ProducerRecord<>(topicName, key, value);

            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (recordMetadata != null) {
                        String infoString = String.format("Success partition: %d, offset: %d", recordMetadata.partition(), recordMetadata.offset());
                        System.out.println(infoString);
                    } else {
                        String infoString = String.format("Failed: %s", e.getMessage());
                        System.err.println(infoString);
                    }
                }
            });
        }

        producer.close();
    }
}
