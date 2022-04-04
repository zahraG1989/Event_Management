package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import easv.BE.Event;
import easv.GUI.Model.EventModel;
import easv.GUI.Model.util.ModelException;
import javafx.animation.*;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    public TilePane tilePane;
    @FXML
    public JFXButton loginin;
    @FXML
    public StackPane stackpne;
    @FXML
    public BorderPane borderpne;
    @FXML
    public VBox vBox;
    @FXML
    public Image image;
    @FXML
    public JFXButton exitid;
    @FXML
    public AnchorPane anchorpane;
    @FXML
    ImageView view;
    @FXML
    public Label movinglbl ;

    private EventModel eventModel;
    private ObservableList<Event> listofEvents;
    public String name;
    public String loc;
    public String notes;
    public int participants;
    public Date startevent;
    public Date endevent;
    public String locationGuidance;
    public String imagepath;
    public int eventid;
    public Event selectedevent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        iniClock();
        eventModel = EventModel.getInstance();
        try {
            listofEvents = eventModel.getAllEvents();
        } catch (ModelException e) {
            setUpAlert("list are currently unavailable ");
        }

        for (Event e : listofEvents) {

            imagepath = e.getImagepath();
            Label lbl = new Label(e.getName());
            lbl.setTextFill(Color.LIMEGREEN);
            lbl.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            image = new Image(imagepath);
            view = new ImageView(image);
            vBox = new VBox();
            vBox.setAlignment(Pos.BASELINE_CENTER);
            vBox.getChildren().add(view);
            vBox.getChildren().add(lbl);
            tilePane.getChildren().add(vBox);
            vBox.setOnMousePressed(event -> {
                selectedevent = e;
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
                } catch (Exception ex) {
                    setUpAlert("something went wrong please try again later ");
                }
            });
        }
    }

    private void iniClock() {
        movinglbl.setText("made by zahra & mustafa");
        movinglbl.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
        Timeline clouck = new Timeline(new KeyFrame(Duration.ZERO , event -> {
            movinglbl.setLayoutX(movinglbl.getLayoutX() - 2);
            if(movinglbl.getLayoutX() <= -250 ){
                movinglbl.setLayoutX(700);
            }
        }) , new KeyFrame(Duration.millis(25)));
        clouck.setCycleCount(Animation.INDEFINITE);
        clouck.play();
    }




    public void showEventInfo() throws IOException {
        FXMLLoader laoder = new FXMLLoader(getClass().getResource("/easv/GUI/View/EventInfo.fxml"));
        Parent root = laoder.load();
        laoder.<EventInfoController>getController().setController(this);
        Scene scene = vBox.getScene();
        root.translateXProperty().set(scene.getWidth());
        stackpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
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
            stackpne.getChildren().remove(anchorpane);
        });
        timeline.play();
    }

    public void exitbtn(ActionEvent actionEvent) {
        stackpne.getChildren().clear();
    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
