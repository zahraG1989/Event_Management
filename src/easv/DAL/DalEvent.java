package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Event;
import easv.BE.User;
import easv.DAL.DataAccess.DataAccess;

import java.sql.*;
import java.util.ArrayList;

import java.util.List;

public class DalEvent implements DaoEvent{

    private final DataAccess dataAccess ;

    public DalEvent(){

        dataAccess = new DataAccess();
    }

    @Override
    public List<Event> getAllEvents() {
        ArrayList<Event> events = new ArrayList<>();

        try(Connection con = dataAccess.getConnection()){
            String sql = "SELECT * FROM Events ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){

                String name = rs.getString("name");
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                int participants = rs.getInt("participants");
                Date startevent = rs.getDate("startevent");
                Date endevent = rs.getDate("endevent");
                String locationguidance = rs.getString("LocationGuidance");
                int id = rs.getInt("id");
                Event event = new Event(id ,name ,location , notes , participants , startevent , endevent , locationguidance);
                events.add(event);
            }

        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public Event createEvent(String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance) {

        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO Events(name , location, notes , participants , startevent , endevent , LocationGuidance )" +
                    "VALUES  (?,?,?,? , ? , ?, ?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, name);
            prs.setString(2, location);
            prs.setString(3, notes);
            prs.setInt(4, participants);
            prs.setDate(5 , startevent);
            prs.setDate(6, endevent);
            prs.setString(7 , locationGuidance);
            prs.executeUpdate();
            Event event = new Event(newestid() , name , location , notes , participants , startevent , endevent , locationGuidance );
            return event ;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int newestid() {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Events ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {

                newid = rs.getInt("id");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newid;
    }

    @Override
    public void updateEvent(Event event, String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE Events SET  name = ? , location = ? , notes = ?  , participants = ? ,startevent = ? ,startevent = ? ,startevent = ? WHERE id = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.setString(2 , location);
            prs.setString(3, notes);
            prs.setInt(4, participants);
            prs.setDate(5, startevent);
            prs.setDate(6 , endevent);
            prs.setString(7 , locationGuidance);
            prs.setInt(8 , event.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteEvent(Event event) {
        try(Connection con = dataAccess.getConnection()) {

            String sql = "DELETE FROM Events WHERE id = ?  " ;

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , event.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
