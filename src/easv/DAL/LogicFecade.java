package easv.DAL;

import easv.BE.Customer;
import easv.BE.Event;
import easv.BE.Ticket;

import java.util.Date;
import java.util.List;

public interface LogicFecade {

    List<Customer> getAllCustomers() ;

    Customer addCustomer(String username, String password, String email);

    int newestidcustomer();

    void updateCustomer(Customer customer , String username, String password, String email) ;

    void deleteCustomer(Customer customer) ;

    List<Event> getAllEvents();

    Event createEvent(String name, String location, String notes, Date startevent, Date endevent, String locationGuidance);

    int newestidevent();

    void deleteEvent(Event event);

    void updateEvent(String name, String location, String notes, Date startevent, Date endevent, String locationGuidance);

    // String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan

    List<Ticket> getAllTickets(String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan);

    Ticket createTicket(String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan);

    void deleteTicket(Ticket ticket);
}
