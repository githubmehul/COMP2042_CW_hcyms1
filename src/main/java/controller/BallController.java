package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * The BallController abstract class provides a template that allows different
 * types of Balls to be created. It is responsible for defining its shape and
 * location , and the color is defined by the implementation class of the BallController
 * Not responsible for defining its behaviour in the game.
 * It provides methods that allow the Game class to define its behaviour in the game.
 */
abstract public class BallController {

    //Shape of the Ball
    private Shape BallFace;

    //Coordinates of the Ball
    private Point2D center;
    private double centerX;
    private double centerY;
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    //Border Color and Inner Color of the Ball
    private Color Ball_Border_Color;
    private Color Ball_Inner_Color;

    //Speed of the Ball
    private int speedX;
    private int speedY;

    //Width and Height of Ball
    private int width;
    private int height;


    /**
     * 1. Sets the Coordinates and Location of the Ball
     * 2. Sets shape of the ball
     * 3. Defines the colors of the ball
     * 4. Defines the speed of the ball
     * @param center - The center position.
     * @param width  - The horizontal diameter.
     * @param height - The vertical diameter.
     */
    public BallController(Point2D center, int width, int height){

        // Define location
        this.center = (Point2D) center.clone();
        this.centerX = center.getX();
        this.centerY = center.getY();

        //Define the Points of the Ball
        setUpLocation(new Point2D.Double());
        setDownLocation(new Point2D.Double());
        setLeftLocation(new Point2D.Double());
        setRightLocation(new Point2D.Double());
        setPoints(this.center);

        //Create The shape of the Ball
        BallFace = makeBall(center,width,height);

        // Define the Border Color and Inner Color of the Ball
        this.Ball_Border_Color = setBallBorderColor();
        this.Ball_Inner_Color  = setBallInnerColor();

        // Initialise the Speed of the Ball
        speedX = 0;
        speedY = 0;

        //Define the Size of the Ball
        this.width = width;
        this.height = height;
    }

    /**
     * An abstract method for creating the Ball's Shape.
     * @param center - The center position.
     * @param width  - The horizontal diameter.
     * @param height - The vertical diameter.
     * @return The {@code Shape} of the ball.
     */
    protected abstract Shape makeBall(Point2D center,int width,int height);

    /**
     * Increment the Ball's location by {@code speedX} and {@code speedY}.
     */
    public void move() {
        //Change the Location by adding the speed
        centerX += speedX;
        centerY += speedY;

        // Change location by adding with speed
        setLocation(centerX, centerY);
    }

    /**
     * Set a new center location.
     * @param x - The new center X coordinate.
     * @param y - The new center Y coordinate.
     */
    public void setLocation(double x , double y){
        //Set the Center Location
        this.centerX = x;
        this.centerY = y;
        this.center.setLocation(centerX , centerY);

        //Set BallFace Location
        RectangularShape temp = (RectangularShape) BallFace;
        double widthX = centerX - (width / 2);
        double heightY = centerY - (height/2);
        temp.setFrame((widthX),(heightY),width,height);
        setPoints(center);
        BallFace = temp;
    }

    /**
     * Implements the ball location when the ball resets
     * @param p
     */
    public void moveTo(Point2D p){
        this.setLocation(p.getX() , p.getY());
    }


    /**
     * Set the Up , Down , Left and Right Points on the Ball
     * @param center - The new center position.
     */
    private void setPoints(Point2D center){
        int CenterX = (int) center.getX();
        int CenterY = (int) center.getY();

        getUpLocation().setLocation(CenterX, CenterY  - (height / 2));
        getDownLocation().setLocation(CenterX, CenterY + (height / 2));
        getLeftLocation().setLocation(CenterX - (width / 2), CenterY );
        getRightLocation().setLocation(CenterX + (width / 2), CenterY );
    }

    //Setter and Getter Methods
    /**
     *Abstract Method for setting the Inner Color of the Ball
     * Implemented in the Child Class
     */
    protected abstract Color setBallInnerColor();
    /**
     * Returns the Inner Color
     * @return Ball_Inner_Color - Inner Color of the Ball
     */
    public Color getInnerColor(){ return Ball_Inner_Color; }


    /**
     * Abstract Method for setting the Border Color of the Ball
     * Implemented in the Child Class
     */
    protected abstract Color setBallBorderColor();
    /**
     * Returns the Border Color
     * @return Ball_Border_Color - Border Color of the Ball
     */
    public Color getBorderColor(){ return Ball_Border_Color;}


    /**
     * Set the new speed in the X axis.
     * @param s - The new speed in the X axis.
     */
    public void setSpeedX(int s){
        speedX = s;
    }
    /**
     * Returns the speedX
     * @return speedX - Speed of Ball in X Axis
     */
    public int getSpeedX(){
        return speedX;
    }


    /**
     * Set the new speed in the Y axis.
     * @param s - The new speed in the Y axis.
     */
    public void setSpeedY(int s){
        speedY = s;
    }
    /**
     * Returns the SpeedY
     * @return speedY - The new speed in Y axis
     */
    public int getSpeedY(){
        return speedY;
    }


    /**
     * Sets the up Position Coordinate
     * @param up
     */
    public void setUpLocation(Point2D up) {
        this.up = up;
    }
    /**
     * Returns the up Position Coordinate
     * @return A {@code Point2D} of the Ball's up-most point.
     */
    public Point2D getUpLocation() {
        return up;
    }


    /**
     * Sets the down Position Coordinate
     * @param down
     */
    public void setDownLocation(Point2D down) {
        this.down = down;
    }
    /**
     * Returns the down Position Coordinate
     * @return A {@code Point2D} of the Ball's bottom-most point.
     */
    public Point2D getDownLocation() {
        return down;
    }


    /**
     * Sets the Left Position Coordinate
     * @param left
     */
    public void setLeftLocation(Point2D left) {
        this.left = left;
    }
    /**
     * Returns the left Position Coordinate
     * @return A {@code Point2D} of the Ball's left-most point.
     */
    public Point2D getLeftLocation() {
        return left;
    }


    /**
     * Sets the right Position Coordinate
     * @param right
     */
    public void setRightLocation(Point2D right) {
        this.right = right;
    }
    /**
     * Returns the right Position Coordinate
     * @return A {@code Point2D} of the Ball's right-most point.
     */
    public Point2D getRightLocation() {
        return right;
    }


    /**
     * Sets the Speed of the Ball in reverse in X Direction
     */
    public void reverseX(){
        speedX *= -1;
    }
     /**
     * Sets the Speed of the Ball in reverse in Y Direction
     */
    public void reverseY(){
        speedY *= -1;
    }
    /**
     * Returns the Center Position
     * @return center - The Center Position
     */
    public Point2D getPosition(){
        return center;
    }
    /**
     * Returns the BallFace
     * @return BallFace - The Super BallFace
     */
    public Shape getBallFace(){
        return BallFace;
    }

}