package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.LoginController;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class AdminController {
    @FXML
    public StackPane stackpne;
    @FXML
    public JFXButton exitid;
    @FXML
    public TableView customertable;
    @FXML
    public TableView eventcorditable;
    @FXML
    public TableView eventstable;

    public LoginController cntrl ;

    public void exitbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = exitid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpne.getChildren().remove(stackpne);
        });
        timeline.play();


    }

    public void deleteUserbtn(ActionEvent actionEvent) {

    }

    public void manageUserbtn(ActionEvent actionEvent) {

    }

    public void deleteEventerbtn(ActionEvent actionEvent) {

    }

    public void manageEventerbtn(ActionEvent actionEvent) {

    }

    public void createEventbtn(ActionEvent actionEvent) {

    }

    public void createUserbtn(ActionEvent actionEvent) {

    }

    public void setController(LoginController loginController) {
        this.cntrl = loginController;
    }
}
