package controller;

import model.RubberBallModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * Implements the Functionality Characteristics in the Wall of the Game
 */
public class WallController {

    private final Rectangle area;
    private final Point StartPoint;
    private BallController ballController;
    private PlayerController playerController;
    private BrickController[] brickController;
    private int bricks;
    private int brickCount;
    private int ballCount = 3;
    private boolean BallLost = false;

    /**
     * Responsible for specifying the StartPoint of the Ball Position, Speed, Parameters of Player Model,
     * And Wall Area Definition.
     *
     * @param drawArea - Area of Level Wall
     * @param ballPos  - Position of Ball
     */

    public WallController(Rectangle drawArea, Point ballPos) {

        //specifies the location of the ball position
        this.StartPoint = new Point(ballPos);
        //create random object
        new Random();
        //adding ball position in the make ball function
        makeBall(ballPos);

        int speedX = 3;
        int speedY = -3;

        // sets the ball speed x and speed y
        getBall().setSpeedX(speedX);
        getBall().setSpeedY(speedY);

        //Adds in the Parameters for the player model
        setPlayer(new PlayerController((Point) ballPos.clone(), 150, 10, drawArea));

        //Define the Area of the wall
        area = drawArea;
    }

    /**
     * Assigns the ball position in the RubberBallModel
     *
     * @param ballPos - Position of Ball
     */
    private void makeBall(Point2D ballPos) {
        setBall(new RubberBallModel(ballPos));
    }

    /**
     * Calls the methods to implement the movement of the player and the movement of ball
     */
    public void move() {
        getPlayer().move();
        getBall().move();
    }

    /**
     * Implements the impact of the ball on the various layout of the wall ,
     * and the outcome of it
     */
    public void findImpacts() {
        if (getPlayer().impact(getBall())) {
            new AudioController("Bounce Sound.wav");
            getBall().reverseY();
        }
        // if the player impacts the wall , then reduce the brick count
        else if (impactWall()) {
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            new AudioController("Bounce Sound.wav");
            brickCount--;
        } else if (impactBorder()) {
            new AudioController("Bounce Sound.wav");
            getBall().reverseX();
        } else if (getBall().getPosition().getY() < area.getY()) {
            getBall().reverseY();
        }
        //if the ball goes out of frame , reduce the ball count , and ballLost = true
        else if (getBall().getPosition().getY() > area.getY() + area.getHeight()) {
            new AudioController("Ball Lose.wav");
            ballCount--;
            BallLost = true;
        }
    }

    /**
     * Provides the implementation of when the ball hits the wall
     *
     * @return the impact of the ball on the wall
     */
    private boolean impactWall() {
        for (BrickController b : getBricks()) {
            switch (b.findImpact(getBall())) {
                case BrickController.UP_IMPACT -> {
                    getBall().reverseY();
                    return b.setImpact(getBall().getDownLocation(), CrackController.UP);
                }
                case BrickController.DOWN_IMPACT -> {
                    getBall().reverseY();
                    return b.setImpact(getBall().getUpLocation(), CrackController.DOWN);
                }
                case BrickController.LEFT_IMPACT -> {
                    getBall().reverseX();
                    return b.setImpact(getBall().getRightLocation(), CrackController.RIGHT);
                }
                case BrickController.RIGHT_IMPACT -> {
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeftLocation(), CrackController.LEFT);
                }
            }
        }
        return false;
    }

    /**
     * Provides the implementation when the ball hits the border
     *
     * @return A Boolean of when the ball hits the border
     */
    private boolean impactBorder() {
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) || (p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * Returns if the ball is lost
     *
     * @return ballLost
     */
    public boolean isBallLost() {
        return BallLost;
    }


    /**
     * When the ball is lost , this method provides the implementation for ball reset
     */
    public void ballReset() {

        getPlayer().moveTo(StartPoint);
        getBall().moveTo(StartPoint);
        int speedX, speedY;
        speedX = 3;
        speedY = -3;

        getBall().setSpeedX(speedX);
        getBall().setSpeedY(speedY);
        BallLost = false;
    }

    /**
     * Provides implementation for when the wall Resets
     */
    public void wallReset() {
        for (BrickController b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * If the ball's have ended , then set the ballCount to 0
     *
     * @return ballCount
     */
    public boolean ballEnd() {
        return ballCount == 0;
    }

    /**
     * If the all the bricks have been broken , then set the brickCount to 0
     *
     * @return brickCount
     */
    public boolean isDone() {
        return brickCount == 0;
    }

    /**
     * Sets the ball's Speed for X Coordinates
     *
     * @param s - Speed in X Direction of Ball
     */
    public void setBallXSpeed(int s) {
        ballController.setSpeedX(s);
    }

    /**
     * Sets the ball's Speed for Y Coordinates
     *
     * @param s - Speed in Y Direction of ball
     */
    public void setBallYSpeed(int s) {
        ballController.setSpeedY(s);
    }

    /**
     * resetBallCount Method:
     * Provides Implementation to reset the BallCount
     */
    public void resetBallCount() {
        ballCount = 3;
    }

    public BrickController[] getBricks() {
        return brickController;
    }

    public void setBricks(BrickController[] bricks) {
        this.brickController = bricks;
    }

    public int getBallCount() {
        return ballCount;
    }

    public BallController getBall() {
        return ballController;
    }

    public void setBall(BallController ball) {
        this.ballController = ball;
    }

    public PlayerController getPlayer() {
        return playerController;
    }

    public void setPlayer(PlayerController player) {
        this.playerController = player;
    }

    public int getBrickCount() {
        return brickCount;
    }

    public void setBrickCount(int brickCount) {
        this.brickCount = brickCount;
    }

    /**
     * Renders the brick Graphics
     *
     * @param g - Graphic Instance
     */
    public void brickRender(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();
        for (BrickController b : getBricks()) {
            if (!b.isBroken())
                b.render(g2d);
        }
    }
}