package easv.GUI.Controller.create;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.Users.EventMangersController;
import easv.GUI.Model.EventModel;
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
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
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
    public ImageView imageviewspace;
    @FXML
    public Button upload;

    private EventMangersController cntrl;
    @FXML
    private EventModel eventModel;

   private  File file ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        eventModel = EventModel.getInstance();
        information();
        upload.setOnAction(event -> {
            Stage stage = new Stage();
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.
                    ExtensionFilter("PNG FILE","*png" , "*jpg"));
            file = fileChooser.showOpenDialog(stage);
            if(file != null) {
                Image image = new Image(file.toString());
                imageviewspace.setImage(image);
            }

        });
    }




    public void save(ActionEvent actionEvent) throws ParseException {
        if (cntrl.createeventid.getText().equals("Modify")) {
            System.out.println("updateing ");
            updateevent();
        } else {
            System.out.println("creating ");
            createEvent();
        }
    }
    public void information(){
        upload.setTooltip(new Tooltip("image has to be 64 x 64 pixel"));

    }

    private void updateevent() throws ParseException {

        try {
            String name = eventnameid.getText();
            String locations = eventlocid.getText();
            String notes = eventinfoid.getText();
            String locationGuidance = locationguideid.getText();
            String imagepath = "/resourse/icons8_java_64px.png";
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = dateFormat.parse(startdateid.getText());
            Date date2 = dateFormat.parse(enddateid.getText());
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            Timestamp stamp1 = new Timestamp(time1);
            Timestamp stamp2 = new Timestamp(time2);
            eventModel.updateEvent(cntrl.selectedevent, cntrl.tableviewevents.getSelectionModel().getSelectedIndex(), name, locations, notes, 0, stamp1, stamp2, locationGuidance, file.toString());
        } catch (Exception e) {
            setUpAlert(" some thing wnt wrong with the data base  please try again later ");
        }
    }
    /*

     */


    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }


    public void createEvent() throws ParseException {

        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date date1 = dateFormat.parse(startdateid.getText());
            Date date2 = dateFormat.parse(enddateid.getText());
            long time1 = date1.getTime();
            long time2 = date2.getTime();
            Timestamp stamp1 = new Timestamp(time1);
            Timestamp stamp2 = new Timestamp(time2);
            String name = eventnameid.getText();
            String locations = eventlocid.getText();
            String notes = eventinfoid.getText();
            String locationGuidance = locationguideid.getText();


            eventModel.createEvent(name, locations, notes, 0, stamp1, stamp2, locationGuidance,file.toString());
            eventModel.updatethelist();
        } catch (Exception e) {
            setUpAlert("database under heavy fire please try again later ");
        }

    }

    public void updataevent() {
        eventnameid.setText(cntrl.selectedevent.getName());
        eventlocid.setText(cntrl.selectedevent.getLocation());
        startdateid.setText(cntrl.selectedevent.getStartevent().toString());
        enddateid.setText(cntrl.selectedevent.getEndevent().toString());
        eventinfoid.setText(cntrl.selectedevent.getNotes());
        locationguideid.setText(cntrl.selectedevent.getLocationGuidance());
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


    public void setController(EventMangersController eventMangersController) {
        this.cntrl = eventMangersController;
        if (cntrl.createeventid.getText().equals("Modify")) {
            updataevent();
        }
    }
}
