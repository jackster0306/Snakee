package Application;

/**
 * Contains the information for each Theme
 * @author Jack Gribble
 */
public class Theme {
    //Class variables
    private static String m_foodimg;
    private static String m_wallimg;
    private static String m_bombimg;
    public static String m_snakebodyimg;
    public static String m_snakeheadimg;
    private static String m_background = "PlayPaneSky";
    private static boolean m_snaketheme = true;
    private static String m_foodeffect;
    private static String m_walleffect;

    //Getters

    /**
     * Gets if the theme is the snake theme
     * @return true or false
     */
    public static boolean GetSnakeTheme(){return m_snaketheme;}

    /**
     * Gets the background filename
     * @return the background filename
     */
    public static String GetBackground(){
        return m_background;
    }

    /**
     * Gets the filepath for the image of the food
     * @return the filepath
     */
    public static String GetFoodImg(){
        return m_foodimg;
    }
    /**
     * Gets the filepath for the image of the snake head
     * @return the filepath
     */
    public static String GetSnakeHImg(){
        return m_snakeheadimg;
    }
    /**
     * Gets the filepath for the image of the snake body
     * @return the filepath
     */
    public static String GetSnakeBImg(){
        return m_snakebodyimg;
    }
    /**
     * Gets the filepath for the image of the wall
     * @return the filepath
     */
    public static String GetWallImg() {return m_wallimg;}
    /**
     * Gets the filepath for the image of the bomb
     * @return the filepath
     */
    public static String GetBombImg() {return m_bombimg;}
    /**
     * Gets the filepath for the sound when food is eaten
     * @return the filepath
     */
    public static String GetFoodSound(){return m_foodeffect;}
    /**
     * Gets the filepath for the sound when a wall is hit
     * @return the filepath
     */
    public static String GetWallSound(){return m_walleffect;}

    /**
     * Sets if the theme is the Snake theme
     * @param b true or false
     */
    public static void SetSnakeTheme(boolean b){m_snaketheme = b;}

    /**
     * Sets the variables to different things depending on the integer provided, setting the theme
     * @param i the integer that correlates to a theme
     */
    public Theme(int i) {
        switch(i){
            case 0:
                m_background = "PlayPaneSky";
                m_wallimg = "Resources/Images/brick-clipart-briks-10.png";
                m_bombimg = "Resources/Images/skull_bomb_by_alishavolkman_daf7ku4.png";
                m_foodimg = "Resources/Images/food-apple.png";
                m_snakeheadimg = "Resources/Images/snake-head-right.png";
                m_snakebodyimg = "Resources/Images/snake-body.png";
                m_foodeffect = "src/Resources/Music/eatingaudio.mp3";
                m_walleffect = "src/Resources/Music/wallhitaudio.mp3";
                m_snaketheme = true;
                break;
            case 1:
                m_background = "PlayPaneBB";
                m_wallimg = "Resources/Images/lebroninjured.png";
                m_bombimg = "Resources/Images/MichaelJordan.png";
                m_foodimg = "Resources/Images/Basketball.png";
                m_snakeheadimg = "Resources/Images/LeBron.png";
                m_snakebodyimg = "Resources/Images/lebroncavsjersey.png";
                m_foodeffect = "src/Resources/Music/Basketball-net-swish.mp3";
                m_walleffect = "src/Resources/Music/lebronjamesaudio.mp3";
                m_snaketheme = false;
                break;
            case 2:
                m_background = "PlayPaneFootball";
                m_wallimg = "Resources/Images/yellowcard.png";
                m_bombimg = "Resources/Images/redcard.png";
                m_foodimg = "Resources/Images/Football.png";
                m_snakeheadimg = "Resources/Images/head.png";
                m_snakebodyimg = "Resources/Images/ArgShirt.png";
                m_foodeffect = "src/Resources/Music/Ronaldo-suiii.mp3";
                m_walleffect = "src/Resources/Music/refereewhistle.mp3";
                m_snaketheme = false;
        }
    }
}
