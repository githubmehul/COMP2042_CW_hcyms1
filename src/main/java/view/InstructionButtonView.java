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

public class InstructionButtonView extends JButton implements ActionListener {
    private final GameFrameModel owner;
    JFrame frame = new JFrame();
    JLabel label = new JLabel("<html><center><h3><b><div style='font-size: 30;'>GAME COMMANDS</div></b></center></h2>" +
            "<h4>Click <b>[SPACE]</b> To Play And Pause</h4>" +
            "<h4>CLICK <b>[A]</b> To Move Left</h4>" +
            "<h4>CLICK <b>[D]</b> To Move Right</h4>" +
            "<h4>CLICK <b>[ESC]</b> To Enable Pause Menu</h4>" +
            "<h4>CLICK <b>[SHIFT+ALT+F1]</b> To Enable Game Console</h4>" +
            "Time Limit for Each Level"+
            "</html>" , SwingConstants.CENTER);
    JButton button = new JButton("Exit Instruction Page");

    public InstructionButtonView(GameFrameModel owner){
        this.owner = owner;
        this.setBounds(100,0, 600,480);
        this.setText("HELP");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        AudioController audioController = new AudioController("Button Sound.wav");
        label.setFont(new Font("Raleway", Font.PLAIN,30));
        label.setForeground(Color.WHITE);
        Border b2 = new LineBorder(Color.decode("#FF007F"), 10);
        label.setBorder(b2);
        frame.add(label);

        button.setBounds(200,450,200,20); /*Distance from left,
                      Distance from top,length of button, height of button*/
        button.setBackground(Color.decode("#FF007F"));
        button.setFont(new Font("Raleway", Font.PLAIN,15));
        button.setFocusPainted(false);
        label.add(button);

        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(0,0, 600,510);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
    }
}