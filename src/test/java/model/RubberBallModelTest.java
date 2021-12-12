package model;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.awt.geom.Ellipse2D;

import static org.junit.jupiter.api.Assertions.*;

class RubberBallModelTest {
    RubberBallModel rubberBallModel = new RubberBallModel(new Point());

    @Test
    void makeBallShape() {
        assertEquals(new Ellipse2D.Double(1 , 1 , 1 , 1) ,rubberBallModel.makeBallShape(new Point(1, 1) , 1 , 1) );
    }

    @Test
    void setBallInnerColor() {
        assertEquals(new Color(255, 219, 88) , rubberBallModel.setBallInnerColor());
    }

    @Test
    void setBallBorderColor() {
        assertEquals(new Color(124, 107, 42), rubberBallModel.setBallBorderColor());
    }
}