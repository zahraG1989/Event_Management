package easv.GUI.Model;

import easv.BE.Event;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import easv.BLL.bllException;
import easv.GUI.Model.util.ModelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.Timestamp;

public class EventModel {

    private static final EventModel eventingleton = new EventModel();
    private final LogicFecade logicFecade;
    private ObservableList<Event> events;

    public EventModel() {
        logicFecade = new Manager();
    }

    public static EventModel getInstance() {
        if (eventingleton == null) {
            return new EventModel();
        }
        return eventingleton;
    }

    public ObservableList<Event> getAllEvents() throws ModelException {
        events = FXCollections.observableArrayList();
        try {
            events.addAll(logicFecade.getAllEvents());
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
        return events;
    }

    public ObservableList<Event> getcurrentEvents() throws ModelException {
        return events;
    }

    public void createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String imagepath) throws ModelException {
        try {
            Event e = logicFecade.createEvent(name, location, notes, participants, startevent, endevent, locationGuidance, imagepath);
            events.add(e);
        } catch (bllException ex) {
            throw new ModelException(ex.getMessage());
        }
    }

    public void deleteEvent(Event event, int item) throws ModelException {
        try {
            logicFecade.deleteEvent(event);
            events.remove(item);
            updatethelist();
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
    }

    public void updateEvent(Event event, int index, String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String image) throws ModelException {
        try {
            logicFecade.updateEvent(event, name, location, notes, participants, startevent, endevent, locationGuidance, image);
            events.set(index, event);
            updatethelist();
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
    }

    public void updatethelist() throws ModelException {
        try {
            events.setAll(logicFecade.getAllEvents());
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
    }
}
