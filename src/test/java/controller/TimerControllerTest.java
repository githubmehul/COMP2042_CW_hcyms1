package controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerControllerTest {
    TimerController timerController = new TimerController();


    @Test
    void resetGame() {
        timerController.setMinutes(3);
        timerController.setTempSeconds(5);
        timerController.setTempMinutes(4);
        timerController.setGameRunning(true);
        timerController.resetGame(); //Check if reset, put everything back to 0 and false
        assertEquals(0, timerController.getSeconds());
        assertEquals(0, timerController.getMinutes());
        assertEquals(0, timerController.getTempMinutes());
        assertEquals(0,timerController.getTempSeconds());
        assertFalse(timerController.isGameRunning());
    }

    @org.junit.jupiter.api.Test
    void setTempSeconds() {
        timerController.setTempSeconds(30); //Set the temp second to 30
        assertEquals(30, timerController.getTempSeconds()); //Check if the temp second is 30
    }

    @org.junit.jupiter.api.Test
    void getSeconds() {
        timerController.setSeconds(15); //Set the second to 15
        assertEquals(15, timerController.getSeconds()); //Check if the second is 15
    }

    @org.junit.jupiter.api.Test
    void setSeconds() {
        timerController.setSeconds(45); //Set the second to 45
        assertEquals(45, timerController.getSeconds()); //Check if the second is 45
    }

    @org.junit.jupiter.api.Test
    void isGameRunning() {
        timerController.setGameRunning(true);
        assertTrue(timerController.isGameRunning());
    }

    @org.junit.jupiter.api.Test
    void setGameRunning() {
        timerController.setGameRunning(false);
        assertFalse(timerController.isGameRunning());
    }

    @org.junit.jupiter.api.Test
    void getMinutes() {
        timerController.setMinutes(3);
        assertEquals(3, timerController.getMinutes());
    }

    @org.junit.jupiter.api.Test
    void setMinutes() {
        timerController.setMinutes(2);
        assertEquals(2, timerController.getMinutes());
    }

    @org.junit.jupiter.api.Test
    void getTempMinutes() {
        timerController.setTempMinutes(4);
        assertEquals(4, timerController.getTempMinutes());
    }

    @org.junit.jupiter.api.Test
    void setTempMinutes() {
        timerController.setTempMinutes(1);
        assertEquals(1, timerController.getTempMinutes());
    }
}