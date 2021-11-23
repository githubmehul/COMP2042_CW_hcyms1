package controller;

import controller.BallController;

import java.awt.*;
import java.awt.geom.Point2D;


/**
 * The PlayerModel class is responsible for defining the Player's shape, looks,
 * location and behaviour.
 */
public class PlayerController {
    //Final Color Declarations
    private static final Color PLAYER_BORDER_COLOR = Color.GREEN.darker().darker();
    private static final Color PLAYER_INNER_COLOR = Color.GREEN;
    private static final int PLAYER_MOVE_AMOUNT = 5;

    private Rectangle PlayerFace;
    private Point BallPoint;
    private int BallPointX;
    private int BallPointY;
    //The move Amount
    private int MoveAmount = 0;
    private int Min;
    private int Max;

    /**
     * PlayerModel Constructor:
     * 1. Assigns the value of ballPoint to ballPoint
     * 2. moveAmount is set to 0
     * 3. Create the Platform Shape with the width and height parameter
     * 4. min and max
     * @param BallPoint
     * @param width
     * @param height
     * @param container
     */
    public PlayerController(Point BallPoint, int width, int height, Rectangle container) {

        // Define location (center)
        this.BallPointX = (int) BallPoint.getX();
        this.BallPointY = (int) BallPoint.getY();
        this.BallPoint = (Point) BallPoint.clone();

        // Create the Player's Shape
        PlayerFace = makeRectangle(width, height);

        Min = container.x + (width / 2);
        Max = Min + container.width - width;
    }

    /**
     * Create the Player's model, which is a Rectangle.
     * @param width  - The width.
     * @param height - The height.
     * @return A {@code Rectangle} object.
     */
    private Rectangle makeRectangle(int width,int height){
        Point p = new Point((BallPointX - (width / 2)),BallPointY);
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * Returns the boolean if the Ball has impacted the Platform
     * @param b
     * @return
     */
    public boolean impact(BallController b){
        return PlayerFace.contains(b.getPosition()) && PlayerFace.contains(b.getDownLocation()) ;
    }

    /**
     * Implements the Platform's position by the Move Amount
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
     * Set a new center location.
     * @param p - The new center location.
     */
    public void moveTo(Point p){
        //Set the Center Location
        BallPoint.setLocation(p);
        // Set top left corner location of the Player Shape
        PlayerFace.setLocation(BallPoint.x - (int)PlayerFace.getWidth()/2,BallPoint.y);
    }

    /**
     * To Return the Border Color
     * @return PLAYER_BORDER_COLOR
     */
    public static Color getBorderColor() {
        return PLAYER_BORDER_COLOR;
    }

    /**
     * To return the Inner Color
     * @return PLAYER_INNER_COLOR
     */
    public static Color getInnerColor() {
        return PLAYER_INNER_COLOR;
    }
}