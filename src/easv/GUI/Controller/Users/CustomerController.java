package easv.GUI.Controller.Users;

import easv.BE.Ticket;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.TicketModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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
    private ObservableList<Ticket> getalltickets ;
    private TicketModel ticketModel ;

    private String name ;
    private String when ;
    private String where ;
    private int price ;
    private int ticketid ;
    private String barcode ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();
        getalltickets = FXCollections.observableArrayList();
    }

    public void setController(LoginController loginController) {
    this.cntrl = loginController ;
        getalltickets.addAll(ticketModel.getuserTickets(cntrl.userid));
        choicebox.setItems(getalltickets);
    }

}
