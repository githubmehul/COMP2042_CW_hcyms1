package controller;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.Random;

/**
 * Implements the Brick Template as an abstract class for CementBrickModel ,
 * ClayBrickModel an SteelBrickModel.
 * This is responsible for defining its shape, looks and location.
 */
abstract public class BrickController {

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    private static Random rnd;

    private String name;
    private Shape brickFace;
    //Border Color and Inner Color
    private Color Brick_Border_Color;
    private Color Brick_Inner_Color;

    private int fullStrength;
    private int strength;
    //Flage to check if the Brick if broken
    private boolean broken;


    public BrickController(String name, Point pos,Dimension size,int strength){
        setRnd(new Random());
        this.name = name;

        //Define the Colors
        this.Brick_Border_Color = setBrickBorderColor();
        this.Brick_Inner_Color= setBrickInnerColor();

        //Define the Properties
        this.fullStrength = strength;
        this.strength = fullStrength;
        this.broken = false;

        //Create the Brick
        setBrickFace(makeBrickFace(pos,size));

    }

    /**
     * Abstract Method for creating the Brick
     * @param pos
     * @param size
     * @return
     */
    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    /**
     *Abstract Method for setting the Inner Color of the Ball
     */
    protected abstract Color setBrickInnerColor();

    /**
     * Abstract Method for setting the Border Color of the Ball
     */
    protected abstract Color setBrickBorderColor();
    /**
     * Abstract Method for getting the shape of the brick
     */
    public abstract Shape getBrick();
    /**
     * Method to determine the impact of the brick and the ball
     * @param b
     * @return
     */
    public final int findImpact(BallController b){
        if(broken)
            return 0;
        int out  = 0;
        // If the right side of the ball impacts the left side of the brick
        if(getBrickFace().contains(b.getRightLocation()))
            out = LEFT_IMPACT;
        // If the left side of the ball impacts the right side of the brick
        else if(getBrickFace().contains(b.getLeftLocation()))
            out = RIGHT_IMPACT;
        // If the top side of the ball impacts the bottom side of the brick
        else if(getBrickFace().contains(b.getUpLocation()))
            out = DOWN_IMPACT;
        // If the bottom side of the ball impacts the top side of the brick
        else if(getBrickFace().contains(b.getDownLocation()))
            out = UP_IMPACT;
        return out;
    }

    /**
     * Method to Set the Impact on the Brick
     * @param point
     * @param dir
     * @return
     */
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }
    /**
     * Method to implement the brick strength when impacted
     */
    public void impact(){
        strength--;
        broken = (strength == 0);
    }
    /**
     * Returns the value of broken
     * @return broken
     */
    public boolean isBroken(){
        return broken;
    }
    /**
     * Method to default the strength of the brick when the game restarts
     */
    public void repair() {
        broken = false;
        strength = fullStrength;
    }
    /**
     * Returns the Brick's Border Color
     * @return Brick_Border_Color
     */
    public Color getBorderColor(){
        return  Brick_Border_Color;
    }
    /**
     * Returns the Brick's Inner Color
     * @return Brick_Inner_Color
     */
    public Color getInnerColor(){
        return Brick_Inner_Color;
    }

    /**
     * Returns the super brick face
     * @return brickFace
     */
    public Shape getBrickFace() {
        return brickFace;
    }
    /**
     * Sets the brick face
     * @param brickFace
     */
    public void setBrickFace(Shape brickFace) {
        this.brickFace = brickFace;
    }

    /**Returns the random value
     * @return rnd
     */
    public static Random getRnd() {
        return rnd;
    }
    /**
     * Sets the random value
     * @param rnd
     */
    public static void setRnd(Random rnd) {
        BrickController.rnd = rnd;
    }
}




