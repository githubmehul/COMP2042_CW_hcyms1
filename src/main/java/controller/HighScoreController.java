package controller;

import javax.swing.*;
import java.io.*;

public class HighScoreController {
    private WallController wallController;
    private String highScore = "Name:0";

    public HighScoreController(WallController wallController){
        this.wallController = wallController;
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
        if (wallController.getTotalBrickBroken() > Integer.parseInt((highScore.split(":")[1]))) {

            String name = JOptionPane.showInputDialog("You set a new highScore. What your name?");
            highScore = name + ":" + wallController.getTotalBrickBroken();

            File scoreFile = new File("highscore.csv");
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter writeFile = null;
            BufferedWriter writer = null;
            try {
                writeFile = new FileWriter(scoreFile);
                writer = new BufferedWriter(writeFile);
                writer.write(this.highScore);
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
    }
    public String getHighScore() {
        return highScore;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

}
