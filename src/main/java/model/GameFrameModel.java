package model;

import view.GameBoardView;
import view.HomeMenuView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;


/***
 *GameFrameModel extends JFrame,which further implements WindowFocusListener,which takes care
 *of the implementation of the game
 */
public class GameFrameModel extends JFrame implements WindowFocusListener {

    private static final String DEF_TITLE = "Brick Destroy";

    private GameBoardView gameBoard;
    private HomeMenuView homeMenu;

    private boolean gaming;

    /**
     *GameFrameModel constructor:<br>
     *1.sets the default BorderLayout using the setLayoutMethod<br>
     *2.Adds the homeMenu as a BorderLayout with Center Alignment<br>
     *3.Sets undecorated as true
     */
    public GameFrameModel(){
        super();

        gaming = false;

        this.setLayout(new BorderLayout());

        gameBoard = new GameBoardView(this);

        homeMenu = new HomeMenuView(this,new Dimension(450,300));

        this.add(homeMenu,BorderLayout.CENTER);

        this.setUndecorated(true);


    }


    /**
     *initialize method on the owner:<br>
     *1.Sets the Frame Title to"BrickDestroy"<br>
     *2.Sets the CloseOperation to be the default WindowsCloseOperation<br>
     *3.Sets the Window to be sized to fit the preferred size and layouts of its subcomponents.
     *4.Shows the Window when focussed
     */
    public void initialize(){
        this.setTitle(DEF_TITLE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
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
    public void enableGameBoard(){
        this.dispose();
        this.remove(homeMenu);
        this.add(gameBoard,BorderLayout.CENTER);
        this.setUndecorated(false);
        initialize();
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
        gaming = true;
    }
    /**
     *When LostFocus,then call the on LostFocus from GameBoard
     *@param windowEvent
     */
    @Override
    public void windowLostFocus(WindowEvent windowEvent) {
        if(gaming)
            gameBoard.onLostFocus();

    }
}