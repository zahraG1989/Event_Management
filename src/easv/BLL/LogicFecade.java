package easv.BLL;

import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface LogicFecade {

    List<Event> getAllEvents();

    Event createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance , String imagepath);

    void updateEvent(Event event , String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance , String image);

    void deleteEvent(Event event);

    List<Ticket> getTicketsinEvent( int idi );

    List<User> getusersinEvent(int idi);

    List<User> getAllUsers();

    List<User> getAllAdmins();

    List<User> getAllEventCoordinatoer();

    User adduser(String username, String password, String email , String usertype);

    void updateUser(User customer , String username, String email , String usertype);

    void deleteUser(User customer);

    List<Ticket> getAllTickets();

    Ticket createticket(Event event, int id , String type , int ticketprice , Timestamp expirationdan , String info);

    void updateTicket(Ticket ticket , String type , int ticketprice, String info);

    void deleteTicket(Ticket ticket);

    List<Ticket> getusertickets(int id);

    void addusertoEvent(User user , Event event , Ticket ticket , String path );

    void removeuserfromEvent(User user , Event event ,Ticket ticket);

    User verifyUsers(String username, String password);

    List<User> searchforUser(String quury);

    String getqrcode(User user);
}
