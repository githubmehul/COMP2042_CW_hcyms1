package view;
import controller.AudioController;
import controller.GameFrameController;

import javax.swing.*;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class HighScoreButtonView extends JButton implements ActionListener {
    private final GameFrameController owner;
    JFrame frame = new JFrame("Frame");
    JLabel label = new JLabel();

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
        frame.setBounds(100, 0, 400, 350);
        frame.setSize(400, 350);
        frame.setLayout(new BorderLayout());
        //Reset textpanes content so on every button click the new content of the read file will be displayed
        String fileResult = "";
        try {
            BufferedReader csvReader = new BufferedReader(new FileReader("highscore.csv"));
            String line = null;
            while ((line = csvReader.readLine()) != null) {
                //Do your logic here which information you want to parse from the csv file and which information you want to display in your textpane
                fileResult = fileResult + "\n" +line;
            }
        }
        catch(FileNotFoundException ex) {
            System.err.println("File was not found");
        }
        catch(IOException ioe) {
            System.err.println("There was an error while reading the file");
        }
        label.setText(fileResult);
        frame.add(label);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
