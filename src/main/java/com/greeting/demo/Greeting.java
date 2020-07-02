package com.greeting.demo;

public class Greeting {

    private long greetingId;
    private String greetingMessage;

    public Greeting(long greetingId, String greetingMessage) {
        this.greetingId = greetingId;
        this.greetingMessage = greetingMessage;
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
