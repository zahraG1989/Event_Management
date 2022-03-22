package easv.GUI.Controller.create;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.Users.EventMangersController;
import easv.GUI.Model.TicketModel;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateTicketController implements Initializable {
    @FXML
    public StackPane stackid;
    @FXML
    public AnchorPane anchorid;
    @FXML
    public JFXButton saveid;
    @FXML
    public JFXButton exitid;
    @FXML
    public TextField tickettypeid;
    @FXML
    public TextField ticketpriceid;
    @FXML
    public TextArea infoid;

    private EventMangersController cntrl ;
    private TicketModel ticketModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();

    }

    public void savebtn(ActionEvent actionEvent) throws IOException {
        createTicekt();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/EventMangers.fxml"));
        Parent root = loader.load();
        Scene scene = exitid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackid.getChildren().remove(anchorid);
        });
        timeline.play();
    }

    public void exitbtn(ActionEvent actionEvent) {

    }

    public void setController(EventMangersController eventMangersController) {
       this.cntrl = eventMangersController ;

    }

    public void createTicekt(){
        //Event event, int id, String type, int ticketprice, String barcode, Date expirationdan, String info'
        String type = tickettypeid.getText();
        int price = Integer.parseInt(ticketpriceid.getText());
        String info = infoid.getText();
        ticketModel.createTicket(cntrl.selectedevent ,cntrl.selectedeventid , type , price ,"151" , cntrl.selectedevent.getEndevent() , info  );
    }


}
