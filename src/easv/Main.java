package easv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
       FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/easv/GUI/View/mainWindow.fxml"));
        Parent root = fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
       primaryStage.setTitle("MainWindow");
        primaryStage.setResizable(false);
        primaryStage.show();

       System.out.println("first test in Main class ");

        /*
        Dialog<String> dialog = new Dialog<String>();
        //Setting the title
        dialog.setTitle("Dialog");
        ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        ButtonType type1 = new ButtonType("yes" , ButtonBar.ButtonData.OK_DONE);
        //Setting the content of the dialog
        dialog.setContentText("This is a sample dialog");
        //Adding buttons to the dialog pane
        dialog.getDialogPane().getButtonTypes().add(type);
        dialog.getDialogPane().getButtonTypes().add(type1);
        //Setting the label
        Text txt = new Text("Click the button to show the dialog");
        Font font = Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12);
        txt.setFont(font);
        //Creating a button
        Button button = new Button("Show Dialog");
        //Showing the dialog on clicking the button
        button.setOnAction(e -> {
            dialog.showAndWait();
        });
        //Creating a vbox to hold the button and the label
        HBox pane = new HBox(15);
        //Setting the space between the nodes of a HBox pane
        pane.setPadding(new Insets(50, 150, 50, 60));
        pane.getChildren().addAll(txt, button);
        //Creating a scene object
        Scene scene = new Scene(new Group(pane), 595, 250, Color.BEIGE);
        primaryStage.setTitle("Dialog");
        primaryStage.setScene(scene);
        primaryStage.show();

         */
    }
}
