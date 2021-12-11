package model;

import controller.BrickController;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

import static controller.HighScoreController.getHighScoreInstance;


/***
 * The ClayBrickModel class is a child class of BrickController class.
 * ClayBrickModel Class extends BrickControllerClass to create the implementation of
 * the ClayBrickModel.
 * It is responsible for defining its colours, strength and crack properties.
 */
public class ClayBrickModel extends BrickController {
    //Inner Color and Border Color
    private static final Color brickInnerColor = new Color(178, 34, 34).darker();
    private static final Color brickBorderColor = Color.GRAY;
    //Strength of Brick
    private static final int BRICK_STRENGTH = 1;

    /**
     * Implement the super BrickController Class
     *
     * @param point - The point position of the brick (left)
     * @param size  - Encapsulates the Width and Height of Brick
     */
    public ClayBrickModel(Point point, Dimension size) {

        super(point, size, BRICK_STRENGTH);
    }

    /**
     * Implements the makeBall Method from the BrickController Class
     *
     * @param pos- The position coordinate of the Brick
     * @param size - Encapsulates the Width and Height of Brick
     * @return
     */
    @Override
    protected Shape makeBrickShape(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * Sets the Inner Color of the Ball
     *
     * @return brickInnerColor - Inner Color of the Brick
     */
    @Override
    protected Color setBrickInnerColor() {
        return brickInnerColor;
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
     * {@inheritDoc}
     */
    @Override
    public Shape getChildBrickShape() {

        return super.getParentBrickShape();
    }

    @Override
    public boolean setImpact(Point2D point, int direction) {
        //if the cement is not broken
        if (super.isBroken())
            return false;
        //Reduce the strength
        super.impact();
        //if the cement is broken
        if (!super.isBroken()) {

            //return false
            return false;
        }
        getHighScoreInstance().setScore(getHighScoreInstance().getScore() + 1);
        return true;
    }
}