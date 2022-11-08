package example;

/**
 * Class Snake is never used
 * Will not do maintenance until class is used
 */
public class Snake {
    private static final long serialVersionUID = -3641221053272056036L;


    // TODO: It needs renovation

    /**
     * Initialises variable m_Snake_moving, an integer to say whether the snake is m_Snake_moving or not
     */
    public static int m_Snake_moving;


    /**
     * move Method
     * @param x
     * Takes m_MyFrame_x and changes the variable m_Snake_moving to m_MyFrame_x, then returns m_Snake_moving to the caller
     * @return m_Snake_moving
     */
    public static int move(int x) {
        m_Snake_moving = x;
        return m_Snake_moving;
    }


    /**
     * Used to stop the snake by setting m_Snake_moving to 0
     */
    public static void stop() {
        m_Snake_moving = 0;
    }
}
