package easv.GUI.Controller;

import easv.BE.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MainWindowController implements Initializable {
    @FXML
    public TilePane tlepane;
    @FXML
    public VBox vbox;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Event> events = new ArrayList<>();

        Event e1 = new Event("dffdf","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        Event e2 = new Event("dffdf","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        Event e3 = new Event("dffdf","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        Event e4 = new Event("dffdf","dfdsf","dsfsdfsdf",0 ,null ,null,"dsfsdf");
        events.add(e1);
        events.add(e2);
        events.add(e3);
        events.add(e4);

       for(Event e : events){
           Label label = new Label(e.getName());
           vbox = new VBox();
           vbox.getChildren().add(label);
           tlepane.getChildren().add(vbox);
       }
    }

}
