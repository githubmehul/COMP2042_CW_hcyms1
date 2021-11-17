package view;

import controller.BallController;
import controller.BrickController;
import model.LevelModel;
import model.PlayerModel;
import controller.WallController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

/***
 *GameBoardView Class extends JComponent and implements KeyListener , MouseListener and MouseMotionListener
 *to generate the GameBoard(The Actual Game on the GameFrameModel).
 */
public class GameBoardView extends JComponent implements KeyListener,MouseListener,MouseMotionListener {

    //Final String Declarations
    private static final String CONTINUE_TEXT = "Continue";
    private static final String RESTART_TEXT = "Restart";
    private static final String EXIT_TEXT = "Exit";
    private static final String PAUSE_MENU_TEXT = "Pause Menu";

    //Final Color Declarations
    private static final Color MENU_COLOR = new Color(0,255,0);
    private static final Color GAMEBOARD_COLOR = Color.WHITE;

    //Final int Declarations
    private static final int TEXT_SIZE = 30;
    private static final int GAMEBOARD_WIDTH = 600;
    private static final int GAMEBOARD_HEIGHT = 450;

    //Final Font Declaration
    private static final Font PAUSE_MENU_FONT = new Font("Monospaced",Font.PLAIN,TEXT_SIZE);

    //Final Object Declarations
    //parameter area of wall,brick count,line count,brick dimension,platform starting point
    private WallController wall = new WallController(new Rectangle(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT),30,3,6/2,new Point(300,430));
    private DebugConsoleView DebugConsole;
    private LevelModel level = new LevelModel(new Rectangle(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT),30,3,6/2, wall);
    //PauseMenu Button Declaration Button
    private Rectangle ContinueButtonRect;
    private Rectangle ExitButtonRect;
    private Rectangle RestartButtonRect;

    //Game Timer Declaration
    private Timer GAMEBOARD_TIMER;
    //Boolean Declaration
    private boolean ShowPauseMenu = false;

    public String message = "";
    private int StrLen = 0;


