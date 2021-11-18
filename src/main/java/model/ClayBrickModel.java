package model;

import controller.BrickController;

import java.awt.*;
import java.awt.Point;


/***
 * ClayBrickModel Class extends BrickController Class to create the implementation of
 * the ClayBrickModel (Abstraction)
 */
public class ClayBrickModel extends BrickController {

    //Name of Brick
    private static final String BRICK_NAME = "Clay Brick";
    //Inner Color and Border Color
    private static final Color BRICK_INNER_COLOR = new Color(178, 34, 34).darker();
    private static final Color BRICK_BORDER_COLOR = Color.GRAY;
    //Strength of Brick
    private static final int BRICK_STRENGTH = 1;

    /**
     * Implement the super BrickController Class
     * @param point
     * @param size
     */
    public ClayBrickModel(Point point, Dimension size){

        super(BRICK_NAME,point,size,BRICK_STRENGTH);
    }

    /**
     * An abstract implementation of makeBrickFace of BrickController class to create the
     * shape of the brick.
     * @param pos
     * @param size
     * @return Rectangle(pos , size)
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size)
    {
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
     * Returns the BrickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {

        return super.getBrickFace();
    }


}