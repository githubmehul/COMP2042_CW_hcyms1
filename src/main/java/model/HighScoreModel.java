package model;

import static controller.HighScoreController.getHighScoreInstance;

import java.awt.*;

/**
 * Responsible for creating the score model, shown during the game play.
 */
public class HighScoreModel {

    /**
     * Shows the Score in the GameBoard while the user is playing the game
     *
     * @param g - Graphics
     */
    public void drawScore(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("serif", Font.BOLD, 15));
        g.drawString("Score: " + getHighScoreInstance().getScore(), 0, 100);
    }
}
