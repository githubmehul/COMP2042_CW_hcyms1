package view;

import controller.GameFrameController;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonView extends JButton implements ActionListener {

    private GameFrameController owner;

    public StartButtonView(GameFrameController owner) {

        this.owner = owner;
        this.setBounds(200, 600, 150, 35);
        this.setText("START");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this){
            owner.enableGameBoard();
        }

    }

}