package model;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;

import controller.BrickController;
import controller.CrackController;

import static controller.HighScoreController.getHighScoreInstance;

/***
 * The CementBrickModel class is a child class of BrickController class.
 * CementBrickModel Class extends BrickControllerClass to create the implementation of
 * the CementBrickModel.
 * It is responsible for defining its colours, strength and crack properties.
 */
public class CementBrickModel extends BrickController {

    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;
    //Define the Inner and Border Color
    private static final Color brinkInnerColor = new Color(147, 147, 147);
    private static final Color brickBorderColor = new Color(217, 199, 175);
    //Brick Strength
    private static final int BRICK_STRENGTH = 2;
    private final CrackController Crack;
    private Shape brickFace;

    /**
     * Implement the super BrickController Class
     *
     * @param point - The point position of the brick (left)
     * @param size  - Encapsulates the Width and Height of Brick
     */
    public CementBrickModel(Point point, Dimension size) {
        super(point, size, BRICK_STRENGTH);
        Crack = new CrackController(this, DEF_CRACK_DEPTH, DEF_STEPS);
        brickFace = super.getParentBrickShape();
    }

    /**
     * Implements the makeBall Method from the BrickController Class
     *
     * @param pos- The position coordinate of the Brick
     * @param size - Encapsulates the Width and Height of Brick
     * @return The Shape of the Cement Brick
     */
    @Override
    protected Shape makeBrickShape(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * Sets the Border Color of the Ball
     *
     * @return brickBorderColor - Border Color of the Brick
     */
    @Override
    protected Color setBrickBorderColor() {
        return brickBorderColor;
    }

    /**
     * Sets the Inner Color of the Ball
     *
     * @return brinkInnerColor- Inner Color of the Brick
     */
    @Override
    protected Color setBrickInnerColor() {
        return brinkInnerColor;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setImpact(Point2D point, int direction) {
        //if the cement is not broken
        if (super.isBroken())
            return false;
        //Reduce the strength
        super.impact();
        //if the cement is broken
        if (!super.isBroken()) {
            //make a crack at the point
            Crack.makeCrack(point, direction);
            //update the brick
            updateBrick();
            //return false
            return false;
        }
        getHighScoreInstance().setScore(getHighScoreInstance().getScore() + 2);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getChildBrickShape() {
        return brickFace;
    }

    /**
     * Draw cracks on the Brick if it's not broken.
     */
    private void updateBrick() {
        //if the brick is struck
        if (!super.isBroken()) {
            //Create the Crack on the Brick
            GeneralPath gp = Crack.draw();
            gp.append(super.getParentBrickShape(), false);
            //Update the BrickFace to the Crack
            brickFace = gp;
        }
    }

    /**
     * Calls the parent's {@code repair()} method and resets the cracks.
     */
    public void repair() {
        //Call the repair method in brickController
        super.repair();
        //reset the crack
        Crack.reset();
        //update the brickFace
        brickFace = super.getParentBrickShape();
    }
}