package model;

import controller.BrickController;

import java.awt.*;
import java.awt.geom.Point2D;


/***
 * The FireBrickModel class is a child class of BrickController class.
 * FireBrickModel Class extends BrickController Class to create the implementation of
 * the FireBrickModel.
 * It is responsible for defining its colours, strength properties
 */
public class FireBrickModel extends BrickController {
    private static final Color BRICK_INNER_COLOR = new Color(247, 55, 24);
    private static final Color BRICK_BORDER_COLOR = new Color(255, 203, 0);
    private static final int FAST_STRENGTH = 2;
    /**
     * Implement the super BrickController Class
     *
     * @param point - The point position of the brick (left)
     * @param size  - Encapsulates the Width and Height of Brick
     */
    public  FireBrickModel(Point point, Dimension size) {
        super(point, size, FAST_STRENGTH);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean setImpact(Point2D point, int dir) {
        if (super.isBroken())
            return false;
        else {
            brickImpact();
        }
        return super.isBroken();
    }

    /**
     * Implements the makeBrickShape from the BrickController Class
     *
     * @param pos- The position coordinate of the Brick
     * @param size - Encapsulates the Width and Height of Brick
     * @return - Shape of Parent Brick
     */
    @Override
    protected Shape makeBrickShape(Point pos, Dimension size) {
        return new Rectangle(pos, size);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getChildBrickShape() {
        return super.getParentBrickShape();
    }

    /**
     * Implements the change in speed functionality , called in the SetImpact Method
     */
    public void brickImpact() {
        super.impact();
        if (super.isBroken()) {
            LevelModel.getWall().setBallXSpeed(2);
            LevelModel.getWall().setBallYSpeed(-2);
        }
    }

    /**
     * Sets the Inner Color of the Ball
     *
     * @return BRICK_INNER_COLOR - Inner Color of the Brick
     */
    @Override
    protected Color setBrickInnerColor() {
        return BRICK_INNER_COLOR;
    }

    /**
     * Sets the Border Color of the Ball
     *
     * @return BRICK_BORDER_COLOR - Border Color of the Brick
     */
    @Override
    protected Color setBrickBorderColor() {
        return BRICK_BORDER_COLOR;
    }

}