

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class testedmain {
    public static void main(String[] args) throws Exception {

        String text = "eide nowroz \n Zahra something ";
        System.out.println(text);
        File input = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\ticket.jpg");
        File output = new File("C:\\Users\\samkaxe\\Event_Management\\src\\resourse\\images3.jpg");

        addTixttoimage(text , "jpg" , input , output);
    }

    private static void addTixttoimage(String text, String type, File source, File destination) throws IOException {
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
