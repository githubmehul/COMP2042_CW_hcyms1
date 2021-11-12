package controller;

import model.WallModel;

import javax.swing.*;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;


/**
 * DebugPanelController extends the JPanel to provide implementation for the Debug Console
 */
public class DebugPanelController extends JPanel {

    //Background Color of the Debug Console
    private static final Color DEF_BKG = Color.WHITE;


    private JButton skipLevel;
    private JButton resetBalls;

    private JSlider ballXSpeed;
    private JSlider ballYSpeed;

    private WallModel wall;

    /**
     * DebugPanelController Constructor:
     * To implement the Debug Panel Controller
     * @param wall
     */
    public DebugPanelController(WallModel wall){

        this.wall = wall;

        initialize();
        //Button to Skip level and reset balls
        skipLevel = makeButton("Skip Level",e -> wall.nextLevel());
        resetBalls = makeButton("Reset Balls",e -> wall.resetBallCount());

        //Setting the X and Y Speed for the ball
        ballXSpeed = makeSlider(-4,4,e -> wall.setBallXSpeed(ballXSpeed.getValue()));
        ballYSpeed = makeSlider(-4,4,e -> wall.setBallYSpeed(ballYSpeed.getValue()));

        //Add it to the owner
        this.add(skipLevel);
        this.add(resetBalls);

        this.add(ballXSpeed);
        this.add(ballYSpeed);

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