package model;

import controller.BallController;
import controller.BrickController;
import controller.CrackController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/**
 * WallModel Class:
 * Implements the Functionality Characteristics in the Wall , Impact and Ball Count.
 */
public class WallModel {
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
    private int BrickCount;
    private int BallCount;
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
    public WallModel(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio, Point ballPos){

        //specifies the location of the ball position
        this.StartPoint = new Point(ballPos);
        // level takes in the value of makelevels function
        levels = makeLevels(drawArea,brickCount,lineCount,brickDimensionRatio);
        level = 0;
        BallCount = 3;
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
     * makeSingleTypeLevel Method:
     * Creates the 1st Level for The ClayBrickModel
     * @param drawArea
     * @param brickCnt
     * @param lineCnt
     * @param brickSizeRatio
     * @param type
     * @return
     */
    private BrickController[] makeSingleTypeLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int type){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            double x = (i % brickOnLine) * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,type);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = new ClayBrickModel(p,brickSize);
        }
        return tmp;

    }

    /**
     * makeChessboardLevel Method:
     * Implement the Chessboard Pattern for the levels after the 1st level
     * @param drawArea
     * @param brickCnt
     * @param lineCnt
     * @param brickSizeRatio
     * @param typeA
     * @param typeB
     * @return
     */
    private BrickController[] makeChessboardLevel(Rectangle drawArea, int brickCnt, int lineCnt, double brickSizeRatio, int typeA, int typeB){
        /*
          if brickCount is not divisible by line count,brickCount is adjusted to the biggest
          multiple of lineCount smaller then brickCount
         */
        brickCnt -= brickCnt % lineCnt;

        int brickOnLine = brickCnt / lineCnt;

        int centerLeft = brickOnLine / 2 - 1;
        int centerRight = brickOnLine / 2 + 1;

        double brickLen = drawArea.getWidth() / brickOnLine;
        double brickHgt = brickLen / brickSizeRatio;

        brickCnt += lineCnt / 2;

        BrickController[] tmp  = new BrickController[brickCnt];

        Dimension brickSize = new Dimension((int) brickLen,(int) brickHgt);
        Point p = new Point();

        int i;
        for(i = 0; i < tmp.length; i++){
            int line = i / brickOnLine;
            if(line == lineCnt)
                break;
            int posX = i % brickOnLine;
            double x = posX * brickLen;
            x =(line % 2 == 0) ? x : (x - (brickLen / 2));
            double y = (line) * brickHgt;
            p.setLocation(x,y);

            boolean b = ((line % 2 == 0 && i % 2 == 0) || (line % 2 != 0 && posX > centerLeft && posX <= centerRight));
            tmp[i] = b ?  makeBrick(p,brickSize,typeA) : makeBrick(p,brickSize,typeB);
        }

        for(double y = brickHgt;i < tmp.length;i++, y += 2*brickHgt){
            double x = (brickOnLine * brickLen) - (brickLen / 2);
            p.setLocation(x,y);
            tmp[i] = makeBrick(p,brickSize,typeA);
        }
        return tmp;
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
     * makeLevels Method:
     * Uses the array to call the assigned methods to create the Level Layout
     * @param drawArea
     * @param brickCount
     * @param lineCount
     * @param brickDimensionRatio
     * @return
     */
    private BrickController[][] makeLevels(Rectangle drawArea, int brickCount, int lineCount, double brickDimensionRatio){
        BrickController[][] tmp = new BrickController[LEVELS_COUNT][];
        tmp[0] = makeSingleTypeLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY_BRICK);
        tmp[1] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY_BRICK,CEMENT_BRICK);
        tmp[2] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,CLAY_BRICK,STEEL_BRICK);
        tmp[3] = makeChessboardLevel(drawArea,brickCount,lineCount,brickDimensionRatio,STEEL_BRICK,CEMENT_BRICK);
        return tmp;
    }

    private BrickController makeBrick(Point point, Dimension size, int type){
        BrickController out;
        switch(type){
            case CLAY_BRICK:
                out = new ClayBrickModel(point,size);
                break;
            case STEEL_BRICK:
                out = new SteelBrickModel(point,size);
                break;
            case CEMENT_BRICK:
                out = new CementBrickModel(point, size);
                break;
            default:
                throw  new IllegalArgumentException(String.format("Unknown Type:%d\n",type));
        }
        return  out;
    }
    /**
     * move Method:
     * When this method is called , it calls the methods to implement the movement of the player and the
     * width . height and movement of ball
     */
    public void move(){
        player.move();
        ball.move();
    }

    /**
     * findImpacts Method:
     * implements the impact of the ball on the various layout of the wall , and the outcome of it
     */
    public void findImpacts(){
        //if the player impacts the ball , then reverse y
        if(player.impact(ball)){
            ball.reverseY();
        }
        // if the player impacts the wall , then reduce the brick count
        else if(impactWall()){
            /*for efficiency reverse is done into method impactWall
             * because for every brick program checks for horizontal and vertical impacts
             */
            BrickCount--;
        }
        //if the ball hits the border of the wall , reverse x
        else if(impactBorder()) {
            ball.reverseX();
        }

        else if(ball.getPosition().getY() < area.getY()){
            ball.reverseY();
        }
        //if the ball goes out of frame , reduce the ball count , and balllost = true
        else if(ball.getPosition().getY() > area.getY() + area.getHeight()){
            BallCount--;
            BallLost = true;
        }
    }

    /**
     * impactWall Method:
     * Provides the implementation of when the ball hits the wall
     * @return
     */
    private boolean impactWall(){
        for(BrickController b : bricks){
            switch(b.findImpact(ball)) {
                //Vertical Impact
                case BrickController.UP_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getDownLocation(), CrackController.UP);
                case BrickController.DOWN_IMPACT:
                    ball.reverseY();
                    return b.setImpact(ball.getUpLocation(), CrackController.DOWN);

                //Horizontal Impact
                case BrickController.LEFT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getRightLocation(), CrackController.RIGHT);
                case BrickController.RIGHT_IMPACT:
                    ball.reverseX();
                    return b.setImpact(ball.getLeftLocation(), CrackController.LEFT);
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
        Point2D p = ball.getPosition();
        return ((p.getX() < area.getX()) ||(p.getX() > (area.getX() + area.getWidth())));
    }

    /**
     * getBrickCount Method:
     * Returns the brickCount
     * @return brickCount
     */
    public int getBrickCount(){
        return BrickCount;
    }

    /**
     * getBallCount Method:
     * Returns the ballCount
     * @return ballCount
     */
    public int getBallCount(){
        return BallCount;
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
        player.moveTo(StartPoint);
        ball.moveTo(StartPoint);
        int speedX,speedY;
        do{
            speedX = random.nextInt(5) - 2;
        }while(speedX == 0);
        do{
            speedY = -random.nextInt(3);
        }while(speedY == 0);

        ball.setSpeed(speedX,speedY);
        BallLost = false;
    }

    /**
     * wallReset Method:
     * Provides implementation for when the wall Resets
     */
    public void wallReset(){
        for(BrickController b : bricks)
            b.repair();
        BrickCount = bricks.length;
        BallCount = 3;
    }

    /**
     * ballEnd Method:
     * If the ball's have ended , then set the ballcount to 0
     * @return ballCount
     */
    public boolean ballEnd(){
        return BallCount == 0;
    }

    /**
     * isDone Method:
     * If the all the bricks have been broken , then set the brickCount to 0
     * @return brickCount
     */
    public boolean isDone(){
        return BrickCount == 0;
    }


    /**
     * nextLevel Method:
     * Proved implementation to go to the next level , sets the brickCount and
     * the bricks based on the level
     */
    public void nextLevel(){
        setBricks(levels[level++]);;
        this.BrickCount = bricks.length;
//        getLevelsCount();

    }

    /**
     * hasLevel Method:
     * Returns if the current level is less than the level length
     * @return level < levels.length;
     */
    public boolean hasLevel(){
        return level < levels.length;
    }


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
        BallCount = 3;
    }
    public BrickController[] getBricks(){
        return bricks;
    }
    public void setBricks(BrickController[] bricks) {
        this.bricks = bricks;
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
}