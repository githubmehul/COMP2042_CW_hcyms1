package model;

import java.awt.*;
import controller.PlayerController;

public class PlayerModel {

    //Final Color Declarations
    private static final Color PLAYER_BORDER_COLOR = Color.GREEN.darker().darker();
    private static final Color PLAYER_INNER_COLOR = Color.GREEN;

    /**
     * Create the Player's model, which is a Rectangle.
     * @param width  - The width.
     * @param height - The height.
     * @return A {@code Rectangle} object.
     */
    public static Rectangle makeRectangle(int width, int height){
        Point p = new Point((300- (width / 2)),506 - (width/2));
        return  new Rectangle(p,new Dimension(width,height));
    }

    /**
     * To Return the Border Color
     * @return PLAYER_BORDER_COLOR
     */
    public static Color getBorderColor() {
        return PLAYER_BORDER_COLOR;
    }

    /**
     * To return the Inner Color
     * @return PLAYER_INNER_COLOR
     */
    public static Color getInnerColor() {
        return PLAYER_INNER_COLOR;
    }
}
