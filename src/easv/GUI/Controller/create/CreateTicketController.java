package easv.GUI.Controller.create;

import com.jfoenix.controls.JFXButton;
import easv.GUI.Controller.Users.EventMangersController;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.util.ModelException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
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

    private EventMangersController cntrl;
    private TicketModel ticketModel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();

    }

    public void savebtn(ActionEvent actionEvent) throws IOException {

        if (cntrl.createticketid.getText().equals("Modify")) {
            System.out.println("updateing ");
            updateticket();
        } else {
            System.out.println("creating ");
            createTicekt();
        }
        switchsence();
    }

    public void switchsence() throws IOException {
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

    public void exitbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
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

    public void setController(EventMangersController eventMangersController) {
        this.cntrl = eventMangersController;
        if (cntrl.createticketid.getText().equals("Modify")) {
            updateticketnf();
        }
    }

    public void createTicekt() {
        try {
            String type = tickettypeid.getText();
            int price = Integer.parseInt(ticketpriceid.getText());
            String info = infoid.getText();
            ticketModel.createTicket(cntrl.selectedevent, cntrl.selectedeventid, type, price, cntrl.selectedevent.getEndevent(), info);
        } catch (Exception e) {
            setUpAlert("some thing went wrong please try again later ");
        }
    }

    protected void setUpAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Alert");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    public void updateticketnf() {
        String price = String.valueOf(cntrl.tableviewtickets.getSelectionModel().getSelectedItem().getTicketprice());
        tickettypeid.setText(cntrl.tableviewtickets.getSelectionModel().getSelectedItem().getType());
        ticketpriceid.setText(price);
        infoid.setText(cntrl.tableviewtickets.getSelectionModel().getSelectedItem().getInfo());

    }

    public void updateticket() {
        try {
            System.out.println(" updateticket()");
            int price = Integer.parseInt(ticketpriceid.getText());
            String info = infoid.getText();
            ticketModel.updateTicket(cntrl.tableviewtickets.getSelectionModel().getSelectedItem(), cntrl.tableviewtickets.getSelectionModel().getSelectedIndex(),
                    tickettypeid.getText(), price, info);
        } catch (Exception e) {
            setUpAlert("some thing went wrong please try again later ");

        }
    }
}
