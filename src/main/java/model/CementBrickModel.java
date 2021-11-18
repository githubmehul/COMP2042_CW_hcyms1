package model;

import controller.*;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

/***
 * CementBrickModel Class extends BrickController Class to create the implementation of
 * the CementBrickModel (Abstraction)
 */
public class CementBrickModel extends BrickController {

    //Brick Name
    private static final String BRICK_NAME = "Cement Brick";
    //Define the Inner and Border Color
    private static final Color BRICK_INNER_COLOR = new Color(147, 147, 147);
    private static final Color BRICK_BORDER_COLOR = new Color(217, 199, 175);
    //Brick Strength
    private static final int BRICK_STRENGTH = 2;
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    private CrackController Crack;
    private Shape brickCementFace;

    /**
     * Implement the super BrickController Class
     * @param point
     * @param size
     */
    public CementBrickModel(Point point, Dimension size){
        super(BRICK_NAME,point,size,BRICK_STRENGTH);
        Crack = new CrackController(DEF_CRACK_DEPTH, DEF_STEPS);
        brickCementFace = super.getBrickFace();
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
    protected Color setBrickBorderColor() {
        return BRICK_BORDER_COLOR;
    }
    /**
     * Abstract Method for setting the Inner Color of the Brick
     */
    @Override
    protected Color setBrickInnerColor() {
        return BRICK_INNER_COLOR;
    }
    /**
     * Implements the Functionality to set the Impact based on the boolean value
     * @param point
     * @param direction
     */
    @Override
    public boolean setImpact(Point2D point, int direction) {
        //if the cement is not broken
        if(super.isBroken())
            return false;
        //Reduce the strength
        super.impact();
        //if the cement is broken
        if(!super.isBroken()){
            //make a crack at the point
            Crack.makeCrack(point,direction);
            //update the brick
            updateBrick();
            //return false
            return false;
        }
        return true;
    }

    /**
     * Returns the BrickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return brickCementFace;
    }
    /**
     * Implementation of crack when the Brick is Impacted by the Ball.
     */
    private void updateBrick(){
        //if the brick is struck
        if(!super.isBroken()){
            //Create the Crack on the Brick
            GeneralPath gp = Crack.draw();
            gp.append(super.getBrickFace(),false);
            //Update the BrickFace to the Crack
            brickCementFace = gp;
        }
    }
    /**
     * Implementation to remove the broken crack and
     * increase the strength of the brick
     */
    public void repair(){
        //Call the repair method in brickcontroller
        super.repair();
        //reset the crack
        Crack.reset();
        //update the brickface
        brickCementFace = super.getBrickFace();
    }
}