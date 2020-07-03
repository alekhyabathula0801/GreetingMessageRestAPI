package com.greeting.demo;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class GreetingService {

    private final String template = "Hello, %s ! ";
    private final AtomicLong atomicLong = new AtomicLong();

    public Greeting findGreeting(String name) {
        return new Greeting(atomicLong.incrementAndGet(),String.format(template,name));
    }

    public Greeting saveGreeting(User user) {
        String message = String.format(template,(user.toString().isEmpty()) ? "Hello World" : user.toString());
        return new Greeting(atomicLong.incrementAndGet(),message);
    }

}
