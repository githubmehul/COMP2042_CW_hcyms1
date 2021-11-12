package model;

import controller.BrickController;

import java.awt.*;
import java.awt.Point;


/***
 * ClayBrickModel Class extends BrickController Class to create the implementation of
 * the ClayBrickModel (Abstraction)
 */
public class ClayBrickModel extends BrickController {

    private static final String NAME = "Clay Brick";
    private static final Color DEF_INNER = new Color(178, 34, 34).darker();
    private static final Color DEF_BORDER = Color.GRAY;
    private static final int CLAY_STRENGTH = 1;


    /**
     * ClayBrickModel Constructor:
     * Implement the super BrickController Class
     * @param point
     * @param size
     */
    public ClayBrickModel(Point point, Dimension size){

        super(NAME,point,size,DEF_BORDER,DEF_INNER,CLAY_STRENGTH);
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
    protected Shape makeBrickFace(Point pos, Dimension size)
    {

        return new Rectangle(pos,size);
    }

    /**getBrick Method:
     * Returns the BrickFace
     * @return brickFace
     */
    @Override
    public Shape getBrick() {

        return super.brickFace;
    }


}