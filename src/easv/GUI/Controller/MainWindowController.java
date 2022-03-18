package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import easv.BE.Event;
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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    public TilePane tlepane;
    @FXML
    public VBox vbox;
    @FXML
    public JFXButton signin;
    @FXML
    public JFXButton loginin;
    @FXML
    public StackPane stackpne;
    @FXML
    public BorderPane borderpne;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Event> events = new ArrayList<>();

        Event e1 = new Event( 1, "1","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        Event e2 = new Event(2, "2","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        Event e3 = new Event(3,"3","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        Event e4 = new Event(4,"4","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);

       for(Event e : events){
           Label label = new Label(e.getName());
           vbox = new VBox();
           vbox.getChildren().add(label);

           tlepane.getChildren().add(vbox);


       }
    }



    public void login(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/loginpage.fxml"));
        Parent root = loader.load();
        Scene scene = loginin.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpne.getChildren().remove(borderpne);
        });
        timeline.play();
    }

    public void sign(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/CreateAccount.fxml"));
        Parent root = loader.load();
        Scene scene = loginin.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpne.getChildren().remove(borderpne);
        });
        timeline.play();
    }
}
