package model;

import controller.GameBoardController;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/***
 *GameFrameModel extends JFrame,which further implements WindowFocusListener,which takes care
 *of the model of the game initialization.
 */
public class GameFrameModel extends JFrame implements WindowFocusListener {

    private static final String GAMEFRAME_TITLE_TEXT = "Brick Destroy";

    private final GameBoardController gameBoardController;
    private final HomeMenuView homeMenuView;
    private boolean gaming;

    /**
     * Responsible for attaching all the components as a model in the HomeMenu Screen
     */
    public GameFrameModel() {
        this.initialize();
        gaming = false;
        homeMenuView = new HomeMenuView();
        this.add(homeMenuView);
        StartButtonView startBtn = new StartButtonView(this);
        homeMenuView.add(startBtn);

        InstructionButtonView instructionView = new InstructionButtonView(this);
        homeMenuView.add(instructionView);

        HighScoreButtonView highScoreButtonView = new HighScoreButtonView(this);
        homeMenuView.add(highScoreButtonView);

        ExitButtonView exitButtonView = new ExitButtonView(this);
        homeMenuView.add(exitButtonView);

        gameBoardController = new GameBoardController(this);
    }


    /**
     * 1.Sets the Frame Title to"BrickDestroy"<br>
     * 2.Sets the CloseOperation to be the default WindowsCloseOperation<br>
     * 3.Sets the Window to be sized to fit the preferred size and layouts of its subcomponents.
     * 4.Shows the Window when focussed
     */
    public void initialize() {
        this.setTitle(GAMEFRAME_TITLE_TEXT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 480);
        this.setResizable(false);
        this.setLayout(null);
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     * 1.Releases all of the native screen resources used by this Window<br>
     * 2.Removes the component of homeMenu<br>
     * 3.Adds the gameBoard as a BorderLayout with CenterAlignment<br>
     * 4.Calls the initialize method
     * 5.To avoid GraphicIssues,a WindowFocusListener is added.
     */
    public void enableGameBoard() {
        this.dispose();
        this.remove(homeMenuView);
        this.setSize(603, 480);
        this.setUndecorated(false);
        this.setTitle(GAMEFRAME_TITLE_TEXT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.autoLocate();
        this.setLayout(new BorderLayout());
        this.add(gameBoardController, BorderLayout.CENTER);
        this.setVisible(true);
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * Sets the Location of the GameFrame
     */
    private void autoLocate() {
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x, y);
    }


    /***
     *The first time the frame loses focus is because it has been disposed to install the GameBoard,
     *so when it regains the focus it's ready to play.
     *@param windowEvent An object that indicates when the status of Windows has changed
     */
    @Override
    public void windowGainedFocus(WindowEvent windowEvent) {
        /*
            the first time the frame loses focus is because
            it has been disposed to install the GameBoard,
            so went it regains the focus it's ready to play.
            of course calling a method such as 'onLostFocus'
            is useful only if the GameBoard as been displayed
            at least once
         */
        gaming = true;
    }

    /**
     * When LostFocus,then call the on LostFocus from GameBoard
     *
     * @param windowEvent - Event Instance
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if (gaming)
            gameBoardController.onLostFocus();

    }

    /**
     * Returns the flag of if the user is still on screen or not.
     * @return gaming
     */
    public boolean isGaming() {
        return gaming;
    }
}