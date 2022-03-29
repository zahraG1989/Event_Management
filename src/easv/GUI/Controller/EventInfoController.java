package easv.GUI.Controller;


import com.barcodelib.barcode.Linear;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import easv.BE.Ticket;
import easv.BE.User;
import easv.GUI.Controller.Users.CustomerController;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.UserModel;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EventInfoController implements Initializable {
    @FXML
    public ImageView imageid;
    @FXML
    public Label eventnameid;
    @FXML
    public Label infoid;
    @FXML
    public Label participantsid;
    @FXML
    public Label locationid;
    @FXML
    public Label locguideid;
    @FXML
    public TilePane tilepaneid;
    @FXML
    public JFXButton backid;
    @FXML
    public StackPane stackpaneid;
    @FXML
    public AnchorPane anchorid;
    @FXML
    private MainWindowController cntrl ;
    @FXML
    private TicketModel ticketModel ;

    public ObservableList<Ticket> tickets ;
    public UserModel userModel ;
    @FXML
    private VBox vBox ;
    private  ObservableList<User> users ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    ticketModel = TicketModel.getInstance();
    userModel = UserModel.getInstance();

    users = userModel.getAllUsers();
        // tickets = ticketModel.
        tilepaneid.setHgap(100);


    }

    public void setController(MainWindowController mainWindowController) {
       this.cntrl = mainWindowController;
       Image i = new Image(cntrl.imagepath);
       imageid.setImage(i);
       eventnameid.setText(cntrl.name);
       infoid.setText(cntrl.notes);
        String s =String.valueOf(cntrl.participants);

       locationid.setText(cntrl.loc);
        locguideid.setText(cntrl.locationGuidance);
        showtickets();

    }

    public void showtickets(){

        tickets = ticketModel.getticketinevent(cntrl.eventid);

        for(Ticket t : tickets){
            String s =String.valueOf(t.getTicketprice());
            Label price = new Label(s);
           price.setTextFill(javafx.scene.paint.Color.BLACK);
           javafx.scene.text.Font font = new javafx.scene.text.Font( Font.PLAIN);
            price.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
            Label btn = new Label();
            btn.setTextFill(javafx.scene.paint.Color.BLACK);
            btn.setText(t.getType());
            btn.setFont(javafx.scene.text.Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

            vBox = new VBox();

            vBox.getChildren().add(price);
            vBox.getChildren().add(btn);
            vBox.setBackground(new Background(new BackgroundFill(javafx.scene.paint.Paint.valueOf("#A3CB38"), CornerRadii.EMPTY, Insets.EMPTY)));
            tilepaneid.getChildren().add(vBox);
            vBox.setOnMousePressed(event -> {
                int answer = JOptionPane.showConfirmDialog(null, "Do you have an Account ");
                System.out.println(answer);

                if(answer == 0){
                    System.out.println("user said yes ,, he have an account ,, so we need to create page where he have to enter his name and password ");
                    GridPane grid = new GridPane();
                    grid.setHgap(10);
                    grid.setVgap(10);
                    grid.setPadding(new Insets(0, 10, 0, 10));
                    final javafx.scene.control.TextField username = new TextField();
                    username.setPromptText("Username");
                    final PasswordField password = new PasswordField();
                    password.setPromptText("Password");
                    Button button = new Button();
                    button.setText("Log");
                    grid.add(new Label("Username:"), 0, 0);
                    grid.add(username, 1, 0);
                    grid.add(new Label("Password:"), 0, 1);
                    grid.add(password, 1, 1);
                    grid.add(button ,1 , 2);
                    Stage stage = new Stage();
                    Scene scene = new Scene(grid);
                    stage.setScene(scene);
                    stage.show();
                    button.setOnAction(event1 -> {
                     User user  =  userModel.verifyUsers(username.getText(), password.getText());
                        if(user != null){
                            if(user.getUserType().equals("Customer")) {
                                System.out.println(username.getText() + " " + password.getText());
                                try {

                                    File input = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\newtemplate.png");
                                    File output = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\newticket.jpg");
                                    ticketModel.createUsTiEv(cntrl.selectedevent ,t ,user , String.valueOf(output));
                                    String name = cntrl.selectedevent.getName()+" \n" + t.getType()+" \n" + user.getUsername()+" \n" + t.getInfo() +" \n" + t.getTicketprice() ; // event name and ticket name
                                    ByteArrayOutputStream out = QRCode.from(name).to(ImageType.PNG).stream();
                                    File f = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\qrcode.png");
                                    FileOutputStream fos = new FileOutputStream(f);
                                    fos.write(out.toByteArray());
                                    fos.flush();

                                    BufferedImage img = null ;
                                    try {
                                         img = ImageIO.read(f);
                                    } catch (IOException e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }

                                    addTixttoimage(img ,user.getUsername() , cntrl.selectedevent.getName() , cntrl.selectedevent.getStartevent().toString() , cntrl.selectedevent.getLocation(),t.getTicketprice(),t.getId(),t.getType() , "jpg" , input , output);
                                    logintouser();
                                } catch (Exception e) {
                                   Alert alert = new Alert(Alert.AlertType.ERROR);
                                   alert.setContentText("this user already have ticket for this Event ");
                                }
                            }
                        }else{
                            System.out.println("Wrong user m8");
                        }
                        stage.close();
                            });

                }else if ( answer == 1){
                    try {
                        createuser();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("user said no he doesnot have an account so he have to create one ");
                }
            });
        }
    }

    public void logintouser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/Users/customerpage.fxml"));
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

    public void createuser() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/easv/GUI/View/CreateAccount.fxml"));
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



    private static void addTixttoimage(BufferedImage image2 , String username, String name , String when , String where  , int price , int ticketid,String tickettype ,String type, File source, File destination) throws IOException {

        BufferedImage image = ImageIO.read(source);
        int imagetype = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;

        BufferedImage bold = new BufferedImage(image.getWidth(), image.getHeight(), imagetype);

        Graphics2D w = (Graphics2D) bold.getGraphics();

        w.drawImage(image, 1, 2, null);
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        w.setComposite(alpha);
        w.setColor(Color.white);
        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(username, w);
        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = image.getHeight() / 2;

        w.drawString(name, 284, 90);
        w.drawString(where, 51, 65);
        w.drawString(when, 50, 30);
        w.drawString(String.valueOf(price), 45, 97);
        w.drawString(tickettype, 80, 97);
        w.drawString(String.valueOf(ticketid), 75, 131);
        w.drawString(username, 279, 123);
         w.drawImage(image2,null ,10, 147);
        ImageIO.write(bold, type, destination);
        w.dispose();
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

