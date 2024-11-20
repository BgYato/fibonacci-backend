package com.bgyato.fibonacci_time_backend.service.impl;

import com.bgyato.fibonacci_time_backend.models.dto.FibonacciResponse;
import com.bgyato.fibonacci_time_backend.models.entity.FibonacciEntity;
import com.bgyato.fibonacci_time_backend.repository.IFibonacciRepository;
import com.bgyato.fibonacci_time_backend.service.interfaces.IFibonacciService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FibonacciServiceImpl implements IFibonacciService {

    private final IFibonacciRepository fibonacciRepository;

    @Override
    public FibonacciResponse generateFibonacciDefault() {
        LocalTime now = LocalTime.now();
        return FibonacciResponse.builder()
                .timeExecution(formatTime(now))
                .series(generateFibonacci(now))
                .build();
    }

    @Override
    public FibonacciResponse generateFibonacciByTime(String time) {
        if (time == null || time.isEmpty()) throw new IllegalArgumentException("The time is null or empty.");
        LocalTime localTime;
        try {
            localTime = LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } catch (Exception e) {
            throw new IllegalArgumentException("This format is invalid, you must need use: HH:mm:ss", e);
        }

        return FibonacciResponse.builder()
                .timeExecution(localTime.toString())
                .series(generateFibonacci(localTime))
                .build();
    }

    @Override
    public List<FibonacciResponse> listFibonacciSeries() {
        return fibonacciRepository.findAll().stream()
                .map(entity -> {
                    return new FibonacciResponse(entity.getTimeExecution(), entity.getSeries());
                })
                .collect(Collectors.toList());
    }

    private String generateFibonacci(LocalTime time) {
        if (time == null) time = LocalTime.now();

        int minutes = time.getMinute();
        int seed1 = minutes / 10;
        int seed2 = minutes % 10;

        int n = time.getSecond();

        List<Integer> series = new ArrayList<>();

        // Returns if n is 0
        if (n == 0) return series.toString();

        series.add(seed1);
        series.add(seed2);

        for (int i = 0; i < n; i++) {
            int next = series.getLast() + series.get(series.size() - 2);
            series.add(next);
        }

        Collections.reverse(series);

        String seriesString = series.stream().map(String::valueOf)
                .collect(Collectors.joining(", "));

        saveSeries(formatTime(time), seriesString);

        return seriesString;
    }

    private String formatTime(LocalTime time) {
        return time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
    }

    private void saveSeries(String timeExecution, String series) {
        FibonacciEntity entity = new FibonacciEntity();
        entity.setSeries(series);
        entity.setTimeExecution(timeExecution);
        fibonacciRepository.save(entity);
    }
}
