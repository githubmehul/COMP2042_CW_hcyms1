package view;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HomeMenuView extends JPanel {
    public HomeMenuView(){
        this.setBounds(0,0, 600,480);
        JLabel label = new JLabel();
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("src/main/resources/GameImage.jpg").getImage().getScaledInstance(600, 350, Image.SCALE_DEFAULT));
        this.setBackground(Color.BLACK);
        Border b2 = new LineBorder(Color.YELLOW, 10);
        label.setBorder(b2);
        label.setIcon(imageIcon);
        this.add(label);
    }
}