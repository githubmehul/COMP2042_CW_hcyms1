package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class AudioController {
    Clip clip;
    public String filename;
    public  AudioController(String filename){
        AudioInputStream input= null;
        try {
            input = AudioSystem.getAudioInputStream(new File("src/main/resources/" + filename));
        } catch (UnsupportedAudioFileException unsupportedAudioFileException) {
            unsupportedAudioFileException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        try {
            clip=AudioSystem.getClip();
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        }
        try {
            clip.open(input);
        } catch (LineUnavailableException lineUnavailableException) {
            lineUnavailableException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        clip.start();

    }
}
