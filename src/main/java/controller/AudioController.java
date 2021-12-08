package controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


/**
 * The Audio Controller Class is responsible for setting audio at various points in the game.
 */
public class AudioController {
    Clip clip;

    /**
     * AudioController Constructor is responsible for implementing the functionality to play
     * the audio.
     * @param filename
     */
    public AudioController(String filename){

        AudioInputStream input= null;
        try {
            //Input the Audio based on the filename
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

        //Start playing the audio
        clip.start();
    }
}
