package view;

import controller.HighScoreController;
import controller.WallController;

import java.awt.*;

public class HighScoreView {
    private WallController wall;
    private HighScoreController highScoreController;

    public void drawscore(Graphics g , WallController wall , HighScoreController highScoreController) {
        this.wall = wall;
        this.highScoreController = highScoreController;
        g.setColor(Color.green);
        g.setFont(new Font("serif",Font.BOLD,15));
        g.drawString("Score: "+wall.getTotalBrickBroken(), 0, 100);
        g.drawString("Highscore: "+highScoreController.GetHighScore(),0,125);
    }
}
