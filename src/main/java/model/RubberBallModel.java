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


    private static final int DEF_RADIUS = 10;
    private static final Color DEF_INNER_COLOR = new Color(255, 219, 88);
    private static final Color DEF_BORDER_COLOR = DEF_INNER_COLOR.darker().darker();


    /**
     * RubberBallModel Constructor:
     * Calls the BallController Class Constructor
     * @param center
     */
    public RubberBallModel(Point2D center){

        super(center,DEF_RADIUS,DEF_RADIUS,DEF_INNER_COLOR,DEF_BORDER_COLOR);
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

//        double x = center.getX() - (radiusA / 2);
//        double y = center.getY() - (radiusB / 2);
        double x = 100;
        double y = 100;
        return new Ellipse2D.Double(x,y,radiusA,radiusB);
    }
}