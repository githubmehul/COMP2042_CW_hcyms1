package view;

import controller.GameBoardController;
import controller.WallController;
import static controller.HighScoreController.getHighScoreInstance;
import static controller.TimerController.getTimeInstance;
import model.LevelModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * DebugPanelController extends the JPanel to provide implementation for the Debug Panel
 */
public class DebugPanelView extends JPanel {

    private final WallController wallController;
    private final LevelModel levelModel;
    private final GameBoardController gameBoardController;

    /**
     * To implement the Debug Panel View
     * @param wallController - WallController object instance
     * @param gameBoardController - gameBoardController object instance
     * @param levelModel - levelModel object instance
     */
    public DebugPanelView(WallController wallController , LevelModel levelModel , GameBoardController gameBoardController){

        this.wallController = wallController;
        this.levelModel = levelModel;
        this.gameBoardController = gameBoardController;
        initialize();
        //Button to Skip level and reset balls
        JButton skipLevel = makeButton("Skip Level", e -> skipLevel());
        JButton resetBalls = makeButton("Reset Balls", e -> resetBalls());

        //Add it to the owner
        this.add(skipLevel);
        this.add(resetBalls);

    }

    /**
     * Responsible to implement the functionality when the Level is Skipped
     */
    private void skipLevel(){
        if(levelModel.hasLevel()){
            levelModel.nextLevel();
            gameBoardController.LevelMessage(levelModel.getLevel());
            getHighScoreInstance().acquireHighScore();
            getTimeInstance().setTempSeconds(getTimeInstance().getSeconds());
            getTimeInstance().setTempMinutes(getTimeInstance().getMinutes());
        }
    }


    /**
     * Called to Reset the Ball Count
     */
    private void resetBalls(){
        wallController.resetBallCount();
    }


    /**
     * Provide implementation to initialize the layout of the Debug Panel
     */
    private void initialize(){
        this.setPreferredSize(new Dimension(240,50));
        this.setLayout(new GridLayout(1,2));
    }

    /**
     * Creates the Button Implementation
     * @param title - Title of Button
     * @param e - Instance of Action
     * @return button - The Button
     */
    private JButton makeButton(String title, ActionListener e){
        JButton button = new JButton(title);
        button.setBackground(Color.decode("#FF007F"));
        button.addActionListener(e);
        return  button;
    }

}