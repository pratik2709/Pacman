import java.awt.*;

public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;

    public  static final int WIDTH = 640, HEIGHT = 480;

    public static final String Title = "Pac-Man";

    private Thread thread;

    public Game(){

    }

    public synchronized void start(){

    }

    public synchronized void stop(){

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

    }

}
