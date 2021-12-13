package model;

import controller.BallController;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

/***
 * RubberBallModel Class extends BallController Class creates the implementation of
 * the RubberBall.
 * Responsible for how its {@code Shape} should be created and defining its colours.
 */
public class RubberBallModel extends BallController {

    private static final int BALL_RADIUS = 10;
    private static final Color BALL_INNER_COLOR = new Color(255, 219, 88);
    private static final Color BALL_BORDER_COLOR = BALL_INNER_COLOR.darker().darker();

    /**
     * Calls the BallController Constructor and sets the Radius of the Ball.
     *
     * @param center - The center position
     */
    public RubberBallModel(Point2D center) {
        super(center, BALL_RADIUS, BALL_RADIUS);
    }


    /**
     * Implements the makeBall Method from the BallController Class
     *
     * @param center - The center position.
     * @param width  - The horizontal diameter.
     * @param height - The vertical diameter.
     * @return The Shape of the Ball
     */
    public Shape makeBallShape(Point2D center, int width, int height) {
        double x = center.getX() - (width >> 2);
        double y = center.getY() - (height >> 2);
        return new Ellipse2D.Double(x, y, width, height);
    }

    /**
     * Sets the Inner Color of the Ball
     *
     * @return BALL_INNER_COLOR - Inner Color of the Ball
     */
    @Override
    protected Color setBallInnerColor() {
        return BALL_INNER_COLOR;
    }

    /**
     * Sets the Border Color of the Ball
     *
     * @return BALL_BORDER_COLOR - Border Color of the Ball
     */
    @Override
    protected Color setBallBorderColor() {
        return BALL_BORDER_COLOR;
    }

}