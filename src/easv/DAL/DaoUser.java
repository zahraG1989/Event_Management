package easv.DAL;

import easv.BE.User;
import easv.DAL.DataAccess.dalException;

import java.util.List;

public interface DaoUser {

    List<User> getAllUsers() throws dalException;

    List<User> getAllAdmins() throws dalException;

    List<User> getAllEventCoordinatoer() throws dalException;

    User adduser(String username, String password, String email, String usertype) throws dalException;

    int newestid() throws dalException;

    void updateUser(User customer, String username, String email, String usertype) throws dalException;

    void updatepassword(int id , String password) throws dalException;

    void deleteUser(User customer) throws dalException;

    User verifyUsers(String username, String password) throws dalException;

    List<User> searchforUser(String quury) throws dalException;
}
