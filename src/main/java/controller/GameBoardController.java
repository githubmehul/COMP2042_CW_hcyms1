package controller;
import model.GameFrameModel;
import model.LevelModel;
import model.HighScoreModel;
import view.GameboardView;
import view.PauseMenuView;
import static controller.HighScoreController.getInstance;
import static controller.TimerController.getTimeInstance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/***
 *GameBoardView Class extends JComponent and implements KeyListener , MouseListener and MouseMotionListener
 *to generate the GameBoard(The Actual Game on the GameFrameModel).
 */
public class GameBoardController extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    //Final Color Declarations
    private static final Color GAMEBOARD_COLOR = Color.WHITE;
    //Final int Declarations
    private static final int GAMEBOARD_WIDTH = 600;
    private static final int GAMEBOARD_HEIGHT = 450;

    //parameter area of wall,brick count,line count,brick dimension,platform starting point
    private WallController wall = new WallController(new Rectangle(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT), 30,3,6/2,new Point(300,430));
    private DebugConsoleController DebugConsole;
    private LevelModel level = new LevelModel(new Rectangle(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT),30,3,6/2, wall);
    private HighScoreModel highScoreModel = new HighScoreModel();
    private PauseMenuView pauseMenuView = new PauseMenuView();
    private PauseMenuController pauseMenuController = new PauseMenuController(this , wall,pauseMenuView);
    GameboardView gameboardView = new GameboardView(wall, wall.getPlayer(), wall.getBall() , this , highScoreModel,pauseMenuView);
    private Timer gameTimer;
    //Game Timer Declaration
    private Timer GAMEBOARD_TIMER;
    //Boolean Declaration
    private boolean ShowPauseMenu = false;
    public String message = "";
    public String message2 = "";


    /**
     * GameBoardView Constructor implements Gameboard characteristics
     * @param owner
     */
    public GameBoardController(JFrame owner){
        // Create the View of the GameBoard
        this.initialize();
        //initialize the first level
        DebugConsole = new DebugConsoleController(owner,wall ,this , level);;
        level.nextLevel();
//        inputname();
//        message = " Welcome to the Game "  + name;
        // Timer setting the delay between the initial delay and and event firing (the ball speed)
        GAMEBOARD_TIMER = new Timer(0,e ->{
            // Calls the move function in the WallModel Class
            wall.move();
            //Calls the findImpacts function in the WallModel Class
            wall.findImpacts();
            getTimeInstance().setGameRunning(true);
            // Message Display for Brick Count and Ball Count
            message = String.format("Bricks: %d Balls %d Total Bricks Broken %d",
                    wall.getBrickCount(),wall.getBallCount(), getInstance().getScore());
            message2 = String.format("Total Bricks Broken: %d Timer: %02dm %02ds", getInstance().getScore(),
                    getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
            if (level.getLevel() == 1){
                if(getTimeInstance().getSeconds() == 15){
                    GAMEBOARD_TIMER.stop();
                    message = "Oh No! You couldn't complete the level within the time!";
                    message2 = "Try Again!";
                    wall.wallReset();
                    wall.ballReset();
                    getTimeInstance().resetGame();
                }
            }
            else if(level.getLevel() == 2){
                if(getTimeInstance().getSeconds() == 10){
                    GAMEBOARD_TIMER.stop();
                    wall.wallReset();
                    wall.ballReset();
                    getTimeInstance().resetGame();
                }
            }
            else if (level.getLevel() == 3){
                if(getTimeInstance().getSeconds() == 10){
                    GAMEBOARD_TIMER.stop();
                    wall.wallReset();
                    wall.ballReset();
                    getTimeInstance().resetGame();
                }
            }
            else if (level.getLevel() == 4){
                if(getTimeInstance().getSeconds() == 10){
                    GAMEBOARD_TIMER.stop();
                    wall.wallReset();
                    wall.ballReset();
                    getTimeInstance().resetGame();
                }
            }
            //If the Ball is Lost at the bottom
            if(wall.isBallLost()){
                //And If Ball Count is 0
                if(wall.ballEnd()){
                    //Reset the Wall
                    wall.wallReset();
                    wall.ballReset();
                    getInstance().CheckScore();
                    getInstance().setScore(0);
                    getInstance().sortHighScore();
                    //Display Message Game Over
                    message = "GAME OVER!";
                    message2 = String.format("Score is %d Bricks at the time of %02dm %02ds",
                            getInstance().getScore(), getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                    getTimeInstance().resetGame();
                    GameFrameModel gameFrameController = new GameFrameModel();
                }
                getTimeInstance().setGameRunning(false);
                // If the Ball Is Lost , Reset Ball and the Player
                wall.ballReset();
                // Stop the Game Timer , till the Player Presses Spacebar
                GAMEBOARD_TIMER.stop();
            }
            //If the Wall Is Done
            else if(wall.isDone()){
                //If the Wall has another level
                if(level.hasLevel()){
                    //Stop the GameTimer
                    GAMEBOARD_TIMER.stop();
                    //Reset the Number of Balls
                    wall.ballReset();
                    //Reset the Wall
                    wall.wallReset();
                    //Go to the Next Level
                    level.nextLevel();
                    getTimeInstance().setTempMinutes(getTimeInstance().getMinutes());
                    getTimeInstance().setTempSeconds(getTimeInstance().getSeconds());
                    if (level.getLevel() == 2){
                        message = "Welcome to Level 2! Score is" + getInstance().getScore();
                        message2 = "You finished the first level in : " + getTimeInstance().getSeconds();
                        getTimeInstance().resetGame();
                    }
                    else if(level.getLevel() == 3){
                        message = "Welcome to Level 3! Score is " + getInstance().getScore();
                        message2 = "You finished the first level in : " + getTimeInstance().getSeconds();
                        getTimeInstance().resetGame();
                    }
                    else if(level.getLevel() == 4){
                        message = "Welcome to the Last Level! Score is " + getInstance().getScore();
                        message2 = "You finished the first level in : " + getTimeInstance().getSeconds();
                        getTimeInstance().resetGame();
                    }
                }
                else{
                    //If the Player reaches end of game , Display this message
                    message = "ALL WALLS DESTROYED";
                    message2 = String.format("Your Score is %d Bricks at the time of %02dm %02ds",
                            getInstance().getScore(), getTimeInstance().getMinutes(), getTimeInstance().getSeconds());
                    getInstance().CheckScore();
                    getInstance().sortHighScore();
                    getTimeInstance().resetGame();
                    //Game Timer Stopped.
                   GAMEBOARD_TIMER.stop();
                }
            }
            //performs a request to erase and perform redraw of the component after a small delay in time.
            repaint();
        });
    }

    /**
     * initialize Method:
     * Method to initialize the KeyListener , Mouselistener , Focusable , etc
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT));
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        gameboardView.render(g2d);
    }

    /**
     * Paint method implements Graphics , inherited from JComponent
     * ,it is part of the draw system of the GUI.
     * It's invoked from Java Swing Framework to ask for a Component to draw itself on the screen
     */

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }
    /**
     * keyPressed Method:
     * To assign the Key of the KeyBoard to the corresponding action.
     * @override
     * @param keyEvent
     */
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch(keyEvent.getKeyCode()){
            // A to move left
            case KeyEvent.VK_A:
                wall.getPlayer().moveLeft();
                break;
            //D to move right
            case KeyEvent.VK_D:
                wall.getPlayer().moveRight();
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
                if(!ShowPauseMenu)
                    if(GAMEBOARD_TIMER.isRunning()) {
                        GAMEBOARD_TIMER.stop();
                        getTimeInstance().setGameRunning(false);
                    }
                    else
                        GAMEBOARD_TIMER.start();
                break;
            //F1 to see the debugConsole
            case KeyEvent.VK_F1:
                if(keyEvent.isAltDown() && keyEvent.isShiftDown())
                    DebugConsole.setVisible(true);
            default:
                wall.getPlayer().stop();
        }
    }

    /**
     * keyReleased Method:
     * To mention the Function when the key is released
     * @param keyEvent
     */
    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //Move amount will be 0
        wall.getPlayer().stop();
    }

    /**
     * mouseClicked Method:
     * To mention the function corresponding to the click of the mouse
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        pauseMenuController.mouseClicked(mouseEvent);
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

    /**
     * mouseMoved method:
     * To mention the cursor shape when it's hovered
     * @param mouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        pauseMenuController.mouseMoved(mouseEvent);

    }

    /**
     * onLostFocus Method:
     * To mention the functionality when the Window has lost focus
     */
    public void onLostFocus(){
        //Stop the game timer
        GAMEBOARD_TIMER.stop();
        //Print this message
        getTimeInstance().setGameRunning(false);
        message = "Focus Lost";
        repaint();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage2() {
        return message2;
    }

    public boolean isShowPauseMenu() {
        return ShowPauseMenu;
    }
    public void setShowPauseMenu(boolean ShowPauseMenu){
        this.ShowPauseMenu = ShowPauseMenu;
    }

}