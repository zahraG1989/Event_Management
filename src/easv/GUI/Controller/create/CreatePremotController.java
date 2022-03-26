package easv.GUI.Controller.create;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.Users.AdminController;
import easv.GUI.Model.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CreatePremotController implements Initializable {
    @FXML
    public ChoiceBox choiceboxid;
    @FXML
    public TextField usernametxt;
    @FXML
    public TextField emailtxt;
    @FXML
    public JFXButton saveid;
    @FXML
    public JFXButton exitid;

    public AdminController cntrl ;
    public UserModel userModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = UserModel.getInstance();

    }

    public void save(ActionEvent actionEvent) {

    }

    private void updateuser() {
        String name = cntrl.customertable.getSelectionModel().getSelectedItem().getUsername();
        String email =cntrl.customertable.getSelectionModel().getSelectedItem().getEmail();
        String admin = "Admin";
        String eventer = "EventCoordinator";
        String choice = choiceboxid.getItems().toString();
        System.out.println(choice);
        choiceboxid.getItems().add(admin);
        choiceboxid.getItems().add(eventer);

        userModel.updateUser(cntrl.customertable.getSelectionModel().getSelectedItem(),cntrl.customertable.getSelectionModel().getSelectedIndex()
        ,name,email ,choice);
    }
    public void eixit(ActionEvent actionEvent) {
    }

    public void setController(AdminController adminController) {
        this.cntrl = adminController ;
        updateuser();
    }


}
