package view;

import controller.GameBoardController;
import model.GameFrameModel;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;

/***
 *HomeMenuView Class extends JComponent and implements MouseListener and MouseMotionListener
 * to generate the HomeMenu of the Game
 */
public class HomeMenuView extends JComponent implements MouseListener, MouseMotionListener {
    //Static Variables for the strings
    private static final String GREETINGS_TEXT = "Welcome to:";
    private static final String GAME_TITLE_TEXT = "Brick Destroy";
    private static final String CREDITS_TEXT = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String INSTRUCTION_TEXT = "Instruction";
    private static final String EXIT_TEXT = "Exit";
    //Static Variables to Specify the Colors in the Home Menu
    private static final Color HOME_MENU_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASHED_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = HOME_MENU_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;

    private static final int BORDER_SIZE = 10;
    private static final float[] DASHES = {20,20};//the red dashes on the border

    //The Menu Board
    private final Rectangle MenuFace;
    //The Start Button
    private final Rectangle StartButton;
    //The Exit Button
    private final Rectangle ExitButton;
    //The Instruction Button
    private final Rectangle InstructionButton;

    //Statics Stroke Attribute
    private final BasicStroke BorderStroke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
    private final BasicStroke BorderStroke_NoDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

    //Font of the Text in the Home Menu
    private final Font Greetings_Font = new Font("Noto Mono",Font.PLAIN,25);
    private final Font GameTitle_Font = new Font("Noto Mono",Font.BOLD,40);
    private final Font Credits_Font = new Font("Monospaced",Font.PLAIN,10);
    private final Font Button_Font;

    //The JFrame Owner
    private GameFrameModel owner;
    private GameBoardController gameBoardController;

    //Boolean to mark when the start is clicked
    private boolean Start_Button_Clicked;
    //Boolean to mark when the exit is clicked
    private boolean Exit_Button_Clicked;
    //Boolean to mark when the Instruction is clicked
    private boolean Instruction_Button_Clicked;
    public String name;


