package controller;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static controller.TimerController.getTimeInstance;
public class HighScoreController {
    private int score;
    private String highScore = "Name:0";
    /**
     * private Object instance (apply Singleton pattern)
     */
    private static HighScoreController instance;

    /**
     * other class can access to Object instance
     * @return instance of Object
     */
    public static HighScoreController getInstance(){
        if(instance == null){
            instance = new HighScoreController();

        }
        return instance;
    }

    public HighScoreController(){
    }

    public String GetHighScore()  {
        FileReader readFile ;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highscore.csv");
            reader = new BufferedReader(readFile);
            return reader.readLine();
        }
        catch (Exception e)
        {
            return "Nobody:0";
        }
        finally
        {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void CheckScore() {
        getScore();
        if ( score> Integer.parseInt((highScore.split(":")[1]))) {

            String name = JOptionPane.showInputDialog("You set a new highScore. What your name?");
            highScore = name + ":" + score + "Time" + getTimeInstance().getSeconds();

            File scoreFile = new File("highscore.csv");
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            BufferedWriter writer = null;
            FileWriter writeFile = null;
            try {
                BufferedReader infile = new BufferedReader(new FileReader(scoreFile));
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);

                List<String> scores = new ArrayList<>();
                while ((infile.readLine()) != null) {
                    scores.add(this.highScore);
                    writer.write(String.valueOf(scores));
                }
                infile.close();
            }
            catch (Exception e) {

            }
            finally {
                try {
                    if (writer != null)
                        writer.close();
                }
                catch (Exception e) {}

            }
        }
        else{

        }
    }
    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

}
