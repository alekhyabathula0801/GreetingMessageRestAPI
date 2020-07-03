package com.greeting.demo.model;

import com.greeting.demo.exception.GreetingException;

public class User {

    private String firstName;
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if(lastName == null)
            throw new GreetingException("Entered Null", GreetingException.ExceptionType.ENTERED_NULL);
        if(lastName.isEmpty())
            throw new GreetingException("Entered Empty", GreetingException.ExceptionType.ENTERED_EMPTY);
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if(firstName == null)
            throw new GreetingException("Entered Null", GreetingException.ExceptionType.ENTERED_NULL);
        if(firstName.isEmpty())
            throw new GreetingException("Entered Empty", GreetingException.ExceptionType.ENTERED_EMPTY);
        this.firstName = firstName;
    }

    @Override
    public String toString() {
        return "'" + firstName + " " + lastName+'\'';
    }

}
