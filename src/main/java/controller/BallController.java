package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * The BallController abstract class provides a template that allows different
 * types of Balls to be created.
 * It is responsible for defining its shape and location.
 * The color is defined by the implementation class of the BallController.
 * It provides methods that allow the WallController class to define its behaviour in the game.
 */
abstract public class BallController {

    //Border Color and Inner Color of the Ball
    private static Color ballBorderColor;
    private static Color ballInnerColor;

    //Coordinates of the Ball
    private final Point2D ballCenter;
    //Width and Height of Ball
    private final int width;
    private final int height;
    private double ballCenterX;
    private double ballCenterY;
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;
    //Shape of the Ball
    private Shape ballShape;

    //Speed of the Ball
    private int speedX;
    private int speedY;

    /**
     * 1. Sets the Coordinates and Location of the Ball
     * 2. Sets shape of the ball
     * 3. Defines the colors of the ball
     * 4. Defines the speed of the ball
     *
     * @param BallCenter - The BallCenter position.
     * @param width      - The horizontal diameter.
     * @param height     - The vertical diameter.
     */
    public BallController(Point2D BallCenter, int width, int height) {

        // Define location
        this.ballCenter = (Point2D) BallCenter.clone();
        this.ballCenterX = BallCenter.getX();
        this.ballCenterY = BallCenter.getY();

        //Define the Points of the Ball
        setUpLocation(new Point2D.Double());
        setDownLocation(new Point2D.Double());
        setLeftLocation(new Point2D.Double());
        setRightLocation(new Point2D.Double());
        setPoints(this.ballCenter);

        //Create The shape of the Ball
        ballShape = makeBallShape(BallCenter, width, height);

        // Define the Border Color and Inner Color of the Ball
        ballBorderColor = setBallBorderColor();
        ballInnerColor = setBallInnerColor();

        // Initialise the Speed of the Ball
        speedX = 0;
        speedY = 0;

        //Define the Size of the Ball
        this.width = width;
        this.height = height;
    }

    /**
     * Returns the Inner Color
     *
     * @return ballInnerColor - Inner Color of the Ball
     */
    public static Color getBallInnerColor() {
        return ballInnerColor;
    }

    /**
     * Returns the Border Color
     *
     * @return ballBorderColor - Border Color of the Ball
     */
    public static Color getBallBorderColor() {
        return ballBorderColor;
    }

    /**
     * An abstract method for creating the Ball's Shape.
     *
     * @param ballCenter - The BallCenter position.
     * @param width      - The horizontal diameter.
     * @param height     - The vertical diameter.
     * @return The {@code Shape} of the ball.
     */
    protected abstract Shape makeBallShape(Point2D ballCenter, int width, int height);

    /**
     * Increment the Ball's location by {@code speedX} and {@code speedY}.
     */
    public void move() {
        //Change the Location by adding the speed
        ballCenterX += speedX;
        ballCenterY += speedY;

        // Setting the Location
        setLocation(ballCenterX, ballCenterY);
    }

    /**
     * Set a new ballCenter location.
     *
     * @param x - The new BallCenter X coordinate.
     * @param y - The new BallCenter Y coordinate.
     */
    public void setLocation(double x, double y) {
        //Set the BallCenter Location
        this.ballCenterX = x;
        this.ballCenterY = y;
        this.ballCenter.setLocation(ballCenterX, ballCenterY);

        //Set BallShape Location
        RectangularShape temp = (RectangularShape) ballShape;
        var widthX = ballCenterX - (width / 2);
        var heightY = ballCenterY - (height / 2);
        temp.setFrame((widthX), (heightY), width, height);
        setPoints(ballCenter);
        ballShape = temp;
    }

    /**
     * Implements the ball location when the ball resets
     *
     * @param p - The point of the location
     */
    public void moveTo(Point2D p) {
        this.setLocation(p.getX(), p.getY());
    }

    /**
     * Set the Up , Down , Left and Right Points on the Ball
     *
     * @param BallCenter - The BallCenter position.
     */
    private void setPoints(Point2D BallCenter) {
        int ballCenterX = (int) BallCenter.getX();
        int ballCenterY = (int) BallCenter.getY();

        getUpLocation().setLocation(ballCenterX, ballCenterY - (height >> 1));
        getDownLocation().setLocation(ballCenterX, ballCenterY + (height >> 1));
        getLeftLocation().setLocation(ballCenterX - (width >> 2), ballCenterY);
        getRightLocation().setLocation(ballCenterX + (width >> 2), ballCenterY);
    }

    //Setter and Getter Methods

    /**
     * Abstract Method for setting the Inner Color of the Ball
     * Implemented in the Child Class
     */
    protected abstract Color setBallInnerColor();

    /**
     * Abstract Method for setting the Border Color of the Ball
     * Implemented in the Child Class
     */
    protected abstract Color setBallBorderColor();

    /**
     * Returns the speedX
     *
     * @return speedX - Speed of Ball in X Axis
     */
    public int getSpeedX() {
        return speedX;
    }

    /**
     * Set the new speed in the X axis.
     *
     * @param sX - The new speed in the X axis.
     */
    public void setSpeedX(int sX) {
        speedX = sX;
    }

    /**
     * Returns the SpeedY
     *
     * @return speedY - The new speed in Y axis
     */
    public int getSpeedY() {
        return speedY;
    }

    /**
     * Set the new speed in the Y axis.
     *
     * @param sY - The new speed in the Y axis.
     */
    public void setSpeedY(int sY) {
        speedY = sY;
    }

    /**
     * Returns the up Position Coordinate
     *
     * @return A {@code Point2D} of the Ball's up-most point.
     */
    public Point2D getUpLocation() {
        return up;
    }

    /**
     * Sets the up Position Coordinate
     *
     * @param up - Set the {@code Point2D} of the Ball's up-most point
     */
    public void setUpLocation(Point2D up) {
        this.up = up;
    }

    /**
     * Returns the down Position Coordinate
     *
     * @return A {@code Point2D} of the Ball's bottom-most point.
     */
    public Point2D getDownLocation() {
        return down;
    }

    /**
     * Sets the down Position Coordinate
     *
     * @param down - Set the {@code Point2D} of the Ball's bottom-most point.
     */
    public void setDownLocation(Point2D down) {
        this.down = down;
    }

    /**
     * Returns the left Position Coordinate
     *
     * @return A {@code Point2D} of the Ball's left-most point.
     */
    public Point2D getLeftLocation() {
        return left;
    }

    /**
     * Sets the Left Position Coordinate
     *
     * @param left - Set the {@code Point2D} of the Ball's left-most point.
     */
    public void setLeftLocation(Point2D left) {
        this.left = left;
    }

    /**
     * Returns the right Position Coordinate
     *
     * @return A {@code Point2D} of the Ball's right-most point.
     */
    public Point2D getRightLocation() {
        return right;
    }

    /**
     * Sets the right Position Coordinate
     *
     * @param right - Set the {@code Point2D} of the Ball's right-most point.
     */
    public void setRightLocation(Point2D right) {
        this.right = right;
    }

    /**
     * Sets the Speed of the Ball in reverse in X Direction
     */
    public void reverseX() {
        speedX *= -1;
    }

    /**
     * Sets the Speed of the Ball in reverse in Y Direction
     */
    public void reverseY() {
        speedY *= -1;
    }

    /**
     * Returns the Center Position
     *
     * @return BallCenter - The ballCenter Position
     */
    public Point2D getPosition() {
        return ballCenter;
    }


    /**
     * Returns the BallFace
     *
     * @return BallFace - The  ballShape
     */
    public Shape getBallFace() {
        return ballShape;
    }

}