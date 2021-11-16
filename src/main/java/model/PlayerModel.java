package model;

import controller.BallController;

import java.awt.*;


/***
 * PlayerModel Class implements the PLayer Platform
 */
public class PlayerModel {
    //Final Color Declarations
    private static final Color PLAYER_BORDER_COLOR = Color.GREEN.darker().darker();
    private static final Color PLAYER_INNER_COLOR = Color.GREEN;

    private static final int PLAYER_MOVE_AMOUNT = 5;

    //The shape of the platform
    private Rectangle PlayerFace;
    //A Coordinate Variable called ballPoint
    private Point BallPoint;
    //The move Amount
    private int MoveAmount;
    //Min and Max Movement
    private int Min;
    private int Max;

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
        this.BallPoint = ballPoint;
        MoveAmount = 0;
        PlayerFace = makeRectangle(width, height);
        Min = container.x + (width / 2);
        Max = Min + container.width - width;
    }

    /**
     * makeReactangle Method:
     * Creates the Platform Shape
     * @param width
     * @param height
     * @return
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((int)(BallPoint.getX() - (width / 2)),(int)BallPoint.getY());
        return  new Rectangle(p,new Dimension(width,height));
    }


    /**
     * impact Method:
     * Implements the impact of the ball on the platform
     * @param b
     * @return
     */
    public boolean impact(BallController b){
        return PlayerFace.contains(b.getPosition()) && PlayerFace.contains(b.getDownLocation()) ;
    }

    /**
     * move Method:
     * Implements the movement of the ball and the platform
     */
    public void move(){
        double x = BallPoint.getX() + MoveAmount;
        if(x < Min || x > Max)
            return;
        BallPoint.setLocation(x,BallPoint.getY());
        PlayerFace.setLocation(BallPoint.x - (int)PlayerFace.getWidth()/2,BallPoint.y);
    }

    /**
     * moveLeft method:
     * when moved left , reduces the move amount
     */
    public void moveLeft(){
        MoveAmount = -PLAYER_MOVE_AMOUNT;
    }

    /**
     * moveLeft method:
     * when moved right , reduces the move amount
     */
    public void moveRight(){
        MoveAmount = PLAYER_MOVE_AMOUNT;
    }

    /**
     * stop method:
     * stops the move amount so that the platform does not move
     */
    public void stop(){
        MoveAmount = 0;
    }

    /**
     * getPlayerFace method:
     * returns the playerface
     * @return
     */
    public Shape getPlayerFace(){
        return  PlayerFace;
    }

    /**
     * moveTo method:
     * sets the location of the ball point and sets the location of the playerface and sets it to the point
     * @param p
     */
    public void moveTo(Point p){
        BallPoint.setLocation(p);
        PlayerFace.setLocation(BallPoint.x - (int)PlayerFace.getWidth()/2,BallPoint.y);
    }

    /**
     * getBorderColor Method:
     * To Return the Border Color
     * @return PLAYER_BORDER_COLOR
     */
    public static Color getBorderColor() {
        return PLAYER_BORDER_COLOR;
    }

    /**
     * getInnerColor Method:
     * To return the Inner Color
     * @return PLAYER_INNER_COLOR
     */
    public static Color getInnerColor() {
        return PLAYER_INNER_COLOR;
    }
}