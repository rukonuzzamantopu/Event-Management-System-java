/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.eventmanagementsystem;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.List;

/**
 * Event Management System
 * Provides a GUI for managing events and attendee registrations.
 * 
 * Author: Modabbir
 */
public class EventManagementSystem {
    private JFrame frame;
    private EventManager eventManager;

    public EventManagementSystem() {
        eventManager = new EventManager();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Event Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        // Buttons for system functionalities
        JButton createButton = new JButton("Create Event");
        JButton registerButton = new JButton("Register for Event");
        JButton viewButton = new JButton("View Event Info");
        JButton listButton = new JButton("List All Events");
        JButton exitButton = new JButton("Exit");

        // Add button actions
        createButton.addActionListener(e -> createEvent());
        registerButton.addActionListener(e -> registerForEvent());
        viewButton.addActionListener(e -> viewEventInfo());
        listButton.addActionListener(e -> listEvents());
        exitButton.addActionListener(e -> frame.dispose());

        // Add buttons to panel
        panel.add(createButton);
        panel.add(registerButton);
        panel.add(viewButton);
        panel.add(listButton);
        panel.add(exitButton);

        // Add panel to frame and display
        frame.add(panel);
        frame.setVisible(true);
    }

    // Functionality to create a new event
    private void createEvent() {
        JTextField nameField = new JTextField();
        JTextField dateField = new JTextField();
        JTextField capacityField = new JTextField();

        Object[] fields = {
                "Event Name:", nameField,
                "Event Date:", dateField,
                "Event Capacity:", capacityField
        };

        int option = JOptionPane.showConfirmDialog(frame, fields, "Create Event", JOptionPane.OK_CANCEL_OPTION);

        if (option == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                String date = dateField.getText().trim();
                int capacity = Integer.parseInt(capacityField.getText().trim());

                eventManager.createEvent(name, date, capacity);
                JOptionPane.showMessageDialog(frame, "Event created successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid capacity. Please enter a numeric value.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Functionality to register an attendee for an event
    private void registerForEvent() {
        String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
        if (eventName != null) {
            Event event = eventManager.getEvent(eventName.trim());
            if (event != null) {
                String attendeeName = JOptionPane.showInputDialog(frame, "Enter Your Name:");
                if (attendeeName != null && !attendeeName.trim().isEmpty()) {
                    boolean isRegistered = event.registerAttendee(attendeeName.trim());
                    if (isRegistered) {
                        JOptionPane.showMessageDialog(frame, "Registration successful.");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Event is full or you are already registered.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Event not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Functionality to view information of a specific event
    private void viewEventInfo() {
        String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
        if (eventName != null) {
            Event event = eventManager.getEvent(eventName.trim());
            if (event != null) {
                JOptionPane.showMessageDialog(frame, event.displayEventInfo());
            } else {
                JOptionPane.showMessageDialog(frame, "Event not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Functionality to list all events
    private void listEvents() {
        List<String> eventNames = eventManager.listEventNames();
        if (eventNames.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No events available.");
        } else {
            JOptionPane.showMessageDialog(frame, "Available Events:\n" + String.join("\n", eventNames));
        }
    }

    // Main entry point
    public static void main(String[] args) {
        SwingUtilities.invokeLater(EventManagementSystem::new);
    }
}
