package com.bgyato.fibonacci_time_backend.repository;

import com.bgyato.fibonacci_time_backend.models.entity.FibonacciEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFibonacciRepository extends JpaRepository<FibonacciEntity, String> {
}
