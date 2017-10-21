import java.awt.*;

public class Enemy extends Rectangle{
    public Enemy(int x, int y){
        setBounds(x,y, 32, 32);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.drawImage(Texture.ghost, x, y, width,height,null);
//        g.setColor(Color.red);
//        g.fillRect(x, y, width, height);
    }
}
