package model;

import controller.BallController;

import java.awt.*;


/***
 * PlayerModel Class implements the PLayer Platform
 */
public class PlayerModel {

    //Border of the Player
    public static final Color BORDER_COLOR = Color.GREEN.darker().darker();
    //Inner Color of the PLayer
    public static final Color INNER_COLOR = Color.GREEN;

    private static final int DEF_MOVE_AMOUNT = 5;

    //The shape of the platform
    private Rectangle playerFace;
    //A Coordinate Variable called ballPoint
    private Point ballPoint;
    //The move Amount
    private int moveAmount;
    //Min and Max Movement
    private int min;
    private int max;


    /**
     * PlayerModel Constructor:
     * 1. Assigns the value of ballPoint to ballPoint
     * 2. moveAmount is set to 0
     * 3. Create the Platform Shape with the width and height parameter
     * 4. min and max
     * @param ballPoint
     * @param width
     * @param height
     * @param container
     */
    public PlayerModel(Point ballPoint, int width, int height, Rectangle container) {
        this.ballPoint = ballPoint;
        moveAmount = 0;
        playerFace = makeRectangle(width, height);
        min = container.x + (width / 2);
        max = min + container.width - width;

    }

    /**
     * makeReactangle Method:
     * Creates the Platform Shape
     * @param width
     * @param height
     * @return
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(ballPoint.getX() - (width / 2)),(int)ballPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }


    /**
     * impact Method:
     * Implements the impact of the ball on the platform
     * @param b
     * @return
     */
    public boolean impact(BallController b){
        return playerFace.contains(b.getPosition()) && playerFace.contains(b.down) ;
    }

    /**
     * move Method:
     * Implements the movement of the ball and the platform
     */
    public void move(){
        double x = ballPoint.getX() + moveAmount;
        if(x < min || x > max)
            return;
        ballPoint.setLocation(x,ballPoint.getY());
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }

    /**
     * moveLeft method:
     * when moved left , reduces the move amount
     */
    public void moveLeft(){
        moveAmount = -DEF_MOVE_AMOUNT;
    }

    /**
     * moveLeft method:
     * when moved right , reduces the move amount
     */
    public void movRight(){
        moveAmount = DEF_MOVE_AMOUNT;
    }

    /**
     * stop method:
     * stops the move amount so that the platform does not move
     */
    public void stop(){
        moveAmount = 0;
    }

    /**
     * getPlayerFace method:
     * returns the playerface
     * @return
     */
    public Shape getPlayerFace(){
        return  playerFace;
    }

    /**
     * moveTo method:
     * sets the location of the ball point and sets the location of the playerface and sets it to the point
     * @param p
     */
    public void moveTo(Point p){
        ballPoint.setLocation(p);
        playerFace.setLocation(ballPoint.x - (int)playerFace.getWidth()/2,ballPoint.y);
    }
}