package com.greeting.demo.exception;

import javax.xml.ws.WebServiceException;

public class GreetingException extends WebServiceException {

    public enum ExceptionType {ENTERED_NULL,ENTERED_EMPTY}

    ExceptionType type;
    public GreetingException(String message,ExceptionType type) {
        super(message);
        this.type = type;
    }
}
