package controller;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * HighScoreController is responsible the Score Functionality. For Getting the HighScores , Checking the HighScore and Sorting the
 * High Scores in the game.
 */
public class HighScoreController {

    /**
     * Use the Singleton pattern when a class has a single instance available to all.
     */
    private static HighScoreController instance;
    private int score;
    private String highScore = "Name:0";

    /**
     * Classes can access object instance
     *
     * @return instance - Object instance
     */
    public static HighScoreController getHighScoreInstance() {
        if (instance == null) instance = new HighScoreController();
        return instance;
    }

    /**
     * Reads the HighScores from the highScore.dat file
     *
     * @return HighScore - The HighScores from the file
     */
    public String acquireHighScore() {
        FileReader readScoreFile;
        BufferedReader reader = null;
        try {
            readScoreFile = new FileReader("highScore.dat");
            reader = new BufferedReader(readScoreFile);
            String line;
            while ((line = reader.readLine()) != null) {
                highScore = line;
            }
            return highScore; //return the last line
        } catch (Exception e) {
            return highScore = "Name:0";
        } finally {
            try {
                if (reader != null)
                    reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Checks the score to see if the recently updated highScore is lower than the new highScore , and updates the highScore.dat file
     */
    public void checkScore() {
        getScore();
        if (score > Integer.parseInt((highScore.split(":")[1]))) {

            String name = JOptionPane.showInputDialog("You Set A New HighScore! What's your name?");
            highScore = name + ":" + score;

            File scoreFile = new File("highScore.dat");
            if (!scoreFile.exists()) {
                try {
                    scoreFile.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            FileWriter writeScoreFile;
            BufferedWriter writer = null;
            try {
                writeScoreFile = new FileWriter(scoreFile, true);
                writer = new BufferedWriter(writeScoreFile);
                writer.write(this.highScore);
                writer.newLine();
            } catch (Exception e) {
                System.out.println("Error");
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (Exception e) {
                    System.out.println("ERROR");
                }
            }
        }
    }

    /**
     * Sorts the High Scores in the highScore.dat to present it in the High Score Button View
     */
    public void sortHighScore() {
        ArrayList<String> str = new ArrayList<>();
        String line;
        //Try to read the high score file
        try{
            BufferedReader reader = new BufferedReader(new FileReader("highScore.dat"));
            while((line=reader.readLine())!=null){
                str.add(line);
            }
            reader.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //Sort the high score file and store it in leaderboard.dat
        Collections.sort(str);
        str.sort((o1, o2) -> Integer.compare( //Sort the score not the name
                Integer.parseInt(o2.substring(o2.indexOf(":") + 1)),
                Integer.parseInt(o1.substring(o1.indexOf(":") + 1))));
        try{
            FileWriter writer = new FileWriter("leaderboard.dat"); //Creating or Overwriting the file
            for(String s: str){
                writer.write(s);
                writer.write("\r\n");
            }

            writer.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Returns the HighScore
     * @return highScore - Returns the High Score
     */
    public String getHighScore() {
        return highScore;
    }

    /**
     * Sets the High Score
     * @param highScore - The HighScore
     */
    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

    /**
     * Returns the score
     * @return score - The score of the user during the game
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score
     * @param score - The Score during the game
     */
    public void setScore(int score) {
        this.score = score;
    }

}

