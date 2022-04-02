package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.DAL.DataAccess.DataAccess;
import easv.DAL.DataAccess.dalException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DalTicket implements DaoTicket {

    private final DataAccess dataAccess;

    public DalTicket() {
        dataAccess = new DataAccess();
    }

    @Override
    public List<Ticket> getAllTickets() throws dalException {
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "Select * from Ticket";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String info = rs.getString("info");
                String type = rs.getString("tickettype");
                int ticketPrice = rs.getInt("price");
                Ticket ticket = new Ticket(newestid(), type, ticketPrice, null, info);
                tickets.add(ticket);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    @Override
    public Ticket createticket(Event event, int id, String type, int ticketprice, Timestamp expirationdan, String info) throws dalException {
        Ticket ticket = null;
        try (Connection con = dataAccess.getConnection()) {
            String sql = "insert into Ticket(Eventid , tickettype , price  , info) values (?, ?, ?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, event.getId());
            prs.setString(2, type);
            prs.setInt(3, ticketprice);
            prs.setString(4, info);
            prs.executeUpdate();
            ticket = new Ticket(newestid(), type, ticketprice, expirationdan, info);
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ticket;
    }

    @Override
    public List<Ticket> getusertickets(int id) throws dalException {
        System.out.println(id);
        ArrayList<Ticket> tickets = new ArrayList<>();
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT  barcode , price  , ticketimage ,  Ticket.id as TicketID" +
                    "       from users " +
                    "  join userevent on userevent.userid = users.id" +
                    "  join Ticket on  userevent.ticketid = Ticket.id" +
                    "   WHERE users.id =?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, id);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                int idi = rs.getInt("TicketID");
                String type = rs.getString("barcode");
                int price = rs.getInt("price");
                String info = rs.getString("ticketimage");

                Ticket ticket = new Ticket(idi, type, price, null, info);
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
    public int newestid() throws dalException {
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
    public void updateTicket(Ticket ticket, String type, int ticketprice, String info) throws dalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "UPDATE Ticket SET  tickettype = ? , price = ? , info = ?  WHERE id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1, type);
            prs.setInt(2, ticketprice);
            prs.setString(3, info);
            prs.setInt(4, ticket.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTicket(Ticket ticket) throws dalException {
        try (Connection con = dataAccess.getConnection()) {
            String sql = "DELETE FROM Ticket WHERE id = ?  ";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1, ticket.getId());
            prs.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
