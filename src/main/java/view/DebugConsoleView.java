package view;

import controller.BallController;
import controller.DebugPanelController;
import model.LevelModel;
import controller.WallController;

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
    private static final String DEBUG_CONSOLE_TITLE = "Dialog Console";

    private JFrame owner;
    private DebugPanelController DebugPanel;
    private GameBoardView Gameboard;
    private WallController wall;
    private LevelModel level;


    /**
     *DebugConsoleView Constructor:
     * Calls the Initialize Method to initialize the Debug Panel Window
     * @param owner
     * @param wall
     * @param gameboard
     */
    public DebugConsoleView(JFrame owner, WallController wall, GameBoardView gameboard , LevelModel level){
        this.wall = wall;
        this.owner = owner;
        this.Gameboard = gameboard;
        this.level = level;
        //Calling the initialize method
        initialize();
        // debugPanel is an object of the DebugPanelController Method
        DebugPanel = new DebugPanelController(wall , level);
        //Add the Panel , and keep the Border Layout as Center
        this.add(DebugPanel,BorderLayout.CENTER);
        this.pack();
    }

    /**
     * initialize Method:
     * Provides Implementation to initialize the Debug Console Window
     */
    private void initialize(){
        this.setModal(true);
        this.setTitle(DEBUG_CONSOLE_TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * setLocation Method:
     * Set the Coordinates of Presentation of the Debug Console
     */
    private void setLocation(){
        int x = ((owner.getWidth() - this.getWidth()) / 2) + owner.getX();
        int y = ((owner.getHeight() - this.getHeight()) / 2) + owner.getY();
        this.setLocation(x,y);
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {

    }

    /**windowClosing Method:
     * Repaints the gameboard , when the Debug Console Window is Closed
     * @param windowEvent
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        Gameboard.repaint();
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
     * Provides Implementation to set the Values of the speed when the windows is activated.
     * @param windowEvent
     */
    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
        BallController b = wall.getBall();
        DebugPanel.setValues(b.getSpeedX(),b.getSpeedY());
    }
    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}