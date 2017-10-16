import java.awt.*;

public class Player extends Rectangle{

    //no idea
    private static final long serialVersionUID = 1L;

    public Player(int x, int y){
        setBounds(x, y, 32, 32);
    }

    public void tick(){

    }

    public void render(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
    }
}
