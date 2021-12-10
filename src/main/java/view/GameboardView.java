package view;

import controller.*;
import model.PlayerModel;
import model.RubberBallModel;
import model.HighScoreModel;

import java.awt.*;

import static controller.HighScoreController.getHighScoreInstance;

public class GameboardView {
    private final WallController wallController;
    private final GameBoardController gameBoardController;
    private final HighScoreModel highScoreModel;
    private final PlayerController player;
    private final BallController ball;
    private final PauseMenuView pauseMenuView;


    public GameboardView(WallController wallController, PlayerController player, BallController ball, GameBoardController gameBoardController, HighScoreModel highScoreModel, PauseMenuView pauseMenuView) {
        this.gameBoardController = gameBoardController;
        this.wallController = wallController;
        this.ball = ball;
        this.player = player;
        this.highScoreModel = highScoreModel;
        this.pauseMenuView = pauseMenuView;
    }

    /**
     * Paint method implements Graphics , inherited from JComponent
     * ,it is part of the draw system of the GUI.
     * It's invoked from Java Swing Framework to ask for a Component to draw itself on the screen
     * @param g
     */
    public void render(Graphics g){
        // Create Object g2d
        Graphics2D g2d = (Graphics2D) g;
        //Setting the Background Color
        BackgroundColor(g2d);
        //Setting the color of the Message
        g2d.setColor(Color.BLUE);
        g2d.drawString(gameBoardController.getMessage(), 200, 225);
        g2d.drawString(gameBoardController.getMessage2(),200,260);
        highScoreModel.drawscore(g2d , wallController);
        playerRender(g2d);
        ballRender(g2d);
        wallController.brickRender(g2d);
        //If the showPauseMenu is true , draw the drawMenu
        if(gameBoardController.isShowPauseMenu())
            pauseMenuView.render(g2d);
        //Binding various component implementations and synchronizes them.
        Toolkit.getDefaultToolkit().sync();
        if (getHighScoreInstance().getHighScore().equals("")) {
            getHighScoreInstance().setHighScore(getHighScoreInstance().acquireHighScore());
        }
    }
    /**
     * BackgroundColor method:
     * Uses the Graphics2D Object to paint the Background Color of the GameBoard
     * @param g2d
     */
    private void BackgroundColor(Graphics2D g2d){
        Color tmp = g2d.getColor();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0,0, gameBoardController.getWidth(), gameBoardController.getHeight());
        g2d.setColor(tmp);
    }

        public void playerRender (Graphics2D g){
            Graphics2D g2d = (Graphics2D) g.create();

            // Set the interior colour
            g2d.setColor(PlayerModel.getInnerColor());
            g2d.fill(player.getPlayerShape());

            // Set the border colour
            g2d.setColor(PlayerModel.getBorderColor());
            g2d.draw(player.getPlayerShape());
        }

        /**
         * Renders the Ball Shape and Color, called in the wallController Class
         * @param g
         */
        public void ballRender (Graphics2D g){
            Graphics2D g2d = (Graphics2D) g.create();

            // Set the interior colour
            g2d.setColor(RubberBallModel.getBallInnerColor());
            g2d.fill(ball.getBallFace());

            // Set the border colour
            g2d.setColor(RubberBallModel.getBallBorderColor());
            g2d.draw(ball.getBallFace());
        }
    }

