package view;

import controller.GameBoardController;
import controller.TimerController;
import controller.WallController;
import static controller.HighScoreController.getInstance;
import static controller.TimerController.getTimeInstance;
import model.LevelModel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * DebugPanelController extends the JPanel to provide implementation for the Debug Console
 */
public class DebugPanelView extends JPanel {

    //Background Color of the Debug Console
    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private WallController wall;
    private LevelModel level;
    private GameBoardController gameBoardController;
    private TimerController displayTimer;
    public String finallevelmessage;

    /**
     * DebugPanelController Constructor:
     * To implement the Debug Panel Controller
     * @param wall
     */
    public DebugPanelView(WallController wall , LevelModel level , GameBoardController gameBoardController){

        this.wall = wall;
        this.level = level;
        this.gameBoardController = gameBoardController;
        initialize();
        //Button to Skip level and reset balls
        skipLevel = makeButton("Skip Level",e -> skipLevel());
        resetBalls = makeButton("Reset Balls",e -> resetBalls());

        //Setting the X and Y Speed for the ball
        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        //Add it to the owner
        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

    }
    private void skipLevel(){
        if (level.hasLevel()) {
            level.nextLevel();
            getInstance().getScore();
            getTimeInstance().setTempSeconds(getTimeInstance().getSeconds());
            getTimeInstance().setTempMinutes(getTimeInstance().getMinutes());
        }
        if(level.getLevel() == 2){
            gameBoardController.message = "Welcome to Level 2 Score is" + getInstance().getScore();
        }
        else if(level.getLevel() == 3){
            gameBoardController.message = "Welcome to Level 3 Score is" + getInstance().getScore();
        }
        else if(level.getLevel() == 4){
            gameBoardController.message = "Welcome to Level 4 Score is" + getInstance().getScore();
        }
    }
    private void resetBalls(){
        wall.resetBallCount();
    }



    /**
     * initialize Method:
     * Provide implementation to initialize the layout of the Debug Panel
     */
    private void initialize(){
        this.setBackground(DEF_BKG);
        this.setLayout(new GridLayout(2,2));
    }

    /**
     * makeButton Method:
     * Creates the Button Implementation
     * @param title
     * @param e
     * @return
     */
    private JButton makeButton(String title, ActionListener e){
        JButton out = new JButton(title);
        out.addActionListener(e);
        return  out;
    }
    /**
     * makeSlider Implementation:
     * Provides method implementation for the slider in the Debug Panel
     * @param min
     * @param max
     * @param e
     * @return
     */
    private JSlider makeSlider(int min, int max, ChangeListener e){
        JSlider out = new JSlider(min,max);
        out.setMajorTickSpacing(1);
        out.setSnapToTicks(true);
        out.setPaintTicks(true);
        out.addChangeListener(e);
        return out;
    }

    /**
     * setValues Method:
     * Sets the values of ballXSpeed and ballYSpeed
     * @param x
     * @param y
     */
    public void setValues(int x,int y){
        ballXSpeed.setValue(x);
        ballYSpeed.setValue(y);
    }

}