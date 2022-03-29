package easv.DAL;

import easv.BE.Event;
import easv.BE.QrCode;
import easv.BE.Ticket;
import easv.BE.User;
import java.util.List;

public interface DaoUserEvent {

    List<User> getusersinEvent(int idi);

    List<Ticket> getTicketsinEvent(int idi );

    void addusertoEvent(User user , Event event , Ticket ticket , String imagepath );

    void removeuserfromEvent(User user , Event event ,Ticket ticket);

     String getqrcode(User user);
}
