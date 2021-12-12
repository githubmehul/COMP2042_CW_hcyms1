package model;

import static controller.HighScoreController.getHighScoreInstance;

import java.awt.*;

/**
 * Sets the HighScore Model
 */
public class HighScoreModel {

    /**
     * Shows the Score in the GameBoard
     *
     * @param g - Graphics
     */
    public void drawScore(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Score: " + getHighScoreInstance().getScore(), 0, 100);
    }
}
