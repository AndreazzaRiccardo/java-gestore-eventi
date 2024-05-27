package org.eventmanager;

import java.time.LocalDate;

public class Event {

    private String title;
    private LocalDate date;
    private int totalSeats;
    private int bookedSeats;

    public Event(String title, LocalDate date, int totalSeats) {
        this.title = title;
        this.date = validateDate(date);
        this.totalSeats = isPositive(totalSeats);
        this.bookedSeats = 0;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = validateDate(date);
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getBookedSeats() {
        return bookedSeats;
    }

    public void book(int bookSeats) throws IllegalArgumentException {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The event is already over");
        } else if (isPositive(bookSeats) + bookedSeats > totalSeats) {
            throw new IllegalArgumentException("Available seats finished. Remaining seats: " +
                    (totalSeats - bookedSeats));
        }
        bookedSeats += bookSeats;
    }

    public void cancelBook(int cancelValue) throws IllegalArgumentException {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("The event is already over");
        } else if (bookedSeats - isPositive(cancelValue) < 0) {
            throw new IllegalArgumentException("You can’t cancel so many bookings");
        }
        bookedSeats -= cancelValue;
    }

    @Override
    public String toString() {
        return date + " - " + title;
    }

    private LocalDate validateDate(LocalDate date) throws IllegalArgumentException {
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Insert a valid date");
        }
        return date;
    }

    private int isPositive(int num) throws IllegalArgumentException {
        if (num < 0) {
            throw new IllegalArgumentException("This value cannot be negative");
        }
        return num;
    }
}
