package Application;

import java.awt.*;

public class Snake extends SnakeObject{
    int direction;
    // 0 is Up
    // 1 is Down
    // 2 is Left
    // 3 is Right



    public Snake(){
        this.x = 100;
        this.y = 100;
        this.alive = false;
        this.direction = 3;
    }

    public int GetDirection(){
        return direction;
    }


    @Override
    public void Draw(Graphics g) {

    }
}
