package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXButton;
import easv.BE.Event;
import easv.BE.User;
import easv.GUI.Controller.LoginController;
import easv.GUI.Controller.create.CreateeventController;
import easv.GUI.Model.EventModel;
import easv.GUI.Model.UserModel;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    @FXML
    public StackPane stackpne;
    @FXML
    public JFXButton exitid;
    @FXML
    public TableView<User> customertable;
    @FXML
    public TableView<User> eventcorditable;
    @FXML
    public TableView<Event> eventstable;
    @FXML
    public JFXButton createUserid;
    @FXML
    public LoginController cntrl ;
    @FXML
    public AnchorPane anchorid;
    @FXML
    public TableColumn<User, String> cutomername;
    @FXML
    public TableColumn<User , String> customeremail;
    @FXML
    public TableColumn<User , String> evecorname;
    @FXML
    public TableColumn<User , String> evecoremail;
    @FXML
    public TableColumn<Event ,String > eventname;
    @FXML
    public TableColumn<Event , String > eventlocation;
    @FXML
    public TableColumn<Event , Date> evestartdate;
    @FXML
    public TableColumn<Event , Date> eventenddate;

    private UserModel userModel ;
    private EventModel eventModel ;
   private ObservableList<User> listofusers;
   private ObservableList<Event> listofEvents ;
   private ObservableList<User> listofeventcoordinatoers ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
       displayinfo();
    }

    public void displayinfo(){
        userModel = UserModel.getInstance();
        eventModel = EventModel.getInstance();

        listofusers = FXCollections.observableArrayList();
        listofusers.addAll(userModel.getAllUsers());

        listofEvents = FXCollections.observableArrayList();
        listofEvents.addAll(eventModel.getAllEvents());

        listofeventcoordinatoers = FXCollections.observableArrayList();
        listofeventcoordinatoers.addAll(userModel.getAlleventcoordinatoers());

        customertable.setItems(listofusers);
        cutomername.setCellValueFactory(new PropertyValueFactory<>("username"));
        customeremail.setCellValueFactory(new PropertyValueFactory<>("email"));

        eventcorditable.setItems(listofeventcoordinatoers);
        evecorname.setCellValueFactory(new PropertyValueFactory<>("username"));
        evecoremail.setCellValueFactory(new PropertyValueFactory<>("email"));

        eventstable.setItems(listofEvents);
        eventname.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        evestartdate.setCellValueFactory(new PropertyValueFactory<>("startevent"));
        eventenddate.setCellValueFactory(new PropertyValueFactory<>("endevent"));

    }

    public void deleteUserbtn(ActionEvent actionEvent) {
        if(customertable.getSelectionModel().getSelectedIndex() != -1 ){
            userModel.deleteUser(customertable.getSelectionModel().getSelectedItem() , customertable.getSelectionModel().getSelectedIndex());
            userModel.updatethelist();
            customertable.refresh();
        }
    }

    public void manageUserbtn(ActionEvent actionEvent) {

    }

    public void deleteEventerbtn(ActionEvent actionEvent) {
       if(eventcorditable.getSelectionModel().getSelectedIndex() != -1){
         userModel.deleteUser(eventcorditable.getSelectionModel().getSelectedItem(), eventcorditable.getSelectionModel().getSelectedIndex());
         userModel.updatethelist();
         eventcorditable.refresh();
       }
    }

    public void manageEventerbtn(ActionEvent actionEvent) {

    }

    public void createEventbtn(ActionEvent actionEvent) {

    }

    public void createUserbtn(ActionEvent actionEvent) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/adminpage.fxml"));
        Parent root = loader.load();
        //loader.<AdminController>getController().setController(this);
        Scene scene = createUserid.getScene();
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



    public void setController(LoginController loginController) {
        this.cntrl = loginController;
    }

    public void searcher(KeyEvent keyEvent) {
    }

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


}
