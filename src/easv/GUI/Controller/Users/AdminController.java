package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXButton;
import easv.BE.Event;
import easv.BE.User;
import easv.GUI.Controller.LoginController;
import easv.GUI.Controller.create.CreatePremotController;
import easv.GUI.Controller.create.CreateeventController;
import easv.GUI.Model.EventModel;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
    public TableView<Event> eventstable;
    @FXML
    public LoginController cntrl;
    @FXML
    public AnchorPane anchorid;
    @FXML
    public TableColumn<User, String> cutomername;
    @FXML
    public TableColumn<User, String> customeremail;
    @FXML
    public TableColumn<Event, String> eventname;
    @FXML
    public TableColumn<Event, String> eventlocation;
    @FXML
    public TableColumn<Event, Date> evestartdate;
    @FXML
    public TableColumn<Event, Date> eventenddate;
    @FXML
    public TableColumn<User, String> customertyoe;
    @FXML
    public TextField filter;
    @FXML
    public JFXButton createid;

    private UserModel userModel;
    private EventModel eventModel;
    private ObservableList<Event> listofEvents;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayinfo();
    }

    public void displayinfo() {

        try {
            eventModel = EventModel.getInstance();
            listofEvents = FXCollections.observableArrayList();
            listofEvents.addAll(eventModel.getAllEvents());
        } catch (Exception e) {
            setUpAlert("list of events cant be initialized please try again later  ");
        }

        try {
            userModel = UserModel.getInstance();
            eventModel = EventModel.getInstance();
            customertable.setItems(userModel.getAllUsers());
        } catch (Exception e) {
            setUpAlert("something went wrong please try again later ");

        }
        cutomername.setCellValueFactory(new PropertyValueFactory<>("username"));
        customeremail.setCellValueFactory(new PropertyValueFactory<>("email"));
        customertyoe.setCellValueFactory(new PropertyValueFactory<>("userType"));


        eventstable.setItems(listofEvents);
        eventname.setCellValueFactory(new PropertyValueFactory<>("name"));
        eventlocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        evestartdate.setCellValueFactory(new PropertyValueFactory<>("startevent"));
        eventenddate.setCellValueFactory(new PropertyValueFactory<>("endevent"));

    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void searcher(KeyEvent keyEvent) {
        if (filter.getText() == null || filter.getText().length() <= 0) {
            System.out.println(" the filter is empty ");
            try {
                customertable.setItems(userModel.getAllUsers());
            } catch (Exception e) {
                setUpAlert("something went wrong please try again later ");

            }
        } else {
            System.out.println("something is added ");
            ObservableList<User> found;

            try {
                found = userModel.searchforuser(userModel.getAllUsers(), filter.getText());
                if (found != null) {
                    customertable.setItems(found);
                }
            } catch (Exception e) {
                setUpAlert("something went wrong please try again later ");

            }

        }
    }

    public void deleteUserbtn(ActionEvent actionEvent) {
        if (customertable.getSelectionModel().getSelectedIndex() != -1) {
            try {
                userModel.deleteUser(customertable.getSelectionModel().getSelectedItem(), customertable.getSelectionModel().getSelectedIndex());
                userModel.updatethelist();
                customertable.refresh();
            } catch (Exception e) {
                setUpAlert("something went wrong please try again later ");

            }

        }
    }

    public void createUserbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/create/create_premot.fxml"));
        Parent root = loader.load();
        loader.<CreatePremotController>getController().setController(this);
        Scene scene = createid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpne.getChildren().remove(anchorid);
        });
        timeline.play();

    }


    public void setController(LoginController loginController) {
        this.cntrl = loginController;
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


    public void updatebtn(ActionEvent event) {
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
        javafx.scene.control.Button button = new Button();
        button.setText("update");
        grid.add(new javafx.scene.control.Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new javafx.scene.control.Label("Password:"), 0, 1);
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
                if(!username.getText().isEmpty() || !password.getText().isEmpty() || !email.getText().isEmpty()) {
                    userModel.updateUserinuserpage(cntrl.user, username.getText(), email.getText(), "Admin");
                    userModel.updatepassword(cntrl.user.getId(), password.getText());
                }else {
                    setUpAlert("you left field empty ");
                }
            } catch (ModelException e) {
                setUpAlert("user cant be updated ");
            }
        });
    }

    public void mailbtn(ActionEvent event) throws URISyntaxException, IOException {
        if(customertable.getSelectionModel().getSelectedIndex() != -1) {
            Desktop desktop;
            if (Desktop.isDesktopSupported()
                    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
                URI mailto = new URI("mailto:" + customertable.getSelectionModel().getSelectedItem().getEmail() + "?subject=Hello%20World");
                desktop.mail(mailto);
            } else {
                // TODO fallback to some Runtime.exec(..) voodoo?
                throw new RuntimeException("desktop doesn't support mailto; mail is dead anyway ;)");
            }
        }else {
            setUpAlert("please select user ");
        }
    }
}
