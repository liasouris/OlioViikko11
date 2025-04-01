package com.example.olio_viikko11;

import android.widget.RadioGroup;

public class Contact {
    // Instance variables
    private String firstName;
    private String lastName;
    private String number;
    private String contactGroup;

    // Constructor
    public Contact(String firstName, String surName, String number, String contactGroup) {
        this.firstName = firstName;
        this.lastName = surName;
        this.number = number;
        this.contactGroup = contactGroup;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getNumber() {
        return number;
    }

    public String getContactGroup() {
        return contactGroup;
    }
}

