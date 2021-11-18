package model;

import controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;


/***
 * SteelBrickModel Class extends BrickController Class to create the implementation of
 * the SteelBrickModel (Abstraction)
 */
public class SteelBrickModel extends BrickController {

    //Brick Name
    private static final String BRICK_NAME = "Steel Brick";
    //Inner Color and Border Color
    private static final Color BRICK_INNER_COLOR = new Color(203, 203, 201);
    private static final Color BRICK_BORDER_COLOR = Color.BLACK;
    //Strength of Brick
    private static final int BRICK_STRENGTH = 1;
    //Probability to determine a crack
    private static final double BRICK_BREAK_PROBABILITY = 0.4;

    private Random random;
    private Shape brickSteelFace;

    /**
     * Implement the super BrickController Class
     * Creates a Random Object
     * BrickFace is Assigned the super classes brickFace
     * @param point
     * @param size
     */
    public SteelBrickModel(Point point, Dimension size){
        super(BRICK_NAME,point,size,BRICK_STRENGTH);
        random = new Random();
        brickSteelFace = super.getBrickFace();
    }
    /**
     * An abstract implementation of makeBrickFace of BrickController class to create the
     * shape of the brick.
     * @param pos
     * @param size
     * @return Rectangle(pos , size)
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
        return new Rectangle(pos,size);
    }
    /**
     * Abstract Method for setting the Border Color of the Brick
     */
    @Override
    protected Color setBrickInnerColor() {
        return BRICK_INNER_COLOR;
    }

    /**
     * Abstract Method for setting the Border Color of the Brick
     */
    @Override
    protected Color setBrickBorderColor() {
        return BRICK_BORDER_COLOR;
    }
    /**
     * Returns the Steel BrickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return brickSteelFace;
    }
    /**
     * Set's the Impact of the Steel Brick Model
     * @param point
     * @param dir
     * @return
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return super.isBroken();
    }
    /**
     * If the Randomly Generated Value is less than the Steel Probability , impact the block
     */
    public void impact(){
        if(random.nextDouble() < BRICK_BREAK_PROBABILITY){
            super.impact();
        }
    }
}