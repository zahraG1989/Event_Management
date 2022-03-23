package easv.DAL;

import easv.BE.User;

import java.util.List;

public interface DaoUser {

    List<User> getAllUsers();

    List<User> getAllAdmins();

    List<User> getAllEventCoordinatoer();

    User adduser(String username, String password, String email , String usertype);

    int newestid();

    void updateUser(User customer , String username, String password, String email , String usertype);

    void deleteUser(User customer);

    User verifyUsers(String username, String password);
}
