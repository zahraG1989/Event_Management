package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

public class CreateAccountController {
    @FXML
    public StackPane stackpaneid;
    @FXML
    public AnchorPane anchorid;
    @FXML
    public JFXButton save;
    @FXML
    public JFXButton exitid;
    @FXML
    public TextField name;
    @FXML
    public TextField email;
    @FXML
    public TextField password;

    public void saveuser(ActionEvent actionEvent) {
    }

    public void exit(ActionEvent actionEvent) {
    }
}
