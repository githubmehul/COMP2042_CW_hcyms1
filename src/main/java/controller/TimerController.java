package controller;


import java.util.TimerTask;
import java.util.Timer;

/**
 * TimerController is responsible for controlling the timer functionality in the game
 */
public class TimerController {

    /**
     * Use the Singleton pattern when a class has a single instance available to all.
     */
    private static TimerController timeInstance;
    private int seconds;
    private int minutes;
    private int tempSeconds;
    private int tempMinutes;
    private boolean gameRunning = false;


    /**
     * TimerController is responsible for the Timer Functionality
     */
    public TimerController() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (isGameRunning()) {
                    setSeconds(getSeconds() + 1);
                    if (getSeconds() > 59) {
                        setSeconds(0);
                        setMinutes(getMinutes() + 1);
                    }
                }
            }
        }, 0, 1000);
    }

    /**
     * Classes can access object instance
     *
     * @return instance - Object instance
     */
    public static TimerController getTimeInstance() {
        if (timeInstance == null) {
            timeInstance = new TimerController();

        }
        return timeInstance;
    }

    /**
     * Called when to reset the game
     */
    public void resetGame() {
        setTempSeconds(0);
        setSeconds(0);
        setMinutes(0);
        setTempMinutes(0);
        setGameRunning(false);
    }

    /**
     * Returns the Temporary Seconds
     *
     * @return tempSeconds
     */
    public int getTempSeconds() {
        return tempSeconds;
    }

    /**
     * Sets the Temporary Seconds
     *
     * @param seconds - seconds
     */
    public void setTempSeconds(int seconds) {
        this.tempSeconds = seconds;
    }

    /**
     * Returns the final seconds
     *
     * @return seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the seconds
     *
     * @param seconds - Seconds
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    /**
     * Returns boolean of when the Timer is running
     *
     * @return gameRunning - Boolean to check if the game is running
     */
    public boolean isGameRunning() {
        return gameRunning;
    }

    /**
     * Sets the GameRunning Flag.
     *
     * @param gameRunning - Boolean to check if the game is running
     */
    public void setGameRunning(boolean gameRunning) {
        this.gameRunning = gameRunning;
    }

    /**
     * Returns the Minutes in the game
     *
     * @return minutes - Minutes in the game
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Sets the minutes in the game
     *
     * @param minutes - Minutes in the game
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Gets the Temporary Minutes in the Game
     *
     * @return tempMinutes - Temp minutes in the game
     */
    public int getTempMinutes() {
        return tempMinutes;
    }

    /**
     * Sets the Temporary Minutes in the Game
     *
     * @param tempMinutes - Temp minutes in the game
     */
    public void setTempMinutes(int tempMinutes) {
        this.tempMinutes = tempMinutes;
    }
}