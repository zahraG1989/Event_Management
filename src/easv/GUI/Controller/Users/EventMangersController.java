package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXComboBox;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.EventModel;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.UserModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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

    private LoginController cntrl ;

    private EventModel  eventModel ;
    private ObservableList<Event> getallevents ;

    private UserModel userModel;
    private ObservableList<User> getAllusers;

    private TicketModel ticketModel ;
    private ObservableList<Ticket> getAllticket ;

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
        }

    }


    public void getuserinevent(){
        userModel = UserModel.getInstance();
        getAllusers = FXCollections.observableArrayList();

        tableviewusers.setItems(getAllusers);

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        userticket.setCellValueFactory(new PropertyValueFactory<>("password"));
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


    public void createeventbtn(ActionEvent actionEvent) {
    }

    public void createticketbtn(ActionEvent actionEvent) {
    }

    public void deleteeventbtn(ActionEvent actionEvent) {
    }

    public void deleteticketbtn(ActionEvent actionEvent) {
    }

    public void updateeventbtn(ActionEvent actionEvent) {
    }

    public void updateticketbtn(ActionEvent actionEvent) {
    }

    public void backbtn(ActionEvent actionEvent) {
    }

    public void setController(LoginController loginController) {
        this.cntrl = loginController ;
    }


}
/*
         public void getmoviesincat(MouseEvent mouseEvent) {
        if (tableviewCategories.getSelectionModel().getSelectedIndex() != -1) {
            movieInCategory.getItems().clear();
            List<Movie> moviesinlist = tableviewCategories.getSelectionModel().getSelectedItem().getListOfMovies();
            for( int i = 0 ; i < moviesinlist.size() ; i++){
                movieInCategory.getItems().add(moviesinlist.get(i));
            }


        }
    }
         */