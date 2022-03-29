package easv.DAL;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DataAccess.DataAccess;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DalUserEvent implements DaoUserEvent{

    DataAccess dataAccess ;

    public DalUserEvent(){

        dataAccess = new DataAccess();
    }

    @Override
    public List<User> getusersinEvent( int idi ) {
        ArrayList<User> users = new ArrayList<>();
            // we need name and ticket ...
        try(Connection con = dataAccess.getConnection()) {
            String sql = "SELECT [users].[id] , [username] , [name] , [tickettype] " +
                    " from [users] join userevent on [users].[id] = [userevent].[userid] " +
                    " join [Event] on  userevent.eventid = [event].[id] " +
                    " join Ticket on  userevent.ticketid = Ticket.id WHERE event.id = ? ";
           PreparedStatement prs = con.prepareStatement(sql);
           prs.setInt(1 , idi);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
               int id = rs.getInt("id");
               String name = rs.getString("username");
               String ticket = rs.getString("tickettype");
               User user = new User(id ,name , ticket,"Customer");
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
            String sql = "select * from Ticket where Eventid = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , idi);
            ResultSet rs = prs.executeQuery();
            while(rs.next()){
                int id = rs.getInt("id");
                String tikcettype = rs.getString("tickettype");
                int price = rs.getInt("price");
                String ticketinfo = rs.getString("info");
                Ticket ticket = new Ticket(id , tikcettype, price  ,null ,ticketinfo);
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
    public void addusertoEvent(User user, Event event, Ticket ticket , String imagepath) {
        try(Connection connection = dataAccess.getConnection()) {

            String sql = "INSERT INTO userevent(userid,eventid,ticketid ,barcode , ticketimage) VALUES (?,?,?,?,?)";
            PreparedStatement prs = connection.prepareStatement(sql);
            prs.setInt(1, user.getId());
            prs.setInt(2 ,event.getId());
            prs.setInt(3 , ticket.getId());
            prs.setString(4, createQrCode());
            prs.setString(5 ,imagepath);
            prs.executeUpdate();
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String createQrCode(){
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(random.nextInt(9) + 1);
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }
        String sbd = String.valueOf(sb);
        return sbd ;
    }

    @Override
    public String getqrcode(User user){
        QrCode qrCode = null;
        try(Connection con = dataAccess.getConnection()) {
            String sql = "  select barcode from userevent where userid = ?";
            PreparedStatement prs = con.prepareStatement(sql);
            prs.setInt(1 , user.getId());
            prs.execute();
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {

                String qrcode = rs.getString("barcode");

                qrCode = new QrCode(0 , qrcode);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return qrCode.getNumber();
    }

    @Override
    public void removeuserfromEvent(User user, Event event ,Ticket ticket) {
        try(Connection con = dataAccess.getConnection()) {
        String sql = "DELETE FROM userevent WHERE userid = ? AND eventid = ? AND ticketid = ?";
        PreparedStatement prs = con.prepareStatement(sql);
        prs.setInt(1 , user.getId());
        prs.setInt(2 , event.getId());
        prs.setInt(3 , ticket.getId());
        prs.executeUpdate();
        } catch (SQLServerException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
