package easv.GUI.Model;

import easv.BE.User;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import easv.BLL.bllException;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.util.ModelException;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class UserModel {

    private static final  UserModel usersingleton = new UserModel();
    private final LogicFecade logicFecade ;
    private ObservableList<User> users ;

    public UserModel(){

        logicFecade = new Manager();
    }

    public static UserModel getInstance(){
        if(usersingleton == null){
            return new UserModel();
        }
        return usersingleton;
    }

    public User verifyUsers(String username, String password)throws ModelException{
        try {
            return logicFecade.verifyUsers(username, password);
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
    }

    public ObservableList<User> getAllUsers()throws ModelException {

        try {
            users = FXCollections.observableArrayList();
            users.addAll(logicFecade.getAllUsers());
            return users;
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
    }

    public ObservableList<User> getAlleventcoordinatoers()throws ModelException{


        try {
            users = FXCollections.observableArrayList();
            users.addAll(logicFecade.getAllEventCoordinatoer());
            return users;
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }


    }

    public ObservableList<User> getuserinevent(int id)throws ModelException{
        try {
            users = FXCollections.observableArrayList();
            users.addAll(logicFecade.getusersinEvent(id));
            return users;
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }
    }

    public ObservableList<User> getcurrentusers ()throws ModelException{
        return  users ;
    }

    public User adduser(String username, String password, String email, String usertype)throws ModelException{

        try {
            User   u = logicFecade.adduser(username ,password ,email , usertype);
            users.add(u);
            return u ;
        } catch (bllException e) {
         throw new ModelException(e.getMessage());
        }

    }

    public String getqr(User user)throws ModelException{

        try {
            return logicFecade.getqrcode(user);
        } catch (bllException e) {
          throw new ModelException(e.getMessage());
        }
    }

    public ObservableList<User> searchforuser(ObservableList<User> allusers,String qury)throws ModelException{
        try {
            allusers = FXCollections.observableArrayList();
            allusers.addAll(logicFecade.searchforUser(qury));
            return allusers ;
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }


    }

    public void deleteUser(User user , int item)throws ModelException{
        try {
            logicFecade.deleteUser(user);
            users.remove(item);
        } catch (bllException e) {
            throw new ModelException(e.getMessage());
        }

    }

    public void updatethelist() throws ModelException{
        try {
            users.setAll(logicFecade.getAllUsers());
        } catch (bllException e) {
        throw new ModelException(e.getMessage());
        }
    }

    public void updateUser (User user , int index , String username, String email, String usertype)throws ModelException{
        try {
            logicFecade.updateUser(user ,username, email ,usertype);
            users.set(index , user);
            updatethelist();
        } catch (bllException e) {
           throw new ModelException(" " +e.getMessage());
        }

    }


}
