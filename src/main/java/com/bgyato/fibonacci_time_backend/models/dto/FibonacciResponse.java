package com.bgyato.fibonacci_time_backend.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FibonacciResponse {

    private String timeExecution;
    private String series;

    public FibonacciResponse(String timeExecution, String series) {
        this.timeExecution = timeExecution;
        this.series = series;
    }

}
