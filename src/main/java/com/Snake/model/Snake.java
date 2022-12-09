package com.Snake.model;

import com.Snake.controller.PlayScreenController;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.util.Map;

/**
 * Snake class that is used to Add and Remove snake body parts
 * @author Jack Gribble
 */
public class Snake {
    private Image m_snakebodyimg;
    private AnchorPane m_pane;
    private double m_xbound;
    private double m_ybound;
    private Rectangle m_head;


    /**
     * Default Constructor for class
     * Sets up variables
     * @param pane the pane a body part will be added to
     * @param head the snake head from the Play Screen
     * @param x the x-co-ordinate boundary, the maximum it can be
     * @param y the x-co-ordinate boundary, the maximum it can be
     */
    public Snake(AnchorPane pane, Rectangle head, double x, double y){
        m_pane = pane;
        m_head = head;
        m_snakebodyimg = new Image(getClass().getResource(Theme.GetSnakeBImg()).toString());
        m_xbound = x;
        m_ybound = y;
    }

    /**
     * Adds a body part to the snake
     * Called when the snake 'eats' a piece of food
     */
    public void addSnakeBody(Map<Integer, Rectangle> body){
        Rectangle rect = new Rectangle(26,26);
        rect.setFill(new ImagePattern(m_snakebodyimg));
        m_pane.getChildren().add(rect);
        body.put(body.size(),rect);
        PlayScreenController.SetM_newfood(false);
    }

    /**
     * Moves the given body part of the snake
     * Sets the x and y co-ordinates of the body part
     * @param bodypart the body part to move
     * @param x the x co-ordinate to move the body part to
     * @param y the y co-ordinate to move the body part to
     */
    public void moveSnakeBody(Rectangle bodypart, double x, double y){
        bodypart.setLayoutX(x);
        bodypart.setLayoutY(y);
    }

    /**
     * Removes the tail of the snake
     * Called when the snakehead hits a wall
     * @throws IOException
     */
    public boolean RemoveSnakeBody(boolean hit, Map<Integer, Rectangle> body) throws IOException {
        if(!hit){
            new MusicPlayer(Theme.GetWallSound(), false);
            if(body.size() == 0)
               return true;
            m_pane.getChildren().remove(body.get(body.size()-1));
            body.remove(body.size()-1);
            PlayScreenController.SetScore(Integer.parseInt(PlayScreenController.GetScore())-521);
        }
        PlayScreenController.SetHit(true);
        return false;
    }

    /**
     * Checks to see if the snakehead is out of bounds
     * If it is the game is ended and the End Screen is loaded
     * @throws IOException
     */
    public boolean outofBounds() throws IOException {
        boolean xOut = (m_head.getLayoutX() <= 0 || m_head.getLayoutX() >= m_xbound);
        boolean yOut = (m_head.getLayoutY() <= 0 || m_head.getLayoutY() >= m_ybound);


        if (xOut || yOut)
        {
            return true;
        }
        else{
            return false;
        }

    }
}
