import java.awt.*;
import java.util.Random;

public class Enemy extends Rectangle{

    private int random = 0, smart = 1;

    private int state = random;

    private int right = 0, left = 1, up = 2, down = 3;

    private int dir = -1;

    public Random randomGen;

    private int time = 0;

    // 4 seconds
    private int targetTime = 60*4;

    private int speed = 2;

    public Enemy(int x, int y){
        randomGen = new Random();
        setBounds(x,y, 32, 32);
        dir = randomGen.nextInt(4);
    }



    public void tick(){

        if(state == random){

            if(dir == right){

                if(canMove(x+speed, y)){
                    x += speed;
                }
                else{
                    dir = randomGen.nextInt(4);
                }

            }
            else if (dir == left){
                if(canMove(x-speed, y)){
                    x -= speed;
                }
                else{
                    dir = randomGen.nextInt(4);

                }

            }
            else if (dir == up){
                if(canMove(x, y-speed)){
                    y -= speed;
                }
                else{
                    dir = randomGen.nextInt(4);

                }

            }
            else if (dir == down){
                if(canMove(x, y+speed)){
                    y += speed;
                }
                else{
                    dir = randomGen.nextInt(4);

                }

            }

        }
        else if (state == smart){

        }

    }

    private boolean canMove(int nextx, int nexty){
        Rectangle bounds = new Rectangle(nextx, nexty, width, height);
        Level level = Game.level;

        //level.tiles[0].length this shows up as 15 why ??
        for(int xx = 0; xx < level.tiles.length; xx++){
            for (int yy = 0; yy < level.tiles[0].length; yy++){
                System.out.println(level.tiles[0].length);
                if(level.tiles[xx][yy] != null){
                    if(bounds.intersects(level.tiles[xx][yy])){
                        return false;
                    }

                }

            }
        }
        return true;

    }

    public void render(Graphics g){
        g.drawImage(Texture.ghost, x, y, width,height,null);
//        g.setColor(Color.red);
//        g.fillRect(x, y, width, height);
    }
}
