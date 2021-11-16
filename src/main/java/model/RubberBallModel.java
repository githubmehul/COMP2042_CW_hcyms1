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
     * RubberBallModel Constructor:
     * Calls the BallController Class Constructor
     * @param center
     */
    public RubberBallModel(Point2D center){

        super(center,BALL_RADIUS,BALL_RADIUS,BALL_INNER_COLOR,BALL_BORDER_COLOR);
    }


    /**
     * makeBall Method:
     *Provides the implementation to set the initial location of the ball , with the width and height
     * @param center
     * @param radiusA
     * @param radiusB
     * @return
     */
    @Override
    protected Shape makeBall(Point2D center, int radiusA, int radiusB) {
        double x = center.getX() - (radiusA / 2);
        double y = center.getY() - (radiusB / 2);
        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}