package easv.BLL;

import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface LogicFecade {

    List<Event> getAllEvents() throws bllException;

    Event createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String imagepath) throws bllException;

    void updateEvent(Event event, String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String image) throws bllException;

    void deleteEvent(Event event) throws bllException;

    List<Ticket> getTicketsinEvent(int idi) throws bllException;

    List<User> getusersinEvent(int idi) throws bllException;

    List<User> getAllUsers() throws bllException;

    List<User> getAllAdmins() throws bllException;

    List<User> getAllEventCoordinatoer() throws bllException;

    User adduser(String username, String password, String email, String usertype) throws bllException;

    void updateUser(User customer, String username, String email, String usertype) throws bllException;

    void deleteUser(User customer) throws bllException;

    List<Ticket> getAllTickets() throws bllException;

    Ticket createticket(Event event, int id, String type, int ticketprice, Timestamp expirationdan, String info) throws bllException;

    void updateTicket(Ticket ticket, String type, int ticketprice, String info) throws bllException;

    void deleteTicket(Ticket ticket) throws bllException;

    List<Ticket> getusertickets(int id) throws bllException;

    void addusertoEvent(User user, Event event, Ticket ticket, String path) throws bllException;

    void removeuserfromEvent(User user, Event event, Ticket ticket) throws bllException;

    User verifyUsers(String username, String password) throws bllException;

    List<User> searchforUser(String quury) throws bllException;

    String getqrcode(User user) throws bllException;
}
