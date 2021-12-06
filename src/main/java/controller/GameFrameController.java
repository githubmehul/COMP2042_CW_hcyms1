package controller;

import view.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.io.IOException;


/***
 *GameFrameModel extends JFrame,which further implements WindowFocusListener,which takes care
 *of the implementation of the game
 */
public class GameFrameController extends JFrame implements WindowFocusListener {

    private static final String GAMEFRAME_TITLE_TEXT= "Brick Destroy";

    private GameBoardController GameBoard;
    private HomeMenuView HomeMenu;
    private StartButtonView startBtn;
    private ExitButtonView exitBtn;
    private InstructionButtonView instructionView;
    private HighScoreButtonView highScoreButtonView;
    private HighScoreGameView highScoreGameView;
    private AudioController audioController;
    private boolean Gaming;


    /**
     *GameFrameModel constructor:<br>
     *1.sets the default BorderLayout using the setLayoutMethod<br>
     *2.Adds the homeMenu as a BorderLayout with Center Alignment<br>
     *3.Sets undecorated as true
     */
    public GameFrameController(){
        this.initialize();
         Gaming = false;
        HomeMenu = new HomeMenuView();
        this.add(HomeMenu);
        startBtn = new StartButtonView(this);
        HomeMenu.add(startBtn);

        instructionView = new InstructionButtonView(this);
        HomeMenu.add(instructionView);

        highScoreButtonView = new HighScoreButtonView(this);
        HomeMenu.add(highScoreButtonView);

        exitBtn = new ExitButtonView(this);
        HomeMenu.add(exitBtn);

        GameBoard = new GameBoardController(this);
    }


    /**
     *initialize method on the owner:<br>
     *1.Sets the Frame Title to"BrickDestroy"<br>
     *2.Sets the CloseOperation to be the default WindowsCloseOperation<br>
     *3.Sets the Window to be sized to fit the preferred size and layouts of its subcomponents.
     *4.Shows the Window when focussed
     */
    public void initialize(){
        this.setTitle(GAMEFRAME_TITLE_TEXT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 480);
        this.setResizable(false);
        this.setLayout(null);
        this.autoLocate();
        this.setVisible(true);
    }

    /**
     *enableGameBoard method:<br>
     *1.Releases all of the native screen resources used by this Window<br>
     *2.Removes the component of homeMenu<br>
     *3.Adds the gameBoard as a BorderLayout with CenterAlignment<br>
     *4.Calls the initialize method
     *5.To avoid GraphicIssues,a WindowFocusListener is added.
     */
    public void enableGameBoard() {
        this.dispose();
        this.remove(HomeMenu);
        this.setSize(603, 480);
        this.setUndecorated(false);
        this.setTitle(GAMEFRAME_TITLE_TEXT);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.autoLocate();
        this.setLayout(new BorderLayout());
        this.add(GameBoard,BorderLayout.CENTER);
        this.setVisible(true);
        /*to avoid problems with graphics focus controller is added here*/
        this.addWindowFocusListener(this);
    }

    /**
     * autoLocate Method:
     * Sets the Location of the GameFrame
     */
    private void autoLocate(){
        Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (size.width - this.getWidth()) / 2;
        int y = (size.height - this.getHeight()) / 2;
        this.setLocation(x,y);
    }


    /***
     *The first time the frame loses focus is because it has been disposed to install the GameBoard,
     *so when it regains the focus it's ready to play.
     *@paramw indowEvent An object that indicates when the status of Windows has changed
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
        Gaming = true;
    }
    /**
     *When LostFocus,then call the on LostFocus from GameBoard
     *@param windowEvent
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(Gaming)
            GameBoard.onLostFocus();

    }
}