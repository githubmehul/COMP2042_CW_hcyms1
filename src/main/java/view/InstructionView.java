package view;
import controller.GameBoardController;
import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstructionView extends JFrame {
    GameBoardController gameBoardController = new GameBoardController(this);
    private ImageIcon infoBackground;
    JFrame frame = new JFrame();
    JLabel label = new JLabel("<html>Game commands:<br/>SPACE - start or pause game <br/>" +
            "A - Move left <br/>" +
            "D - Move right <br/>" +
            "ESC - Pause menu <br/>" +
            "ALT+SHIFT+F1 - Game console" +
            "</html>");
    JButton button = new JButton("Exit Instruction Page!");

    InstructionView(){
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