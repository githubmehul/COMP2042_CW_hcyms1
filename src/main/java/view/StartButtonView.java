package view;

import controller.GameFrameController;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartButtonView extends JButton implements ActionListener {

    private GameFrameController owner;

    public StartButtonView(GameFrameController owner){

        this.owner = owner;

        this.setBounds(150, 215, 150, 35);
        this.setText("START");
        this.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == this){
            owner.enableGameBoard();
        }

    }

}