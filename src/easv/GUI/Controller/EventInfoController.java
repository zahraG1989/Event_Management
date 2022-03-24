package easv.GUI.Controller;

import com.jfoenix.controls.JFXButton;
import easv.BE.Ticket;
import easv.BE.User;
import easv.GUI.Model.TicketModel;
import easv.GUI.Model.UserModel;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
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
    private TicketModel ticketModel ;
    public ObservableList<Ticket> tickets ;
    public UserModel userModel ;
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
       participantsid.setText(s);
       locationid.setText(cntrl.loc);
        locguideid.setText(cntrl.locationGuidance);
        showtickets();
    }

    public void showtickets(){

        tickets = ticketModel.getticketinevent(cntrl.eventid);

        for(Ticket t : tickets){
            String s =String.valueOf(t.getTicketprice());
            Label price = new Label(s);
            Label barcode = new Label(t.getBarcode());
            Button btn = new Button();
            btn.setText(t.getType());
            vBox = new VBox();
            vBox.getChildren().add(price);
            vBox.getChildren().add(barcode);
            vBox.getChildren().add(btn);
            tilepaneid.getChildren().add(vBox);
            vBox.setOnMousePressed(event -> {
                String name= JOptionPane.showInputDialog("Enter Name");
              User user = userModel.adduser(name ,"1234" ,"1234" ,"Customer");
                ticketModel.createUsTiEv(cntrl.selectedevent, t ,user );
                try {
                    String path = "/resourse/tickettemplate.png";

                    File input = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\tickettemplate.png");
                    File output = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\ticket.jpg");
                    addTixttoimage(name , cntrl.selectedevent.getName() , cntrl.selectedevent.getStartevent().toString() , cntrl.selectedevent.getLocation() , t.getBarcode(),t.getTicketprice(),t.getId(),t.getType() , "jpg" , input , output);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void addTixttoimage(String text, String name , String when , String where , String barcode , int price , int ticketid,String tickettype ,String type, File source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(source);
        int imagetype = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;

        BufferedImage bold = new BufferedImage(image.getWidth() , image.getHeight() , imagetype);

        Graphics2D w = (Graphics2D) bold.getGraphics();

        w.drawImage(image , 1 , 2 ,null);
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER , 0.9f );
        w.setComposite(alpha);
        w.setColor(Color.BLACK);
        w.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 15));

        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text , w );
        int centerX = (image.getWidth() - (int) rect.getWidth())/2 ;
        int centerY = image.getHeight()/2 ;


        w.drawString(name , 477  , 59);
        w.drawString(where , 525  , 100);
        w.drawString(when , 518  , 75);
        w.drawString(String.valueOf(price), 521  , 126);
        w.drawString(barcode , 482  , 236);
        w.drawString(tickettype , 476  , 185);
        w.drawString(String.valueOf(ticketid), 552  , 149);
        w.drawString(text , 482  , 210);

        ImageIO.write(bold ,type , destination);
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
