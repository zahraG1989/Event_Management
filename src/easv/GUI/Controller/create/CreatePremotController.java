package easv.GUI.Controller.create;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.Users.AdminController;
import easv.GUI.Model.UserModel;
import easv.GUI.Model.util.ModelException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
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

    public AdminController cntrl;
    public UserModel userModel;
    @FXML
    public StackPane stackpaneid;
    @FXML
    public AnchorPane anchorpaneid;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = UserModel.getInstance();
        String admin = "Admin";
        String eventer = "EventCoordinator";
        choiceboxid.getItems().add(admin);
        choiceboxid.getItems().add(eventer);


    }

    public void save(ActionEvent actionEvent) throws IOException {
        updateuser();
        switchsence();
    }

    private void updateuser() {

        try {
            String name = usernametxt.getText();
            String email = emailtxt.getText();
            userModel.updateUser(cntrl.customertable.getSelectionModel().getSelectedItem(), cntrl.customertable.getSelectionModel().getSelectedIndex()
                    , name, email, choiceboxid.getValue());
        } catch (Exception e) {
            setUpAlert("something went wrong please try again later ");
        }

    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void eixit(ActionEvent actionEvent) throws IOException {
        switchsence();
    }

    public void switchsence() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/adminpage.fxml"));
        Parent root = loader.load();
        Scene scene = exitid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpaneid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpaneid.getChildren().remove(anchorpaneid);
        });
        timeline.play();
    }

    public void setController(AdminController adminController) {
        this.cntrl = adminController;
        usernametxt.setText(cntrl.customertable.getSelectionModel().getSelectedItem().getUsername());
        emailtxt.setText(cntrl.customertable.getSelectionModel().getSelectedItem().getEmail());
    }


}
