package com.greeting.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("/greeting/{greetingId}")
    public Greeting findGreeting(@PathVariable(value = "greetingId") Long greetingId) {
        return greetingService.findGreeting(greetingId);
    }

    @PostMapping("/greeting")
    public Greeting saveGreeting(@RequestBody User user) {
        return greetingService.saveGreeting(user);
    }

    @DeleteMapping("/greeting/{greetingId}")
    public void deleteGreeting(@PathVariable(value = "greetingId") Long greetingId) {
        greetingService.deleteGreeting(greetingId);
    }

    @DeleteMapping("/greeting")
    public void deleteGreeting() {
        greetingService.deleteAllGreetings();
    }

}
