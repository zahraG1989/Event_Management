package easv.GUI.Model;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class EventModel {

    private static final EventModel  eventingleton = new EventModel();
    private final LogicFecade logicFecade ;
    private ObservableList<Event> events ;

    public EventModel(){
        logicFecade = new Manager();
    }

    public static EventModel getInstance(){

        if(eventingleton == null)
        {
            return  new EventModel();
        }
        return  eventingleton;
    }

    public ObservableList<Event> getAllEvents(){

        events = FXCollections.observableArrayList();

        events.addAll(logicFecade.getAllEvents());

        return events;
    }


    public ObservableList<Event> getcurrentEvents (){
        return  events ;
    }

    public void createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String imagepath){
        Event e = logicFecade.createEvent(name, location, notes, participants, startevent, endevent, locationGuidance, imagepath);
        events.add(e);
    }

    public void deleteEvent(Event event , int item){
        logicFecade.deleteEvent(event);
        events.remove(item);
        updatethelist();
    }

    public void updateEvent (Event event, int index , String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String image){

        logicFecade.updateEvent(event,name ,location , notes , participants , startevent , endevent , locationGuidance ,image);
        events.set(index , event);
        updatethelist();
    }



    public void updatethelist() {
        events.setAll(logicFecade.getAllEvents());
    }


}
