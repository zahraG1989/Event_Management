package easv.GUI.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public StackPane stcpne;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Label lbl = new Label();
        lbl.setText("Hey i am another window \n This window will allow the User to login to thier account \n" +
                "and see more ditalies of the event and how many users in the event  ");
        lbl.setLayoutX(400);
        lbl.setLayoutY(500);
       stcpne.getChildren().add(lbl);
    }
}
