package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighScoreControllerTest {
    HighScoreController highScoreController = new HighScoreController();

    @Test
    void checkScore() {
        highScoreController.setScore(10);
        highScoreController.checkScore();
        assertEquals("Mehul:10" , highScoreController.getHighScore());
    }

    @Test
    void sortHighScore() {
        highScoreController.setScore(20);
        highScoreController.checkScore();
        highScoreController.sortHighScore();
        assertEquals("Mehul:20" , highScoreController.getHighScore());
    }

    @Test
    void getHighScore() {
        highScoreController.setHighScore("Mehul:10");
        assertEquals("Mehul:10" , highScoreController.getHighScore());

    }

    @Test
    void setHighScore() {
        highScoreController.setHighScore("Mehul:10");
        assertEquals("Mehul:10" , highScoreController.getHighScore());

    }

    @Test
    void getScore() {
        highScoreController.setScore(10);
        assertEquals(10 , highScoreController.getScore());
    }

    @Test
    void setScore() {
        highScoreController.setScore(10);
        assertEquals(10 , highScoreController.getScore());
    }
}