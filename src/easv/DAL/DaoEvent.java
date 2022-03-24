package easv.DAL;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface DaoEvent {

    List<Event> getAllEvents();

    Event createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance , String imagepath);

    int newestid();


    void updateEvent(Event event , String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance , String image);

    void deleteEvent(Event event);
}
