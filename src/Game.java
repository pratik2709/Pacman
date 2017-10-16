import java.awt.*;

public class Game extends Canvas implements Runnable{

    private boolean isRunning = false;

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
