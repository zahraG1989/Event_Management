package easv.DAL;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DataAccess.dalException;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface DaoTicket {

    List<Ticket> getAllTickets()throws dalException;
        //String ticketholder, String type, String eventname, int ticketprice, String eventlocation, String barcode, Date expirationdan
    Ticket createticket(Event event, int id , String type , int ticketprice , Timestamp expirationdan , String info)throws dalException;

    List<Ticket> getusertickets(int id)throws dalException;

    int newestid()throws dalException;

    void updateTicket(Ticket ticket , String type , int ticketprice, String info)throws dalException;

    void deleteTicket(Ticket ticket)throws dalException;
}
