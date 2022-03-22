package easv.GUI.Controller.Users;

import easv.BE.Ticket;
import easv.GUI.Controller.LoginController;
import easv.GUI.Model.TicketModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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

    private LoginController cntrl ;
    private ObservableList<Ticket> getalltickets ;
    private TicketModel ticketModel ;

    private String name ;
    private String when ;
    private String where ;
    private int price ;
    private int ticketid ;
    private String barcode ;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ticketModel = TicketModel.getInstance();
        getalltickets = FXCollections.observableArrayList();


        String path = "/resourse/tickettemplate.png";
        //  String text = "eide nowroz \n Zahra  ";
        // File input = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\ticket.jpg");
        // File output = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\images3.jpg");

        //addTixttoimage(text , "jpg" , input , output);
    }

    public void setController(LoginController loginController) {
    this.cntrl = loginController ;
        getalltickets.addAll(ticketModel.getuserTickets(cntrl.userid));
        choicebox.setItems(getalltickets);
    }
            // this method will add the data from each ticket in the user
    private static void addTixttoimage(String text, String name , String when , String where , String barcode , int price , int ticketid,String type,  File source, File destination) throws IOException {
        BufferedImage image = ImageIO.read(source);
        int imagetype = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;

        BufferedImage bold = new BufferedImage(image.getWidth() , image.getHeight() , imagetype);

        Graphics2D w = (Graphics2D) bold.getGraphics();

        w.drawImage(image , 1 , 2 ,null);
        AlphaComposite alpha = AlphaComposite.getInstance(AlphaComposite.SRC_OVER , 0.9f );
        w.setComposite(alpha);
        w.setColor(Color.YELLOW);
        w.setFont(new Font(Font.SANS_SERIF , Font.BOLD , 15));

        FontMetrics fontMetrics = w.getFontMetrics();
        Rectangle2D rect = fontMetrics.getStringBounds(text , w );
        int centerX = (image.getWidth() - (int) rect.getWidth())/2 ;

        int centerY = image.getHeight()/2 ;

        w.drawString(text , centerX , centerY);
        ImageIO.write(bold ,type , destination);
        w.dispose();
    }

}
