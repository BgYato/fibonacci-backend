package com.bgyato.fibonacci_time_backend.service.interfaces;

import com.bgyato.fibonacci_time_backend.models.dto.FibonacciResponse;

import java.util.List;

public interface IFibonacciService {
    public FibonacciResponse generateFibonacciDefault();
    public FibonacciResponse generateFibonacciByTime(String time);
    public List<FibonacciResponse> listFibonacciSeries();
}
