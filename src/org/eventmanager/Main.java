package org.eventmanager;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.println("Insert new event");

        Event event = null;
        while (event == null){
            System.out.print("Event name: ");
            String name = scan.nextLine();

            System.out.print("Date (yyyy-mm-dd): ");
            LocalDate date = LocalDate.parse(scan.nextLine());

            System.out.print("Total seats: ");
            int totalSeats = Integer.parseInt(scan.nextLine());

            try{
                event = new Event(name, date, totalSeats);
                System.out.println(event);
            } catch (IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        boolean exit = false;
        while (!exit) {
            System.out.print("1 - Enter reservations  2 - Cancel reservation  3 - exit: ");
            String input = scan.nextLine();
            switch (input){
                case "3" :
                    exit = true;
                    System.out.println("Bye bye");
                    break;
                case "1":
                    addReservation(event, scan);
                    break;
                case "2":
                    cancelReservation(event, scan);
                    break;
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
        System.out.println("Seats booked: " + event.getBookedSeats() + " - Remaining seats: " +
                (event.getTotalSeats() - event.getBookedSeats()));
    }

    private static void cancelReservation(Event event, Scanner scan) {
        System.out.print("Cancel reservation: ");
        try {
            event.cancelBook(Integer.parseInt(scan.nextLine()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Seats booked: " + event.getBookedSeats() + " - Remaining seats: " +
                (event.getTotalSeats() - event.getBookedSeats()));
    }
}
