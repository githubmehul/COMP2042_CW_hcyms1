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

    private static final String BRICK_NAME = "Steel Brick";
    private static final Color BRICK_INNER_COLOR = new Color(203, 203, 201);
    private static final Color BRICK_BORDER_COLOR = Color.BLACK;
    private static final int BRICK_STRENGTH = 1;
    private static final double BRICK_BREAK_PROBABILITY = 0.4;

    private Random random;
    private Shape brickFace;

    /**
     * SteelBrickModel Constructor:
     * Implement the super BrickController Class
     * Creates a Random Object
     * BrickFace is Assigned the super classes brickFace
     * @param point
     * @param size
     */
    public SteelBrickModel(Point point, Dimension size){
        super(BRICK_NAME,point,size,BRICK_BORDER_COLOR,BRICK_INNER_COLOR,BRICK_STRENGTH);
        random = new Random();
        brickFace = super.getBrickFace();
    }

    /**
     * makeBrickFace Method:
     * An abstract implementation of makeBrickFace of BrickController class to create the
     * shape of the brick. , value assigned to brickFace
     * @param pos
     * @param size
     * @return Rectangle(pos , size)
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {

        return new Rectangle(pos,size);
    }

    /**getBrick Method:
     * Returns the BrickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }


    /**
     * setImpact Method:
     * Set's the Impact of the Steel Brick Model
     * @param point
     * @param dir
     * @return
     */
    public  boolean setImpact(Point2D point , int dir){
        if(super.isBroken())
            return false;
        impact();
        return  super.isBroken();
    }

    /**
     * impact Method:
     * If the Randomly Generated Value is less than the Steel Probability , impact the block
     */
    public void impact(){
        if(random.nextDouble() < BRICK_BREAK_PROBABILITY){
            super.impact();
        }
    }

}