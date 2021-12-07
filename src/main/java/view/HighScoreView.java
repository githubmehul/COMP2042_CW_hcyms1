package view;

import static controller.HighScoreController.getInstance;
import controller.WallController;

import java.awt.*;

public class HighScoreView {
    private WallController wall;

    public void drawscore(Graphics g , WallController wall ) {
        this.wall = wall;
        g.setColor(Color.yellow);
        g.setFont(new Font("serif",Font.BOLD,15));
        g.drawString("Score: "+getInstance().getScore(), 0, 100);
    }
}
