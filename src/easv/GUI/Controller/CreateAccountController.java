package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;

public class CreateAccountController {
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

    public void saveuser(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) throws IOException {
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
