package easv.BE;




import javafx.scene.image.Image;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Event {

    private String name ;
    private String location ;
    private String notes ;
    private int participants ;
    private Timestamp startevent ;
    private Timestamp endevent ;
    private String LocationGuidance ;
    private int id ;
    private String imagepath ;


    public Event( int id, String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String imagepath ) {
        this.name = name;
        this.location = location;
        this.notes = notes;
        this.participants = participants;
        this.startevent = startevent;
        this.endevent = endevent;
        LocationGuidance = locationGuidance;
        this.id = id ;
       this.imagepath = imagepath ;

    }



    public String getImagepath() {
        return imagepath;
    }

    public void setImagepath(String imagepath) {
        this.imagepath = imagepath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getParticipants() {
        return participants;
    }

    public void setParticipants(int participants) {
        this.participants = participants;
    }

    public Timestamp getStartevent() {
        return startevent;
    }

    public void setStartevent(Timestamp startevent) {
        this.startevent = startevent;
    }

    public Timestamp getEndevent() {
        return endevent;
    }

    public void setEndevent(Timestamp endevent) {
        this.endevent = endevent;
    }

    public String getLocationGuidance() {
        return LocationGuidance;
    }

    public void setLocationGuidance(String locationGuidance) {
        LocationGuidance = locationGuidance;
    }

    @Override
    public String toString() {
        return "Event{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", notes='" + notes + '\'' +
                ", participants=" + participants +
                ", startevent=" + startevent +
                ", endevent=" + endevent +
                ", LocationGuidance='" + LocationGuidance + '\'' +
                '}';
    }
}
