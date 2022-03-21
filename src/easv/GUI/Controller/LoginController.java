package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import easv.BE.User;
import easv.GUI.Controller.Users.CustomerController;
import easv.GUI.Model.UserModel;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    public StackPane stcpne;
    @FXML
    public AnchorPane anchorpne;
    @FXML
    public TextField usernameid;
    @FXML
    public TextField passwordid;
    @FXML
    public JFXButton loginid;
    @FXML
    public JFXButton backid;

    private UserModel userModel ;
    private ObservableList<User> users ;

    public int userid ;
    public String username ;
    public String userpassword ;
    public String useremail;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
     userModel = UserModel.getInstance();
     users = userModel.getAllUsers();

    }

    public void loginbtn(ActionEvent actionEvent) throws IOException {

        for(User user : users){
          //  System.out.println(user.getUsername() + " " + user.getPassword());
            if(usernameid.getText().equals(user.getUsername()) && passwordid.getText().equals(user.getPassword()) && user.getUserType().equals("Customer")){
                System.out.println(user.getId());
                userid = user.getId();
                username = user.getUsername();
                userpassword = user.getPassword();
                useremail = user.getEmail();
                logintouser();

            }else if (usernameid.getText().equals(user.getUsername()) && passwordid.getText().equals(user.getPassword()) && user.getUserType().equals("Admin")){
                logintoAdmin();

            }else if (usernameid.getText().equals(user.getUsername()) && passwordid.getText().equals(user.getPassword()) && user.getUserType().equals("EventCoordinator")){
                logintoEventCoordinatoer();

            }else {
                System.out.println("wrong data");
            }

        }
    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = loginid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stcpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stcpne.getChildren().remove(anchorpne);
        });
        timeline.play();
    }

    public void logintouser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/customerpage.fxml"));
        Parent root = loader.load();
        loader.<CustomerController>getController().setController(this);
        Scene scene = loginid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stcpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stcpne.getChildren().remove(anchorpne);
        });
        timeline.play();
    }

    public void logintoAdmin() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/adminpage.fxml"));
        Parent root = loader.load();
        Scene scene = loginid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stcpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stcpne.getChildren().remove(anchorpne);
        });
        timeline.play();
    }

    public void logintoEventCoordinatoer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/EventMangers.fxml"));
        Parent root = loader.load();
        Scene scene = loginid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stcpne.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stcpne.getChildren().remove(anchorpne);
        });
        timeline.play();
    }

}
