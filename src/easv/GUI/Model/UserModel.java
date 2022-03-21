package easv.GUI.Model;

import easv.BE.User;
import easv.BLL.LogicFecade;
import easv.BLL.Manager;
import easv.GUI.Controller.LoginController;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserModel {

    private static final  UserModel usersingleton = new UserModel();
    private final LogicFecade logicFecade ;
    private ObservableList<User> users ;

    public UserModel(){

        logicFecade = new Manager();
    }

    public static UserModel getInstance(){

        return usersingleton;
    }

    public ObservableList<User> getAllUsers(){

        users = FXCollections.observableArrayList();

        users.addAll(logicFecade.getAllUsers());

        return users;
    }

    public ObservableList<User> getcurrentusers (){
        return  users ;
    }

    public void adduser(String username, String password, String email, String usertype){

        User u = logicFecade.adduser(username ,password ,email , usertype);

        users.add(u);
    }

    public void deleteUser(User user , int item){
        logicFecade.deleteUser(user);
        users.remove(item);
    }

    public void updatethelist() {
        users.setAll(logicFecade.getAllUsers());
    }

    public void updateUser (User user , int index , String username, String password, String email, String usertype){

        logicFecade.updateUser(user ,username,password , email ,usertype);
        users.set(index , user);
        updatethelist();
    }


}
