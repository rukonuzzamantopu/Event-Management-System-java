/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.eventmanagementsystem;

/**
 *
 * @author Modabbir
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Represents an event with a name, date, capacity, and attendees.
 */
public class Event {
    private String name;
    private String date;
    private int capacity;
    private List<String> attendees;

    // Constructor to initialize the event
    public Event(String name, String date, int capacity) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
    }

    // Getters for event properties
    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public int getCapacity() {
        return capacity;
    }

    public List<String> getAttendees() {
        return attendees;
    }

    // Register an attendee if the capacity allows and the attendee is not already registered
    public boolean registerAttendee(String attendee) {
        if (attendees.contains(attendee)) {
            return false; // Already registered
        }
        if (attendees.size() < capacity) {
            attendees.add(attendee);
            return true; // Successfully registered
        }
        return false; // Event is full
    }

    // Display event details in a readable format
    public String displayEventInfo() {
        StringBuilder info = new StringBuilder();
        info.append("Event: ").append(name).append("\n");
        info.append("Date: ").append(date).append("\n");
        info.append("Capacity: ").append(capacity).append("\n");
        info.append("Registered Attendees: ").append(attendees.size()).append("/").append(capacity).append("\n");

        if (!attendees.isEmpty()) {
            info.append("Attendees: ").append(String.join(", ", attendees));
        } else {
            info.append("No attendees registered yet.");
        }

        return info.toString();
    }
}

