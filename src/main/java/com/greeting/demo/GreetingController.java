package com.greeting.demo;

import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private final String template = "Hello, %s ! ";
    private final AtomicLong atomicLong = new AtomicLong();

    @GetMapping("/greeting/{name}")
    public Greeting greeting(@PathVariable(value = "name") String name) {
        return new Greeting(atomicLong.incrementAndGet(),String.format(template,name));
    }

}
