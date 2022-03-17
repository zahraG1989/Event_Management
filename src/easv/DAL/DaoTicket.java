package easv.DAL;

import easv.BE.Ticket;
import easv.BE.User;

import java.sql.Date;
import java.util.List;

public interface DaoTicket {

    List<Ticket> getAllTickets();
        //String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan
    Ticket createticket(int id , String type , int ticketprice , String barcode, Date expirationdan , String info);

    int newestid();

    void updateTicket(Ticket ticket , String type , int ticketprice, String info);

    void deleteTicket(Ticket ticket);
}