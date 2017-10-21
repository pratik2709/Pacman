import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(String path){
        try{
            sheet = ImageIO.read(getClass().getResource(path));
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public BufferedImage getSprite(int xx, int yy){
        //because each sub image is 16*16
        return sheet.getSubimage(xx, yy, 16, 16);
    }
}
