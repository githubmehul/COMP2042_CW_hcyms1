package controller;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * The BrickController abstract class provides a template that allows different types of Bricks to be created.
 * It is responsible for defining its shape and location
 * The color is defined by the implementation class of the BrickController.
 * Implements simple behaviour regarding the strength of the brick.
 */
abstract public class BrickController {
    //Shape of Brick
    private Shape brickShape;

    //Sets the constants for impact in various directions
    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    //Brick Border Color and Brick Inner Color
    private Color brickBorderColor;
    private Color brickInnerColor;

    private int fullStrength;
    private int strength;

    private boolean broken;
    private static Random rnd;


    /**
     * @param pos - The Point position
     * @param size - Encapsulates Width and Height of Brick
     * @param strength - The Strength of the Brick
     */
    public BrickController(Point pos,Dimension size,int strength){
        setRnd(new Random());

        //Define the Colors
        this.brickBorderColor = setBrickBorderColor();
        this.brickInnerColor= setBrickInnerColor();

        //Define the Properties
        this.fullStrength = strength;
        this.strength = fullStrength;
        this.broken = false;

        //Create the brickShape
        setBrickShape(makeBrickFace(pos,size));

    }

    /**
     * Abstract Method for creating the Brick's Shape
     * @param pos - The position coordinate of the Brick
     * @param size - Encapsulates the Width and Height of Brick
     * @return
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);
    /**
     * Abstract Method for getting the Child Classes Brick Shape
     */
    public abstract Shape getBrick();


    /**
     * Gets the shape of the parent Brick
     * @return brickFace
     */
    public Shape getBrickShape() {
        return brickShape;
    }
    /**
     * Sets the Shape of the Parent Brick
     * @param brickFace
     */
    public void setBrickShape(Shape brickFace) {
        this.brickShape= brickFace;
    }


    /**
     *Abstract Method for setting the Inner Color of the Brick
     * @param red
     */
    protected abstract Color setBrickInnerColor();
    /**
     * Returns the Brick's Inner Color from Child Classes
     * @return Brick_Inner_Color - Inner Color of Brick
     */
    public Color getBrickInnerColor() {
        return brickInnerColor;
    }


    /**
     * Abstract Method for setting the Border Color of the Ball
     */
    protected abstract Color setBrickBorderColor();

    /**
     * Returns the Brick's Border Color from Child Classes
     * @return Brick_Border_Color - Border Color of Brick
     */
    public Color getBrickBorderColor(){
        return  brickBorderColor;
    }


    /**
     * Method to determine the impact of the Brick and the Ball
     * @param b - BallController
     * @return
     */
    public final int findImpact(BallController b){
        if(broken)
            return 0;
        int out  = 0;
        // If the right side of the ball impacts the left side of the brick
        if(getBrickShape().contains(b.getRightLocation()))
            out = LEFT_IMPACT;
        // If the left side of the ball impacts the right side of the brick
        else if(getBrickShape().contains(b.getLeftLocation()))
            out = RIGHT_IMPACT;
        // If the top side of the ball impacts the bottom side of the brick
        else if(getBrickShape().contains(b.getUpLocation()))
            out = DOWN_IMPACT;
        // If the bottom side of the ball impacts the top side of the brick
        else if(getBrickShape().contains(b.getDownLocation()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Method implementation when WallController detects a collision between the Ball and
     * the Brick. The impact() method is called
     * @param point - The point of impact.
     * @param dir   - The direction at which the Crack will be created.
     * @return A boolean value of the Brick's state - False if the brick is broken
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    /**
     * Decrements the strength  of the Brick and updates the Brick's broken status.
     */
    public int impact(){
        strength--;
        broken = (strength == 0);
        return 0;
    }
    /**
     * Get the broek status of the Brick.
     * @return A Boolean value of the broken status of the Brick.
     */
    public boolean isBroken(){
        return broken;
    }
    /**
     * Sets the Brick's broken status to true and restores its full
     * strength.
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }

    /**Returns the random value
     * @return rnd - A Random Value
     */
    public static Random getRnd() {
        return rnd;
    }
    /**
     * Sets the random value
     * @param rnd - A Random Value for the Steel Brick Probability
     */
    public static void setRnd(Random rnd) {
        BrickController.rnd = rnd;
    }

    /**
     * Renders the Brick Shape and Color, called in the wallController Class
     * @param g
     */
    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(getBrickInnerColor());
        g2d.fill(getBrick());

        // Set the border colour
        g2d.setColor(getBrickBorderColor());
        g2d.draw(getBrick());
    }
}




