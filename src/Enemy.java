import java.awt.*;
import java.util.Random;

public class Enemy extends Rectangle {

    private int random = 0, smart = 1, find_path = 2;

    private int state = smart;

    private int right = 0, left = 1, up = 2, down = 3;

    private int dir = -1;

    public Random randomGen;

    private int time = 0;

    // 4 seconds
    private int targetTime = 60 * 4;

    private int speed = 4;

    private int lastDir = -1;

    public Enemy(int x, int y) {
        randomGen = new Random();
        setBounds(x, y, 32, 32);
        dir = randomGen.nextInt(4);
    }


    public void tick() {

        if (state == random) {

            if (dir == right) {

                if (canMove(x + speed, y)) {
                    if(randomGen.nextInt(100) < 50) x += speed;
                } else {
                    dir = randomGen.nextInt(4);
                }

            } else if (dir == left) {
                if (canMove(x - speed, y)) {
                    if(randomGen.nextInt(100) < 50) x -= speed;
                } else {
                    dir = randomGen.nextInt(4);

                }

            } else if (dir == up) {
                if (canMove(x, y - speed)) {
                    if(randomGen.nextInt(100) < 50) y -= speed;
                } else {
                    dir = randomGen.nextInt(4);

                }

            } else if (dir == down) {
                if (canMove(x, y + speed)) {
                    if(randomGen.nextInt(100) < 50) y += speed;
                } else {
                    dir = randomGen.nextInt(4);

                }

            }

            time++;
            if (time == targetTime) {
                state = smart;
                time = 0;
            }

        } else if (state == smart) {

            boolean move = false;

            //these if ensure to follow the player to its position
            if (x < Game.player.x) {
                if (canMove(x + speed, y)) {
                    if(randomGen.nextInt(100) < 50) x += speed;
                    move = true;
                    lastDir = right;
                }
            }

            if (x > Game.player.x) {
                if (canMove(x - speed, y)) {
                    if(randomGen.nextInt(100) < 50) x -= speed;
                    move = true;
                    lastDir = left;
                }
            }

            if (y < Game.player.y) {
                if (canMove(x, y + speed)) {
                    if(randomGen.nextInt(100) < 50) y += speed;
                    move = true;
                    lastDir = down;
                }
            }

            if (y > Game.player.y) {
                if (canMove(x, y - speed)) {
                    if(randomGen.nextInt(100) < 50) y -= speed;
                    move = true;
                    lastDir = up;
                }
            }

            if (x == Game.player.x && y == Game.player.y) {
                move = true;
            }

            if (!move) {
                //if the player is not found in the tick then find him!
                state = find_path;
            }

            time++;
            if(time == targetTime){
                state = random;
                time = 0;
            }

        } else if (state == find_path) {

            //right : either go up or down
            if (lastDir == right) {
                if (y < Game.player.y) {
                    if (canMove(x, y + speed)) {
                        if(randomGen.nextInt(100) < 50) y += speed;
                        state = smart;
                    }
                } else {
                    if (canMove(x, y - speed)) {
                        if(randomGen.nextInt(100) < 50) y -= speed;
                        state = smart;
                    }
                }
                //actually move ?
                if (canMove(x + speed, y)) {
                    if(randomGen.nextInt(100) < 50) x += speed;
                }
            }

            if (lastDir == left) {
                if (y < Game.player.y) {
                    if (canMove(x, y + speed)) {
                        if(randomGen.nextInt(100) < 50) y += speed;
                        state = smart;
                    }
                } else {
                    if (canMove(x, y - speed)) {
                        if(randomGen.nextInt(100) < 50) y -= speed;
                        state = smart;
                    }
                }

                if (canMove(x - speed, y)) {
                    if(randomGen.nextInt(100) < 50) x -= speed;
                }
            }

            if (lastDir == up) {
                if (x < Game.player.x) {
                    if (canMove(x + speed, y)) {
                        if(randomGen.nextInt(100) < 50) x += speed;
                        state = smart;
                    }
                } else {
                    if (canMove(x - speed, y)) {
                        if(randomGen.nextInt(100) < 50) x -= speed;
                        state=smart;
                    }
                }
                if (canMove(x, y - speed)) {
                    if(randomGen.nextInt(100) < 50) y -= speed;
                }
            }

            if (lastDir == down) {
                if (x < Game.player.x) {
                    if (canMove(x + speed, y)) {
                        if(randomGen.nextInt(100) < 50) x += speed;
                        state = smart;
                    }
                } else {
                    if (canMove(x - speed, y)) {
                        if(randomGen.nextInt(100) < 50) x -= speed;
                        state = smart;
                    }
                }
                if (canMove(x, y + speed)) {
                    if(randomGen.nextInt(100) < 50) y += speed;
                }
            }

            time++;
            if(time == targetTime){
                state = random;
                time = 0;
            }

        }

    }

    private boolean canMove(int nextx, int nexty) {
        Rectangle bounds = new Rectangle(nextx, nexty, width, height);
        Level level = Game.level;

        //level.tiles[0].length this shows up as 15 why ??
        for (int xx = 0; xx < level.tiles.length; xx++) {
            for (int yy = 0; yy < level.tiles[0].length; yy++) {
                if (level.tiles[xx][yy] != null) {
                    if (bounds.intersects(level.tiles[xx][yy])) {
                        return false;
                    }

                }

            }
        }
        return true;

    }

    public void render(Graphics g) {
        g.drawImage(Texture.ghost, x, y, width, height, null);
//        g.setColor(Color.red);
//        g.fillRect(x, y, width, height);
    }
}
