package view;

import model.GameFrameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Implements the View of the ExitButton with ActionListener
 */
public class ExitButtonView extends JButton implements ActionListener {

    private final GameFrameModel owner;

    /**Implement the Exitbutton View Model
     * @param owner - GameFrameModel Owner
     */
    public ExitButtonView(GameFrameModel owner){
        this.owner = owner;
        this.setBounds(500 , 500, 150, 35);
        this.setText("QUIT");
        this.setBackground(Color.decode("#FF007F"));
        this.addActionListener(this);
        this.setFocusPainted(false);
    }

    /**
     * Implement the Functionality of the Button
     * @param e - Action instance
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }
}
