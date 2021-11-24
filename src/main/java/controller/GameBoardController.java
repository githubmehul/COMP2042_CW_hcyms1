package controller;
import model.LevelModel;
import view.HighScoreView;
import view.PauseMenuView;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
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

    //Final Object Declarations
    //parameter area of wall,brick count,line count,brick dimension,platform starting point
    private WallController wall = new WallController(new Rectangle(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT), 30,3,6/2,new Point(300,430));
    private DebugConsoleController DebugConsole;
    private LevelModel level = new LevelModel(new Rectangle(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT),5,1,6/2, wall);
    private PauseMenuView pauseMenuView = new PauseMenuView();
    private HighScoreController highScoreController= new HighScoreController(wall);
    private HighScoreView highScoreView = new HighScoreView();
    //Game Timer Declaration
    private Timer GAMEBOARD_TIMER;
    //Boolean Declaration
    private boolean ShowPauseMenu = false;
    public String message = "";
    private String name;


    /**
     * GameBoardView Constructor implements Gameboard characteristics
     * @param owner
     */
    public GameBoardController(JFrame owner){
        // Create the View of the GameBoard
        this.initialize();
        //initialize the first level
        DebugConsole = new DebugConsoleController(owner,wall , this , level);;
        level.nextLevel();
//        inputname();
//        message = " Welcome to the Game "  + name;
        // Timer setting the delay between the initial delay and and event firing (the ball speed)
        GAMEBOARD_TIMER = new Timer(1,e ->{
            // Calls the move function in the WallModel Class
            wall.move();
            //Calls the findImpacts function in the WallModel Class
            wall.findImpacts();
            // Message Display for Brick Count and Ball Count
            message = String.format("Bricks: %d Balls %d Total Bricks Broken %d",
                    wall.getBrickCount(),wall.getBallCount(), wall.getTotalBrickBroken());
            //If the Ball is Lost at the bottom
            if(wall.isBallLost()){
                //And If Ball Count is 0
                if(wall.ballEnd()){
                    //Reset the Wall
                    wall.wallReset();
                    wall.ballReset();
                    highScoreController.CheckScore();
                    wall.setTotalBrickBroken(0);
                    //Display Message Game Over
                    message = "GAME OVER! Would you like to Restart the Game ?";

                }
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
                    highScoreController.CheckScore();
                    if (level.getLevel() == 2){
                        message = "Welcome to Level 2!";
                    }
                    else if(level.getLevel() == 3){
                        message = "Welcome to Level 3!";
                    }
                    else if(level.getLevel() == 4){
                        message = "Welcome to the Last Level!";
                    }
                }
                else{
                    //If the Player reaches end of game , Display this message
                    message = "ALL WALLS DESTROYED";
                    highScoreController.CheckScore();
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

    /**
     * Paint method implements Graphics , inherited from JComponent
     * ,it is part of the draw system of the GUI.
     * It's invoked from Java Swing Framework to ask for a Component to draw itself on the screen
     * @param g
     */
    public void paint(Graphics g){
        // Create Object g2d
        Graphics2D g2d = (Graphics2D) g;
        //Setting the Background Color
        BackgroundColor(g2d);
        //Setting the color of the Message
        g2d.setColor(Color.BLUE);
        drawString(g, message, 240, 225);
        highScoreView.drawscore(g2d , wall ,highScoreController);
        wall.render(g2d);
        //If the showPauseMenu is true , draw the drawMenu
        if(ShowPauseMenu)
            pauseMenuView.render(g2d);
        //Binding various component implementations and synchronizes them.
        Toolkit.getDefaultToolkit().sync();
        if (highScoreController.getHighScore() == ("")) {
            highScoreController.setHighScore(highScoreController.GetHighScore());
        }
    }
    private void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
    /**
     * BackgroundColor method:
     * Uses the Graphics2D Object to paint the Background Color of the GameBoard
     * @param g2d
     */
    private void BackgroundColor(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }
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
                repaint();
                GAMEBOARD_TIMER.stop();
                break;
            //Space Bar to Pause The Game
            case KeyEvent.VK_SPACE:
                if(!ShowPauseMenu)
                    if(GAMEBOARD_TIMER.isRunning())
                        GAMEBOARD_TIMER.stop();
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
        //the p object gets the mouse point
        Point p = mouseEvent.getPoint();
        if(!ShowPauseMenu)
            return;
        //if clicked the continueButtonRect
        if(pauseMenuView.getContinueButtonRect().contains(p)){
            //remove the pause menu and repaint
            ShowPauseMenu = false;
            repaint();
        }
        //if clicked the restartButtonrect
        else if(pauseMenuView.getRestartButtonRect().contains(p)){
            //show the message
            message = "Restarting Game...";
            //restart the ball
            wall.ballReset();
            //restart the wall
            wall.wallReset();
            wall.setTotalBrickBroken(wall.getBrickCount());
            //remove the pause menu
            ShowPauseMenu = false;
            repaint();
        }
        //if the exitButtonRect is clicked
        else if(pauseMenuView.getExitButtonRect().contains(p)){
            //exit the system
            System.exit(0);
        }

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
        //get the mouse point
        Point p = mouseEvent.getPoint();
        //if the exitButtonRect is not clicked and the Pause Menu is shown
        if(pauseMenuView.getExitButtonRect() != null && ShowPauseMenu) {
            // if the cursor is on any of the Rect in the Pause Menu
            if (pauseMenuView.getExitButtonRect().contains(p) || pauseMenuView.getContinueButtonRect().contains(p) || pauseMenuView.getRestartButtonRect().contains(p))
                //Change it to a hand cursor
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                this.setCursor(Cursor.getDefaultCursor());
        }
        else{
            //else keep it as a default cursor
            this.setCursor(Cursor.getDefaultCursor());
        }
    }

    /**
     * onLostFocus Method:
     * To mention the functionality when the Window has lost focus
     */
    public void onLostFocus(){
        //Stop the game timer
        GAMEBOARD_TIMER.stop();
        //Print this message
        message = "Focus Lost";
        repaint();
    }
    public void inputname(){
        UIManager UI=new UIManager();
        UI.put("OptionPane.background",new ColorUIResource(255,0,0));
        UI.put("Panel.background",new ColorUIResource(255,0,0));
        // Get the user's name.
        name = JOptionPane.showInputDialog("What is your name?");
    }
}