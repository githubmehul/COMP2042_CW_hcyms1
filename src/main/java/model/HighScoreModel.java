package model;

import static controller.HighScoreController.getHighScoreInstance;
import controller.WallController;

import java.awt.*;

public class HighScoreModel {
    private WallController wall;

    public void drawscore(Graphics g , WallController wall ) {
        this.wall = wall;
        g.setColor(Color.yellow);
        g.setFont(new Font("serif",Font.BOLD,15));
        g.drawString("Score: "+getHighScoreInstance().getScore(), 0, 100);
    }
}
