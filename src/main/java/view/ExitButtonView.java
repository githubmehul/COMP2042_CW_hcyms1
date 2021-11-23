package view;

import controller.GameFrameController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitButtonView extends JButton implements ActionListener {
    private final GameFrameController owner;
    public ExitButtonView(GameFrameController owner){
        this.owner =owner;
        this.setBounds(150, 290, 150, 35);
        this.setText("EXIT");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
    }

}
