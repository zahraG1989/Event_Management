package easv.GUI.Controller.Users;

import easv.BE.Ticket;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.util.ModelException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import java.net.URL;
import java.util.ResourceBundle;

public class CustomerController implements Initializable {
    @FXML
    public StackPane stackpanid;
    @FXML
    public AnchorPane anchorpaneid;
    @FXML
    public ChoiceBox<Ticket> choicebox;
    @FXML
    public ImageView imageid;

    private LoginController cntrl ;
    private TicketModel ticketModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();

    }

    public void setController(LoginController loginController) {
    this.cntrl = loginController ;
        try {
            choicebox.setItems(ticketModel.getuserTickets(cntrl.userid));
        } catch (ModelException e) {
           setUpAlert("items are not available at the moment please try again later ");
        }
        choicebox.setOnAction(this::getimage);
    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void getimage(ActionEvent event){
            Image image = new javafx.scene.image.Image( choicebox.getValue().getInfo());
            imageid.setImage(image);
        System.out.println(choicebox.getValue().getInfo());

    }

}
