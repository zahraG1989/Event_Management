package easv.BLL;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;

import java.sql.Date;
import java.util.List;

public interface LogicFecade {
    //  Event  methods

    List<Event> getAllEvents();

    Event createEvent(String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance , String imagepath);

    void updateEvent(Event event , String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance , String image);

    void deleteEvent(Event event);

    List<Ticket> getTicketsinEvent( int idi );

    List<User> getusersinEvent(int idi);
        // user methods

    List<User> getAllUsers();

    List<User> getAllAdmins();

    List<User> getAllEventCoordinatoer();

    User adduser(String username, String password, String email , String usertype);

    void updateUser(User customer , String username, String password, String email , String usertype);

    void deleteUser(User customer);

    // ticket
    List<Ticket> getAllTickets();
    //String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan
    Ticket createticket(Event event, int id , String type , int ticketprice , String barcode, Date expirationdan , String info);

    void updateTicket(Ticket ticket , String type , int ticketprice, String info);

    void deleteTicket(Ticket ticket);

    List<Ticket> getusertickets(int id);

    // usereventticket

    void addusertoEvent(User user , Event event , Ticket ticket);

    void removeuserfromEvent(User user , Event event ,Ticket ticket);

    User verifyUsers(String username, String password);
}
