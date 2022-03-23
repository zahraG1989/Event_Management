package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DataAccess.DataAccess;

import javax.swing.text.html.HTMLDocument;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DalTicket implements DaoTicket{

    private final DataAccess dataAccess ;

    public DalTicket(){

        dataAccess = new DataAccess();
    }

    @Override
    public List<Ticket> getAllTickets() {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {
            String sql = "Select * from Ticket";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                // int id , String type , int ticketprice , String barcode, Date expirationdan , String info
             String info = rs.getString("info");
             String type = rs.getString("tickettype");
             int ticketPrice = rs.getInt("price");
             String barcode = rs.getString("barcode");
             Ticket ticket = new Ticket(newestid() , type , ticketPrice , barcode , null , info);
             tickets.add(ticket);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket createticket(Event event, int id , String type , int ticketprice , String barcode, Date expirationdan , String info) {
        //--insert into Ticket(Eventid , tickettype , price , barcode , info) values (1 , 'standard' , 50 , 14584 , ' just for showing ');
        try(Connection con = dataAccess.getConnection()) {
            String sql = "insert into Ticket(Eventid , tickettype , price , barcode , info) values (?, ?, ?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1,event.getId());
            prs.setString(2 ,type);
            prs.setInt(3 , ticketprice);
            prs.setString(4 , barcode);
            prs.setString(5,info);
            prs.executeUpdate();
            // int id , String type , int ticketprice , String barcode, Date expirationdan , String info
            Ticket ticket = new Ticket(newestid() , type , ticketprice , barcode , expirationdan ,info);
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Ticket> getusertickets(int id) {
        System.out.println(id);
        ArrayList<Ticket> tickets = new ArrayList<>();
        try(Connection con = dataAccess.getConnection()) {

            String sql = "SELECT  tickettype , price , barcode , info ,  Ticket.id as TicketID" +
                    "       from users " +
                    "  join userevent on userevent.userid = users.id" +
                    "  join Ticket on  userevent.ticketid = Ticket.id" +
                    "   WHERE users.id =?";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , id);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                //int id , String type , int ticketprice , String barcode, Date expirationdan , String info
                int idi = rs.getInt("TicketID");
                String type = rs.getString("tickettype");
                int price = rs.getInt("price");
                String barcode = rs.getString("barcode");
                String info = rs.getString("info");

                Ticket ticket = new Ticket(idi , type ,price ,barcode , null , info);
                tickets.add(ticket);
            }
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public int newestid() {
        int newid = -1;

        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM Ticket  ORDER by id desc";
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
    public void updateTicket(Ticket ticket , String type , int ticketprice, String info) {
        try(Connection con = dataAccess.getConnection()){
            String sql = "UPDATE Ticket SET  tickettype = ? , price = ? , info = ?  WHERE id = ? ";

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1 , type);
            prs.setInt(2 , ticketprice);
            prs.setString(3, info );
            prs.setInt(4, ticket.getId());
            prs.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        try(Connection con = dataAccess.getConnection()) {

            String sql = "DELETE FROM Ticket WHERE id = ?  " ;

            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , ticket.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
