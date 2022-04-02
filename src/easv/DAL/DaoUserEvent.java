package easv.DAL;

import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;
import easv.DAL.DataAccess.dalException;

import java.util.List;

public interface DaoUserEvent {

    List<User> getusersinEvent(int idi)throws dalException;

    List<Ticket> getTicketsinEvent(int idi )throws dalException;

    void addusertoEvent(User user , Event event , Ticket ticket , String imagepath )throws dalException;

    void removeuserfromEvent(User user , Event event ,Ticket ticket)throws dalException;

     String getqrcode(User user)throws dalException;
}
