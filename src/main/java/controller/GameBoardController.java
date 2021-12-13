package controller;

import static controller.HighScoreController.getHighScoreInstance;
import static controller.TimerController.getTimeInstance;

import model.GameFrameModel;
import model.LevelModel;
import model.HighScoreModel;
import view.GameboardView;
import view.PauseMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/***
 *GameBoardView Class extends JComponent and implements KeyListener , MouseListener and MouseMotionListener
 *to generate the GameBoard Functionality.
 */
public class GameBoardController extends JComponent implements KeyListener, MouseListener, MouseMotionListener {
    //Final int Declarations
    private static final int GAMEBOARD_WIDTH = 600;
    private static final int GAMEBOARD_HEIGHT = 450;

    private final WallController wallController = new WallController(new Rectangle(0, 0, GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT), new Point(300, 430));
    private final DebugPanelController debugPanelController;
    private final PauseMenuController pauseMenuController = new PauseMenuController(this, wallController, new PauseMenuView());
    private final LevelModel levelModel = new LevelModel(new Rectangle(0, 0, GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT), 30, 6 / 2, 3, wallController);
    private final GameboardView gameboardView = new GameboardView(wallController, wallController.getPlayer(), wallController.getBall(), this, new HighScoreModel(), new PauseMenuView());
    private final Timer GAMEBOARD_TIMER;
    public String message = "";
    public String message2 = "";
    private boolean ShowPauseMenu = false;

    /**
     * Responsible for implementing Gameboard functionality and movement at various stages
     * Called using the Action Listener
     *
     * @param JFrameOwner - Uses the user's ownership of the screen
     */
    public GameBoardController(JFrame JFrameOwner) {
        debugPanelController = new DebugPanelController(JFrameOwner, wallController, this, levelModel);
        // Create the View of the GameBoard
        this.initialize();
        //initialize the first level
        levelModel.nextLevel();
        // Timer setting the delay between the initial delay and and event firing (the ball speed)
        GAMEBOARD_TIMER = new Timer(0, this::actionPerformed);
    }

