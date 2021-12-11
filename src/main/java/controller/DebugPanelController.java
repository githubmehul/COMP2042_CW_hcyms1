package controller;

import model.LevelModel;
import view.DebugPanelView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/***
 * DebugPanelController Class extends JDialog and implements WindowListener.
 * It is responsible for the controlling the Debug Panel.
 *
 */
public class DebugPanelController extends JDialog implements WindowListener {

    //The title of the Debug Console Window
    private static final String DEBUG_CONSOLE_TITLE = "Debug Panel";

    private final JFrame JFrameOwner;
    private final GameBoardController gameBoardController;

    /**
     * Calls the Initialize Method to initialize the Debug Panel Window and adds
     * the Debug Panel View as well.
     *
     * @param JFrameOwner         - A modeless dialog with the specified Dialog as its owner.
     * @param wallController      - Uses WallController object
     * @param gameBoardController - Uses GameBoardController object
     * @param levelModel          - Uses LevelModel Object
     */
    public DebugPanelController(JFrame JFrameOwner, WallController wallController, GameBoardController gameBoardController, LevelModel levelModel) {
        this.JFrameOwner = JFrameOwner;
        this.gameBoardController = gameBoardController;
        //Calling the initialize method
        initialize();
        // debugPanelView is an object of the DebugPanelController Method
        DebugPanelView debugPanelView = new DebugPanelView(wallController, levelModel, gameBoardController);
        //Add the Panel , and keep the Border Layout as Center
        this.add(debugPanelView, BorderLayout.CENTER);
        this.pack();
    }

    /**
     * Provides Implementation to initialize the Debug Panel Window
     */
    private void initialize() {
        this.setModal(true);
        this.setTitle(DEBUG_CONSOLE_TITLE);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.addWindowListener(this);
        this.setFocusable(true);
    }


    /**
     * Set the Coordinates of Presentation of the Debug Panel
     */
    private void setLocation() {
        int x = ((JFrameOwner.getWidth() - this.getWidth()) / 2) + JFrameOwner.getX();
        int y = ((JFrameOwner.getHeight() - this.getHeight()) / 2) + JFrameOwner.getY();
        this.setLocation(x, y);
    }

    /**
     * Repaints the gameBoard , when the Debug Panel Window is Closed
     *
     * @param windowEvent - indicates that a window has changed its status.
     */
    @Override
    public void windowClosing(WindowEvent windowEvent) {
        gameBoardController.repaint();
    }

    @Override
    public void windowActivated(WindowEvent windowEvent) {
        setLocation();
    }

    @Override
    public void windowOpened(WindowEvent windowEvent) {
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

    @Override
    public void windowDeactivated(WindowEvent windowEvent) {

    }
}