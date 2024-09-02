Kafka Project.
Created a CustomerEvent.
Read and Write messages to Topic.

Read messages from Topic using terminal:
docker exec --interactive --tty broker kafka-console-consumer --bootstrap-server localhost:9092 --topic "customer.visit" --from-beginning

Write messages to Topic using terminal:
docker exec --interactive --tty broker kafka-console-producer --bootstrap-server localhost:9092 --topic "customer.visit"
