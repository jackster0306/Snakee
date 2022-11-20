package Application;

import java.awt.*;

public abstract class SnakeObject
{
    int x;
    int y;

    public boolean alive;

    /**
     * Draw Method
     * @param g
     */
    public abstract void Draw(Graphics g);


}
