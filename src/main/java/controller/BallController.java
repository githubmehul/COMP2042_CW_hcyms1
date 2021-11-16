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
    private Shape BallFace;

    //Coordinates for center
    private Point2D center;
    private Point2D up;
    private Point2D down;
    private Point2D left;
    private Point2D right;

    //color for border and inner
    private Color Ball_Border_Color;
    private Color Ball_Inner_Color;

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

        setUpLocation(new Point2D.Double());
        setDownLocation(new Point2D.Double());
        setLeftLocation(new Point2D.Double());
        setRightLocation(new Point2D.Double());

        //Coordinates of the movement of the ball
        getUpLocation().setLocation(center.getX(),center.getY()-(radiusB / 2));
        getDownLocation().setLocation(center.getX(),center.getY()+(radiusB / 2));

        getLeftLocation().setLocation(center.getX()-(radiusA /2),center.getY());
        getRightLocation().setLocation(center.getX()+(radiusA /2),center.getY());

        //ballFace - The shape of the ball and initializes the starting location
        BallFace = makeBall(center,radiusA,radiusB);
        this.Ball_Border_Color = border;
        this.Ball_Inner_Color  = inner;
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
        RectangularShape tmp = (RectangularShape) BallFace;
        center.setLocation((center.getX() + speedX),(center.getY() + speedY));
        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        setPoints(w,h);
        BallFace = tmp;
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
        return Ball_Border_Color;
    }

    public Color getInnerColor(){
        return Ball_Inner_Color;
    }

    public Point2D getPosition(){
        return center;
    }

    public Shape getBallFace(){
        return BallFace;
    }

    /**
     * moveTo Method:
     * Implements the ball shape and location when the ball resets , moves to the startpoint
     * @param p
     */
    public void moveTo(Point p){
        center.setLocation(p);

        RectangularShape tmp = (RectangularShape) BallFace;
        double w = tmp.getWidth();
        double h = tmp.getHeight();
        tmp.setFrame((center.getX() -(w / 2)),(center.getY() - (h / 2)),w,h);
        BallFace = tmp;
    }

    private void setPoints(double width,double height){
        getUpLocation().setLocation(center.getX(),center.getY()-(height / 2));
        getDownLocation().setLocation(center.getX(),center.getY()+(height / 2));

        getLeftLocation().setLocation(center.getX()-(width / 2),center.getY());
        getRightLocation().setLocation(center.getX()+(width / 2),center.getY());
    }

    public int getSpeedX(){

        return speedX;
    }

    public int getSpeedY(){

        return speedY;
    }
    public Point2D getUpLocation() {
        return up;
    }

    public void setUpLocation(Point2D up) {
        this.up = up;
    }

    public Point2D getDownLocation() {
        return down;
    }

    public void setDownLocation(Point2D down) {
        this.down = down;
    }

    public Point2D getLeftLocation() {
        return left;
    }

    public void setLeftLocation(Point2D left) {
        this.left = left;
    }

    public Point2D getRightLocation() {
        return right;
    }

    public void setRightLocation(Point2D right) {
        this.right = right;
    }


}