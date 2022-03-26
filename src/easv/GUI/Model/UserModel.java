package easv.GUI.Model;

import easv.BE.User;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import easv.GUI.Controller.LoginController;
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

    public User verifyUsers(String username, String password){
        return logicFecade.verifyUsers(username, password);
    }

    public ObservableList<User> getAllUsers(){
        users = FXCollections.observableArrayList();
        users.addAll(logicFecade.getAllUsers());
        return users;
    }

    public ObservableList<User> getAlleventcoordinatoers(){

        users = FXCollections.observableArrayList();
        users.addAll(logicFecade.getAllEventCoordinatoer());

        return users;
    }

    public ObservableList<User> getuserinevent(int id){
        users = FXCollections.observableArrayList();
        users.addAll(logicFecade.getusersinEvent(id));
        return users;
    }

    public ObservableList<User> getcurrentusers (){
        return  users ;
    }

    public User adduser(String username, String password, String email, String usertype){
        User u = logicFecade.adduser(username ,password ,email , usertype);
        users.add(u);
        return u ;
    }

    public ObservableList<User> searchforuser(ObservableList<User> allusers,String qury){
      allusers = FXCollections.observableArrayList();
      allusers.addAll(logicFecade.searchforUser(qury));

      return allusers ;
    }

    public void deleteUser(User user , int item){
        logicFecade.deleteUser(user);
        users.remove(item);

    }

    public void updatethelist() {
        users.setAll(logicFecade.getAllUsers());
    }

    public void updateUser (User user , int index , String username, String email, String usertype){
        logicFecade.updateUser(user ,username, email ,usertype);
        users.set(index , user);
        updatethelist();
    }


}
