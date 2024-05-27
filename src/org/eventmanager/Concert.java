package org.eventmanager;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Concert extends Event{

    private LocalTime hour;
    private BigDecimal price;

    public Concert(String title, LocalDate date, int totalSeats, LocalTime hour, BigDecimal price) {
        super(title, date, totalSeats);
        this.hour = hour;
        this.price = validatePrice(price);
    }

    public LocalTime getHour() {
        return hour;
    }

    public void setHour(LocalTime hour) {
        this.hour = hour;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = validatePrice(price);
    }

    @Override
    public String toString() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        DecimalFormat decimalFormatter = new DecimalFormat("#0.00");
        return "Date: " + getDate().format(dateFormatter) + "\n" +
                "Hour: " + getHour().format(timeFormatter) + "\n" +
                "Name: " + getTitle() + "\n" +
                "Price: " + decimalFormatter.format(getPrice()) + " €";
    }

    private BigDecimal validatePrice(BigDecimal price){
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException("The price must be greater than zero");
        }
        return price;
    }
}
