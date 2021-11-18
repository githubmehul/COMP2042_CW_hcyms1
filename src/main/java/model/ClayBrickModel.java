package model;

import controller.BrickController;

import java.awt.*;
import java.awt.Point;


/***
 * The ClayBrickModel class is a child class of BrickController class.
 * ClayBrickModel Class extends BrickControllerClass to create the implementation of
 * the ClayBrickModel.
 * It is responsible for defining its colours, strength and crack properties.
 */
public class ClayBrickModel extends BrickController {
    //Inner Color and Border Color
    private static final Color BRICK_INNER_COLOR = new Color(178, 34, 34).darker();
    private static final Color BRICK_BORDER_COLOR = Color.GRAY;
    //Strength of Brick
    private static final int BRICK_STRENGTH = 1;

    /**
     * Implement the super BrickController Class
     * @param point - The point position of the brick (left)
     * @param size - Encapsulates the Width and Height of Brick
     */
    public ClayBrickModel(Point point, Dimension size){

        super(point,size,BRICK_STRENGTH);
    }

    /**
     * Implements the makeBall Method from the BrickController Class
     * @param pos- The position coordinate of the Brick
     * @param size  - Encapsulates the Width and Height of Brick
     * @return
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size)
    {
        return new Rectangle(pos,size);
    }

    /**
     * Sets the Inner Color of the Ball
     * @return BRICK_INNER_COLOR - Inner Color of the Brick
     */
    @Override
    protected Color setBrickInnerColor() {
        return BRICK_INNER_COLOR;
    }

    /**
     * Sets the Border Color of the Ball
     * @return BRICK_BORDER_COLOR - Border Color of the Brick
     */
    @Override
    protected Color setBrickBorderColor() {
        return BRICK_BORDER_COLOR;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Shape getBrick() {

        return super.getBrickFace();
    }
}