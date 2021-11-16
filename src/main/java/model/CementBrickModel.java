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

    private static final String BRICK_NAME = "Cement Brick";
    private static final Color BRICK_INNER_COLOR = new Color(147, 147, 147);
    private static final Color BRICK_BORDER_COLOR = new Color(217, 199, 175);
    //Hit the Cement twice to crack it.
    private static final int BRICK_STRENGTH = 2;

    //Object crack
    private CrackController Crack;
    //Object BrickFace from Shape
    private Shape brickFace;


    /**
     * CementBrickModel Constructor:
     * Implement the super BrickController Class , the Crack Constructor ,
     * and the BrickFace of Brick Controller Class
     * @param point
     * @param size
     */
    public CementBrickModel(Point point, Dimension size){
        super(BRICK_NAME,point,size,BRICK_BORDER_COLOR,BRICK_INNER_COLOR,BRICK_STRENGTH);
        Crack = new CrackController(this, DEF_CRACK_DEPTH, DEF_STEPS);
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

    /**
     * setImpact Method:
     * Implements the Functionality to set the Impact based on the boolean value
     * @param point
     * @param direction
     * @return
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


    /**getBrick Method:
     * Returns the BrickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {
        return brickFace;
    }

    /**
     * updateBrick Method:
     * Implementation when the Brick is Impacted by the Ball.
     */
    private void updateBrick(){
        //if the brick is struck
        if(!super.isBroken()){
            //Create the Crack on the Brick
            GeneralPath gp = Crack.draw();
            gp.append(super.getBrickFace(),false);
            //Update the BrickFace to the Crack
            brickFace = gp;
        }
    }

    /**
     * repair Method:
     * Implementation to remove the broken crack and increase the strength of the brick
     */
    public void repair(){
        //Call the repair method in brickcontroller
        super.repair();
        //reset the crack
        Crack.reset();
        //update the brickface
        brickFace = super.getBrickFace();
    }
}