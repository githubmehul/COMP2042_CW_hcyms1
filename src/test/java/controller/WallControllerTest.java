package controller;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class WallControllerTest {
    WallController wallController = new WallController(new Rectangle(0, 0, 0, 0), new Point(0, 0));


    @Test
    void isBallLost() {
        wallController.setBallXSpeed(2);
        wallController.setBallYSpeed(2);
        assertFalse(wallController.isBallLost());
    }

    @Test
    void ballReset() {
        wallController.setBallXSpeed(2);
        wallController.setBallYSpeed(2);
        wallController.ballReset();
        assertFalse(wallController.isBallLost());
    }

    @Test
    void ballEnd() {
        wallController.resetBallCount();
        assertNotEquals(wallController.getBallCount(), 0);
    }

    @Test
    void isDone() {
        wallController.setBrickCount(0);
        assertEquals(wallController.getBrickCount(), 0);
    }

    @Test
    void resetBallCount() {
        wallController.setBrickCount(2);
        wallController.ballReset();
        assertFalse(wallController.isBallLost());
        wallController.resetBallCount();
    }

    @Test
    void getBallCount() {
        wallController.setBrickCount(3);
        assertEquals(3 , wallController.getBrickCount());
    }


    @Test
    void getBrickCount() {
        wallController.setBrickCount(25);
        assertEquals(25 , wallController.getBrickCount());
    }

    @Test
    void setBrickCount() {
        wallController.setBrickCount(23);
        assertEquals(23 , wallController.getBrickCount());
    }
}