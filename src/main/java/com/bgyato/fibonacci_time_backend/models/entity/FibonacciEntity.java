package com.bgyato.fibonacci_time_backend.models.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FibonacciEntity {

    @Id
    @UuidGenerator
    private String UUID;

    private String timeExecution;

    @Column(length = 1000)
    private String series;

}
