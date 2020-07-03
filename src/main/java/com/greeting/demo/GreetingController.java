package com.greeting.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable(value = "name") String name) {
        return greetingService.findGreeting(name);
    }

    @PostMapping("/greeting")
    public Greeting greeting(@RequestBody User user) {
        return greetingService.saveGreeting(user);
    }

}
