package model;

import controller.BallController;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/***
 * RubberBallModel Class extends BallController Class to create the implementation of
 * the RubberBall (Abstraction)
 */
public class RubberBallModel extends BallController {

    private static final int BALL_RADIUS = 10;
    private static final Color BALL_INNER_COLOR = new Color(255, 219, 88);
    private static final Color BALL_BORDER_COLOR = BALL_INNER_COLOR.darker().darker();

    /**
     * Calls the BallController Constructor
     * @param center
     */
    public RubberBallModel(Point2D center){

        super(center,BALL_RADIUS,BALL_RADIUS);
    }

    /**
     *Implements makeBall Method from BallController to create the Shape of the Ball
     * @param center
     * @param width
     * @param height
     * @return
     */
    @Override
    protected Shape makeBall(Point2D center, int width, int height) {
        double x = center.getX() - (width / 2);
        double y = center.getY() - (height / 2);
        return new Ellipse2D.Double(x,y,width,height);
    }

    /**
     * Sets the Inner Color of the Ball
     * @return BALL_INNER_COLOR
     */
    @Override
    protected Color setInnerColor() {
        return BALL_INNER_COLOR ;
    }

    /**
     * Sets the Border Color of the Ball
     * @return BALL_BORDER_COLOR
     */
    @Override
    protected Color setBorderColor() {
        return BALL_BORDER_COLOR;
    }

}