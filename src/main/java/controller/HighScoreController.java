package controller;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static controller.TimerController.getTimeInstance;
public class HighScoreController {
    private int score;
    private String last=null, line;
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
        FileReader readFile = null;
        BufferedReader reader = null;
        try {
            readFile = new FileReader("highscore.dat");
            reader = new BufferedReader(readFile);
            while ((line = reader.readLine()) != null) {
                if (line != null) {
                    last = line;
                    highScore=last;
                }
            }
            return  highScore; //return the last line
        }
        catch (Exception e)
        {
            return  highScore="Nobody:0";
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
        GetHighScore();
        if (score > Integer.parseInt((highScore.split(":")[1]))) {

            String name = JOptionPane.showInputDialog("You set a new highScore. What 's your name?");
            highScore = name + ":" + score;

            File scoreFile = new File("highscore.dat");
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
                writeFile = new FileWriter(scoreFile,true);
                writer = new BufferedWriter(writeFile);
                writer.write(this.highScore);
                writer.newLine();
            }
            catch (Exception e) {
                System.out.println("Some Error happened");
            }
            finally {
                try {
                    if (writer != null)
                        writer.close();
                }
                catch (Exception e) { System.out.println("Some Error happend");}
            }
        }
    }
    public void sortHighScore() {
        ArrayList<String> str = new ArrayList<>();
        String line = "";
        //Try to read the high score file
        try{
            BufferedReader reader = new BufferedReader(new FileReader("highscore.dat"));
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
    public String getHighScore() {
        return highScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHighScore(String highScore) {
        this.highScore = highScore;
    }

}

