package view;
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
    JLabel label = new JLabel("<html>Game commands:<br/>SPACE - start or pause game <br/>" +
            "A - Move left <br/>" +
            "D - Move right <br/>" +
            "ESC - Pause menu <br/>" +
            "ALT+SHIFT+F1 - Game console" +
            "</html>");
    JButton button = new JButton("Exit Instruction Page!");

    public InstructionButtonView(GameFrameController owner){
        this.owner = owner;
        this.setBounds(150, 253, 150, 35);
        this.setText("INSTRUCTION");
        this.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        label.setBackground(Color.red);
        label.setBounds(0, 0, 400, 350);
        label.setFont(new Font(null, Font.PLAIN,25));

        button.setBounds(0,0,200,20); /*Distance from left,
                      Distance from top,length of button, height of button*/
        button.setBackground(Color.red);
        frame.add(label);
        label.add(button);

        label.setBackground(Color.decode("#FFDF4F"));
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