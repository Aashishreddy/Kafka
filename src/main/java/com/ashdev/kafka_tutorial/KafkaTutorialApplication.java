package com.ashdev.kafka_tutorial;

import com.ashdev.kafka_tutorial.config.KafkaConfigProps;
import com.ashdev.kafka_tutorial.domain.CustomerEventVisit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class KafkaTutorialApplication {

	@Autowired
	private ObjectMapper objectMapper;

	public static void main(String[] args) {
		SpringApplication.run(KafkaTutorialApplication.class, args);
	}

	@Bean
	public ApplicationRunner applicationRunner(KafkaTemplate<String, String> kafkaTemplate,
											   final KafkaConfigProps kafkaConfigProps) throws JsonProcessingException {

		final CustomerEventVisit customerEvent = CustomerEventVisit.builder()
														.customerId(UUID.randomUUID().toString())
														.dateTime(LocalDateTime.now())
														.build();

		final String payload = objectMapper.writeValueAsString(customerEvent);

		return args -> {
			kafkaTemplate.send(kafkaConfigProps.getTopic(), payload);
		};
	}

	@KafkaListener(topics = "customer.visit")
	public String listens(final String in){
		System.out.println(in);
		return in;
	}

}
