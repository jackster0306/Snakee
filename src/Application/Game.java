package Application;

public class Game {
    Snake snake = new Snake();

    private void outofBounds()
    {
        /**
         * Creates variables m_MyFrame_xOut and m_MyFrame_yOut
         * Assigns the values that cause the snake to be out of bounds
         * Will be true if the requirements are met
         */
        boolean xOut = (snake.x <= 0 || snake.x >= (870));
        boolean yOut = (snake.y <= 0 || snake.y >= (560));
        /**
         * Checks to see if m_MyFrame_xOut or m_MyFrame_yOut are true
         * If they are, snake is no longer visible, game ends
         */
        if (xOut || yOut)
        {
            snake.alive = false;
        }
    }
}
