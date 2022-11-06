package example;
import java.awt.*;

/**
 * Paddle Class is never used
 */
public class Paddle {

    /**
     * Creates and initialises the variables BORDER_COLOR and INNER_COLOR, both constant variables
     */
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    public static final Color INNER_COLOR = Color.GREEN;

    /**
     * Creates and initialises the variable DEF_MOVE_AMOUNT, a constant variables
     */
    public static final int DEF_MOVE_AMOUNT = 5;

    /**
     * Creayes variables paddleFace, ballPoint, moveAmount, min and max
     */
    private Rectangle paddleFace;
    private Point ballPoint;
    private int moveAmount;
    private int min;
    private int max;


    /**
     * Paddle Method
     * @param ballPoint
     * @param width
     * @param height
     * @param container
     * Not used
     */
    public Paddle(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        paddleFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * Rectangle Method
     * @param width
     * @param height
     * @return new Rectangle
     * Used once by a method that isn't used, so is technically not used
     */
    public Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * move Method
     * Not used
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }

    /**
     * moveLeft Method
     * Not used
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * moveRight Method
     * Not used
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * stop Method
     * Not used
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * Shape Method
     * Not used
     */
    public Shape getPaddleFace(){
        return paddleFace;
    }

    /**
     * moveTo Method
     * @param p
     * Not used
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        paddleFace.setLocation(ballPoint.x - (int) paddleFace.getWidth()/2,ballPoint.y);
    }
}
