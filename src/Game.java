import javax.swing.*;
import java.awt.*;

public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;

    public  static final int WIDTH = 640, HEIGHT = 480;

    public static final String Title = "Pac-Man";

    private Thread thread;

    public Game(){

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

    }

    public void render(){

    }

    @Override
    public void run(){
        while(isRunning){
            tick();
            render();

        }
    }

    public static void main(String[] args){
        Game game = new Game();
        JFrame frame = new JFrame();
        frame.setTitle(game.Title);
        frame.add(game);
        frame.setResizable(false);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        game.start();

    }

}
