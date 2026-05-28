package com.jalendar.jalendar_service;

// Import statements.
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * For lack of a better way to put this, this file does EFFECTIVELY nothing.
 * It exists to, in a worst case scenario, try to test if the backend is up
 * and running. Going to localhost:8080/test should display Hello World if
 * it's all working properly. I may delete it at some point, but it's a
 * short enough file to not really be concerning.
 */
@RestController
public class JalendarController {

    @GetMapping("/test")
    public String home() {
        return "Hello World";
    }
}
