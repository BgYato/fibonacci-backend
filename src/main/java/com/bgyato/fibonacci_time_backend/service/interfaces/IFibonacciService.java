package com.bgyato.fibonacci_time_backend.service.interfaces;

import com.bgyato.fibonacci_time_backend.models.dto.FibonacciResponse;
import jakarta.mail.MessagingException;

import java.util.List;

public interface IFibonacciService {
    public FibonacciResponse generateFibonacciDefault() throws MessagingException;
    public FibonacciResponse generateFibonacciByTime(String time) throws MessagingException;
    public List<FibonacciResponse> listFibonacciSeries();
}
