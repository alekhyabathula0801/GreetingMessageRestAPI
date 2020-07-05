package com.greeting.demo.controller;

import com.greeting.demo.exception.GreetingException;
import com.greeting.demo.model.Greeting;
import com.greeting.demo.model.User;
import com.greeting.demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class GreetingController {

    @Autowired
    GreetingService greetingService;

    @GetMapping(path = "/greeting/{greetingId}",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Greeting findGreeting(@PathVariable(value = "greetingId") Long greetingId) {
        try {
            return greetingService.findGreeting(greetingId);
        } catch (NoSuchElementException e) {
            throw new GreetingException("Greeting with Id " + greetingId + " not found ");
        }
    }

    @GetMapping(path = "/greeting",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public List<Greeting> findAllGreetings() {
        return greetingService.findAllGreetings();
    }

    @PostMapping(path = "/greeting",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Greeting saveGreeting(@RequestBody User user) {
        if(user.getFirstName() == null | user.getLastName() == null)
            throw new GreetingException("Name cannot be NULL");
        if(user.getLastName().isEmpty() | user.getFirstName().isEmpty())
            throw new GreetingException("Name cannot be Empty");
        return greetingService.saveGreeting(user);
    }

    @DeleteMapping("/greeting/{greetingId}")
    public void deleteGreeting(@PathVariable(value = "greetingId") Long greetingId) {
        try {
            greetingService.deleteGreeting(greetingId);
        } catch (EmptyResultDataAccessException e) {
            throw new GreetingException("Greeting with Id " + greetingId + " not found ");
        }
    }

    @DeleteMapping("/greeting")
    public void deleteAllGreetings() {
        greetingService.deleteAllGreetings();
    }

    @PutMapping(path = "/greeting",produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
    public Greeting updateGreeting(@RequestBody Greeting greeting) {
        return greetingService.updateGreeting(greeting);
    }

}
