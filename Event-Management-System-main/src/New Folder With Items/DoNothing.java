import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Event {
    private String name;
    private String date;
    private int capacity;
    private List<String> attendees;

    public Event(String name, String date, int capacity) {
        this.name = name;
        this.date = date;
        this.capacity = capacity;
        this.attendees = new ArrayList<>();
    }

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

    public boolean registerAttendee(String attendee) {
        if (attendees.contains(attendee)) {
            return false;
        }
        if (attendees.size() < capacity) {
            attendees.add(attendee);
            return true;
        }
        return false;
    }

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

class EventManager {
    private HashMap<String, Event> events;

    public EventManager() {
        events = new HashMap<>();
    }

    public void createEvent(String name, String date, int capacity) {
        if (events.containsKey(name)) {
            return;
        }
        Event event = new Event(name, date, capacity);
        events.put(name, event);
    }

    public Event getEvent(String name) {
        for (String key : events.keySet()) {
            if (key.equalsIgnoreCase(name)) {
                return events.get(key);
            }
        }
        return null;
    }

    public List<String> listEventNames() {
        return new ArrayList<>(events.keySet());
    }
}

public class DoNothing {
    private JFrame frame;
    private EventManager eventManager;

    public DoNothing() {
        eventManager = new EventManager();
        frame = new JFrame("Event Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 1));

        JButton createButton = new JButton("Create Event");
        JButton registerButton = new JButton("Register for Event");
        JButton viewButton = new JButton("View Event Info");
        JButton listButton = new JButton("List All Events");
        JButton exitButton = new JButton("Exit");

        createButton.addActionListener(e -> createEvent());
        registerButton.addActionListener(e -> registerForEvent());
        viewButton.addActionListener(e -> viewEventInfo());
        listButton.addActionListener(e -> listEvents());
        exitButton.addActionListener(e -> frame.dispose());

        panel.add(createButton);
        panel.add(registerButton);
        panel.add(viewButton);
        panel.add(listButton);
        panel.add(exitButton);

        frame.add(panel);
        frame.setVisible(true);
    }

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
                String name = nameField.getText();
                String date = dateField.getText();
                int capacity = Integer.parseInt(capacityField.getText());
                eventManager.createEvent(name, date, capacity);
                JOptionPane.showMessageDialog(frame, "Event created successfully.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(frame, "Invalid capacity. Please enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void registerForEvent() {
        String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
        if (eventName != null) {
            Event event = eventManager.getEvent(eventName);
            if (event != null) {
                String attendeeName = JOptionPane.showInputDialog(frame, "Enter Your Name:");
                if (attendeeName != null && !attendeeName.isEmpty()) {
                    if (event.registerAttendee(attendeeName)) {
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

    private void viewEventInfo() {
        String eventName = JOptionPane.showInputDialog(frame, "Enter Event Name:");
        if (eventName != null) {
            Event event = eventManager.getEvent(eventName);
            if (event != null) {
                JOptionPane.showMessageDialog(frame, event.displayEventInfo());
            } else {
                JOptionPane.showMessageDialog(frame, "Event not found.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void listEvents() {
        List<String> eventNames = eventManager.listEventNames();
        if (eventNames.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "No events available.");
        } else {
            JOptionPane.showMessageDialog(frame, "Available Events:\n" + String.join("\n", eventNames));
        }
    }

    public static void main(String[] args) {
        new DoNothing();
    }
}
