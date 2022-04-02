package easv.DAL;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DataAccess.dalException;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface DaoEvent {

    List<Event> getAllEvents() throws dalException;

    Event createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String imagepath) throws dalException;

    int newestid() throws dalException;

    void updateEvent(Event event, String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String image) throws dalException;

    void deleteEvent(Event event) throws dalException;
}
