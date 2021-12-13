package view;

import controller.AudioController;
import model.GameFrameModel;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the InstructionButtonView with the Implementation
 * Updates Audio from the Audio Controller.
 */
public class InstructionButtonView extends JButton implements ActionListener {
    private final GameFrameModel owner;
    JFrame frame = new JFrame();
    JLabel label = new JLabel("<html><center><h2><b><div style='font-size: 30;'>GAME COMMANDS</div></b></h2>" +
            "<h4>Click <b>[SPACE]</b> To Play And Pause</h4>" +
            "<h4>CLICK <b>[A]</b> To Move Left</h4>" +
            "<h4>CLICK <b>[D]</b> To Move Right</h4>" +
            "<h4>CLICK <b>[ESC]</b> To Enable Pause Menu</h4>" +
            "<h4>CLICK <b>[SHIFT+ALT+F1]</b> To Enable Game Console</h4>" +
            "<center><h2><b><div style='font-size: 30;'>TIME LIMIT</div></b></center></h2>" +
            "<h4>Level 1, Level 2 , Level 3 : 01m 30s</h4>" +
            "<h4>Level 4 , Level 5 : 02m 00s</h4>" +
            "<h4>Level 6 : 03m 00s</h4>" +
            "</center></html>", JLabel.CENTER);
    JButton button = new JButton("Exit Instruction Page");

    /**
     * Implements the Instruction Button View Model
     *
     * @param owner - GameFrameModel owner
     */
    public InstructionButtonView(GameFrameModel owner) {
        this.owner = owner;
        this.setBounds(100, 0, 600, 480);
        this.setText("HELP");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);

    }

    /**
     * Implements the Functionality of the InstructionButton with the implementation of Audio
     *
     * @param e - Action Instance
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new AudioController("Button Sound.wav");

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(0, 0, 600, 510);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        label.setFont(new Font("Raleway", Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        label.setVerticalAlignment(JLabel.TOP);
        Border b2 = new LineBorder(Color.decode("#FF007F"), 10);
        label.setBorder(b2);
        frame.add(label);

        button.setBounds(205, 460, 200, 20);
        button.setBackground(Color.decode("#FF007F"));
        button.setFont(new Font("Raleway", Font.PLAIN, 15));
        button.setFocusPainted(false);
        button.addActionListener(e1 -> frame.dispose());
        label.add(button);

        frame.setVisible(true);

    }
}