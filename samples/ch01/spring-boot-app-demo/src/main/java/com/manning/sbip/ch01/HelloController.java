package com.manning.sbip.ch01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot Application!";
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot 3.4 with Java 21!";
    }
}
