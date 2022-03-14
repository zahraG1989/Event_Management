package easv.BE;

import javax.xml.crypto.Data;
import java.util.Date;

public class Event {

    private String name ;
    private String location ;
    private String notes ;
    private int participants ;
    private Date startevent ;
    private Date endevent ;
    private String LocationGuidance ;

    public Event(String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance) {
        this.name = name;
        this.location = location;
        this.notes = notes;
        this.participants = participants;
        this.startevent = startevent;
        this.endevent = endevent;
        LocationGuidance = locationGuidance;
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

    public Date getStartevent() {
        return startevent;
    }

    public void setStartevent(Date startevent) {
        this.startevent = startevent;
    }

    public Date getEndevent() {
        return endevent;
    }

    public void setEndevent(Date endevent) {
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
