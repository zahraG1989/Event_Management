package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Customer;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.DAL.DataAccess.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DalManager implements LogicFecade {

    private DataAccess dataAccess;

    public DalManager() {

        dataAccess = new DataAccess();
    }
                // this method for event cordinator
    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> getallCustomers = new ArrayList<>();

        try(Connection con = dataAccess.getConnection()) {
          String sql = "SELECT * FROM User where type = 'Customer'";

            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()){
                String name = rs.getString("username");
                String email = rs.getString("email");
                String password = rs.getString("password");
                Customer customer = new Customer(name ,password ,email);

                getallCustomers.add(customer);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

        return getallCustomers;
    }
                    // this method will be public for all user you can create an admin or customer or event cordinator
    @Override
    public Customer addCustomer(String username, String password, String email) {

        try(Connection con = dataAccess.getConnection()) {

            String sql = "INSERT INTO User (username , password , email , type) VALUES (?,?,?,?)";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setString(1,username);
            prs.setString(2 ,password);
            prs.setString(3,email);
            prs.executeUpdate();
            Customer customer = new Customer(username ,password,email);

            return customer;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public int newestIdCustomer() {
        int newid = -1;
        try (Connection con = dataAccess.getConnection()) {
            String sql = "SELECT TOP(1) * FROM User ORDER by id desc";
            PreparedStatement prs = con.prepareStatement(sql);
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {

                newid = rs.getInt("MovieId");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return newid;
    }

    @Override
    public void updateCustomer(Customer customer ,String username, String password, String email) {

        try(Connection con = dataAccess.getConnection()) {

            String sql = "UPDATE User SET  username = ? , password = ? , email = ?   WHERE id = ? ";
            PreparedStatement prs = con.prepareStatement(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteCustomer(Customer customer) {
        String sql = "DELETE FROM User WHERE Id = ? ";
    }

    @Override
    public List<Event> getAllEvents() {
        return null;
    }

    @Override
    public Event createEvent(String name, String location, String notes, Date startevent, Date endevent, String locationGuidance) {
        return null;
    }

    @Override
    public int newestidevent() {
        return 0;
    }

    @Override
    public void deleteEvent(Event event) {

    }

    @Override
    public void updateEvent(String name, String location, String notes, Date startevent, Date endevent, String locationGuidance) {

    }

    @Override
    public List<Ticket> getAllTickets(String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan) {
        return null;
    }

    @Override
    public Ticket createTicket(String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan) {
        return null;
    }

    @Override
    public void deleteTicket(Ticket ticket) {

    }
}
