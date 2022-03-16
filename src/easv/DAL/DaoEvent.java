package easv.DAL;

import easv.BE.Event;
import easv.BE.User;


import java.sql.Date;
import java.util.List;

public interface DaoEvent {

    List<Event> getAllEvents();

    Event createEvent(String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance);

    int newestid();

    void updateEvent(Event event , String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance);

    void deleteEvent(Event event);
}
