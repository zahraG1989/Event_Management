package easv.DAL;

import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import java.util.List;

public interface DaoUserEvent {

    List<User> getusersinEvent();

    void addusertoEvent(User user , Event event , Ticket ticket);

    void removeuserfromEvent(User user , Event event ,Ticket ticket);
}