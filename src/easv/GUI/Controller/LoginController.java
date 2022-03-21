package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
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

    /*
    private void refreshmovieincat() {
        movieInCategory.getItems().clear();
        refreshcategorylist();
        if (tableviewCategories.getSelectionModel().getSelectedIndex() != -1) {
            List<Movie> movieList = tableviewCategories.getSelectionModel().getSelectedItem().getListOfMovies();
            for( int i =0 ; i < movieList.size() ; i++ ){
                movieInCategory.getItems().add(movieList.get(i));
            }
        }
    }

     */

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loginbtn(ActionEvent actionEvent) throws IOException {

        if(usernameid.getText().equals("user") && passwordid.getText().equals("user")){
            System.out.println("hee");
            logintouser();
        }
        else
        if(usernameid.getText().equals("admin") && passwordid.getText().equals("admin")){
            logintoAdmin();
        }
        else
            if(usernameid.getText().equals("eventer") && passwordid.getText().equals("eventer")){
                logintoAdmin();
        }else {
                System.out.println("Wrong password");
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

}
