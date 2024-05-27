package org.eventmanager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProgramEvent {
    private String title;
    List<Event> eventList;

    public ProgramEvent(String title) {
        this.title = title;
        this.eventList = new ArrayList<>();
    }

    public void addEvent(Event event){
        if(event != null){
            eventList.add(event);
        }
    }

    public List<Event> filterByDate(LocalDate date) {
        List<Event> filteredEvents = new ArrayList<>();
        for (Event event : eventList) {
            if (event.getDate().equals(date)) {
                filteredEvents.add(event);
            }
        }
        return filteredEvents;
    }

    public int getNumberEvents(){
        return eventList.size();
    }

    public void clearList(){
        eventList.clear();
    }

    public Event getEvent(int index){
        return eventList.get(index);
    }

    public String getAllEvents(){
        eventList.sort(Comparator.comparing(Event::getDate));
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        StringBuilder sb = new StringBuilder();
        for (Event event : eventList) {
            sb.append(event.getDate().format(dateFormatter)).append(" - ").append(event.getTitle()).append("\n");
        }
        return sb.toString();
    }
}
