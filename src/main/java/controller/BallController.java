package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * Implements the Ball Movement Functionality.
 * Responsible for defining its shape, looks and location.
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
     * 1. Sets the Coordinates and Location of the Ball<br>
     * 2. Sets shape of the ball<br>
     * 3. Defines the colors of the ball<br>
     * 4. Defines the speed of the ball
     * @param center
     * @param width
     * @param height
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

        //Define The shape of the Ball
        BallFace = makeBall(center,width,height);

        // Define the Border Color and Inner Color of the Ball
        this.Ball_Border_Color = setBorderColor();
        this.Ball_Inner_Color  = setInnerColor();

        // Initialise the Speed of the Ball
        speedX = 0;
        speedY = 0;

        //Define the Size of the Ball
        this.width = width;
        this.height = height;
    }

    /**
     * Abstract Method to make the Ball
     * @param center
     * @param width
     * @param height
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int width,int height);

    /**
     *Abstract Method for setting the Inner Color of the Ball
     */
    protected abstract Color setInnerColor();

    /**
     * Abstract Method for setting the Border Color of the Ball
     */
    protected abstract Color setBorderColor();

    /**
     *Called to move the ball everytime the method is called
     */
    public void move() {
        //Change the Location by adding the speed
        centerX += speedX;
        centerY += speedY;

        // Change location by adding with speed
        setLocation(centerX, centerY);
    }

    /**
     * Sets the Ball's Location
     * @param x
     * @param y
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
     * Sets the Ball Points
     * @param center
     */
    private void setPoints(Point2D center){
        int CenterX = (int) center.getX();
        int CenterY = (int) center.getY();

        getUpLocation().setLocation(CenterX, CenterY  - (height / 2));
        getDownLocation().setLocation(CenterX, CenterY + (height / 2));
        getLeftLocation().setLocation(CenterX - (width / 2), CenterY );
        getRightLocation().setLocation(CenterX + (width / 2), CenterY );
    }

    /**
     * Sets Speed in X Direction
     * @param s
     */
    public void setXSpeed(int s){
        speedX = s;
    }
    /**
     * Sets the Speed in the Y Direction
     * @param s
     */
    public void setYSpeed(int s){
        speedY = s;
    }
    /**
     * Returns the speedX
     * @return speedX
     */
    public int getSpeedX(){
        return speedX;
    }
    /**
     * returns the SpeedY
     * @return
     */
    public int getSpeedY(){
        return speedY;
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
     * Returns the Border Color
     * @return Ball_Border_Color
     */
    public Color getBorderColor(){
        return Ball_Border_Color;
    }
    /**
     * Returns the Inner Color
     * @return Ball_Inner_Color
     */
    public Color getInnerColor(){
        return Ball_Inner_Color;
    }
    /**
     * Returns the Center Position
     * @return center
     */
    public Point2D getPosition(){
        return center;
    }
    /**
     * Returns the BallFace
     * @return BallFace
     */
    public Shape getBallFace(){
        return BallFace;
    }

    /**
     * Returns the up Position Coordinate
     * @return up
     */
    public Point2D getUpLocation() {
        return up;
    }

    /**
     * Sets the up Position Coordinate
     * @param up
     */
    public void setUpLocation(Point2D up) {
        this.up = up;
    }

    /**
     * Returns the down Position Coordinate
     * @return down
     */
    public Point2D getDownLocation() {
        return down;
    }

    /**
     * Sets the down Position Coordinate
     * @param down
     */
    public void setDownLocation(Point2D down) {
        this.down = down;
    }

    /**
     * Returns the left Position Coordinate
     * @return left
     */
    public Point2D getLeftLocation() {
        return left;
    }

    /**
     * Sets the Left Position Coordinate
     * @param left
     */
    public void setLeftLocation(Point2D left) {
        this.left = left;
    }

    /**
     * Returns the right Position Coordinate
     * @return right
     */
    public Point2D getRightLocation() {
        return right;
    }

    /**
     * Sets the right Position Coordinate
     * @param right
     */
    public void setRightLocation(Point2D right) {
        this.right = right;
    }

}