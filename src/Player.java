import java.awt.*;

public class Player extends Rectangle{

    //no idea
    private static final long serialVersionUID = 1L;

    public boolean right, left, up, down;
    private int speed = 4;


    public Player(int x, int y){
        setBounds(x, y, 32, 32);
    }

    public void tick(){
        if(right) x += speed;
        if(left) x -= speed;
        if(up) y -= speed;
        if(down) y += speed;
    }

    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }
}
