package model;

import controller.WallController;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LevelModelTest {
    WallController wallController = new WallController(new Rectangle(1, 1), new Point());
    LevelModel levelModel = new LevelModel(new Rectangle(1, 1), 30, 3, 6 >> 2, wallController);

    @Test
    void getWall() {
        assertEquals(wallController, LevelModel.getWall());
    }

    @Test
    void nextLevel() {
        assertEquals(5, wallController.setBrickCount(5));
    }

    @Test
    void hasLevel() {
        assertTrue(levelModel.hasLevel());
    }

    @Test
    void getLevel() {
        assertEquals(0, levelModel.getLevel());
    }
}