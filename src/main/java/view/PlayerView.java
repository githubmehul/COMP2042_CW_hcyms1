package view;

import controller.PlayerController;
import static model.PlayerModel.getInstance;

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
        g2d.setColor(getInstance().getInnerColor());
        g2d.fill(playerController.getPlayerFace());

        // Set the border colour
        g2d.setColor(getInstance().getBorderColor());
        g2d.draw(playerController.getPlayerFace());
    }
}