    /**
     * HomeMenuView CConstructor:
     * Implements the HomeMenuView Characteristics
     * @param owner
     * @param area
     */
    public HomeMenuView(GameFrameModel owner, Dimension area , GameBoardController gameBoardController){
        this.setFocusable(true);
        this.requestFocusInWindow();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.owner = owner;
        this.gameBoardController = gameBoardController;
        //Define the Menu Area
        MenuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);
        //Define the Dimension of the Button
        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        //Associate Dimension with the Start Button
        StartButton = new Rectangle(btnDim);
        //Associate Dimension with the Exit Button
        ExitButton = new Rectangle(btnDim);
        //Associate Dimension with Instruction Button
        InstructionButton = new Rectangle(btnDim);
        //Font of the Button
        Button_Font = new Font("Monospaced",Font.PLAIN,StartButton.height-4);
    }


    /**
     * paint Method:
     * To paint the output the Home Menu
     * @param g
     */
    public void paint(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        DrawMenu(g2d);
    }

    /**
     * drawMenu Method:
     * To draw the Home Menu Contents
     * @param g2d
     */
    public void DrawMenu(Graphics2D g2d){
        //Calling the drawContainer method to create the container
        DrawContainer(g2d);
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();
        //x and y coordinates of the contents of the menu
        double x = MenuFace.getX();
        double y = MenuFace.getY();
        g2d.translate(x,y);

        //Method Call to Draw the Text
        DrawText(g2d);
        //Method Call to Draw the Button
        DrawButton(g2d);
        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);
    }

    /**
     * drawContainer Method:
     * To implement the container characteristics
     * @param g2d
     */
    private void DrawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();
        // Set the Menu to the Background Color
        g2d.setColor(HOME_MENU_COLOR);
        //Fill the Menu Area with the Color
        g2d.fill(MenuFace);
        //Fill the MenuFace with the Border Dashes
        Stroke tmp = g2d.getStroke();
        g2d.setStroke(BorderStroke_NoDashes);
        g2d.setColor(DASHED_BORDER_COLOR);
        g2d.draw(MenuFace);
        //Fill the MenuFace with the Border Color
        g2d.setStroke(BorderStroke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(MenuFace);

        g2d.setStroke(tmp);
        g2d.setColor(prev);
    }

    /**
     * drawText Method:
     *Implements the Greetings , GameTitle , and various text at the home menu
     * @param g2d
     */
    private void DrawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = Greetings_Font.getStringBounds(GREETINGS_TEXT,frc);
        Rectangle2D gameTitleRect = GameTitle_Font.getStringBounds(GAME_TITLE_TEXT,frc);
        Rectangle2D creditsRect = Credits_Font.getStringBounds(CREDITS_TEXT,frc);

        int sX,sY;

        sX = (int)(MenuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(MenuFace.getHeight() / 4);
        g2d.setFont(Greetings_Font);
        g2d.drawString(GREETINGS_TEXT,sX,sY);

        sX = (int)(MenuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings
        g2d.setFont(GameTitle_Font);
        g2d.drawString(GAME_TITLE_TEXT,sX,sY);

        sX = (int)(MenuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;
        g2d.setFont(Credits_Font);
        g2d.drawString(CREDITS_TEXT,sX,sY);
    }

    /**
     * drawButton Method:
     * Implements the Button for the startButton and Exit Button
     * @param g2d
     */
    private void DrawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = Button_Font.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = Button_Font.getStringBounds(EXIT_TEXT,frc);
        Rectangle2D iTxtRect = Button_Font.getStringBounds(INSTRUCTION_TEXT,frc);

        g2d.setFont(Button_Font);

        int x = (MenuFace.width - StartButton.width) / 2;
        int y =(int) ((MenuFace.height - StartButton.height) * 0.8);

        StartButton.setLocation(x,y);

        x = (int)(StartButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(StartButton.getHeight() - txtRect.getHeight()) / 2;

        x += StartButton.x;
        y += StartButton.y + (StartButton.height * 0.9);

        if(Start_Button_Clicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(StartButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(StartButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = StartButton.x;
        y = StartButton.y;

        x *= 1.0;
        y *= 1.2;

        ExitButton.setLocation(x,y);

        x = (int)(ExitButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(ExitButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += ExitButton.x;
        y += ExitButton.y + (StartButton.height * 0.9);

        if(Exit_Button_Clicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(ExitButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(EXIT_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(ExitButton);
            g2d.drawString(EXIT_TEXT,x,y);
        }

        x = StartButton.x;
        y = StartButton.y;

        x*=1.01;
        y*=0.8;
        InstructionButton.setLocation(x,y);

        x = (int)(InstructionButton.getWidth() - iTxtRect.getWidth()) / 2;
        y = (int)(InstructionButton.getHeight() - iTxtRect.getHeight()) / 2;

        x += InstructionButton.x;
        y += InstructionButton.y + (StartButton.height * 0.9);

        if(Instruction_Button_Clicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(InstructionButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(INSTRUCTION_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(InstructionButton);
            g2d.drawString(INSTRUCTION_TEXT,x,y);
        }
    }
    /**
     * mouseClicked Method:
     * To mention the function corresponding to the click of the mouse in the homemenu
     * @param mouseEvent
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(StartButton.contains(p)){
            owner.enableGameBoard();
        }
        else if(ExitButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(InstructionButton.contains(p)){
            InstructionView instructionView = new InstructionView();

        }
    }


    /**mousePressed Method:
     * Implements the Functionality when the buttons are pressed but not released
     * @param mouseEvent
     */
    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(StartButton.contains(p)){
            Start_Button_Clicked = true;
            repaint(StartButton.x,StartButton.y,StartButton.width+1,StartButton.height+1);

        }
        else if(ExitButton.contains(p)){
            Exit_Button_Clicked = true;
            repaint(ExitButton.x,ExitButton.y,ExitButton.width+1,ExitButton.height+1);
        }
        else if(InstructionButton.contains(p)){
            Instruction_Button_Clicked = true;
            repaint(InstructionButton.x,InstructionButton.y,InstructionButton.width+1,InstructionButton.height+1);
        }
    }

    /**
     * mouseReleased Method:
     * Implements the functionality when the mouse is released from the button
     * @param mouseEvent
     */
    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(Start_Button_Clicked){
            Start_Button_Clicked = false;
            repaint(StartButton.x,StartButton.y,StartButton.width+1,StartButton.height+1);
        }
        else if(Exit_Button_Clicked){
            Exit_Button_Clicked = false;
            repaint(ExitButton.x,ExitButton.y,ExitButton.width+1,ExitButton.height+1);
        }
        else if(Instruction_Button_Clicked){
            Instruction_Button_Clicked = false;
            repaint(InstructionButton.x,InstructionButton.y,InstructionButton.width+1,InstructionButton.height+1);
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    /**
     * mouseMoved Method:
     * Implements the functionality when the cursor hovers on the button
     * @param mouseEvent
     */
    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if (StartButton.contains(p) || ExitButton.contains(p) || InstructionButton.contains(p))
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}