package example;

import java.awt.*;

/**
 * SnakeObject Class
 * The snake itself
 * Has all the variables for the snake
 */
public abstract class SnakeObject
{
    /**
     * Creates variables x, y, i, w, h and l
     * x and y being the co-ordinates
     * w and h being the width and height
     * i being the image
     * l being whether the snake is alive or dead
     */
    public int GetX(){
        return this.x;
    }
    public int GetY(){
        return this.y;
    }
    public int GetW(){
        return this.w;
    }
    public int GetH(){
        return this.h;
    }
    public Image GetI(){
        return this.i;
    }
    int x;
    int y;
    Image i;
    int w;
    int h;

    public boolean l;

    /**
     * Draw Method
     * @param g
     */
    public abstract void Draw(Graphics g);

    /**
     * GetRectangle Method
     * Returns a new Rectangle at co-ordinates x y, of width w and height h
     * @return new Rectangle
     */
    public Rectangle GetRectangle()
    {
        return new Rectangle(x, y, w, h);
    }
}
