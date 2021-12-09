package view;

import java.awt.*;

import controller.PlayerController;
import model.PlayerModel;

/**
 * PlayerView is responsible for the rendering of the view of the Player.
 * As a view class, it is rendering data received from the model.
 * PlayerController updates the view when data changes.
 */
public class PlayerView {
    private final PlayerController playerController;

    /**
     * Calls the render function in the PlayerView Constructor.
     *
     * @param g2d
     * @param playerController
     */
    public PlayerView(Graphics2D g2d, PlayerController playerController) {
        this.playerController = playerController;
        playerRender(g2d);
    }

    /**
     * Renders the Player's color and shape.
     *
     * @param g
     */
    public void playerRender(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(PlayerModel.getInnerColor());
        g2d.fill(playerController.getPlayerFace());

        // Set the border colour
        g2d.setColor(PlayerModel.getBorderColor());
        g2d.draw(playerController.getPlayerFace());
    }
}
