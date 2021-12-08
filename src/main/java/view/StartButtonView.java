package view;

import controller.AudioController;
import model.GameFrameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * StartButtonView is responsible for designing and rendering the StartButton.
 * It is also responsible for the functionality of the start button by rendering the data
 * received from the GameFrameModel
 */
public class StartButtonView extends JButton implements ActionListener {

    private final GameFrameModel owner;

    public StartButtonView(GameFrameModel owner) {

        this.owner = owner;
        this.setBounds(200, 600, 150, 35);
        this.setText("PLAY!");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            new AudioController("Button Sound.wav");
            owner.enableGameBoard();

        }
    }
}