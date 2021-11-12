package controller;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.RectangularShape;


/**
 * BallController Abstract Class:
 * Implements the Ball Movement Functionality
 */
abstract public class BallController {

    //Shape of the Ball
    private Shape ballFace;

    //Coordinates for center
    private Point2D center;
    //Coordinates for up , down , left , right
    public Point2D up;
    public Point2D down;
    public Point2D left;
    public Point2D right;

    //color for border and inner
    private Color border;
    private Color inner;

    //Speed of Ball
    private int speedX;
    private int speedY;


    /**
     * BallController Constructor:
     *
     * @param center
     * @param radiusA
     * @param radiusB
     * @param inner
     * @param border
     */
    public BallController(Point2D center, int radiusA, int radiusB, Color inner, Color border){
        this.center = center;

        up = new Point2D.Double();
        down = new Point2D.Double();
        left = new Point2D.Double();
        right = new Point2D.Double();

        //Coordinates of the movement of the ball
        up.setLocation(center.getX(),center.getY()-(radiusB / 2));
        down.setLocation(center.getX(),center.getY()+(radiusB / 2));
        left.setLocation(center.getX()-(radiusA /2),center.getY());
        right.setLocation(center.getX()+(radiusA /2),center.getY());

        //ballFace - The shape of the ball and initializes the starting location
        ballFace = makeBall(center,radiusA,radiusB);
        this.border = border;
        this.inner  = inner;
        speedX = 0;
        speedY = 0;
    }

    /**
     * makeBall Method:
     * Abstract Method for makeBall Implementation in RubberBall Class
     * @param center
     * @param radiusA
     * @param radiusB
     * @return
     */
    protected abstract Shape makeBall(Point2D center,int radiusA,int radiusB);

    /**
     * move method:
     * Implementation of the width and height of the ball when in motion , called by wall class
     */
    public void move(){
        RectangularShape tmp = (RectangularShape) ballFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);
        ballFace = tmp;
    }

    /**
     * setSpeed Method:
     * Sets the Speed of the Ball Movement
     * @param x
     * @param y
     */
    public void setSpeed(int x,int y){
        speedX = x;
        speedY = y;
    }

    /**
     * setXSpeed Method:
     * Sets the X Coordinate Direction Speed
     * @param s
     */
    public void setXSpeed(int s){

        speedX = s;
    }

    /**
     *setYSpeed Method:
     *Sets the Y Coordinate Direction Speed
     * @param s
     */
    public void setYSpeed(int s){

        speedY = s;
    }

    /**
     * reverseX Method:
     * Sets the reverse X Coordinate Direction Speed when the Ball hits the brick
     */
    public void reverseX(){

        speedX *= -1;
    }

     /**
     * reverseY Method:
     * Sets the reverse Y Coordinate Direction Speed when the Ball hits the brick
     */
    public void reverseY(){

        speedY *= -1;
    }

    public Color getBorderColor(){
        return border;
    }

    public Color getInnerColor(){
        return inner;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return ballFace;
    }

    /**
     * moveTo Method:
     * Implements the ball shape and location when the ball resets , moves to the startpoint
     * @param p
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) ballFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        ballFace = tmp;
    }

    private void setPoints(double width,double height){
        up.setLocation(center.getX(),center.getY()-(height / 2));
        down.setLocation(center.getX(),center.getY()+(height / 2));

        left.setLocation(center.getX()-(width / 2),center.getY());
        right.setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){

        return speedX;
    }

    public int getSpeedY(){

        return speedY;
    }


}