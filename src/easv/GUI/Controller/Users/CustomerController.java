package easv.GUI.Controller.Users;

import easv.BE.Ticket;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.TicketModel;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
        choicebox.setItems(ticketModel.getuserTickets(cntrl.userid));

        for(Ticket t : ticketModel.getuserTickets(cntrl.userid)){
            Image image = new javafx.scene.image.Image(t.getInfo());
            imageid.setImage(image);
        }
    }

}
