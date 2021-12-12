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
import java.io.*;

/**
 * Presents the View of the HighScore Button
 */
public class HighScoreButtonView extends JButton implements ActionListener {

    static String lineSeparator = System.getProperty("line.separator");
    private final GameFrameModel owner;

    JFrame frame = new JFrame();
    JLabel label = new JLabel("LEADERBOARD", JLabel.CENTER);
    JButton button = new JButton("Exit High Score Page");
    JTextArea textArea = new JTextArea();

    /**
     * Implements the HighScore Button View Model
     *
     * @param owner - GameFrame Owner
     */
    public HighScoreButtonView(GameFrameModel owner) {
        this.owner = owner;
        this.setBounds(150, 800, 150, 35);
        this.setText("HIGH SCORE");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }

    /**
     * Implements the functionality of the High Score Button
     * @param e  - Action Instance
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        new AudioController("Button Sound.wav");

        frame.setBounds(100, 0, 400, 350);
        frame.setSize(400, 350);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setBounds(0, 0, 600, 510);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getRootPane().setWindowDecorationStyle(JRootPane.NONE);

        textArea.setBounds(260, 110, 100, 300);
        textArea.setEditable(false); //This line of code will prevent the user from edit the text in JTextArea
        textArea.setFont(textArea.getFont().deriveFont(18f));
        textArea.setForeground(Color.white);
        textArea.setBackground(Color.BLACK);
        frame.add(textArea);

        //Display the High Scores
        File leaderBoardFile = new File("leaderboard.dat");
        try (BufferedReader reader = new BufferedReader(new FileReader(leaderBoardFile))) {
            StringBuilder cont = new StringBuilder();
            String text;

            while ((text = reader.readLine()) != null) {
                cont.append(text).append(lineSeparator).append(lineSeparator);
            }
            textArea.setText(cont.toString());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        label.setFont(new Font("Raleway", Font.PLAIN, 30));
        label.setForeground(Color.WHITE);
        Border b2 = new LineBorder(Color.decode("#FF007F"), 8);
        label.setBorder(b2);
        label.setVerticalAlignment(JLabel.TOP);
        frame.add(label);

        button.setBounds(200, 450, 200, 20);
        button.setBackground(Color.decode("#FF007F"));
        button.setFont(new Font("Raleway", Font.PLAIN, 15));
        button.setFocusPainted(false);
        button.addActionListener(e1 -> frame.dispose());
        label.add(button);

        frame.setVisible(true);

    }
}
