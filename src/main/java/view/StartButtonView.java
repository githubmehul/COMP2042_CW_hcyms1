package view;

import controller.GameFrameController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;

public class StartButtonView extends JButton implements ActionListener {

    private GameFrameController owner;
    Clip clip;
    public StartButtonView(GameFrameController owner) {

        this.owner = owner;
        this.setBounds(200, 600, 150, 35);
        this.setText("PLAY");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this) {
            owner.enableGameBoard();
            AudioInputStream input= null;
            try {
                input = AudioSystem.getAudioInputStream(new File("src/main/resources/StartButtonSound.wav"));
            } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
                unsupportedAudioFileException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            try {
                clip=AudioSystem.getClip();
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            }
            try {
                clip.open(input);
            } catch (LineUnavailableException lineUnavailableException) {
                lineUnavailableException.printStackTrace();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            clip.start();
        }
    }
}