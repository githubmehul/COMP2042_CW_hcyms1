package view;
import controller.AudioController;
import controller.GameFrameController;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionButtonView extends JButton implements ActionListener {
    private final GameFrameController owner;
    JFrame frame = new JFrame();
    JLabel label = new JLabel("<html><center><h3>GAME COMMANDS</center></h2>" +
            "<h4>Click [SPACE] To Play And Pause</h4>" +
            "<h4>CLICK [A] To Move Left</h4>" +
            "<h4>CLICK [D] To Move Right</h4>" +
            "<h4>CLICK [ESC] To Enable Pause Menu</h4>" +
            "<h4>CLICK [SHIFT+ALT+F1] To Enable Game Console</h4>" +
            "</html>");
    JButton button = new JButton("Exit Instruction Page!");

    public InstructionButtonView(GameFrameController owner){
        this.owner = owner;
        this.setBounds(150, 400, 150, 35);
        this.setText("HELP");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        AudioController audioController = new AudioController("Button Sound.wav");
        label.setBackground(Color.red);
        label.setBounds(100, 0, 400, 350);
        label.setFont(new Font(null, Font.PLAIN,25));

        button.setBounds(0,0,200,20); /*Distance from left,
                      Distance from top,length of button, height of button*/
        button.setBackground(Color.red);
        frame.add(label);
        label.add(button);

        label.setBackground(Color.decode("#FFDF4F"));
        frame.setBackground(Color.RED);
        frame.setSize(400,350);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e)
            {
                frame.dispose();
            }
        });
    }
}