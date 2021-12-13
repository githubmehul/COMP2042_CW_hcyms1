package controller;

import view.PauseMenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.*;

import static controller.HighScoreController.getHighScoreInstance;
import static controller.TimerController.getTimeInstance;

/**
 * PauseMenuController is responsible for the PauseMenu Functionality in the GameBoard.
 */
public class PauseMenuController extends JComponent implements KeyListener, MouseListener, MouseMotionListener {
    private final GameBoardController gameBoardController;
    private final WallController wallController;
    private final PauseMenuView pauseMenuView;

    public PauseMenuController(GameBoardController gameBoardController, WallController wallController, PauseMenuView pauseMenuView) {
        this.pauseMenuView = pauseMenuView;
        this.gameBoardController = gameBoardController;
        this.wallController = wallController;
    }

    /**
     * To implement the functionality when the Mouse is Clicked on the PauseMenu
     *
     * @param mouseEvent - Mouse Clicked Instance
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (!gameBoardController.isShowPauseMenu())
            return;
        if (pauseMenuView.getContinueButtonRect().contains(p)) {
            gameBoardController.setShowPauseMenu(false);
            gameBoardController.repaint();
        } else if (pauseMenuView.getRestartButtonRect().contains(p)) {
            gameBoardController.setMessage("Restarting Game...");

            getTimeInstance().setSeconds(getTimeInstance().getTempSeconds());
            getTimeInstance().setMinutes(getTimeInstance().getTempMinutes());

            wallController.ballReset();
            wallController.wallReset();
            getHighScoreInstance().setScore(0);

            //remove the pause menu
            gameBoardController.setShowPauseMenu(false);
            gameBoardController.repaint();
        } else if (pauseMenuView.getExitButtonRect().contains(p)) {
            //exit the system
            getTimeInstance().resetGame();
            System.exit(0);
        }
    }

    /**
     * To implement the functionality when the Mouse is hovering on the PauseMenu Functionality
     *
     * @param mouseEvent - Mouse moved Instance
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (pauseMenuView.getExitButtonRect() != null && gameBoardController.isShowPauseMenu())
            if (pauseMenuView.getExitButtonRect().contains(p) || pauseMenuView.getContinueButtonRect().contains(p) || pauseMenuView.getRestartButtonRect().contains(p))
                gameBoardController.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoardController.setCursor(Cursor.getDefaultCursor());
        else {
            gameBoardController.setCursor(Cursor.getDefaultCursor());
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

}
