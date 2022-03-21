package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.GUI.Model.EventModel;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.UserModel;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
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
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    public TilePane tlepane;
    @FXML
    public JFXButton signin;
    @FXML
    public JFXButton loginin;
    @FXML
    public StackPane stackpne;
    @FXML
    public BorderPane borderpne;
    @FXML
    public VBox vBox ;
    @FXML
    public Image image ;

    @FXML
    ImageView view ;
    private EventModel eventModel ;
    private ObservableList<Event> listofEvents;
    public String name ;
    public String loc ;
    public String notes ;
    public int participants ;
    public Date startevent ;
    public Date endevent ;
    public String locationGuidance ;
    public String imagepath ;
    public int eventid ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       eventModel = EventModel.getInstance();
        listofEvents = eventModel.getAllEvents();



        for(Event e : listofEvents){

            imagepath = e.getImagepath();
         String s =String.valueOf(e.getParticipants());
         Label lbl = new Label(e.getName());
          image = new Image(imagepath);
         view = new ImageView(image);
            vBox = new VBox();
            vBox.getChildren().add(view);
            vBox.getChildren().add(lbl);
            tlepane.getChildren().add(vBox);
            vBox.setOnMousePressed(event -> {

                eventid = e.getId();
                imagepath = e.getImagepath();
                name = e.getName();
                loc = e.getLocation();
                notes = e.getNotes();
                participants = e.getParticipants();
                startevent = e.getStartevent();
                endevent = e.getEndevent();
                locationGuidance = e.getLocationGuidance();
                System.out.println(name + loc + notes + participants + startevent + endevent + locationGuidance);
                try {
                    showEventInfo();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        }


    }

    public void showEventInfo() throws IOException {
        FXMLLoader laoder = new FXMLLoader(getClass().getResource("/easv/GUI/View/EventInfo.fxml"));
        Parent root = laoder.load();
        laoder.<EventInfoController>getController().setController(this);
        Scene scene = vBox.getScene();
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

    public void aboutusbtn(ActionEvent actionEvent) {
    }

    public void blogbtn(ActionEvent actionEvent) {
    }

    public void exitbtn(ActionEvent actionEvent) {
    }
}
