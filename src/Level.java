import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class Level {

    public int width;
    public int height;

    public Tile[][] tiles;
    public java.util.List<Apple> apples;
    public java.util.List<Enemy> enemies;

       private static void printArray(int[] anArray) {
          for (int i = 0; i < anArray.length; i++) {
             if (i > 0) {
                System.out.printf(", %s::", i);
             }
             System.out.print(anArray[i]);
          }
       }

    public Level(String path){
        apples = new ArrayList<Apple>();
        enemies = new ArrayList<Enemy>();
        try{
            BufferedImage map = ImageIO.read(getClass().getResource(path));

            //why use this here?
            this.width = map.getWidth();
            this.height = map.getHeight();


            //stores all the rgb values in the image ??
            int[] pixels = new int[width*height];
            tiles = new Tile[width][height];

            // no idea about the offset and scansize
            // seems to store the map color values in the pixel array
            map.getRGB(0, 0, width, height, pixels, 0, width);


            for(int xx= 0; xx < width; xx++){
                for(int yy= 0; yy < height; yy++){
                    // no idea how it traverses
                    int val = pixels[xx + (yy*width)];

                    //wherever black print a new tile of violet color
                    if(val == 0xFF000000){

                        tiles[xx][yy] = new Tile(xx*32, yy*32);
                    }
                    else if(val == 0xFF0000FF){
                        Game.player.x = xx*32;
                        Game.player.y = yy*32;
                    }
                    else if(val == 0xFFFF0000){
                        enemies.add(new Enemy(xx*32, yy*32));
                    }
                    else{
                        apples.add(new Apple(xx*32, yy*32));
                    }

                }
            }

        }
        catch (IOException ex){
            ex.printStackTrace();
        }

    }

    public void tick(){
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).tick();
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

        for(int i = 0; i < apples.size(); i++){
            apples.get(i).render(g);
        }

        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).render(g);
        }
    }
}
