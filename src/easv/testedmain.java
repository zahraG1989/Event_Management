package easv;




import com.barcodelib.barcode.Linear;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;


public class testedmain {
    public static void main(String[] args) throws Exception {

        Random random = new Random();
        StringBuilder sb = new StringBuilder();

        // first not 0 digit
        sb.append(random.nextInt(9) + 1);

        // rest of 11 digits
        for (int i = 0; i < 11; i++) {
            sb.append(random.nextInt(10));
        }
        System.out.println(sb);

        String sbd = String.valueOf(sb);
        System.out.println(sbd);
/*
        Calendar cal = Calendar.getInstance();
        java.sql.Timestamp timestamp = new Timestamp(cal.getTimeInMillis());

        System.out.println(timestamp);


        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Calendar calw = Calendar.getInstance();
        System.out.println(dateFormat.format(calw.getTime()));


        Calendar calendar = Calendar.getInstance();
        java.util.Date currentTime = calendar.getTime();
        System.out.println(currentTime);

 */

     //   String path = "/resourse/tickettemplate.png";

      //   File input = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\tickettemplate.png");
        // File output = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\images3.jpg");

    //    addTixttoimage("" , "teaparty" , "2022-12-15" , "koldingvej13" , "156264",50,1  , "jpg" , input , output);
    }

    private static void addTixttoimage(String text, String name , String when , String where , String barcode , int price , int ticketid , String type, File source, File destination) throws IOException {
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


        w.drawString(name , 477  , 56);
        w.drawString(where , 525  , 100);
        w.drawString(when , 518  , 75);
        w.drawString(String.valueOf(price), 521  , 126);
        w.drawString(barcode , 482  , 236);
        w.drawString(type , 476  , 185);

        ImageIO.write(bold ,type , destination);
        w.dispose();
    }


}
