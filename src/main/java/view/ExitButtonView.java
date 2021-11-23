package view;

import controller.GameFrameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButtonView extends JButton implements ActionListener {
    private final GameFrameController owner;
    public ExitButtonView(GameFrameController owner){
        this.owner =owner;

        this.setBounds(500 , 500, 150, 35);
        this.setText("EXIT");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

}
