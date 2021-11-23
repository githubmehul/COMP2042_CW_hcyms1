package view;

import controller.PlayerController;

import java.awt.*;

public class PlayerView {
    private PlayerController playerController;

    public PlayerView(Graphics2D g2d , PlayerController playerController) {
        this.playerController = playerController;
        render(g2d);
    }

    public void render(Graphics2D g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Set the interior colour
        g2d.setColor(playerController.getInnerColor());
        g2d.fill(playerController.getPlayerFace());

        // Set the border colour
        g2d.setColor(playerController.getBorderColor());
        g2d.draw(playerController.getPlayerFace());
    }
}
