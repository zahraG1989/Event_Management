package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import easv.BE.User;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateAccountController implements Initializable {
    @FXML
    public StackPane stackpaneid;
    @FXML
    public AnchorPane anchorid;
    @FXML
    public JFXButton save;
    @FXML
    public JFXButton exitid;
    @FXML
    public TextField name;
    @FXML
    public TextField email;
    @FXML
    public TextField password;
    @FXML
    public TextField repeatpassword;

    private UserModel userModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        userModel = UserModel.getInstance();

    }

    public void saveUser(ActionEvent actionEvent) {
        if (!name.getText().isEmpty() || !email.getText().isEmpty() || !password.getText().isEmpty()) {
            if (password.getText().equals(repeatpassword.getText())) {
                User user = null;
                try {
                    user = userModel.verifyUsers(name.getText(), password.getText());
                } catch (Exception e) {
                    setUpAlert("cant verify this user at the moment ");
                }
                if (user == null) {
                    try {
                        userModel.adduser(name.getText(), password.getText(), email.getText(), "Customer");
                        goBack();
                    } catch (Exception e) {
                        setUpAlert("cant verify this user at the moment ");
                    }
                    JOptionPane.showMessageDialog(null, "Account created :D ");
                } else {
                    JOptionPane.showMessageDialog(null, "user already exsisted");

                }
            } else {
                JOptionPane.showMessageDialog(null, "Password-field doesn't match ");
            }
        } else {
            JOptionPane.showMessageDialog(null, "please fill out all the fields");
        }

    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void exit(ActionEvent actionEvent) throws IOException {
       goBack();
    }


    public void goBack() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = exitid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpaneid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpaneid.getChildren().remove(anchorid);
        });
        timeline.play();
    }


}
