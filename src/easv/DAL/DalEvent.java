package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Event;
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
            String sql = "SELECT * FROM Event ";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                String name = rs.getString("name");
                String location = rs.getString("location");
                String notes = rs.getString("notes");
                int participants = rs.getInt("participants");
                Timestamp startevent = rs.getTimestamp("startevent");
                Timestamp endevent = rs.getTimestamp("endevent");
                String locatioguide = rs.getString("LocationGuidance");
                int id = rs.getInt("id");
                String imagepath = rs.getString("images");
                Event event = new Event(id ,name ,location , notes , participants , startevent , endevent, locatioguide , imagepath );
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
    public Event createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance , String imagepath) {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO Event(name , location, notes , participants , startevent , endevent , LocationGuidance , images )" +
                    "VALUES  (?,?,?,?,?, ?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, name);
            prs.setString(2, location);
            prs.setString(3, notes);
            prs.setInt(4, participants);
            prs.setTimestamp(5 , startevent);
            prs.setTimestamp(6, endevent);
            prs.setString(7 , locationGuidance);
            prs.setString(8, imagepath);
            prs.executeUpdate();
            Event event = new Event(newestid() , name , location , notes , participants , startevent , endevent , locationGuidance, imagepath );
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
            String sql = "SELECT TOP(1) * FROM Event ORDER by id desc";
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
    public void updateEvent(Event event, String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance , String image) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE Event SET  name = ? , location = ? , notes = ?  , participants = ? ,startevent = ? ,endevent = ? ,LocationGuidance = ? , images = ?  WHERE id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.setString(2 , location);
            prs.setString(3, notes);
            prs.setInt(4, participants);
            prs.setTimestamp(5, startevent);
            prs.setTimestamp(6 , endevent);
            prs.setString(7 , locationGuidance);
            prs.setString(8 , image);
            prs.setInt(9 , event.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void deleteEvent(Event event) {
        try(Connection con = dataAccess.getConnection()) {
            String sql = "DELETE FROM Event WHERE id = ?  " ;
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , event.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
