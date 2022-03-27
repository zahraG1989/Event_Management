package easv.GUI.Controller;


import com.barcodelib.barcode.Linear;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
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
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;
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
        Image image = new Image("/resourse/user.png");
       // ImageView view = new ImageView();
        for(Ticket t : tickets){
            String s =String.valueOf(t.getTicketprice());
            Label price = new Label(s);
            Label btn = new Label();
            btn.setText(t.getType());
           // view.setImage(image);
            vBox = new VBox();

            vBox.getChildren().add(price);
            vBox.getChildren().add(btn);

           // vBox.getChildren().add(view);
            tilepaneid.getChildren().add(vBox);
            vBox.setOnMousePressed(event -> {
                int answer = JOptionPane.showConfirmDialog(null, "Do you have an Account ");
                System.out.println(answer);
                //  0 yes 1 no 2 cancel
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
                        if(user != null) {
                            if (user.getUserType().equals("Customer")) {
                                System.out.println(username.getText() + " " + password.getText());
                                try {
                                    logintouser();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else{
                            System.out.println("Wrong user m8");
                        }
                        stage.close();
                            });
                            // this is freaking awesome ... implementing javafx inside java swing inside javafx


                }else if ( answer == 1){
                    try {
                        createuser();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println("user said no he doesnot have an account so he have to create one ");
                }

                /*
                String name= JOptionPane.showInputDialog("Enter Name");
                if(name != null ) {
                    User user = userModel.adduser(name, "1234", "1234", "Customer");
                    ticketModel.createUsTiEv(cntrl.selectedevent, t ,user );
                    try {
                        File input = new File("C:\\Movies\\Ticket 627_643 px (1).png");
                        File output = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\"+user.getUsername()+".jpg");

                        Linear barcodes = new Linear();
                        barcodes.setType(Linear.CODE128B);
                        barcodes.setData(t.getBarcode());
                        barcodes.setI(1);


                      BufferedImage  image2 = barcodes.renderBarcode();
                        Image image1  = new Image(String.valueOf(output));

                        addTixttoimage(image2 ,name , cntrl.selectedevent.getName() , cntrl.selectedevent.getStartevent().toString() , cntrl.selectedevent.getLocation() , t.getBarcode(),t.getTicketprice(),t.getId(),t.getType() , "jpg" , input , output);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
               else {
                    System.out.println("nothing selected ");
                }

                 */

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



    private static void addTixttoimage(BufferedImage image2,String text, String name , String when , String where , String barcode , int price , int ticketid,String tickettype ,String type, File source, File destination) throws IOException {

        BufferedImage image = ImageIO.read(source);
        int imagetype = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;

        BufferedImage bold = new BufferedImage(image.getWidth(), image.getHeight(), imagetype);

        Graphics2D w = (Graphics2D) bold.getGraphics();

        w.drawImage(image, 1, 2, null);
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.9f);
        w.setComposite(alpha);
        w.setColor(Color.BLACK);
        w.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));

        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text, w);
        int centerX = (image.getWidth() - (int) rect.getWidth()) / 2;
        int centerY = image.getHeight() / 2;


        w.drawString(name, 477, 59);
        w.drawString(where, 525, 100);
        w.drawString(when, 518, 75);
        w.drawString(String.valueOf(price), 521, 126);
        w.drawString(barcode, 482, 236);
        w.drawString(tickettype, 476, 185);
        w.drawString(String.valueOf(ticketid), 552, 149);
        w.drawString(text, 482, 210);
        w.drawImage(image2, null, -20, 250);
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

