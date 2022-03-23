package easv.GUI.Model;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;

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

    public void createTicket(Event event, int id, String type, int ticketprice, String barcode, Date expirationdan, String info){
       Ticket t = logicFecade.createticket(event , id , type , ticketprice , barcode , expirationdan , info);
       tickets.add(t);
    }


    public ObservableList<Ticket> getticketinevent(int id){
        tickets = FXCollections.observableArrayList();
        tickets.addAll(logicFecade.getTicketsinEvent(id));
        return tickets ;
    }

    public void createUsTiEv(Event event , Ticket ticket , User user){
        logicFecade.addusertoEvent(user , event , ticket);
    }
                                // used inside the customer controller
    public ObservableList<Ticket> getuserTickets(int id){
        tickets = FXCollections.observableArrayList();
        System.out.println(logicFecade.getTicketsinEvent(id));
        tickets.addAll(logicFecade.getusertickets(id));

        return tickets ;
    }

    public void deleteTicket(Ticket ticket , int item ){
        logicFecade.deleteTicket(ticket);
        tickets.remove(item);
        updatethelist();
    }

    public void updateTicket(Ticket ticket , int index , String type, int ticketprice, String info ){
        logicFecade.updateTicket(ticket , type , ticketprice , info );
        tickets.set(index , ticket);
        updatethelist();

    }

    public void updatethelist(){

        tickets.setAll(logicFecade.getAllTickets());
    }

}
