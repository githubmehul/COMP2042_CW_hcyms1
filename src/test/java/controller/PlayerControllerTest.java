package controller;

import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class PlayerControllerTest {
    PlayerController playerController = new PlayerController((Point) new Point(300,430).clone(),150,10, new Rectangle(0,0,600,450));
    @Test
    void moveLeft() {
        playerController.setMoveAmount(3);
        playerController.moveRight();
        playerController.moveLeft();
        assertEquals(-5 , playerController.getMoveAmount());
    }

    @Test
    void moveRight() {
        playerController.setMoveAmount(3);
        playerController.moveRight();
        playerController.moveLeft();
        assertEquals(-5 , playerController.getMoveAmount());
    }

    @Test
    void stop() {
        playerController.setMoveAmount(3);
        playerController.moveLeft();
        playerController.moveRight();
        playerController.stop();
        assertEquals(0 , playerController.getMoveAmount());
    }

}