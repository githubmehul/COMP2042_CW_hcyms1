package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;

/**
 *
 */
public class PauseMenuView  {
    //Final String Declarations
    private static final String PAUSE_MENU_TEXT = "Pause Menu";
    private static final String CONTINUE_TEXT = "Continue";
    private static final String RESTART_TEXT = "Restart";
    private static final String EXIT_TEXT = "Exit";

    private static final Color MENU_COLOR = new Color(0, 255, 0);
    private static final Font PAUSE_MENU_FONT = new Font("Monospaced", Font.PLAIN, 30);

    //PauseMenu Button Declaration
    private Rectangle ContinueButtonRect;
    private Rectangle ExitButtonRect;
    private Rectangle RestartButtonRect;

    private int StrLen = 0;


    /**
     * drawMenu Method:
     * Method that contains the function call to ObscureGameBoard and drawPauseMen
     * @param g2d
     */
    public void render(Graphics2D g2d) {
        GameBoardWhenPauseMenu(g2d);
        DrawPauseMenu(g2d);
    }

    /**
     * obscureGameBoard Method:
     * To Paint the Look of the GameBoard when the Pause Menu is called
     *
     * @param g2d
     */
    private void GameBoardWhenPauseMenu(Graphics2D g2d) {
        Composite tmp = g2d.getComposite();
        Color tmpColor = g2d.getColor();
        //Setting the Alpha
        AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.55f);
        g2d.setComposite(ac);
        //Setting GameBoard Color when Pause Menu is Called
        g2d.setColor(Color.BLACK);
        //Setting the GameBoard Shape when pause Menu is Called
        g2d.fillRect(0, 0, 600, 450);
        g2d.setComposite(tmp);
        g2d.setColor(tmpColor);
    }

    /**
     * drawPauseMenu Method:
     * To draw the Pause Menu , after obscuring the GameBoard
     *
     * @param g2d
     */
    private void DrawPauseMenu(Graphics2D g2d) {
        //Getting the methods getFont and getColor
        Font tmpFont = g2d.getFont();
        Color tmpColor = g2d.getColor();
        // Setting the Font and Color of the Menu Screen
        g2d.setFont(PAUSE_MENU_FONT);
        g2d.setColor(MENU_COLOR);
        //so they can position it where they want it
        //what this is doing is it's checking the text's width based on its font
        if (StrLen == 0) {
            FontRenderContext frc = g2d.getFontRenderContext();
            StrLen = PAUSE_MENU_FONT.getStringBounds(PAUSE_MENU_TEXT, frc).getBounds().width;
        }
        int x = 202;
        int y = 55;
        g2d.drawString(PAUSE_MENU_TEXT, x, y);

        x = 300 / 8;
        y = 400 / 4;
        //Setting the Location and Drawing the continueButtonRect
        if (ContinueButtonRect == null) {
            FontRenderContext frc = g2d.getFontRenderContext();
            ContinueButtonRect = PAUSE_MENU_FONT.getStringBounds(CONTINUE_TEXT, frc).getBounds();
            ContinueButtonRect.setLocation(x, y - ContinueButtonRect.height);
        }
        g2d.drawString(CONTINUE_TEXT, x, y);

        y *= 2;
        //Setting the Location and Drawing the restartButtonRect
        if (RestartButtonRect == null) {
            RestartButtonRect = (Rectangle) ContinueButtonRect.clone();
            RestartButtonRect.setLocation(x, y - RestartButtonRect.height);
        }
        g2d.drawString(RESTART_TEXT, x, y);

        y *= 3.0 / 2;
        //Setting the Location and Drawing the exitButtonRect
        if (ExitButtonRect == null) {
            ExitButtonRect = (Rectangle) ContinueButtonRect.clone();
            ExitButtonRect.setLocation(x, y - ExitButtonRect.height);
        }
        g2d.drawString(EXIT_TEXT, x, y);

        g2d.setFont(tmpFont);
        g2d.setColor(tmpColor);
    }

    public Rectangle getContinueButtonRect() {
        return ContinueButtonRect;
    }

    public Rectangle getExitButtonRect() {
        return ExitButtonRect;
    }

    public Rectangle getRestartButtonRect() {
        return RestartButtonRect;
    }
}