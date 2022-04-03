package easv.GUI.Controller.Users;

import com.jfoenix.controls.JFXButton;
import easv.BE.Ticket;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.UserModel;
import easv.GUI.Model.util.ModelException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

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
    public JFXButton updateinfiid;

    private LoginController cntrl;
    private TicketModel ticketModel;
    private UserModel userModel ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();
        userModel = UserModel.getInstance();
    }

    public void setController(LoginController loginController) {
        this.cntrl = loginController;
        try {
            choicebox.setItems(ticketModel.getuserTickets(cntrl.userid));
        } catch (Exception e) {
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

    public void getimage(ActionEvent event) {
        Image image = new javafx.scene.image.Image(choicebox.getValue().getInfo());
        imageid.setImage(image);
        System.out.println(choicebox.getValue().getInfo());

    }

    public void updatemyinfobtn(ActionEvent event) {
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
        Button button = new Button();
        button.setText("update");
        grid.add(new Label("Username:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
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
                userModel.updateUserinuserpage(cntrl.user, username.getText(),email.getText(),"Customer");
                userModel.updatepassword(cntrl.user.getId() , password.getText());
            } catch (ModelException e) {
                setUpAlert("user cant be updated ");
            }
        });
    }


}
