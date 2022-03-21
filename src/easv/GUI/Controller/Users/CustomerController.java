package easv.GUI.Controller.Users;

import easv.BE.Ticket;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.TicketModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
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
    public ChoiceBox choiceboxid;
    @FXML
    public ImageView imageid;

    private LoginController cntrl ;

    private ObservableList<Ticket> getalltickets ;
    private TicketModel ticketModel ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();

    }

    public void setController(LoginController loginController) {
    this.cntrl = loginController ;
        getalltickets = ticketModel.getuserTickets(cntrl.userid);
        choiceboxid.setItems(getalltickets);
        for(Ticket z : getalltickets)
            System.out.println(z.getType() + " "+ z.getTicketprice());

    }

}
