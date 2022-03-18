package easv.BE;




import javafx.scene.image.Image;

import java.sql.Date;

public class Event {

    private String name ;
    private String location ;
    private String notes ;
    private int participants ;
    private Date startevent ;
    private Date endevent ;
    private String LocationGuidance ;
    private int id ;
    private Image image ;
    public Event( int id, String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance, Image image) {
        this.name = name;
        this.location = location;
        this.notes = notes;
        this.participants = participants;
        this.startevent = startevent;
        this.endevent = endevent;
        LocationGuidance = locationGuidance;
        this.id = id ;
       this.image = image ;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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
