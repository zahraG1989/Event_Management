package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXButton;
import easv.BE.Event;
import easv.BE.Ticket;
import easv.BE.User;
import easv.GUI.Controller.LoginController;
import easv.GUI.Controller.create.CreateTicketController;
import easv.GUI.Controller.create.CreateeventController;
import easv.GUI.Model.EventModel;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.UserModel;
import easv.GUI.Model.util.ModelException;
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
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
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
    public TableColumn<Event, String> eventname;
    @FXML
    public TableColumn<Event, String> eventloc;
    @FXML
    public TableColumn<Event, Date> startevent;
    @FXML
    public TableColumn<Event, Date> endevent;
    @FXML
    public TableColumn<User, String> username;
    @FXML
    public TableColumn<User, String> useremail;
    @FXML
    public TableColumn<User, String> usercheckbox;
    @FXML
    public TableColumn<Ticket, String> tickettype;
    @FXML
    public TableColumn<Ticket, Integer> ticketprice;
    @FXML
    public TableColumn<Ticket, String> ticketinfo;


    public JFXButton createeventid;
    public JFXButton createticketid;
    public JFXButton deleteeventid;
    public JFXButton deleteticketid;
    public JFXButton backid;
    public TextField filter;

    private LoginController cntrl;

    private EventModel eventModel;
    private ObservableList<Event> getallevents;

    private UserModel userModel;


    private TicketModel ticketModel;
    private ObservableList<Ticket> getAllticket;

    public int selectedeventid;
    public Event selectedevent;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayEventsintableview();
        getuserinevent();
        displayTicketsInsideEvent();

    }

    public void getUsersinSelectedEvent(MouseEvent mouseEvent) {
        if (tableviewevents.getSelectionModel().getSelectedIndex() != -1) {
            tableviewusers.getItems().clear();
            tableviewtickets.getItems().clear();
            try {
                tableviewusers.setItems(userModel.getuserinevent(tableviewevents.getSelectionModel().getSelectedItem().getId()));
            } catch (ModelException e) {
                setUpAlert("something went wrong please try again later ");
            }
            try {
                tableviewtickets.setItems(ticketModel.getticketinevent(tableviewevents.getSelectionModel().getSelectedItem().getId()));
            } catch (ModelException e) {
                setUpAlert("something went wrong please try again later ");
            }
            selectedeventid = tableviewevents.getSelectionModel().getSelectedItem().getId();
            selectedevent = tableviewevents.getSelectionModel().getSelectedItem();
            System.out.println(selectedeventid);
            createeventid.setText("Modify");
        }

    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void filteruser(KeyEvent keyEvent) {
        if (filter.getText() == null || filter.getText().length() <= 0) {
            System.out.println(" the filter is empty ");
            try {
                tableviewusers.setItems(userModel.getAllUsers());
            } catch (ModelException e) {
                setUpAlert("something went wrong please try again later ");
            }
        } else {
            System.out.println("something is added ");
            ObservableList<User> found;

            try {
                found = userModel.searchforuser(userModel.getAllUsers(), filter.getText());
                if (found != null) {
                    tableviewusers.setItems(found);
                }

            } catch (ModelException e) {
                setUpAlert("something went wrong please try again later ");
            }


        }
    }


    public void getuserinevent() {
        userModel = UserModel.getInstance();

        try {
            tableviewusers.setItems(userModel.getAllUsers());
        } catch (ModelException e) {
            setUpAlert("something went wrong please try again later ");

        }

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        useremail.setCellValueFactory(new PropertyValueFactory<>("email"));
        usercheckbox.setCellValueFactory(new PropertyValueFactory<>("select"));
    }

    public void displayTicketsInsideEvent() {
        ticketModel = TicketModel.getInstance();
        getAllticket = FXCollections.observableArrayList();

        tableviewtickets.setItems(getAllticket);

        tickettype.setCellValueFactory(new PropertyValueFactory<>("type"));
        ticketinfo.setCellValueFactory(new PropertyValueFactory<>("info"));
        ticketprice.setCellValueFactory(new PropertyValueFactory<>("ticketprice"));
    }

    public void displayEventsintableview() {
        eventModel = EventModel.getInstance();
        getallevents = FXCollections.observableArrayList();
        try {
            getallevents.addAll(eventModel.getAllEvents());
        } catch (ModelException e) {
            setUpAlert("something went wrong please try again later ");
        }

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
        if(tableviewevents.getSelectionModel().getSelectedIndex() != -1) {
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
        }else{
            setUpAlert("may zahra can look into this ");
        }
    }

    public void deleteeventbtn(ActionEvent actionEvent) {
      if(tableviewevents.getSelectionModel().getSelectedIndex() != -1) {
          int answer = JOptionPane.showConfirmDialog(null, "are you sure you want to delete this event ");
          if(answer == 0) {
              try {
                  eventModel.deleteEvent(tableviewevents.getSelectionModel().getSelectedItem(), tableviewevents.getSelectionModel().getSelectedIndex());
                  ticketModel.updatethelist();
              } catch (ModelException e) {
                  setUpAlert("event cant be delete at te moment please try again later ");
              }
              tableviewevents.refresh();
          }
      }else {
          setUpAlert("please select event ");
      }

    }

    public void deleteticketbtn(ActionEvent actionEvent) {
        try {
            ticketModel.deleteTicket(tableviewtickets.getSelectionModel().getSelectedItem(), tableviewtickets.getSelectionModel().getSelectedIndex());
            ticketModel.updatethelist();
        } catch (ModelException e) {
            setUpAlert("ticket cant be deleted at the moment please try again later ");
        }

        tableviewtickets.refresh();
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
        this.cntrl = loginController;
    }


    public void anchorouse(MouseEvent mouseEvent) {
        try {
            createeventid.setText("Create");
            createticketid.setText("Create");
            tableviewusers.getItems().clear();
            tableviewusers.setItems(userModel.getAllUsers());
        } catch (Exception e) {
            setUpAlert("something went wrong please try again later ");

        }

    }

    public void ticketmouse(MouseEvent mouseEvent) {
        createticketid.setText("Modify");
    }

    public void updateevenmanagerinfobtn(ActionEvent event) {

        GridPane grid = new GridPane();
        grid.setStyle("-fx-background-color:#64dd17 ");
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(0, 10, 0, 10));
        TextField username = new TextField();
        username.setText(cntrl.user.getUsername());
        username.setPromptText("Username");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        TextField email = new TextField();
        email.setText(cntrl.user.getEmail());
        Button button = new Button();
        button.setText("update");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);
        grid.add(new Label("email") , 0 , 2);
        grid.add(email ,1 , 2 );
        grid.add(button, 1, 3);
        Stage stage = new Stage();
        Scene scene = new Scene(grid);
        stage.setScene(scene);
        stage.show();
        button.setOnAction(event1 -> {
            try {
                userModel.updateUserinuserpage(cntrl.user, username.getText(),email.getText(),"EventCoordinator");
                userModel.updatepassword(cntrl.user.getId() , password.getText());
            } catch (ModelException e) {
                setUpAlert("user cant be updated ");
            }
        });
    }
}
