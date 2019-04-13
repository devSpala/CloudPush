package com.spala.invokelambda;

/**
 * Created by Arif on 6/24/2017.
 */

public class Info {
    private String firstName;
    private String lastName;

    public Info() {}

    public Info(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}