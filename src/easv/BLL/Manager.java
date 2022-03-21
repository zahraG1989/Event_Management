package easv.BLL;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DalUser;
import easv.DAL.DalEvent;
import easv.DAL.DalTicket;
import easv.DAL.DalUserEvent;


import java.sql.Date;
import java.util.List;

public class Manager implements LogicFecade{
    private final DalUser daluser;
    private final DalEvent dalEvent;
    private final DalTicket dalTicket;
    private final DalUserEvent dalUserEvent ;

    public Manager(){
      daluser = new DalUser();
      dalEvent = new DalEvent();
      dalTicket  = new DalTicket();
      dalUserEvent = new DalUserEvent();
    }
    @Override
    public List<Event> getAllEvents() {
        return dalEvent.getAllEvents();
    }

    @Override
    public Event createEvent(String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance, String imagepath) {
        return dalEvent.createEvent(name , location,notes,participants,startevent,endevent,locationGuidance,imagepath);
    }

    @Override
    public void updateEvent(Event event, String name, String location, String notes, int participants, Date startevent, Date endevent, String locationGuidance, String image) {
        dalEvent.updateEvent(event , name , location , notes ,participants ,startevent ,endevent ,locationGuidance ,image);
    }

    @Override
    public void deleteEvent(Event event) {
        dalEvent.deleteEvent(event);
    }

    @Override
    public List<User> getAllUsers() {
        return daluser.getAllUsers();
    }

    @Override
    public List<User> getAllAdmins() {
        return daluser.getAllAdmins();
    }

    @Override
    public List<User> getAllEventCoordinatoer() {
        return daluser.getAllEventCoordinatoer();
    }

    @Override
    public User adduser(String username, String password, String email, String usertype) {
        return daluser.adduser(username ,password ,email ,usertype);
    }

    @Override
    public void updateUser(User customer, String username, String password, String email, String usertype) {
        daluser.updateUser(customer , username,password ,email ,usertype);
    }

    @Override
    public void deleteUser(User customer) {
        daluser.deleteUser(customer);
    }

    @Override
    public List<Ticket> getAllTickets() {
        return dalTicket.getAllTickets();
    }

    @Override
    public Ticket createticket(Event event, int id, String type, int ticketprice, String barcode, Date expirationdan, String info) {
        return dalTicket.createticket(event ,id ,type ,ticketprice ,barcode ,expirationdan ,info);
    }

    @Override
    public void updateTicket(Ticket ticket, String type, int ticketprice, String info) {
        dalTicket.updateTicket(ticket ,type ,ticketprice ,info);
    }

    @Override
    public void deleteTicket(Ticket ticket) {
        dalTicket.deleteTicket(ticket);
    }

    @Override
    public List<Ticket> getusertickets(int id) {

        return dalTicket.getusertickets(id);
    }

    @Override
    public List<User> getusersinEvent(int idi ) {
        return  dalUserEvent.getusersinEvent(idi);
    }

    @Override
    public List<Ticket> getTicketsinEvent(int idi) {
        return dalUserEvent.getTicketsinEvent(idi);
    }

    @Override
    public void addusertoEvent(User user, Event event, Ticket ticket) {
        dalUserEvent.addusertoEvent(user ,event ,ticket);
    }

    @Override
    public void removeuserfromEvent(User user, Event event, Ticket ticket) {
        dalUserEvent.removeuserfromEvent(user ,event ,ticket);
    }
}
