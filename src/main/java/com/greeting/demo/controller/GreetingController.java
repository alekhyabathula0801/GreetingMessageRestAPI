package com.greeting.demo.controller;

import com.greeting.demo.model.Greeting;
import com.greeting.demo.model.User;
import com.greeting.demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("/greeting/{greetingId}")
    public Greeting findGreeting(@PathVariable(value = "greetingId") Long greetingId) {
        return greetingService.findGreeting(greetingId);
    }

    @GetMapping("/greeting")
    public List<Greeting> findAllGreetings() {
        return greetingService.findAllGreetings();
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
    public void deleteAllGreetings() {
        greetingService.deleteAllGreetings();
    }

}
