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
    public ChoiceBox<String> choiceboxid;
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
    String value = "" ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = UserModel.getInstance();

        String admin = "Admin";
        String eventer = "EventCoordinator";
        choiceboxid.getItems().add(admin);
        choiceboxid.getItems().add(eventer);

         value =  choiceboxid.getValue();
    }

    public void save(ActionEvent actionEvent) {
        updateuser();
    }

    private void updateuser() {

        String name = usernametxt.getText();
        String email = emailtxt.getText();

        userModel.updateUser(cntrl.customertable.getSelectionModel().getSelectedItem(),cntrl.customertable.getSelectionModel().getSelectedIndex()
        ,name,email ,value);
    }
    public void eixit(ActionEvent actionEvent) {
    }

    public void setController(AdminController adminController) {
        this.cntrl = adminController ;
        usernametxt.setText(cntrl.customertable.getSelectionModel().getSelectedItem().getUsername());
        emailtxt.setText(cntrl.customertable.getSelectionModel().getSelectedItem().getEmail());
    }


}
