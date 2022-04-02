package easv.GUI.Model;

import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import easv.BLL.bllException;
import easv.GUI.Model.util.ModelException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Timestamp;

public class TicketModel {

    private static final TicketModel  ticketsingleton = new TicketModel();
    private final LogicFecade logicFecade ;
    private ObservableList<Ticket> tickets ;

    public TicketModel(){
        logicFecade = new Manager();
    }

    public static TicketModel getInstance(){
        if(ticketsingleton == null) {
            return new TicketModel();
        }
        return ticketsingleton ;

    }

    public ObservableList<Ticket> getCuurentTickets(){
        return tickets ;
    }

    public void createTicket(Event event, int id, String type, int ticketprice, Timestamp expirationdan, String info) throws ModelException {

        try {
          Ticket  t = logicFecade.createticket(event , id , type , ticketprice  , expirationdan , info);
            tickets.add(t);
        } catch (bllException e) {
           throw new ModelException(e.getMessage());
        }

    }


    public ObservableList<Ticket> getticketinevent(int id)throws ModelException {

        try {
            tickets = FXCollections.observableArrayList();
            tickets.addAll(logicFecade.getTicketsinEvent(id));
            return tickets ;
        } catch (bllException e) {
            throw  new ModelException(e.getMessage());
        }

    }

    public void createUsTiEv(Event event , Ticket ticket , User user , String path )throws ModelException {
        try {
            logicFecade.addusertoEvent(user , event , ticket, path );
        } catch (bllException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Ticket> getuserTickets(int id)throws ModelException {

        try {
            tickets = FXCollections.observableArrayList();
            tickets.addAll(logicFecade.getusertickets(id));
            return tickets ;
        } catch (bllException e) {
            throw  new ModelException(e.getMessage());
        }
    }

    public void deleteTicket(Ticket ticket , int item )throws ModelException {
        try {
            logicFecade.deleteTicket(ticket);
            tickets.remove(item);
            updatethelist();
        } catch (bllException e) {
           throw  new ModelException(e.getMessage());
        }

    }



    public void updateTicket(Ticket ticket , int index , String type, int ticketprice, String info )throws ModelException {
        try {
            logicFecade.updateTicket(ticket , type , ticketprice , info );
            tickets.set(index , ticket);
            updatethelist();
        } catch (bllException e) {
            e.printStackTrace();
        }

    }

    public void updatethelist()throws ModelException {

        try {
            tickets.setAll(logicFecade.getAllTickets());
        } catch (bllException e) {
           throw new ModelException(e.getMessage());
        }
    }

}
