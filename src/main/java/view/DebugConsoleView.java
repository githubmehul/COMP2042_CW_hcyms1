package view;

import controller.BallController;
import controller.DebugPanelController;
import model.WallModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/***
 * DebugConsoleView Class extends JDialog and implements WindowListener
 * to generate the GameBoard(The Actual Game on the GameFrameModel).
 */
public class DebugConsoleView extends JDialog implements WindowListener{
    //The title of the Debug Console Window
    private static final String TITLE = "Dialog Console";


    private JFrame owner;
    private DebugPanelController debugPanel;
    private GameBoardView gameBoard;
    private WallModel wall;


    /**
     *DebugConsoleView Constructor:
     * Takes in the parameter owner , wall and gameboard
     * @param owner
     * @param wall
     * @param gameBoard
     */
    public DebugConsoleView(JFrame owner, WallModel wall, GameBoardView gameBoard){

        this.wall = wall;
        this.owner = owner;
        this.gameBoard = gameBoard;
        //Calling the initialize method
        initialize();

        // debugPanel is an object of the DebugPanelController Method
        debugPanel = new DebugPanelController(wall);
        //Add the Panel , and keep the Border Layout as Center
        this.add(debugPanel,BorderLayout.CENTER);
        this.pack();
    }

    /**
     * initialize Method:
     * Initializes the DebugConsole
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * setLocation Method:
     * Set the Coordinates of the Debug Console
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }


    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoard.repaint();
    }

    @Override
    public void windowClosed(WindowEvent windowEvent) {

    }

    @Override
    public void windowIconified(WindowEvent windowEvent) {

    }

    @Override
    public void windowDeiconified(WindowEvent windowEvent) {

    }

    /**
     * windowActivated Method:
     * When the DebugConsole is called , set the speed of the ball
     * @param windowEvent
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        BallController b = wall.ball;
        debugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}