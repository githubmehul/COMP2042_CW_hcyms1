package controller;

import model.PlayerModel;

import java.awt.*;


/**
 * The PlayerController class is responsible for defining the Player's behaviour.
 */
public class PlayerController {

    private static final int PLAYER_MOVE_AMOUNT = 5;

    private final Rectangle playerShape;
    private final Point ballPoint;
    private final int Min;
    private final int Max;
    //The move Amount
    private int moveAmount = 0;

    /**
     * 1. Assigns the value of ballPoint to ballPoint
     * 2. moveAmount is set to 0
     * 3. Create the Platform Shape with the width and height parameter
     * 4. Sets the Min and Max Values
     *
     * @param ballPoint - Position of Player
     * @param width     - Width of Player
     * @param height    - Height of Player
     * @param container - The Player Container Shape
     */
    public PlayerController(Point ballPoint, int width, int height, Rectangle container) {

        // Define location (center)
        this.ballPoint = (Point) ballPoint.clone();

        // Create the Player's Shape
        playerShape = PlayerModel.makeRectangle(width, height);

        Min = container.x + (width / 2);
        Max = Min + container.width - width;
    }


    /**
     * Returns the boolean if the Ball has impacted the Platform
     *
     * @param b - BallController Instance
     * @return A Boolean on the impact of the Ball on the Platform
     */
    public boolean impact(BallController b) {
        return playerShape.contains(b.getPosition()) && playerShape.contains(b.getDownLocation());
    }

    /**
     * Implements the Platform's position by the Move Amount
     */
    public void move() {
        double x = ballPoint.getX() + moveAmount;
        if (x < Min || x > Max)
            return;
        ballPoint.setLocation(x, ballPoint.getY());
        playerShape.setLocation(ballPoint.x - (int) playerShape.getWidth() / 2, ballPoint.y);
    }

    /**
     * When Player moves left , method is responsible to reduce the move amount
     */
    public void moveLeft() {
        moveAmount = -PLAYER_MOVE_AMOUNT;
    }

    /**
     * When Player moves right , method is responsible to reduce the move amount
     */
    public void moveRight() {
        moveAmount = PLAYER_MOVE_AMOUNT;
    }

    /**
     * stops the move amount so that the platform does not move
     */
    public void stop() {
        moveAmount = 0;
    }

    /**
     * returns the playerShape
     *
     * @return playerShape - Shape of the Player
     */
    public Shape getPlayerShape() {
        return playerShape;
    }

    /**
     * Set a new center location.
     *
     * @param p - The new center location.
     */
    public void moveTo(Point p) {
        //Set the Center Location
        ballPoint.setLocation(p);
        // Set top left corner location of the Player Shape
        playerShape.setLocation(ballPoint.x - (int) playerShape.getWidth() / 2, ballPoint.y);
    }
    public void setMoveAmount(int moveAmount) {
        this.moveAmount = moveAmount;
    }
    public int getMoveAmount() {
        return moveAmount;
    }
}