package easv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch();
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader fxmlLoader= new FXMLLoader(getClass().getResource("/easv/gui/view/mainWindow.fxml"));
        Parent root=fxmlLoader.load();
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

    }
}