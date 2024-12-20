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
import java.util.HashMap;
import java.util.List;

/**
 * Manages a collection of events.
 */
public class EventManager {
    private HashMap<String, Event> events;

    // Constructor to initialize the event manager
    public EventManager() {
        this.events = new HashMap<>();
    }

    // Create a new event and add it to the collection
    public void createEvent(String name, String date, int capacity) {
        if (!events.containsKey(name.toLowerCase())) {
            Event event = new Event(name, date, capacity);
            events.put(name.toLowerCase(), event);
        }
    }

    // Retrieve an event by name (case-insensitive)
    public Event getEvent(String name) {
        return events.get(name.toLowerCase());
    }

    // List all event names
    public List<String> listEventNames() {
        return new ArrayList<>(events.keySet());
    }
}
