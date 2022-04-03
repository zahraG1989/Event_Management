package easv.BLL;

import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DalUser;
import easv.DAL.DalEvent;
import easv.DAL.DalTicket;
import easv.DAL.DalUserEvent;
import easv.DAL.DataAccess.dalException;


import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Manager implements LogicFecade {
    private final DalUser daluser;
    private final DalEvent dalEvent;
    private final DalTicket dalTicket;
    private final DalUserEvent dalUserEvent;

    public Manager() {
        daluser = new DalUser();
        dalEvent = new DalEvent();
        dalTicket = new DalTicket();
        dalUserEvent = new DalUserEvent();
    }

    @Override
    public List<Event> getAllEvents() throws bllException {

        try {
            return dalEvent.getAllEvents();
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }

    }

    @Override
    public Event createEvent(String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String imagepath) throws bllException {
        try {
            return dalEvent.createEvent(name, location, notes, participants, startevent, endevent, locationGuidance, imagepath);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public void updateEvent(Event event, String name, String location, String notes, int participants, Timestamp startevent, Timestamp endevent, String locationGuidance, String image) throws bllException {
        try {
            dalEvent.updateEvent(event, name, location, notes, participants, startevent, endevent, locationGuidance, image);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public void deleteEvent(Event event) throws bllException {
        try {
            dalEvent.deleteEvent(event);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() throws bllException {
        try {
            return daluser.getAllUsers();
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<User> getAllAdmins() throws bllException {
        try {
            return daluser.getAllAdmins();
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<User> getAllEventCoordinatoer() throws bllException {
        try {
            return daluser.getAllEventCoordinatoer();
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public User adduser(String username, String password, String email, String usertype) throws bllException {
        try {
            return daluser.adduser(username, password, email, usertype);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public void updateUser(User customer, String username, String email, String usertype) throws bllException {
        try {
            daluser.updateUser(customer, username, email, usertype);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public void deleteUser(User customer) throws bllException {
        try {
            daluser.deleteUser(customer);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<Ticket> getAllTickets() throws bllException {
        try {
            return dalTicket.getAllTickets();
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public Ticket createticket(Event event, int id, String type, int ticketprice, Timestamp expirationdan, String info) throws bllException {
        try {
            return dalTicket.createticket(event, id, type, ticketprice, expirationdan, info);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public void updateTicket(Ticket ticket, String type, int ticketprice, String info) throws bllException {
        try {
            dalTicket.updateTicket(ticket, type, ticketprice, info);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public void deleteTicket(Ticket ticket) throws bllException {
        try {
            dalTicket.deleteTicket(ticket);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<Ticket> getusertickets(int id) throws bllException {
        // System.out.println(dalTicket.getusertickets(id));
        try {
            return dalTicket.getusertickets(id);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<User> getusersinEvent(int idi) throws bllException {
        return dalUserEvent.getusersinEvent(idi);
    }

    @Override
    public List<Ticket> getTicketsinEvent(int idi) throws bllException {
        return dalUserEvent.getTicketsinEvent(idi);
    }

    @Override
    public void addusertoEvent(User user, Event event, Ticket ticket, String path) throws bllException {
        dalUserEvent.addusertoEvent(user, event, ticket, path);
    }

    @Override
    public void removeuserfromEvent(User user, Event event, Ticket ticket) throws bllException {
        dalUserEvent.removeuserfromEvent(user, event, ticket);
    }

    @Override
    public User verifyUsers(String username, String password) throws bllException {
        try {
            return daluser.verifyUsers(username, password);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public List<User> searchforUser(String quury) throws bllException {
        try {
            return daluser.searchforUser(quury);
        } catch (dalException e) {
            throw new bllException(e.getMessage());
        }
    }

    @Override
    public String getqrcode(User user) throws bllException {
        return dalUserEvent.getqrcode(user);
    }

    @Override
    public void updatepassword(int id , String password) throws bllException {
        try {
            daluser.updatepassword(id , password);
        } catch (dalException e) {
            e.printStackTrace();
        }
    }
}
