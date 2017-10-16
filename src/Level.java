import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level {

    public int width;
    public int height;

    public Level(String path){
        try{
            BufferedImage map = ImageIO.read(getClass().getResource("/map/map.png"));
            this.width = map.getWidth();
            this.height = map.getHeight();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void render(Graphics g){

    }
}
