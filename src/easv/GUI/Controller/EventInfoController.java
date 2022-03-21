package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventInfoController implements Initializable {
    public ImageView imageid;
    public Label eventnameid;
    public Label infoid;
    public Label participantsid;
    public Label locationid;
    public Label locguideid;
    public TilePane tilepaneid;
    public JFXButton backid;
    public StackPane stackpaneid;
    public AnchorPane anchorid;
    private MainWindowController cntrl ;
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setController(MainWindowController mainWindowController) {
       this.cntrl = mainWindowController;
       Image i = new Image(cntrl.imagepath);
       imageid.setImage(i);
       eventnameid.setText(cntrl.name);
       infoid.setText(cntrl.notes);
        String s =String.valueOf(cntrl.participants);
       participantsid.setText(s);
       locationid.setText(cntrl.loc);
        locguideid.setText(cntrl.locationGuidance);
    }

    public void backbtn(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
        Parent root = loader.load();
        Scene scene = backid.getScene();
        root.translateYProperty().set(scene.getHeight());
        stackpaneid.getChildren().add(root);
        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.seconds(1), kv);
        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(t -> {
            stackpaneid.getChildren().remove(anchorid);
        });
        timeline.play();
    }
}
