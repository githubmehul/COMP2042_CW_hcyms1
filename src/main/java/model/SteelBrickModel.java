package model;

import controller.BrickController;
import static controller.HighScoreController.getInstance;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;



/***
 * The SteelBrickModel class is a child class of BrickController class.
 * SteelBrickModel Class extends BrickControllerClass to create the implementation of
 * the SteelBrickModel.
 * It is responsible for defining its colours, strength and crack properties.
 */
public class SteelBrickModel extends BrickController {

    //Inner Color and Border Color
    private static final Color BRICK_INNER_COLOR = new Color(203, 203, 201);
    private static final Color BRICK_BORDER_COLOR = Color.BLACK;
    private static final int BRICK_STRENGTH = 1;
    //Probability to determine a crack
    private static final double BRICK_BREAK_PROBABILITY = 0.4;

    private Random random;
    private Shape brickFace;

    /**
     * Implement the super BrickController Class
     * @param point - The point position of the brick (left)
     * @param size - Encapsulates the Width and Height of Brick
     */
    public SteelBrickModel(Point point, Dimension size){
        super(point,size,BRICK_STRENGTH);
        random = new Random();
        brickFace = super.getBrickShape();
    }

    /**
     * Implements the makeBall Method from the BrickController Class
     * @param pos- The position coordinate of the Brick
     * @param size  - Encapsulates the Width and Height of Brick
     * @return
     */
    @Override
    protected Shape makeBrickFace(Point pos, Dimension size) {
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
        return brickFace;
    }

    /**
     * {@inheritDoc}
     */
    public boolean setImpact(Point2D point, int direction) {
        //if the cement is not broken
        if(super.isBroken())
            return false;
        //Reduce the strength
        impact();
        //if the cement is broken
        if(!super.isBroken()){
            //return false
            return false;
        }
        getInstance().setScore(getInstance().getScore()+3);
        return true;
    }
    /**
     * Calls the parent's {@code impact()} method based on the probability.
     */
    public int impact(){
        if(random.nextDouble() < BRICK_BREAK_PROBABILITY){
            super.impact();
        }
        return 0;
    }
}