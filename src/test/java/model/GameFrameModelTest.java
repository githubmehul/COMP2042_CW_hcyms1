package model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameFrameModelTest {
    GameFrameModel gameFrameModel = new GameFrameModel();

    @Test
    void initialize() {
        gameFrameModel.initialize();
    }

    @Test
    void enableGameBoard() {
        gameFrameModel.enableGameBoard();
    }

    @Test
    void windowGainedFocus() {
        assertFalse(gameFrameModel.isGaming());
    }

    @Test
    void windowLostFocus() {
        assertFalse(gameFrameModel.isGaming());
    }
}