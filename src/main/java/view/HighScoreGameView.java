package view;

import javax.swing.*;
import java.awt.*;

public class HighScoreGameView extends JPanel {

    public HighScoreGameView(){
        this.setBounds(0,0, 600,480);
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/GameImage.jpg").getImage().getScaledInstance(600, 350, Image.SCALE_DEFAULT));
        this.setBackground(Color.BLACK);
        label.setIcon(imageIcon);
        this.add(label);
    }
}