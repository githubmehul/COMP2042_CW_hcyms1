package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import controller.AudioController;
import model.GameFrameModel;

/**
 * StartButtonView is responsible for designing and rendering the StartButton.
 * It is also responsible for the functionality of the start button by rendering the data
 * received from the GameFrameModel.
 * Updates Audio from the Audio Controller.
 */
public class StartButtonView extends JButton implements ActionListener {

    private final GameFrameModel owner;

    /**
     * StartButtonView Constructor creates the Start Button appearance
     *
     * @param owner - GameFrameModel owner
     */
    public StartButtonView(GameFrameModel owner) {

        this.owner = owner;
        this.setBounds(200, 600, 150, 35);
        this.setText("PLAY!");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }

    /**
     * actionPerformed is an action listener that plays the audio and enables the gameboard
     * when the button is clicked.
     *
     * @param e - Action Instance
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            new AudioController("Button Sound.wav");
            owner.enableGameBoard();

        }
    }
}