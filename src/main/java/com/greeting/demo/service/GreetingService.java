package com.greeting.demo.service;

import com.greeting.demo.repository.GreetingRepository;
import com.greeting.demo.model.Greeting;
import com.greeting.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class GreetingService {

    private final String template = "Hello %s !";
    private final AtomicLong atomicLong = new AtomicLong();

    @Autowired
    GreetingRepository greetingRepository;

    public Greeting findGreeting(Long employeeId) {
        return greetingRepository.findById(employeeId).get();
    }

    public List<Greeting> findAllGreetings() {
        return greetingRepository.findAll();
    }

    public Greeting saveGreeting(User user) {
        String message = String.format(template,(user.toString().isEmpty()) ? "World" : user.toString());
        return greetingRepository.save(new Greeting(atomicLong.incrementAndGet(),message));
    }

    public void deleteGreeting(Long greetingId) {
        greetingRepository.deleteById(greetingId);
    }

    public void deleteAllGreetings() {
        greetingRepository.deleteAll();
    }

}
