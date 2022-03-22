package easv.GUI.Controller.Users;

import easv.GUI.Controller.LoginController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class EventMangersController implements Initializable {
    @FXML
    public TableView tableviewtickets;
    @FXML
    public TableView tableviewusers;
    @FXML
    public TableView tableviewevents;
    @FXML
    public AnchorPane ancorid;
    @FXML
    public StackPane stackid;


    private LoginController cntrl ;

    public void setController(LoginController loginController) {
    this.cntrl = loginController ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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
}
