import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Level {

    public int width;
    public int height;

    public Tile[][] tiles;

       private static void printArray(int[] anArray) {
          for (int i = 0; i < anArray.length; i++) {
             if (i > 0) {
                System.out.printf(", %s::", i);
             }
             System.out.print(anArray[i]);
          }
       }

    public Level(String path){
        try{
            BufferedImage map = ImageIO.read(getClass().getResource(path));

            //why use this here?
            this.width = map.getWidth();
            this.height = map.getHeight();

            System.out.printf("%s, %s, %s, %s" , this.width, width, this.height, height);

            //stores all the rgb values in the image ??
            int[] pixels = new int[width*height];
            tiles = new Tile[width][height];

            // no idea about the offset and scansize
            // seems to store the map color values in the pixel array
            map.getRGB(0, 0, width, height, pixels, 0, width);

            System.out.println("");
            printArray(pixels);
            System.out.println("");

            for(int xx= 0; xx < width; xx++){
                for(int yy= 0; yy < height; yy++){
                    // no idea how it traverses
                    int val = pixels[xx + (yy*width)];

                    //wherever black print a new tile of violet color
                    if(val == 0xFF000000){
                        System.out.println("here");
                        System.out.printf("%s, %s" , xx, yy);
                        System.out.println("");

                        tiles[xx][yy] = new Tile(xx*32, yy*32);
                        System.out.println("here2");
                    }
                }
            }

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void render(Graphics g){
        for(int x = 0; x < width; x++){
            for(int y = 0; y < height; y++){
                if(tiles[x][y] != null){
                    tiles[x][y].render(g);
                }
            }
        }
    }
}
