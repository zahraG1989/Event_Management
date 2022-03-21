package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DataAccess.DataAccess;
import javafx.scene.image.Image;


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
                Date startevent = rs.getDate("startevent");
                Date endevent = rs.getDate("endevent");
                String locatioguide = rs.getString("LocationGuidance");
                int id = rs.getInt("id");
                String imagepath = rs.getString("images");

                Event event = new Event(id ,name ,location , notes , participants , startevent , endevent, locatioguide , imagepath , getTicketsinEvent(id));
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
    public List<User> getusersinEvent(int idi ) {
        ArrayList<User> users = new ArrayList<>();
        // we need name and ticket ...
        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT [username] , [name] , [tickettype] \n" +
                    "\tfROM [users] join userevent on [userS].[id] = [userevent].[userid] \n" +
                    "\t\t\t  join [Event] on  userevent.eventid = [event].[id] \n" +
                    "\t\t\t  join Ticket on  userevent.ticketid = Ticket.id WHERE event.id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , idi);
            ResultSet rs = prs.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("username");
                String ticket = rs.getString("tickettype");
                User user = new User(id ,name , ticket,null,"Customer");
                users.add(user);

            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }

    @Override
    public List<Ticket> getTicketsinEvent(int idi ) {
        ArrayList<Ticket> tickets = new ArrayList<>();
        // we need name and ticket ...
        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT  [Ticket].[id] , tickettype , price , info , barcode  \n" +
                    "FROM [users] join userevent on [userS].[id] = [userevent].[userid] \n" +
                    " join [Event] on  userevent.eventid = [event].[id] \n" +
                    "  join Ticket on  userevent.ticketid = Ticket.id\n" +
                    "WHERE event.id = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , idi);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String tikcettype = rs.getString("tickettype");
                int price = rs.getInt("price");
                String barcode = rs.getString("barcode");
                String ticketinfo = rs.getString("info");

                Ticket ticket = new Ticket(id , tikcettype, price , barcode ,null ,ticketinfo);

            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tickets;
    }

    @Override
    public Event createEvent(String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance , String imagepath) {

        try (Connection con = dataAccess.getConnection()) {
            String sql = "INSERT INTO Event(name , location, notes , participants , startevent , endevent , LocationGuidance , images )" +
                    "VALUES  (?,?,?,?,?, ?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, name);
            prs.setString(2, location);
            prs.setString(3, notes);
            prs.setInt(4, participants);
            prs.setDate(5 , startevent);
            prs.setDate(6, endevent);
            prs.setString(7 , locationGuidance);
            prs.setString(8, imagepath);
            prs.executeUpdate();
            Event event = new Event(newestid() , name , location , notes , participants , startevent , endevent , locationGuidance, imagepath , null );
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
    public void updateEvent(Event event, String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance , String image) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE Event SET  name = ? , location = ? , notes = ?  , participants = ? ,startevent = ? ,endevent = ? ,LocationGuidance = ? , images = ?  WHERE id = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , name);
            prs.setString(2 , location);
            prs.setString(3, notes);
            prs.setInt(4, participants);
            prs.setDate(5, startevent);
            prs.setDate(6 , endevent);
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
