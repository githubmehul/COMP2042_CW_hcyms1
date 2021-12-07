package view;
import controller.AudioController;
import controller.GameFrameController;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class HighScoreButtonView extends JButton implements ActionListener {
    static String lineSeparator = System.getProperty("line.separator"); //This variable use for displaying Leaderboard
    private final GameFrameController owner;
    JFrame frame = new JFrame("Frame");
    JLabel label = new JLabel("LEADERBOARD", JLabel.CENTER);
    JButton button = new JButton("Exit Instruction Page");
    JTextArea textArea=new JTextArea();

    public HighScoreButtonView(GameFrameController owner) {
        this.owner = owner;
        this.setBounds(150, 800, 150, 35);
        this.setText("HIGH SCORE");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        AudioController audioController = new AudioController("Button Sound.wav");
        frame.setBounds(100, 0, 400, 350);
        frame.setSize(400, 350);
        textArea.setBounds(260, 110, 100, 300);
        textArea.setEditable(false); //This line of code will prevent the user from edit the text in JTextArea
        textArea.setFont(textArea.getFont().deriveFont(18f)); //set text size
        textArea.setForeground(Color.white); //set text font
        textArea.setBackground(Color.BLACK);  //set back ground
        frame.add(textArea);
        //Reset textpanes content so on every button click the new content of the read file will be displayed
        File file = new File("leaderboard.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder cont = new StringBuilder();
            String text;

            while ((text = reader.readLine()) != null) {
                cont.append(text).append(lineSeparator).append(lineSeparator);
            }
            textArea.setText(cont.toString());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        label.setFont(new Font("Raleway", Font.PLAIN,30));
        label.setForeground(Color.WHITE);
        Border b2 = new LineBorder(Color.decode("#FF007F"), 8);
        label.setBorder(b2);
        label.setVerticalAlignment((int) JLabel.TOP);
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
