package Application;

/**
 * Contains the information for each Theme
 */
public class Theme {
    //Class variables
    public static String m_foodimg;
    public static String m_wallimg;
    public static String m_bombimg;
    public static String m_snakebodyimg;
    public static String m_snakeheadimg;
    private static String m_background = "PlayPaneSky";
    private static boolean m_snaketheme = true;

    //Getters
    public static boolean GetSnakeTheme(){return m_snaketheme;}
    public static String GetBackground(){
        return m_background;
    }
    public static String GetFoodImg(){
        return m_foodimg;
    }
    public static String GetSnakeHImg(){
        return m_snakeheadimg;
    }
    public static String GetSnakeBImg(){
        return m_snakebodyimg;
    }
    public static String GetWallImg() {return m_wallimg;}
    public static String GetBombImg() {return m_bombimg;}

    /**
     * Sets the variables to different things depending on the integer provided, setting the theme
     * @param i the integer that correlates to a theme
     */
    public Theme(int i) {
        switch(i){
            case 0:
                m_background = "PlayPaneSky";
                m_wallimg = "Resources/brick-clipart-briks-10.png";
                m_bombimg = "Resources/skull_bomb_by_alishavolkman_daf7ku4.png";
                m_foodimg = "Resources/food-apple.png";
                m_snakeheadimg = "Resources/snake-head-right.png";
                m_snakebodyimg = "Resources/snake-body.png";
                m_snaketheme = true;
                break;
            case 1:
                m_background = "PlayPaneBB";
                m_wallimg = "Resources/lebroninjured.png";
                m_bombimg = "Resources/MichaelJordan.png";
                m_foodimg = "Resources/Basketball.png";
                m_snakeheadimg = "Resources/LeBron.png";
                m_snakebodyimg = "Resources/lebroncavsjersey.png";
                m_snaketheme = false;
                break;
            case 2:
                m_background = "PlayPaneFootball";
                m_wallimg = "Resources/yellowcard.png";
                m_bombimg = "Resources/redcard.png";
                m_foodimg = "Resources/football.png";
                m_snakeheadimg = "Resources/head.png";
                m_snakebodyimg = "Resources/ArgShirt.png";
                m_snaketheme = false;
        }
    }
}
