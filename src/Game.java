import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable, KeyListener{

    private boolean isRunning = false;

    public  static final int WIDTH = 640, HEIGHT = 480;

    public static final String Title = "Pac-Man";

    private Thread thread;

    public static Player player;
    public static Level level;
    public static SpriteSheet spriteSheet;

    public Game(){
        Dimension dimension = new Dimension(Game.WIDTH, Game.HEIGHT);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        addKeyListener(this);
        player = new Player(Game.WIDTH/2, Game.HEIGHT/2);
        level = new Level("map.png");
        spriteSheet = new SpriteSheet("pacman.png");
        new Texture();
    }

    public synchronized void start(){
        if(isRunning) return;
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop(){
        if(!isRunning) return;
        isRunning = false;
        try{
            // wait for completion of the other thread
            thread.join();
        }
        catch (InterruptedException ex){
            ex.printStackTrace();

        }
    }

    public void tick(){
        player.tick();
        level.tick();
    }

    public void render(){
        //https://stackoverflow.com/questions/13590002/understand-bufferstrategy
        BufferStrategy bs = getBufferStrategy();
        if(bs == null){
            createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
        player.render(g);
        level.render(g);

        g.dispose();
        bs.show();
    }

    @Override
    public void run(){
        requestFocus();
        // need to understand how timing works in games
        // delta is time between 2 frames
        //http://gameprogrammingpatterns.com/game-loop.html
        int fps = 0;
        double timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        double targetTick = 60.0;
        double delta = 0;
        //number of nanoseconds per frame
        double ns = 1000000000/targetTick;


        while(isRunning){
            long now = System.nanoTime();
            delta += (now - lastTime)/ns;
            lastTime = now;

            // other than 1 the rest of the time the computer sleeps/skips
            while (delta >= 1){
                tick();
                render();
                fps++;
                delta--;
            }

            if(System.currentTimeMillis() - timer >= 1000){
//                System.out.println(fps);
                fps = 0;
                timer += 1000;
            }

        }
        stop();
    }

    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle(Game.Title);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = true;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = true;
        if(e.getKeyCode() == KeyEvent.VK_UP) player.up = true;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) player.right = false;
        if(e.getKeyCode() == KeyEvent.VK_LEFT) player.left = false;
        if(e.getKeyCode() == KeyEvent.VK_UP) player.up = false;
        if(e.getKeyCode() == KeyEvent.VK_DOWN) player.down = false;
    }
}
