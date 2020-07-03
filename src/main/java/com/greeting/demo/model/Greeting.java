package com.greeting.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Greeting {

    @Id
    private Long greetingId;
    private String greetingMessage;

    public Greeting(long greetingId, String greetingMessage) {
        this.greetingId = greetingId;
        this.greetingMessage = greetingMessage;
    }

    public Greeting() {
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "greetingId=" + greetingId +
                ", greetingMessage='" + greetingMessage + '\'' +
                '}';
    }

    public long getGreetingId() {
        return greetingId;
    }

    public void setGreetingId(long greetingId) {
        this.greetingId = greetingId;
    }

    public String getGreetingMessage() {
        return greetingMessage;
    }

    public void setGreetingMessage(String greetingMessage) {
        this.greetingMessage = greetingMessage;
    }

}
