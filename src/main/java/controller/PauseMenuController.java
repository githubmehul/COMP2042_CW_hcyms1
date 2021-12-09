package controller;

import view.PauseMenuView;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.awt.event.*;

import static controller.HighScoreController.getInstance;
import static controller.TimerController.getTimeInstance;

public class PauseMenuController extends JComponent implements KeyListener, MouseListener, MouseMotionListener  {
    private GameBoardController gameBoardController;
    private WallController wallController;
    private PauseMenuView pauseMenuView;
    private boolean ShowPauseMenu;

    public PauseMenuController(GameBoardController gameBoardController , WallController wallController , PauseMenuView pauseMenuView){
        this.pauseMenuView = pauseMenuView;
        this.gameBoardController = gameBoardController;
        this.wallController = wallController;
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
    public void mouseClicked(MouseEvent mouseEvent) {
        //the p object gets the mouse point
        Point p = mouseEvent.getPoint();
        if(!gameBoardController.isShowPauseMenu())
            return;
        //if clicked the continueButtonRect
        if(pauseMenuView.getContinueButtonRect().contains(p)){
            //remove the pause menu and repaint
            ShowPauseMenu = false;
            gameBoardController.setShowPauseMenu(false);
            gameBoardController.repaint();
        }
        //if clicked the restartButtonrect
        else if(pauseMenuView.getRestartButtonRect().contains(p)){
            gameBoardController.setMessage("Restarting Game...");
            //show the message
            //restart the ball
            wallController.ballReset();
            //restart the wall
            wallController.wallReset();
            getTimeInstance().setSeconds(getTimeInstance().getTempSeconds());
            getTimeInstance().setMinutes(getTimeInstance().getTempMinutes());
            getInstance().setScore(0);
            wallController.setBrickCount(0);
            if (getTimeInstance().getSeconds() == 10){
                getInstance().setScore(0);
                wallController.setBrickCount(0);
            }
            //remove the pause menu
            gameBoardController.setShowPauseMenu(false);
            gameBoardController.repaint();
        }
        //if the exitButtonRect is clicked
        else if(pauseMenuView.getExitButtonRect().contains(p)){
            //exit the system
            getTimeInstance().resetGame();
            System.exit(0);
        }

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

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        //get the mouse point
        Point p = mouseEvent.getPoint();
        //if the exitButtonRect is not clicked and the Pause Menu is shown
        if(pauseMenuView.getExitButtonRect() != null && gameBoardController.isShowPauseMenu()) {
            // if the cursor is on any of the Rect in the Pause Menu
            if (pauseMenuView.getExitButtonRect().contains(p) || pauseMenuView.getContinueButtonRect().contains(p) || pauseMenuView.getRestartButtonRect().contains(p))
                //Change it to a hand cursor
                gameBoardController.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            else
                gameBoardController.setCursor(Cursor.getDefaultCursor());
        }
        else{
            //else keep it as a default cursor
            gameBoardController.setCursor(Cursor.getDefaultCursor());
        }

    }

}