    /**
     * GameBoardView Constructor implements Gameboard characteristics
     * @param owner
     */
    public GameBoardView(JFrame owner){
        // Create the View of the GameBoard
        this.initialize();
        //initialize the first level
        DebugConsole = new DebugConsoleView(owner,wall,this , level);;
        level.nextLevel();
        // Timer setting the delay between the initial delay and and event firing (the ball speed)
        GAMEBOARD_TIMER = new Timer(10,e ->{
            // Calls the move function in the WallModel Class
            wall.move();
            //Calls the findImpacts function in the WallModel Class
            wall.findImpacts();
            // Message Display for Brick Count and Ball Count
            message = String.format("Bricks: %d Balls %d",wall.getBrickCount(),wall.getBallCount());
            //If the Ball is Lost at the bottom
            if(wall.isBallLost()){
                //And If Ball Count is 0
                if(wall.ballEnd()){
                    //Reset the Wall
                    wall.wallReset();
                    //Display Message Game Over
                    message = "Game over!";
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
                    //Display Message
                    message = "Go to Next Level";
                    //Stop the GameTimer
                    GAMEBOARD_TIMER.stop();
                    //Reset the Number of Balls
                    wall.ballReset();
                    //Reset the Wall
                    wall.wallReset();
                    //Go to the Next Level
                    level.nextLevel();
                }
                else{
                    //If the Player reaches end of game , Display this message
                    message = "ALL WALLS DESTROYED";
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
        g2d.drawString(message,250,225);
        //Calling the drawBall Method , with the parameters of the ball object and g2d
        DrawBall(wall.getBall(),g2d);
        // If the brick is not broken , then call the method drawBrick
        for(BrickController b : wall.getBricks()) {
            if(!b.isBroken())
                DrawBrick(b,g2d);
        }
        //Calling the drawPlayer method to draw the player
        DrawPlayer(wall.getPlayer(),g2d);
        //If the showPauseMenu is true , draw the drawMenu
        if(ShowPauseMenu)
            PauseMenu(g2d);
        //Binding various component implementations and synchronizes them.
        Toolkit.getDefaultToolkit().sync();
    }
    /**
     * BackgroundColor method:
     * Uses the Graphics2D Object to paint the Background Color of the GameBoard
     * @param g2d
     */
    private void BackgroundColor(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(GAMEBOARD_COLOR);
        g2d.fillRect(0,0,getWidth(),getHeight());
        g2d.setColor(tmp);
    }

    /**
     * drawBrick method:
     * Uses the Graphics2D Object to paint the Brick of the GameBoard
     * @param g2d
     */
    private void DrawBrick(BrickController brick, Graphics2D g2d){
        Color tmp = g2d.getColor();
        // Calling the getInnerColor of the BrickController Class to get the Inner Color of the Brick
        // And the inner color of it's abstract implementation
        g2d.setColor(brick.getInnerColor());
        // Calls the getBrick Method of the BrickController Class , which captures the Dimension and
        //Size of the Brick in the abstract implementations.
        g2d.fill(brick.getBrick());
        //Calls the getBorderColor Method of the BrickController Class , whcih captures the
        //the Border Color of the Brick by the Abstract Implementations.
        g2d.setColor(brick.getBorderColor());
        // By Taking all parameters , it then paints the brick
        g2d.draw(brick.getBrick());
        g2d.setColor(tmp);
    }


    /**
     * drawBall Method:
     * Uses the Graphics2D Object to Paint the Ball of the GameBoard
     * @param ball
     * @param g2d
     */
    private void DrawBall(BallController ball, Graphics2D g2d){
        Color tmp = g2d.getColor();
        // s takes the ball's coordinates to get it's structure
        Shape ballFace = ball.getBallFace();
        //sets the inner color of the ball by calling the getInnerColor method which returns the inner color.
        g2d.setColor(ball.getInnerColor());
        // fill the ball shape with that color
        g2d.fill(ballFace);
        //sets the border color of the ball by calling the getBorderColor method which returns the border color
        g2d.setColor(ball.getBorderColor());
        //draws the border color in the balls face shape s
        g2d.draw(ballFace);

        g2d.setColor(tmp);
    }

    /**
     * drawPlayer Method:
     * To Draw the PLatform Player in the Game
     * @param p
     * @param g2d
     */
    private void DrawPlayer(PlayerModel p, Graphics2D g2d){
        Color tmp = g2d.getColor();
        // s takes the ball's coordinates to get it's structure
        Shape PlayerFace = p.getPlayerFace();
        //sets the inner color of the platform by calling the InnerColor attribute of the PlayerModel Class.
        g2d.setColor(PlayerModel.getInnerColor());
        g2d.fill(PlayerFace);
        //sets the border color of the platform by calling the BorderColor attribute of the PlayerModel Class.
        g2d.setColor(PlayerModel.getBorderColor());
        g2d.draw(PlayerFace);

        g2d.setColor(tmp);
    }

    /**
     * drawMenu Method:
     * Method that contains the function call to ObscureGameBoard and drawPauseMenu
     * @param g2d
     */
    private void PauseMenu(Graphics2D g2d){
        GameBoardWhenPauseMenu(g2d);
        DrawPauseMenu(g2d);
    }
    /**
     *obscureGameBoard Method:
     * To Paint the Look of the GameBoard when the Pause Menu is called
     * @param g2d
     */
    private void GameBoardWhenPauseMenu(Graphics2D g2d){

        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();
        //Setting the Alpha
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.55f);
        g2d.setComposite(ac);
        //Setting GameBoard Color when Pause Menu is Called
        g2d.setColor(Color.BLACK);
        //Setting the GameBoard Shape when pause Menu is Called
        g2d.fillRect(0,0,GAMEBOARD_WIDTH,GAMEBOARD_HEIGHT);
        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * drawPauseMenu Method:
     * To draw the Pause Menu , after obscuring the GameBoard
     * @param g2d
     */
    private void DrawPauseMenu(Graphics2D g2d){
        //Getting the methods getFont and getColor
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();
        // Setting the Font and Color of the Menu Screen
        g2d.setFont(PAUSE_MENU_FONT);
        g2d.setColor(MENU_COLOR);
        //so they can position it where they want it
        //what this is doing is it's checking the text's width based on its font

        if(StrLen == 0){
            FontRenderContext frc = g2d.getFontRenderContext();
            StrLen = PAUSE_MENU_FONT.getStringBounds(PAUSE_MENU_TEXT,frc).getBounds().width;
        }
        int x = (this.getWidth() - StrLen) / 2;
        int y = this.getHeight() / 10;
        g2d.drawString(PAUSE_MENU_TEXT,x,y);

        x = this.getWidth() / 8;
        y = this.getHeight() / 4;
        //Setting the Location and Drawing the continueButtonRect
        if(ContinueButtonRect == null){
            FontRenderContext frc = g2d.getFontRenderContext();
            ContinueButtonRect = PAUSE_MENU_FONT.getStringBounds(CONTINUE_TEXT,frc).getBounds();
            ContinueButtonRect.setLocation(x,y-ContinueButtonRect.height);
        }
        g2d.drawString(CONTINUE_TEXT,x,y);

        y *= 2;
        //Setting the Location and Drawing the restartButtonRect
        if(RestartButtonRect == null){
            RestartButtonRect = (Rectangle) ContinueButtonRect.clone();
            RestartButtonRect.setLocation(x,y-RestartButtonRect.height);
        }
        g2d.drawString(RESTART_TEXT,x,y);

        y *= 3.0/2;
        //Setting the Location and Drawing the exitButtonRect
        if(ExitButtonRect == null){
            ExitButtonRect = (Rectangle) ContinueButtonRect.clone();
            ExitButtonRect.setLocation(x,y-ExitButtonRect.height);
        }
        g2d.drawString(EXIT_TEXT,x,y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
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
        if(ContinueButtonRect.contains(p)){
            //remove the pause menu and repaint
            ShowPauseMenu = false;
            repaint();
        }
        //if clicked the restartButtonrect
        else if(RestartButtonRect.contains(p)){
            //show the message
            message = "Restarting Game...";
            //restart the ball
            wall.ballReset();
            //restart the wall
            wall.wallReset();
            //remove the pause menu
            ShowPauseMenu = false;
            repaint();
        }
        //if the exitButtonRect is clicked
        else if(ExitButtonRect.contains(p)){
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
        if(ExitButtonRect != null && ShowPauseMenu) {
            // if the cursor is on any of the Rect in the Pause Menu
            if (ExitButtonRect.contains(p) || ContinueButtonRect.contains(p) || RestartButtonRect.contains(p))
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

}