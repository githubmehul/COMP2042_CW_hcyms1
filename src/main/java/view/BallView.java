package view;


import controller.BallController;
import model.RubberBallModel;

import java.awt.*;

public class BallView {

    private final BallController ballController;

    /**
     * Calls the render function in the PlayerView Constructor.
     *
     * @param g2d
     */
    public BallView(Graphics2D g2d, BallController ballController) {
        this.ballController = ballController;
        ballRender(g2d);
    }

    /**
     * Renders the Ball Shape and Color, called in the wallController Class
     * @param g
     */
    public void ballRender(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(RubberBallModel.getBallInnerColor());
        g2d.fill(ballController.getBallFace());

        // Set the border colour
        g2d.setColor(RubberBallModel.getBallBorderColor());
        g2d.draw(ballController.getBallFace());
    }
}
