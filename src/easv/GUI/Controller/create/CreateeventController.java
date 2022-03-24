package easv.GUI.Controller.create;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.Users.EventMangersController;
import easv.GUI.Controller.Users.EventMangersController2;
import easv.GUI.Model.EventModel;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class CreateeventController implements Initializable {
    @FXML
    public StackPane stackid;
    @FXML
    public AnchorPane anchor;
    @FXML
    public TextField eventnameid;
    @FXML
    public TextField eventlocid;
    @FXML
    public TextField startdateid;
    @FXML
    public TextField enddateid;
    @FXML
    public TextArea eventinfoid;
    @FXML
    public TextArea locationguideid;
    @FXML
    public JFXButton backid;
    public JFXButton saveid;
    @FXML
    private EventMangersController2 cntrl ;
    @FXML
    private EventModel eventModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = EventModel.getInstance();

    }

    public void save(ActionEvent actionEvent) throws ParseException {
        java.util.Date Ust = new Date();
        java.sql.Date date = new java.sql.Date(Ust.getTime());
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                // 2022-03-24 20:59
        Date date1 = dateFormat.parse(startdateid.getText());
        Date date2 = dateFormat.parse(enddateid.getText());
        long time1 = date1.getTime();
        long time2 = date2.getTime();
        Timestamp stamp1 = new Timestamp(time1);
        Timestamp stamp2 = new Timestamp(time2);
        // stamp1 will be the start event
        // stamp2 will be the end event

        String name = eventnameid.getText();
        String locations = eventlocid.getText();
        String notes = eventinfoid.getText();
        String locationGuidance = locationguideid.getText();
        String imagepath = "/resourse/icons8_java_64px.png" ;
        eventModel.createEvent(name , locations ,notes ,0 ,stamp1 , stamp2 , locationGuidance , imagepath);
        eventModel.updatethelist();
    }

    public void setController(EventMangersController2 eventMangersController) {
    this.cntrl = eventMangersController ;

    }

    public void back(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/EventMangers.fxml"));
        Parent root = loader.load();
        Scene scene = backid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(anchor);
        });
        timeline.play();
    }


}