    /**
     * Method to initialize the KeyListener , MouseListener , Focusable
     */
    private void initialize() {
        this.setPreferredSize(new Dimension(GAMEBOARD_WIDTH, GAMEBOARD_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    /**
     * Paint method implements Graphics , inherited from JComponent,it is part of the draw system of the GUI.
     * Calls GameBoardView to render the game graphics.
     */
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        gameboardView.render(g2d);
    }

    /**
     * To assign the Key of the KeyBoard to the corresponding action.
     *
     * @param keyEvent - A Key Instance
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()) {
            // A to move left
            case KeyEvent.VK_A:
                wallController.getPlayer().moveLeft();
                break;
            //D to move right
            case KeyEvent.VK_D:
                wallController.getPlayer().moveRight();
                break;
            //Escape Key to call Pause Menu
            case KeyEvent.VK_ESCAPE:
                ShowPauseMenu = !ShowPauseMenu;
                getTimeInstance().setGameRunning(false);
                repaint();
                GAMEBOARD_TIMER.stop();
                break;
            //Space Bar to Pause The Game
            case KeyEvent.VK_SPACE:
                if (!ShowPauseMenu)
                    if (GAMEBOARD_TIMER.isRunning()) {
                        GAMEBOARD_TIMER.stop();
                        getTimeInstance().setGameRunning(false);
                    } else
                        GAMEBOARD_TIMER.start();
                break;
            //F1 to see the debugConsole
            case KeyEvent.VK_F1:
                if (keyEvent.isAltDown() && keyEvent.isShiftDown())
                    debugPanelController.setVisible(true);
            default:
                wallController.getPlayer().stop();
        }
    }

    /**
     * To mention the Function when the key is released
     *
     * @param keyEvent - A Key Instance
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //Move amount will be 0
        wallController.getPlayer().stop();
    }

    /**
     * To mention the function corresponding to the click of the mouse in Pause Menu Screen
     *
     * @param mouseEvent - A Mouse Instance
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        pauseMenuController.mouseClicked(mouseEvent);
    }

    /**
     * To mention the cursor shape when it's hovered on the Pause Menu Screen
     *
     * @param mouseEvent - A Mouse Instance
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        pauseMenuController.mouseMoved(mouseEvent);

    }

    /**
     * To mention the functionality when the Window has lost focus
     */
    public void onLostFocus() {
        //Stop the game timer
        GAMEBOARD_TIMER.stop();
        getTimeInstance().setGameRunning(false);
        //Print this message
        message = "Focus Lost";
        repaint();
    }

    //Getter and Setter Methods

    /**
     * Return the Message 1
     *
     * @return message - The message on line 1
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the Message 1
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Return the Message
     *
     * @return message - The message on line 2
     */
    public String getMessage2() {
        return message2;
    }

    /**
     * Show the Pause Menu Flag
     *
     * @return ShowPauseMenu - A Boolean value to show if the Pause Menu is shown or not
     */
    public boolean isShowPauseMenu() {
        return ShowPauseMenu;
    }

    /**
     * Set the Pause Menu Flag
     */
    public void setShowPauseMenu(boolean ShowPauseMenu) {
        this.ShowPauseMenu = ShowPauseMenu;
    }

    /**
     * Timer for each Level
     *
     * @param minutes     - Minute deadline
     * @param seconds     - Seconds Deadline
     * @param levelNumber - The level Number
     */
    public void levelTimer(int minutes, int seconds, int levelNumber) {
        if (levelModel.getLevel() == levelNumber) {
            if (getTimeInstance().getMinutes() == minutes && getTimeInstance().getSeconds() == seconds) {
                GAMEBOARD_TIMER.stop();
                message = "Oh No! You Couldn't Complete The Level In Time!";
                message2 = "Try Again!";
                wallController.wallReset();
                wallController.ballReset();
                getTimeInstance().resetGame();
            }
        }
    }

    /**
     * Responsible for the functionality in the Gameboard
     *
     * @param e - The action instance
     */
    private void actionPerformed(ActionEvent e) {
        // Calls the move function in the WallController Class
        wallController.move();
        //Calls the findImpacts function in the WallController Class
        wallController.findImpacts();

        getTimeInstance().setGameRunning(true);

        // Message Display for Brick Count and Ball Count
        message = String.format("Bricks: %d Balls %d", wallController.getBrickCount(), wallController.getBallCount());
        message2 = String.format("Timer: %02dm %02ds", getTimeInstance().getMinutes(), getTimeInstance().getSeconds());

        // Time limit for each Level
        levelTimer(1, 30, 1);
        levelTimer(1, 30, 2);
        levelTimer(1, 30, 3);
        levelTimer(2, 0, 4);
        levelTimer(2, 0, 5);
        levelTimer(3, 0, 6);

        //If the Ball is Lost at the bottom
        if (wallController.isBallLost()) {
            //And If Ball Count is 0
            if (wallController.ballEnd()) {
                //Reset the Wall
                wallController.wallReset();
                wallController.ballReset();

                getHighScoreInstance().checkScore();
                getHighScoreInstance().setScore(0);
                getHighScoreInstance().sortHighScore();

                //Display Message Game Over
                message = "GAME OVER!";
                message2 = String.format("Score is %d Bricks at the time of %02dm %02ds",
                        getHighScoreInstance().getScore(), getTimeInstance().getMinutes(), getTimeInstance().getSeconds());

                getTimeInstance().resetGame();
                new GameFrameModel();
            }

            getTimeInstance().setGameRunning(false);
            wallController.ballReset();
            GAMEBOARD_TIMER.stop();
        }
        //If the Wall Is Done
        else if (wallController.isDone()) {
            //If the Wall has another level
            if (levelModel.hasLevel()) {
                GAMEBOARD_TIMER.stop();
                wallController.ballReset();
                wallController.wallReset();
                //Go to the Next Level
                levelModel.nextLevel();
                new AudioController("Game Next Level.wav");

                getTimeInstance().setTempMinutes(getTimeInstance().getMinutes());
                getTimeInstance().setTempSeconds(getTimeInstance().getSeconds());
                LevelMessage(levelModel.getLevel());
            } else {
                //If the Player reaches end of game , Display this message
                message = "ALL WALLS DESTROYED";
                new AudioController("Game Win.wav");
                message2 = String.format("Your Score is %d Bricks at the time of %02dm %02ds", getHighScoreInstance().getScore(), getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                getHighScoreInstance().checkScore();
                getHighScoreInstance().sortHighScore();
                getTimeInstance().resetGame();
                GAMEBOARD_TIMER.stop();
            }
        }
        //performs a request to erase and perform redraw of the component after a small delay in time.
        repaint();
    }

    /**
     * Responsible for implementing the Level indicator , with the score attained and time taken in the previous level.
     *
     * @param level - The Level number
     */
    public void LevelMessage(int level) {
        switch (level) {
            case 2 -> {

                message = "Welcome to Level 2! Score:  " + getHighScoreInstance().getScore();
                message2 = String.format("You finished the first level in %02dm %02ds: ", getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                getTimeInstance().resetGame();
            }
            case 3 -> {
                message = "Welcome to Level 3! Score: " + getHighScoreInstance().getScore();
                message2 = String.format("You finished the second level in %02dm %02ds ", getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                getTimeInstance().resetGame();
            }
            case 4 -> {
                message = "Welcome to the Level 4! Score: " + getHighScoreInstance().getScore();
                message2 = String.format("You finished the third level in %02dm %02ds ", getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                getTimeInstance().resetGame();
            }
            case 5 -> {
                message = "Welcome to the Level 5! Score: " + getHighScoreInstance().getScore();
                message2 = String.format("You finished the fourth level in %02dm %02ds ", getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                getTimeInstance().resetGame();
            }
            case 6 -> {
                message = "Welcome to the Last Level! Score:  " + getHighScoreInstance().getScore();
                message2 = String.format("You finished the fifth level in %02dm %02ds ", getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                getTimeInstance().resetGame();
            }
            default -> throw new IllegalStateException("Unexpected value: " + levelModel.getLevel());
        }
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }


    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }
}