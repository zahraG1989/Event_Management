package easv.DAL;

import easv.BE.Ticket;
import easv.DAL.DataAccess.DataAccess;
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
    public Ticket createticket(int id , String type , int ticketprice , String barcode, Date expirationdan , String info) {
        return null;
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
