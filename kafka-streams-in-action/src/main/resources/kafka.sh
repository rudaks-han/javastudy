./kafka-console-producer.sh --broker-list localhost:9092 --topic transactions
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic rewards --from-beginning
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic patterns --from-beginning
./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic customer_transactions --from-beginning

./kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic transactions;
./kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic rewards;
./kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic patterns;
./kafka-topics.sh --delete --bootstrap-server localhost:9092 --topic customer_transactions;



{"customerId" : "rudaks", "creditCardNumber" : "1234-1234-1234-1234",  "quantity" : 1,"price" : 1000}
{"customerId" : "kmhan", "creditCardNumber" : "1234-1234-1234-1234",  "quantity" : 1,"price" : 1}