package com.ashdev.kafka_tutorial.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerEventVisit {

    private String customerId;

    private LocalDateTime dateTime;
}
