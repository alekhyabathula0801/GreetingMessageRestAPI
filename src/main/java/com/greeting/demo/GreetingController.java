package com.greeting.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("/greeting/{employeeId}")
    public Greeting greeting(@PathVariable(value = "employeeId") Long employeeId) {
        return greetingService.findGreeting(employeeId);
    }

    @PostMapping("/greeting")
    public Greeting greeting(@RequestBody User user) {
        return greetingService.saveGreeting(user);
    }

}
