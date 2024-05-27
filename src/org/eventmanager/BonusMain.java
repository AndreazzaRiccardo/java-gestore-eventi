package org.eventmanager;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class BonusMain {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        ProgramEvent eventList = new ProgramEvent("Event List");

        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("1 - Insert new event");
            System.out.println("2 - Manage events");
            System.out.println("3 - Exit");
            System.out.print("Choose an option: ");
            String input = scan.nextLine();

            switch (input) {
                case "1":
                    insertNewEvent(scan, eventList);
                    break;
                case "2":
                    manageEvents(scan, eventList);
                    break;
                case "3":
                    exitProgram = true;
                    System.out.println("Bye bye");
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }

        scan.close();
    }

    private static void insertNewEvent(Scanner scan, ProgramEvent eventList) {
        System.out.println("Insert new event");

        Event event = null;
        while (event == null) {
            System.out.print("This event is concert ? (y/n): ");
            boolean isConcert = scan.nextLine().equalsIgnoreCase("y");

            System.out.print("Event name: ");
            String name = scan.nextLine();

            System.out.print("Date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scan.nextLine());

            System.out.print("Total seats: ");
            int totalSeats = Integer.parseInt(scan.nextLine());

            if(isConcert){
                System.out.print("Hour: ");
                LocalTime hour = LocalTime.parse(scan.nextLine());

                System.out.print("Price: ");
                BigDecimal price = BigDecimal.valueOf(Double.parseDouble(scan.nextLine()));
                try{
                    event = new Concert(name, date, totalSeats, hour, price);
                } catch (IllegalArgumentException e){
                    System.out.println(e.getMessage());
                }
            } else {
                try {
                    event = new Event(name, date, totalSeats);
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            if(event != null){
                eventList.addEvent(event);
                System.out.println(event.eventDetails());
                System.out.println();
            }
        }
    }

    private static void manageEvents(Scanner scan, ProgramEvent eventList) {
        if (eventList.getNumberEvents() == 0) {
            System.out.println("No events available. Please add an event first.");
            return;
        }

        System.out.println("Select an event to manage:");
        for (int i = 0; i < eventList.getNumberEvents(); i++) {
            System.out.println((i + 1) + " - " + eventList.getEvent(i));
        }

        int eventIndex = -1;
        while (eventIndex < 0 || eventIndex >= eventList.getNumberEvents()) {
            System.out.print("Enter the number of the event: ");
            try {
                eventIndex = Integer.parseInt(scan.nextLine()) - 1;
                if (eventIndex < 0 || eventIndex >= eventList.getNumberEvents()) {
                    System.out.println("Invalid event number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        Event selectedEvent = eventList.getEvent(eventIndex);
        manageEventActions(scan, selectedEvent);
    }

    private static void manageEventActions(Scanner scan, Event event) {
        System.out.println(event.eventDetails());
        boolean exit = false;
        while (!exit) {
            System.out.println("1 - Enter reservations");
            System.out.println("2 - Cancel reservation");
            System.out.println("3 - Return to main menu");
            System.out.print("Choose an action: ");
            String input = scan.nextLine();

            switch (input) {
                case "1":
                    addReservation(event, scan);
                    break;
                case "2":
                    cancelReservation(event, scan);
                    break;
                case "3":
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Please choose again.");
            }
        }
    }

    private static void addReservation(Event event, Scanner scan) {
        System.out.print("Add reservation: ");
        try {
            event.book(Integer.parseInt(scan.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(event.eventDetails());
    }

    private static void cancelReservation(Event event, Scanner scan) {
        System.out.print("Cancel reservation: ");
        try {
            event.cancelBook(Integer.parseInt(scan.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(event.eventDetails());
    }
}
