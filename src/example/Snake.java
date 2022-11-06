package example;

public class Snake {
	
		private static final long serialVersionUID = -3641221053272056036L;


    // TODO: It needs renovation

    /**
     * Initialises variable moving, an integer to say whether the snake is moving or not
     */
    public static int moving;


    /**
     * move Method
     * @param x
     * Takes x and changes the variable moving to x, then returns moving to the caller
     * @return moving
     */
    public static int move(int x) {
        moving = x;
        return moving;
    }


    /**
     * Used to stop the snake by setting moving to 0
     */
    public static void stop() {
        moving = 0;
    }
}
