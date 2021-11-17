package controller;

import controller.BallController;
import controller.BrickController;
import controller.CrackController;
import model.PlayerModel;
import model.RubberBallModel;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * WallModel Class:
 * Implements the Functionality Characteristics in the Wall , Impact and Ball Count.
 */
public class WallController {
    //Number of Levels
    public static final int LEVELS_COUNT = 4;
    // Strength to Break the Blocks
    private static final int CLAY_BRICK = 1;
    private static final int STEEL_BRICK = 2;
    private static final int CEMENT_BRICK = 3;

    private Random random;
    private Rectangle area;

    private BrickController[] bricks;
    private BallController ball;
    private PlayerModel player;

    private BrickController[][] levels;
    private int level;

    private Point StartPoint;
    private int brickCount;
    private int ballCount;
    private boolean BallLost;

    /**
     * WallModel Constructor:
     * Takes in the parameters , to make the level , specifiy the ball start point , the player model and
     * the initial speed.
     * @param drawArea
     * @param brickCount
     * @param lineCount
     * @param brickDimensionRatio
     * @param ballPos
     */
    public WallController(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        //specifies the location of the ball position
        this.StartPoint = new Point(ballPos);
        // level takes in the value of makelevels function
//        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
//        level = 0;
        ballCount = 3;
        BallLost = false;

        //create random object
        random = new Random();
        //adding ball position in the make ball function
        makeBall(ballPos);

        int speedX,speedY;
        do{
            speedX = random.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -random.nextInt(3);
        }while(speedY == 0);

        // sets the ball speed x and speed y
        ball.setSpeed(speedX,speedY);

        //Adds in the Parameters for the player model
        setPlayer(new PlayerModel((Point) ballPos.clone(),150,10, drawArea));
        //Define the Area of the wall
        area = drawArea;


    }

    /**
     * makeBall Method:
     * Assigns the ball position in the RubberBallModel
     * @param ballPos
     */
    private void makeBall(Point2D ballPos){
        setBall(new RubberBallModel(ballPos));
    }

    /**
     * move Method:
     * When this method is called , it calls the methods to implement the movement of the player and the
     * width . height and movement of ball
     */
    public void move(){
        getPlayer().move();
        getBall().move();
    }

    /**
     * findImpacts Method:
     * implements the impact of the ball on the various layout of the wall , and the outcome of it
     */
    public void findImpacts(){
        //if the player impacts the ball , then reverse y
        if(getPlayer().impact(getBall())){
            getBall().reverseY();
        }
        // if the player impacts the wall , then reduce the brick count
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            brickCount--;
        }
        //if the ball hits the border of the wall , reverse x
        else if(impactBorder()) {
            getBall().reverseX();
        }

        else if(getBall().getPosition().getY() < area.getY()){
            getBall().reverseY();
        }
        //if the ball goes out of frame , reduce the ball count , and balllost = true
        else if(getBall().getPosition().getY() > area.getY() + area.getHeight()){
            ballCount--;
            BallLost = true;
        }
    }

    /**
     * impactWall Method:
     * Provides the implementation of when the ball hits the wall
     * @return
     */
    private boolean impactWall(){
        for(BrickController b : getBricks()){
            switch(b.findImpact(getBall())) {
                //Vertical Impact
                case BrickController.UP_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getDownLocation(), CrackController.UP);
                case BrickController.DOWN_IMPACT:
                    getBall().reverseY();
                    return b.setImpact(getBall().getUpLocation(), CrackController.DOWN);

                //Horizontal Impact
                case BrickController.LEFT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getRightLocation(), CrackController.RIGHT);
                case BrickController.RIGHT_IMPACT:
                    getBall().reverseX();
                    return b.setImpact(getBall().getLeftLocation(), CrackController.LEFT);
            }
        }
        return false;
    }

    /**
     * impactBorder Method:
     * Provides the implementation when the ball hits the border
     * @return
     */
    private boolean impactBorder(){
        Point2D p = getBall().getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * isBallLost Method:
     * Returns if the ball is lost or not
     * @return ballLost
     */
    public boolean isBallLost(){
        return BallLost;
    }

//    public int getLevelsCount(){return level;}

    /**
     * ballReset Method:
     * When the ball is lost , this method provides the implementation for ball reset
     */
    public void ballReset(){
        getPlayer().moveTo(StartPoint);
        getBall().moveTo(StartPoint);
        int speedX,speedY;
        do{
            speedX = random.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -random.nextInt(3);
        }while(speedY == 0);

        getBall().setSpeed(speedX,speedY);
        BallLost = false;
    }

    /**
     * wallReset Method:
     * Provides implementation for when the wall Resets
     */
    public void wallReset(){
        for(BrickController b : getBricks())
            b.repair();
        brickCount = getBricks().length;
        ballCount = 3;
    }

    /**
     * ballEnd Method:
     * If the ball's have ended , then set the ballcount to 0
     * @return ballCount
     */
    public boolean ballEnd(){
        return ballCount == 0;
    }

    /**
     * isDone Method:
     * If the all the bricks have been broken , then set the brickCount to 0
     * @return brickCount
     */
    public boolean isDone(){
        return brickCount == 0;
    }


    /**
     * nextLevel Method:
     * Proved implementation to go to the next level , sets the brickCount and
     * the bricks based on the level
     */
//    public void nextLevel(){
//        setBricks(levels[level++]);;
//        this.BrickCount = bricks.length;
////        getLevelsCount();
//
//    }

    /**
     * hasLevel Method:
     * Returns if the current level is less than the level length
     * @return level < levels.length;
     */
//    public boolean hasLevel(){
//        return level < levels.length;
//    }


    /**
     * setBallXSpeed Method:
     * Sets the ball's Speed for X Coordinates
     * @param s
     */
    public void setBallXSpeed(int s){
        ball.setXSpeed(s);
    }

    /**
     * setBallYSpeed Method:
     * Sets the ball's Speed for Y Coordinates
     * @param s
     */
    public void setBallYSpeed(int s){
        ball.setYSpeed(s);
    }

    /**
     * resetBallCount Method:
     * Provides Implementation to reset the BallCount
     */
    public void resetBallCount(){
        ballCount = 3;
    }

    public BrickController[] getBricks() {
        return bricks;
    }

    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks;
    }

    public int getBallCount() {
        return ballCount;
    }

    public void setBrickCount(int brickCount){
        this.brickCount = brickCount;
    }

    public BallController getBall() {
        return ball;
    }

    public void setBall(BallController ball) {
        this.ball = ball;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    public void setPlayer(PlayerModel player) {
        this.player = player;
    }
    public int getBrickCount() {
        return brickCount;
    }
}