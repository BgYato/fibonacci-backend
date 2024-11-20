package com.bgyato.fibonacci_time_backend.controllers;

import com.bgyato.fibonacci_time_backend.models.dto.FibonacciRequest;
import com.bgyato.fibonacci_time_backend.models.dto.FibonacciResponse;
import com.bgyato.fibonacci_time_backend.service.interfaces.IFibonacciService;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fibonacci")
@RequiredArgsConstructor
@CrossOrigin("http://localhost:3000/")
public class FibonacciController {

    private final IFibonacciService fibonacciService;

    @GetMapping("")
    private ResponseEntity<FibonacciResponse> getFibonacciDefault() throws MessagingException {
        return ResponseEntity.status(HttpStatus.OK).body(fibonacciService.generateFibonacciDefault());
    }

    @GetMapping("/all")
    private ResponseEntity<List<FibonacciResponse>> getAllFibonacciSeries() {
        return ResponseEntity.status(HttpStatus.OK).body(fibonacciService.listFibonacciSeries());
    }

    @PostMapping("/generate")
    private ResponseEntity<FibonacciResponse> getFibonacciByTime(@RequestBody FibonacciRequest request) throws MessagingException {
        return ResponseEntity.status(HttpStatus.OK).body(fibonacciService.generateFibonacciByTime(request.getTime()));
    }

}
