package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.GUI.Controller.EventInfoController;
import easv.GUI.Controller.LoginController;
import easv.GUI.Controller.create.CreateTicketController;
import easv.GUI.Controller.create.CreateeventController;
import easv.GUI.Model.EventModel;
import easv.GUI.Model.TicketModel;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

public class EventMangersController implements Initializable {
    @FXML
    public TableView<Ticket> tableviewtickets;
    @FXML
    public TableView<User> tableviewusers;
    @FXML
    public TableView<Event> tableviewevents;
    @FXML
    public AnchorPane ancorid;
    @FXML
    public StackPane stackid;
    @FXML
    public TableColumn<Event , String > eventname;
    @FXML
    public TableColumn<Event , String > eventloc;
    @FXML
    public TableColumn<Event , Integer> usersineventalt;
    @FXML
    public TableColumn<Event , Date> startevent;
    @FXML
    public TableColumn<Event , Date> endevent;
    @FXML
    public TableColumn<Event , Integer> eventticketalt;

    @FXML
    public TableColumn<User , String> username;
    @FXML
    public TableColumn<User , String > userticket;
    @FXML
    public TableColumn<User , String > usercheckbox;
    @FXML
    public TableColumn<Ticket , String> tickettype;
    @FXML
    public TableColumn<Ticket , Integer> ticketprice;
    @FXML
    public TableColumn<Ticket , String > ticketinfo;


    public JFXButton createeventid;
    public JFXButton createticketid;
    public JFXButton deleteeventid;
    public JFXButton deleteticketid;
    public JFXButton updateeventid;
    public JFXButton updateticketid;
    public JFXButton backid;
    public TextField filter;

    private LoginController cntrl ;

    private EventModel  eventModel ;
    private ObservableList<Event> getallevents ;

    private UserModel userModel;
    private ObservableList<User> getAllusers;

    private TicketModel ticketModel ;
    private ObservableList<Ticket> getAllticket ;

    public int selectedeventid ;
    public Event selectedevent ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayEventsintableview();
        getuserinevent();
        displayTicketsInsideEvent();

    }

    public void getUsersinSelectedEvent(MouseEvent mouseEvent) {
        if(tableviewevents.getSelectionModel().getSelectedIndex() != -1){
            tableviewusers.getItems().clear();
            tableviewtickets.getItems().clear();
            getAllusers.addAll(userModel.getuserinevent(tableviewevents.getSelectionModel().getSelectedItem().getId()));
            getAllticket.addAll(ticketModel.getticketinevent(tableviewevents.getSelectionModel().getSelectedItem().getId()));
            selectedeventid = tableviewevents.getSelectionModel().getSelectedItem().getId();
             selectedevent = tableviewevents.getSelectionModel().getSelectedItem();
            System.out.println(selectedeventid);
            createeventid.setText("Modify");
        }

    }

    public void filteruser(KeyEvent keyEvent) {
        if(filter.getText() == null || filter.getText().length() <= 0 ){
            System.out.println(" the filter is empty ");
            tableviewusers.setItems(getAllusers);
        }
        else {
            System.out.println("something is added ");
            ObservableList<User> found ;

            found = userModel.searchforuser( getAllusers, filter.getText());

            if(found != null){
                tableviewusers.setItems(found);
            }
        }
    }


    public void getuserinevent(){
        userModel = UserModel.getInstance();
        getAllusers = FXCollections.observableArrayList();

        tableviewusers.setItems(getAllusers);

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        userticket.setCellValueFactory(new PropertyValueFactory<>("email"));
        usercheckbox.setCellValueFactory(new PropertyValueFactory<>("select"));
    }

    public void displayTicketsInsideEvent(){
        ticketModel = TicketModel.getInstance();
        getAllticket = FXCollections.observableArrayList();

        tableviewtickets.setItems(getAllticket);

        tickettype.setCellValueFactory(new PropertyValueFactory<>("type"));
        ticketinfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        ticketprice.setCellValueFactory(new PropertyValueFactory<>("ticketprice"));
    }

    public void displayEventsintableview(){
        eventModel = EventModel.getInstance();
        getallevents = FXCollections.observableArrayList();
        getallevents.addAll(eventModel.getAllEvents());

        tableviewevents.setItems(getallevents);

        eventname.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventloc.setCellValueFactory(new PropertyValueFactory<>("location"));
        startevent.setCellValueFactory(new PropertyValueFactory<>("startevent"));
        endevent.setCellValueFactory(new PropertyValueFactory<>("endevent"));

    }


    public void createeventbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/create/createevent.fxml"));
        Parent root = loader.load();
        loader.<CreateeventController>getController().setController(this);
        Scene scene = createticketid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(ancorid);
        });
        timeline.play();
    }

    public void createticketbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/create/createTicket.fxml"));
        Parent root = loader.load();
        loader.<CreateTicketController>getController().setController(this);
        Scene scene = createticketid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(ancorid);
        });
        timeline.play();
    }

    public void deleteeventbtn(ActionEvent actionEvent) {
        eventModel.deleteEvent(tableviewevents.getSelectionModel().getSelectedItem() ,tableviewevents.getSelectionModel().getSelectedIndex());
        ticketModel.updatethelist();
        tableviewevents.refresh();
    }

    public void deleteticketbtn(ActionEvent actionEvent) {
        ticketModel.deleteTicket(tableviewtickets.getSelectionModel().getSelectedItem() ,tableviewtickets.getSelectionModel().getSelectedIndex());
        ticketModel.updatethelist();
        tableviewtickets.refresh();
    }

    public void updateeventbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/create/createevent.fxml"));
        Parent root = loader.load();
        loader.<CreateeventController>getController().setController(this);
        Scene scene = createticketid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(ancorid);
        });
        timeline.play();
    }

    public void updateticketbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/create/createTicket.fxml"));
        Parent root = loader.load();
        loader.<CreateeventController>getController().setController(this);
        Scene scene = createticketid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(ancorid);
        });
        timeline.play();
    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = createticketid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(ancorid);
        });
        timeline.play();
    }

    public void setController(LoginController loginController) {
        this.cntrl = loginController ;
    }


    public void anchorouse(MouseEvent mouseEvent) {
        createeventid.setText("Create");
        createticketid.setText("Create");
    }

    public void ticketmouse(MouseEvent mouseEvent) {
        createticketid.setText("Modify");
    }

}
